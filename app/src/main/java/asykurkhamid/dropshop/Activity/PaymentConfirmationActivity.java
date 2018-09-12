package asykurkhamid.dropshop.Activity;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.DataCart;
import asykurkhamid.dropshop.Pojo.DataPaymentConfirmation;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.Utility.TimerReceiver;

public class PaymentConfirmationActivity extends ParentClass implements com.tsongkha.spinnerdatepicker.DatePickerDialog.OnDateSetListener {

    private EditText etNorekHolder, etHolderName, etBankHolder, etAmount, etDateTrf, etNorekDest, etBankDest;
    private ImageButton btnImage;
    private Button btnConfirmation;
    private ImageView imgPhoto;
    private static final int CAMERA_IMAGE = 123;
    private DatabaseReference id_Ref;
    private DatabaseReference trx_Ref,cartRef;
    private String address;
    private String kurir;
    private String pengiriman;
    private String notes;

    private String norekSender;
    private String senderName;
    private String bankSenderName;
    private String nominal;
    private String datePick;
    private String noRekDest;
    private String bankDestName;
    private Uri imageUri = null;
    private ProgressDialog pdUlpoad;
    private Bitmap photo;
    private DataPaymentConfirmation data;
    private String trxID;
    private SimpleDateFormat dateFormat;
    private  String UID;
    DBHelper dbHelper;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_confirmation);

        dbHelper = new DBHelper(PaymentConfirmationActivity.this);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        id_Ref = FirebaseDatabase.getInstance().getReference().child("Payment_Confirmation");
        cartRef = FirebaseDatabase.getInstance().getReference().child("DataCart").child(UID);
        trxID = id_Ref.push().getKey();
        trx_Ref = id_Ref.child(trxID);

        dateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.US);
        etNorekHolder = findViewById(R.id.etNoRekHolder);
        etHolderName = findViewById(R.id.etNamaRekHolder);
        etBankHolder = findViewById(R.id.etNamaBankHolder);
        etAmount = findViewById(R.id.etNominal);
        etDateTrf = findViewById(R.id.etTanggalTrf);

        etDateTrf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDate(2018, 6, 7, R.style.DatePickerSpinner);
            }
        });
        etNorekDest = findViewById(R.id.etNoRekDest);
        etBankDest = findViewById(R.id.etNamaBankDest);
        imgPhoto = findViewById(R.id.imgPhotoBukti);
        pdUlpoad = new ProgressDialog(PaymentConfirmationActivity.this);
        pdUlpoad.setMessage("Uploading ...");
        pdUlpoad.setCancelable(false);
        btnConfirmation = findViewById(R.id.btnKonfirmasi);
        setDefaultToolbar(true, "Konfirmasi Pembayaran");
        getAddressKurir();
        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasi();
            }
        });
    }

    private void getAddressKurir() {
        cartRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        DataCart dataCart = snapshot.getValue(DataCart.class);
                        address = dataCart.getAddress();
                        kurir = dataCart.getKurir();
                        pengiriman = dataCart.getPaketPengiriman();
                        notes = dataCart.getNotes();
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            };
        });
    }

    private void showDate(int year, int month, int date, int datePickerSpinner) {
        new SpinnerDatePickerDialogBuilder()
                .context(PaymentConfirmationActivity.this)
                .callback(PaymentConfirmationActivity.this)
                .spinnerTheme(datePickerSpinner)
                .defaultDate(year, month, date)
                .build()
                .show();
    }

    @Override
    public void onDateSet(com.tsongkha.spinnerdatepicker.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        GregorianCalendar calendar = new GregorianCalendar(year, monthOfYear, dayOfMonth);
        etDateTrf.setText(dateFormat.format(calendar.getTime()));
    }

    private void validasi() {
        pdUlpoad.show();

        norekSender = etNorekHolder.getText().toString();
        senderName = etHolderName.getText().toString();
        bankSenderName = etBankHolder.getText().toString();
        nominal = etAmount.getText().toString();
        datePick = etDateTrf.getText().toString();
        noRekDest = etNorekDest.getText().toString();
        bankDestName = etBankDest.getText().toString();
        if (TextUtils.isEmpty(norekSender)) {
            etNorekHolder.setError("Tidak Boleh Kosong");
            etNorekHolder.requestFocus();
        } else if (TextUtils.isEmpty(senderName)) {
            etHolderName.setError("Tidak Boleh Kosong");
            etHolderName.requestFocus();
        } else if (TextUtils.isEmpty(bankSenderName)) {
            etBankHolder.setError("Tidak Boleh Kosong");
            etBankHolder.requestFocus();
        } else if (TextUtils.isEmpty(nominal)) {
            etAmount.setError("Tidak Boleh Kosong");
            etAmount.requestFocus();
        } else if (TextUtils.isEmpty(datePick)) {
            etDateTrf.setError("Tidak Boleh Kosong");
            etDateTrf.requestFocus();
        } else if (TextUtils.isEmpty(noRekDest)) {
            etNorekDest.setError("Tidak Boleh Kosong");
            etNorekDest.requestFocus();
        } else if (TextUtils.isEmpty(bankDestName)) {
            etBankDest.setError("Tidak Boleh Kosong");
            etBankDest.requestFocus();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    uploadPaymentConf();
                }
            }, 2000);
        }
    }

    private void uploadPaymentConf() {
        data = new DataPaymentConfirmation();
        data.setID(trxID);
        data.setNorekSender(norekSender);
        data.setSenderName(senderName);
        data.setBankSenderName(bankSenderName);
        data.setNominal(nominal);
        data.setDate(datePick);
        data.setNoRekDest(noRekDest);
        data.setBankDestName(bankDestName);
        data.setAddress(address);
        data.setKurir(kurir);
        data.setPengiriman(pengiriman);
        data.setNotes(notes);

        trx_Ref.setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pdUlpoad.dismiss();
                Toast.makeText(PaymentConfirmationActivity.this, "Data berhasil diupload...", Toast.LENGTH_SHORT).show();
                //Stop AlarmManager
                Intent stop = new Intent(PaymentConfirmationActivity.this, TimerReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1253, stop, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pendingIntent);
                Intent intentz = new Intent(PaymentConfirmationActivity.this, MainActivity.class);
                startActivity(intentz);
                finishAffinity();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction");
                        cartRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()){
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        ArrayList<String> pid = new ArrayList<>();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        removeCartData();
                    }
                },500);

            }
        });
    }

    private void removeCartData() {
        DatabaseReference afterCartRef = FirebaseDatabase.getInstance().getReference().child("DataCart").child(UID);
        DatabaseReference cartRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction").child(UID);
        cartRef.removeValue();
        afterCartRef.removeValue();
        //should remove data on SQLITE
    }

}
