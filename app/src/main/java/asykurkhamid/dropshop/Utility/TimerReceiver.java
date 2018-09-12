package asykurkhamid.dropshop.Utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import asykurkhamid.dropshop.Pojo.DataAttribut;
import asykurkhamid.dropshop.Pojo.DataCart;
import asykurkhamid.dropshop.Pojo.DataTransaction;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.SQLite.DbExecuteLogic;

public class TimerReceiver extends BroadcastReceiver {

    private DatabaseReference trxRef;
    private DatabaseReference chartData;
    private DatabaseReference dataTrxRef;
    private MediaPlayer mediaPlayer;
    private String pid,UID;
    private int qtyOrder;
    private String path1,path2,path3,trxID;
    private DataCart dataCart;
    private DBHelper dbHelper;

    @Override
    public void onReceive(final Context context, Intent intent) {
        dbHelper = new DBHelper(context);
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        trxRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction").child(UID);

        path1 = AppPreference.getPreference(context, AppPreference.TAG_PATH1);
        path2 = AppPreference.getPreference(context, AppPreference.TAG_PATH2);
        path3 = AppPreference.getPreference(context, AppPreference.TAG_PATH3);

        mediaPlayer = MediaPlayer.create(context, R.raw.alarm);
        mediaPlayer.start();
        Toast.makeText(context, "Transaksi dibatalkan", Toast.LENGTH_SHORT).show();

        chartData = FirebaseDatabase.getInstance().getReference().child("DataCart").child(UID);
        chartData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    trxID = dataSnapshot.getKey();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        dataCart = snapshot.getValue(DataCart.class);
                        pid = dataCart.getProductID();
                        qtyOrder = dataCart.getQtyOrder();
                    }
                    getDataTrxOnCart();
                    trxRef.removeValue();
                    chartData.removeValue();
                    DbExecuteLogic.deleteProduk(dbHelper, pid);
                    AppPreference.removeData(context, AppPreference.TAG_TOTALBAYAR);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void getDataTrxOnCart() {

        trxRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.i("Cart", snapshot.getKey());
                        kurangiStock(snapshot.getKey(), snapshot.getValue(DataTransaction.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void kurangiStock(final String productID, final DataTransaction transactionData) {
        final DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products").child("Attribut").child(path1).child(path2).child(path3).child(productID);
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    GenericTypeIndicator<List<DataAttribut>> genericTypeIndicator = new GenericTypeIndicator<List<DataAttribut>>() {};
                    List<DataAttribut> data = dataSnapshot.getValue(genericTypeIndicator);

                    for (int index = 0; index < data.size(); index++) {
                        if (transactionData.getColor().equalsIgnoreCase(data.get(index).getColor()) && transactionData.getSize().equalsIgnoreCase(data.get(index).getSize())) {
                            Log.i("Check Cart Data", "Match on index  " + index);
                            int order = transactionData.getQty();
                            int stok = Integer.parseInt(data.get(index).getStock());
                            int hasils = stok + order;
                            productRef.child(String.valueOf(index)).child("stock").setValue(String.valueOf(hasils));
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
