package asykurkhamid.dropshop.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import asykurkhamid.dropshop.Adapter.detailImageAdapter;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.AttrProduk;
import asykurkhamid.dropshop.Pojo.CatalogProduk;
import asykurkhamid.dropshop.Pojo.DataAlgoritma;
import asykurkhamid.dropshop.Pojo.DataImage;
import asykurkhamid.dropshop.Pojo.DataTransaction;
import asykurkhamid.dropshop.R;
import me.relex.circleindicator.CircleIndicator;

public class DetailProductActivity extends ParentClass {

    private static CatalogProduk catalogProduk;
    private DatabaseReference reference;
    private DatabaseReference imageRef, AttributRef, DataTrxRef, pathRef;
    private ArrayList<String> slideArray = new ArrayList<>();
    private ArrayList<AttrProduk> list;
    private ArrayList<String> colorList;
    private ArrayList<String> sizeList;
    private Button btnBeli;
    private ViewPager mPager;
    private ScrollView scPage;
    private Toolbar tbDetailPage;
    private TextView title, desc, price, stock, avaColor, avaSize;
    private Spinner spSize, spColor, spSatuan, spGrosir;
    private ArrayList<DataImage> imageData;
    private ArrayList<DataTransaction> dataTransactions;
    private ArrayList<DataAlgoritma> dataAlgo;
    private DataTransaction dataTrx;
    private DataAlgoritma dataAlgoritma;
    private ProgressDialog pdBuy;
    private static ProgressDialog pgDownload;
    private int count = -1;
    private String UserID;
    private static FirebaseStorage storage = FirebaseStorage.getInstance();
    private static StorageReference storageRef;
    private int satuan = 0;
    private int grosir = 0;
    private int subTotal = 0;
    private int one = 0;
    private String timeStamp;
    private int qtyOrder = 0;
    private int zeroStock = 0;
    private int getStock = 0;
    private int intStock = 0;
    public static String pid;
    int sisaStock = 0;
    int order = 0;
    int hasilFromUpdate = 0;
    private String path1 ;
    private String path2 ;
    private String path3 ;
    private int stk;
    private String stks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        catalogProduk = getIntent().getParcelableExtra("info");
        path1 = catalogProduk.getPath1();
        path2 = catalogProduk.getPath2();
        path3 = catalogProduk.getPath3();

        dataTransactions = new ArrayList<>();
        pid = catalogProduk.getId();
        pgDownload = new ProgressDialog(this);
        pgDownload.setMessage("Downloading...");
        pgDownload.setCancelable(false);

        storageRef = storage.getReferenceFromUrl("gs://dropshop-f4360.appspot.com").child(catalogProduk.getId());
        reference = FirebaseDatabase.getInstance().getReference();
        pathRef = reference.child("Products");
//        getProductPath();

        dataTrx = new DataTransaction();
        dataAlgoritma = new DataAlgoritma();

        DataTrxRef = reference.child("Data_Transaction");
//        CheckTrxRef = reference.child("Tabel_Bayangan");
        DataTrxRef.keepSynced(true);
        AttributRef = reference.child("Products").child("Attribut").child(path1).child(path2).child(path3);
        pdBuy = new ProgressDialog(this);
        pdBuy.setMessage("Processing...");
        pdBuy.setCancelable(false);

        UserID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        avaColor = findViewById(R.id.tvAvaColor);
        avaSize = findViewById(R.id.tvAvaSize);
        stock = findViewById(R.id.tvStock);
        price = findViewById(R.id.tvPrice);
        desc = findViewById(R.id.tvDescProduct);
        spColor = findViewById(R.id.spDetColor);
        spSize = findViewById(R.id.spDetSize);
        spSatuan = findViewById(R.id.spSatuan);
        spGrosir = findViewById(R.id.spGrosir);
        btnBeli = findViewById(R.id.btnBeli);
        scPage = findViewById(R.id.scrollDetailPage);
        tbDetailPage = findViewById(R.id.toolbarDetail);
        tbDetailPage.setBackgroundColor(getResources().getColor(R.color.dark_grey_trns));
        title = findViewById(R.id.tvTitleProduct);
//        detailToolbar(true, catalogProduk.getName());
        detailToolbar(true, catalogProduk.getName(), true);

        Locale locale = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(locale);
        int priceInt = Integer.parseInt(catalogProduk.getPrice());
        price.setText(rupiah.format((double) priceInt));
        desc.setText(catalogProduk.getDesc());

        getDataDetailProduct();
        getAttributProduct();
        cekStock();
        inputOrder();
        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTrxData();
            }
        });
    }

    private void inputOrder() {
        spSatuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                satuan = Integer.parseInt(spSatuan.getSelectedItem().toString());
                if (satuan > 0) {
                    spGrosir.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spGrosir.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                grosir = Integer.parseInt(spGrosir.getSelectedItem().toString());
                if (grosir > 0) {
                    spSatuan.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void cekStock() {

        AttributRef.keepSynced(true);

        spSize.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String selectedSize = spSize.getSelectedItem().toString();
                final String selectedColor = spColor.getSelectedItem().toString();
                AttributRef.child(catalogProduk.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<AttrProduk>> genericTypeIndicator = new GenericTypeIndicator<List<AttrProduk>>() {
                        };
                        List<AttrProduk> dataAtr = dataSnapshot.getValue(genericTypeIndicator);
                        if (dataAtr != null) {
                            for (AttrProduk atr : dataAtr) {
                                if (selectedColor.equalsIgnoreCase(atr.getColor()) && selectedSize.equalsIgnoreCase(atr.getSize())) {
                                    getStock = Integer.parseInt(atr.getStock());
                                    stock.setText(getStock + " pcs");
                                    break;
                                } else {
                                    stock.setText(String.valueOf(zeroStock));
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final String selectedSize = spSize.getSelectedItem().toString();
                final String selectedColor = spColor.getSelectedItem().toString();
                AttributRef.child(catalogProduk.getId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<List<AttrProduk>> genericTypeIndicator = new GenericTypeIndicator<List<AttrProduk>>() {
                        };
                        List<AttrProduk> dataAtr = dataSnapshot.getValue(genericTypeIndicator);

                        if (dataAtr != null) {
                            for (AttrProduk atr : dataAtr) {
                                if (selectedColor.equalsIgnoreCase(atr.getColor()) && selectedSize.equalsIgnoreCase(atr.getSize())) {
                                    getStock = Integer.parseInt(atr.getStock());
                                    stock.setText(getStock + " pcs");
                                    break;
                                } else {
                                    stock.setText(String.valueOf(zeroStock));
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getTrxData() {
        pdBuy.show();
        one = Integer.parseInt(catalogProduk.getPrice());
        stks = stock.getText().toString().replaceAll(" pcs","");
        if (Integer.parseInt(stks) > 0){
            stk = getStock;
        }else if (Integer.parseInt(stks) == 0){
            stk = 0;
        }

//      Assign data order ke var. qtyOrder
        if (satuan != 0) {
            qtyOrder = satuan;
        } else if (grosir != 0) {
            qtyOrder = grosir;
        }

        String aaa = stock.getText().toString();
        String finalStk = aaa.replaceAll(" pcs", "");
        intStock = Integer.parseInt(finalStk);

//      Count SubTotal
        if (satuan > 0) {
            subTotal = satuan * one;
        } else if (grosir > 0) {
            subTotal = grosir * one;
        }

//      Validasi jumlah order
        if (qtyOrder == 0) {
            pdBuy.dismiss();
            spSatuan.setEnabled(true);
            spGrosir.setEnabled(true);
            Snackbar snackbar = Snackbar.make(btnBeli, "Pilih jumlah pesananmu..!", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(Color.RED);
            snackbar.show();
            inputOrder();
        } else if (qtyOrder > stk) {
            pdBuy.dismiss();
            Snackbar snackbar = Snackbar.make(btnBeli, "Maaf, Pesanan anda melebihi stok yang tersedia", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(Color.RED);
            snackbar.show();

        } else {
            insertDataTransaction();

        }
    }

    private void insertDataTransaction() {
        dataTrx.setColor(spColor.getSelectedItem().toString());
        dataTrx.setSize(spSize.getSelectedItem().toString());
        dataTrx.setImage(catalogProduk.getDef_image());
        dataTrx.setName(catalogProduk.getName());
        dataTrx.setQty(qtyOrder);
        dataTrx.setUserID(UserID);
        dataTrx.setSubTotal(subTotal);
        dataTrx.setStock(intStock);
        dataTrx.setUnitPrice(catalogProduk.getPrice());
        dataTrx.setProductID(pid);
        timeStamp = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
        dataTrx.setTime(timeStamp);
        dataTransactions.add(dataTrx);
        uploadDataTRX();
    }

    private void uploadDataTRX() {

        //    Jika stok mencukupi -> upload data ke dataTransaction / masuk ke Cart
        if (qtyOrder <= getStock) {
            DataTrxRef.child(UserID).child(catalogProduk.getId()).setValue(dataTrx).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pdBuy.dismiss();
                            showDialog();
                        }
                    }, 1000);
                }
            });

        } else {
            pdBuy.dismiss();
            Snackbar snackbar = Snackbar.make(btnBeli, "Maaf, stok tidak cukup atau masih ada transaksi lain sedang menunggu persetujuan admin", Snackbar.LENGTH_LONG);
            View view = snackbar.getView();
            view.setBackgroundColor(Color.RED);
            snackbar.show();

            spSatuan.setEnabled(true);
            spGrosir.setEnabled(true);
            spSatuan.setSelection(0);
            spGrosir.setSelection(0);
            qtyOrder = 0;
        }
    }

    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Produk berhasil ditambahkan ke keranjang belanja");
        builder.setCancelable(false);

        builder.setPositiveButton("Bayar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(DetailProductActivity.this, CartActivity.class);
                intent.putExtra("path1",path1);
                intent.putExtra("path2",path2);
                intent.putExtra("path3",path3);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Belanja Lagi", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        final AlertDialog dialog = builder.create();
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.colorPrimary));
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.orange));
            }
        });
        dialog.show();
    }

    private void getAttributProduct() {
        Query query = AttributRef.orderByKey().equalTo(pid);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list = new ArrayList<>();
                colorList = new ArrayList<>();
                sizeList = new ArrayList<>();

                Set<String> colorSet = new HashSet<>();
                Set<String> sizeSet = new HashSet<>();

                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot child : snapshot.getChildren()) {

                            AttrProduk attr = child.getValue(AttrProduk.class);
                            list.add(attr);
                            colorList.add(attr.getColor());
                            sizeList.add(attr.getSize());
                            // distinct color
                            colorSet.addAll(colorList);
                            colorList.clear();
                            colorList.addAll(colorSet);
                            // distinc size
                            sizeSet.addAll(sizeList);
                            sizeList.clear();
                            sizeList.addAll(sizeSet);

                            showSpinnerSize(sizeList);
                            showSpinnerColor(colorList);
//                           showStock(attr.getStock());
                            avaColor.setText(colorList.toString().substring(1, colorList.toString().length() - 1));
                            avaSize.setText(sizeList.toString().substring(1, sizeList.toString().length() - 1));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showSpinnerSize(List<String> dataSize) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataSize);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSize.setAdapter(adapter);

    }

    private void showSpinnerColor(List<String> dataColor) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dataColor);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor.setAdapter(adapter);

    }

    private void getDataDetailProduct() {
        imageRef = reference.child("Products").child("product_image").child(path1).child(path2).child(path3).child(catalogProduk.getId());
        imageRef.keepSynced(true);
        imageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageData = new ArrayList<>();
                GenericTypeIndicator<List<DataImage>> genericTypeIndicator = new GenericTypeIndicator<List<DataImage>>() {
                };
                List<DataImage> data = dataSnapshot.getValue(genericTypeIndicator);
                if (data != null) {
                    imageData.addAll(data);
                    initSlideImage();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initSlideImage() {
        for (int i = 0; i < imageData.size(); i++) {
            slideArray.add(imageData.get(i).getImage());
            mPager = findViewById(R.id.imgDetailSlide);
            mPager.setAdapter(new detailImageAdapter(DetailProductActivity.this, slideArray, this));
            CircleIndicator indicator = findViewById(R.id.pageIndicator);
            indicator.setViewPager(mPager);
        }
    }
}
