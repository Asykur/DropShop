package asykurkhamid.dropshop.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.DataPaymentConfirmation;
import asykurkhamid.dropshop.R;

public class DetailPaymentConfActivity extends ParentClass {

    TextView idtrx,norekSender,senderName,bankSenderName,nominal,date,noRekDest,bankDestName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_payment_conf);

        idtrx = findViewById(R.id.tvdetIdPayment);
        norekSender = findViewById(R.id.tvdetNoRekSender);
        senderName = findViewById(R.id.tvdetSenderName);
        bankSenderName = findViewById(R.id.tvdetSenderBank);
        nominal = findViewById(R.id.tvdetNominal);
        date = findViewById(R.id.tvdetDate);
        noRekDest = findViewById(R.id.tvdetNoRekDest);
        bankDestName = findViewById(R.id.tvdetBankDest);

        setDefaultToolbar(true,"Data Konfirmasi");
        DataPaymentConfirmation data = getIntent().getParcelableExtra("payment");

        idtrx.setText(data.getID());
        norekSender.setText(data.getNorekSender());
        senderName.setText(data.getSenderName());
        bankSenderName.setText(data.getBankSenderName());
        nominal.setText(data.getNominal());
        date.setText(data.getDate());
        noRekDest.setText(data.getNoRekDest());
        bankDestName.setText(data.getBankDestName());
    }
}
