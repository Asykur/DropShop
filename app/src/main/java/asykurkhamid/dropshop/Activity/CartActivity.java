package asykurkhamid.dropshop.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import asykurkhamid.dropshop.Adapter.CartAdapter;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.DataAttribut;
import asykurkhamid.dropshop.Pojo.DataCart;
import asykurkhamid.dropshop.Pojo.DataTransaction;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.Utility.AppPreference;


public class CartActivity extends ParentClass {
    private static int hasilJml = 0;
    private static int getKurirPrice = 0;
    private RecyclerView rvCart;
    private EditText etAlamat, etCatatan;
    private static Spinner spKurir;
    private static Spinner spPengiriman;
    public static TextView tvBiayaPengiriman;
    public static TextView tvTotal;
    private Button btnBayar;
    private String alamat;
    private String kurir;
    private String pengiriman;
    public static String UID;
    private RecyclerView rvSelectedProduct;
    private ArrayList<DataTransaction> trxIntent = new ArrayList<>();
    private ArrayList<DataTransaction> trxList = new ArrayList<>();
    private ArrayList<DataCart> cartList = new ArrayList<>();

    private DatabaseReference dataTrxRef;
    private DatabaseReference dataCartRef;

    private ScrollView displayCart;
    private RelativeLayout gifEmpty;
    public static CartActivity ca;
    private static int reg1 = 9000;
    private static int reg2 = 10000;
    private static int ex1 = 18000;
    private static int ex2 = 20000;
    private static int nul = 0;
    public static int totBayar = 0;
    public static final String TAG_TRX = "dataTRX";

    static NumberFormat rupiah;
    private static DBHelper dbHelper;
    private static String totalFromDB;
    private ArrayList<DataCart> listDataCart = new ArrayList();
    private DataTransaction datatrx;
    private DataTransaction dataTransaction;
    private String trxCartID;
    private static String PID;
    private String getPath1;
    private String getPath2;
    private String getPath3;;
    private ProgressDialog pgProses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ca = this;
        dbHelper = new DBHelper(this);
        datatrx = getIntent().getParcelableExtra(TAG_TRX);
        trxIntent.add(datatrx);

        pgProses = new ProgressDialog(CartActivity.this);
        pgProses.setMessage("Processing...");
        pgProses.setCancelable(false);

        try {
            String path1 = getIntent().getStringExtra("path1");
            String path2 = getIntent().getStringExtra("path2");
            String path3 = getIntent().getStringExtra("path3");

            if (!(path1.isEmpty() && path2.isEmpty() && path3.isEmpty())) {
                AppPreference.setPreference(CartActivity.this, AppPreference.TAG_PATH1, path1);
                AppPreference.setPreference(CartActivity.this, AppPreference.TAG_PATH2, path2);
                AppPreference.setPreference(CartActivity.this, AppPreference.TAG_PATH3, path3);
            }
        } catch (NullPointerException e) {

        }

        getPath1 = AppPreference.getPreference(CartActivity.this, AppPreference.TAG_PATH1);
        getPath2 = AppPreference.getPreference(CartActivity.this, AppPreference.TAG_PATH2);
        getPath3 = AppPreference.getPreference(CartActivity.this, AppPreference.TAG_PATH3);

        Locale locale = new Locale("in", "ID");
        rupiah = NumberFormat.getCurrencyInstance(locale);

        displayCart = findViewById(R.id.scrollCart);
        gifEmpty = findViewById(R.id.relaEmptyCart);
        rvSelectedProduct = findViewById(R.id.rvSelectedProduct);
        etAlamat = findViewById(R.id.etAddress);
        etAlamat.setMaxLines(Integer.MAX_VALUE);
        etAlamat.setHorizontallyScrolling(false);

        etCatatan = findViewById(R.id.etCatatan);
        etCatatan.setMaxLines(Integer.MAX_VALUE);
        etCatatan.setHorizontallyScrolling(false);

        spKurir = findViewById(R.id.spKurir);
        spPengiriman = findViewById(R.id.spPengiriman);
        rvCart = findViewById(R.id.rvSelectedProduct);
        tvBiayaPengiriman = findViewById(R.id.tvBiayaPengiriman);
        tvTotal = findViewById(R.id.tvTotalPembayaran);
        btnBayar = findViewById(R.id.btnBayar);
        setDefaultToolbar(true, "Keranjang Belanja");
        dataTrxRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction").child(UID);

        loadDataTrx();
        selectKurir();
        initRecycler();

        checkCartDataToDisplay();

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgProses.show();
                alamat = etAlamat.getText().toString();
                kurir = spKurir.getSelectedItem().toString();
                pengiriman = spPengiriman.getSelectedItem().toString();
                if (alamat.isEmpty()) {
                    etAlamat.setError("Alamat Harus diisi");
                    etAlamat.requestFocus();
                } else if (kurir.equals("Kurir")) {
                    Snackbar.make(btnBayar.getRootView(), "Pilih Kurir dulu..", Snackbar.LENGTH_SHORT).show();
                } else if (pengiriman.equals("Pengiriman")) {
                    Snackbar.make(btnBayar.getRootView(), "Pilih Paket pengiriman..", Snackbar.LENGTH_SHORT).show();
                } else {
                    getCartData();
                    pgProses.dismiss();

                }
            }
        });
    }

    private void checkCartDataToDisplay() {
        DatabaseReference reff = FirebaseDatabase.getInstance().getReference().child("DataCart").child(UID);
        reff.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DataCart cart = snapshot.getValue(DataCart.class);
                        cartList.add(cart);
                        if (cartList.size() > 0) {
                            displayCart.setVisibility(View.GONE);
                            gifEmpty.setVisibility(View.VISIBLE);
                        } else {
                            displayCart.setVisibility(View.VISIBLE);
                            gifEmpty.setVisibility(View.GONE);
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //    Trigger dari CartAdapter
    public static void gtTtl() {
        readDBPrice();
    }

    private static void readDBPrice() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("select sum (subTotal) as brandTotal from tCart", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            try {
                cursor.moveToPosition(0);
                totalFromDB = cursor.getString(0).toString();
                hasilJml = Integer.parseInt(totalFromDB);// get data subTotal from SQLite
                cursor.close();
            } catch (NullPointerException e) {

            }
        }
    }

    public void getCartData() {
        String Address = alamat;
        String selectedKurir = kurir;
        String selectedPengiriman = pengiriman;
        String notes = etCatatan.getText().toString();
        int pembayaran = totBayar;
        PID = dataTransaction.getProductID();

        DataCart dataCart = new DataCart();
        dataCart.setProductName(dataTransaction.getName());
        dataCart.setColor(dataTransaction.getColor());
        dataCart.setSize(dataTransaction.getSize());
        dataCart.setStock(dataTransaction.getStock());
        dataCart.setUnitPrice(dataTransaction.getUnitPrice());
        dataCart.setProductID(PID);
        dataCart.setQtyOrder(dataTransaction.getQty());
        dataCart.setUserID(UID);
        dataCart.setAddress(Address);
        dataCart.setKurir(selectedKurir);
        dataCart.setPaketPengiriman(selectedPengiriman);
        dataCart.setNotes(notes);
        dataCart.setTotalBayar(pembayaran);

//        Upload data Cart ke server
        dataCartRef = FirebaseDatabase.getInstance().getReference().child("DataCart");
        trxCartID = dataCartRef.push().getKey();


        dataCartRef.child(UID).child("TRX" + trxCartID).setValue(dataCart).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    getDataTrxOnCart();
                } else {
                    Toast.makeText(CartActivity.this, "Transaksi gagal, periksa jaringan anda!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getDataTrxOnCart() {

        dataTrxRef.addListenerForSingleValueEvent(new ValueEventListener() {
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
        Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
        intent.putExtra(PaymentActivity.TAG_BAYAR, listDataCart);
        startActivity(intent);
    }

    private void kurangiStock(final String productID, final DataTransaction transactionData) {
        final DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Products").child("Attribut").child(getPath1).child(getPath2).child(getPath3).child(productID);
        productRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    GenericTypeIndicator<List<DataAttribut>> genericTypeIndicator = new GenericTypeIndicator<List<DataAttribut>>() {};
                    List<DataAttribut> data = dataSnapshot.getValue(genericTypeIndicator);

                    for (int index =0; index<data.size(); index++) {
                        if (transactionData.getColor().equalsIgnoreCase(data.get(index).getColor()) && transactionData.getSize().equalsIgnoreCase(data.get(index).getSize())) {
                            Log.i("Check Cart Data", "Match on index  " + index);
                            int order = transactionData.getQty();
                            int stok = Integer.parseInt(data.get(index).getStock());
                            int hasils = stok - order;
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

    private void loadDataTrx() {
        dataTrxRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction").child(UID);
        dataTrxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        dataTransaction = snapshot.getValue(DataTransaction.class);
                        trxList.add(dataTransaction);
                        checkData();
                    }
                } else {
                    checkData();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void selectKurir() {

        spKurir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPriceKurir();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spPengiriman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getPriceKurir();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void getPriceKurir() {
        String kurir = spKurir.getSelectedItem().toString();
        String pengiriman = spPengiriman.getSelectedItem().toString();

        if (kurir.equalsIgnoreCase("JNE") && pengiriman.equalsIgnoreCase("Reguler")) {
            tvBiayaPengiriman.setText(rupiah.format((double) reg1));
            getKurirPrice = reg1;
            totBayar = getKurirPrice + hasilJml;
            tvTotal.setText(rupiah.format((double) totBayar));
            AppPreference.setPreference(CartActivity.this, AppPreference.TAG_TOTALBAYAR, String.valueOf(totBayar));

        } else if (kurir.equalsIgnoreCase("JNE") && pengiriman.equalsIgnoreCase("Express")) {
            tvBiayaPengiriman.setText(rupiah.format((double) ex1));
            getKurirPrice = ex1;
            totBayar = getKurirPrice + hasilJml;
            tvTotal.setText(rupiah.format((double) totBayar));
            AppPreference.setPreference(CartActivity.this, AppPreference.TAG_TOTALBAYAR, String.valueOf(totBayar));

        } else if (kurir.equalsIgnoreCase("TIKI") && pengiriman.equalsIgnoreCase("Reguler")) {
            tvBiayaPengiriman.setText(rupiah.format((double) reg2));
            getKurirPrice = reg2;
            totBayar = getKurirPrice + hasilJml;
            tvTotal.setText(rupiah.format((double) totBayar));
            AppPreference.setPreference(CartActivity.this, AppPreference.TAG_TOTALBAYAR, String.valueOf(totBayar));

        } else if (kurir.equalsIgnoreCase("TIKI") && pengiriman.equalsIgnoreCase("Express")) {
            tvBiayaPengiriman.setText(rupiah.format((double) ex2));
            getKurirPrice = ex2;
            totBayar = getKurirPrice + hasilJml;
            tvTotal.setText(rupiah.format((double) totBayar));
            AppPreference.setPreference(CartActivity.this, AppPreference.TAG_TOTALBAYAR, String.valueOf(totBayar));

        } else {
            tvBiayaPengiriman.setText(rupiah.format((double) nul));
            tvTotal.setText(rupiah.format((double) nul));
        }

    }

    private void initRecycler() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSelectedProduct.setLayoutManager(manager);
        CartAdapter adapter = new CartAdapter(trxList, CartActivity.this);
        rvSelectedProduct.setAdapter(adapter);
    }

    private void checkData() {
        if (trxList.size() <= 0) {
            displayCart.setVisibility(View.GONE);
            gifEmpty.setVisibility(View.VISIBLE);
        } else {
            displayCart.setVisibility(View.VISIBLE);
            gifEmpty.setVisibility(View.GONE);
        }
    }
}
