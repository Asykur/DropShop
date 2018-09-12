package asykurkhamid.dropshop.Activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.DataCart;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.Utility.AppPreference;
import asykurkhamid.dropshop.Utility.TimerReceiver;

public class PaymentActivity extends ParentClass {

    private TextView tvTime, jmlTagihan;
    private Button btnKonfirmasi;
    private DatabaseReference subToralRef;
    private DataCart dataCart;
    private NumberFormat rupiah;
    public static final String TAG_BAYAR = "databayar";
    private String UID;
    private DBHelper dbHelper;
    private String totalBayar ;
    static final long TIMETOPAY =  60*60*1000; // convert to millisecond

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        dataCart = getIntent().getParcelableExtra(TAG_BAYAR);

        dbHelper = new DBHelper(this);
        UID = CartActivity.UID;
        Locale locale = new Locale("in", "ID");
        rupiah = NumberFormat.getCurrencyInstance(locale);

        setDefaultToolbar(true, "Pembayaran");
        tvTime = findViewById(R.id.tvTimer);
        btnKonfirmasi = findViewById(R.id.btnKonfirmPayment);
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(PaymentActivity.this, PaymentConfirmationActivity.class);
                startActivity(in);
            }
        });

        totalBayar = AppPreference.getPreference(PaymentActivity.this,AppPreference.TAG_TOTALBAYAR);
        int intBayar = Integer.parseInt(totalBayar);
        jmlTagihan = findViewById(R.id.tvJmlTagihan);
        jmlTagihan.setText(rupiah.format((double) intBayar));

        SharedPreferences Preference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = Preference.getBoolean("FIRSTRUN", true);
        if (isFirstRun) {
            showTimerPayment();
            SharedPreferences.Editor editor = Preference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.apply();
        }
        showTimerPayment();
    }

    public void showTimerPayment() {
        AlarmManager alarmManager;
        PendingIntent alarmIntent;

        Intent intent = new Intent(PaymentActivity.this, TimerReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(PaymentActivity.this, 777, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) PaymentActivity.this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + TIMETOPAY,
                alarmIntent
        );
        Date curDate = new Date();
        long ss = curDate.getTime() + TIMETOPAY;
        SimpleDateFormat year = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        String timer = year.format(ss);
        tvTime.setText(timer);
    }

}
