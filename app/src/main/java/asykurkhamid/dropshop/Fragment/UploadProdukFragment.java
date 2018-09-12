package asykurkhamid.dropshop.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import asykurkhamid.dropshop.Activity.ProductCategoryActivity;
import asykurkhamid.dropshop.Adapter.AttrProductAdapter;
import asykurkhamid.dropshop.Adapter.ListImageSelectedAdapter;
import asykurkhamid.dropshop.Pojo.AttrProduk;
import asykurkhamid.dropshop.Pojo.DataProduct;
import asykurkhamid.dropshop.R;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class UploadProdukFragment extends Fragment {

    private EditText etProductName, etPrice, etStock, etDescription, etCategory, etWeight;
    private Spinner spSize, spColor;
    private Button btnPasang, btnSelectPhoto;
    private static final int GALLERY_IMAGE = 1234;
    private List<Uri> multiImageSelected;
    private List<DataProduct> ArraylistAttr;
    private List<String> uploaded;
    private RecyclerView rvPhoto, rvAttr;
    private ListImageSelectedAdapter listImageSelectedAdapter;
    private Button btnInsert;

    private StorageReference storageRef;
    private DatabaseReference product, attribut;
    private DatabaseReference productImage;
    private String productID;
    private int i = 0;
    private ProgressDialog pdUpload;

    private String path1, path2, path3;

    private String TAG = "UploadProductFragment";
    private DatabaseReference product_Ref;
    private DatabaseReference atttribut_Ref;
    private DatabaseReference prdkID;
    private List<Uri> selectedImages = new ArrayList<>();
    private List<String> imagedUploaded = new ArrayList<>();
    private String defImg;
    private String colors;
    private String sizes;
    private String stock;
    private DataProduct dataAttr;

    public UploadProdukFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upload_produk, container, false);

        product_Ref = FirebaseDatabase.getInstance().getReference().child("Products");
        atttribut_Ref = product_Ref.child("Attribut");


//        Generate product id
        prdkID = product_Ref.push();
        productID = prdkID.getKey();

        btnInsert = view.findViewById(R.id.btnInsert);
        rvAttr = view.findViewById(R.id.rvproductAttr);
        rvPhoto = view.findViewById(R.id.rvSelectedImage);
        storageRef = FirebaseStorage.getInstance().getReference();
        pdUpload = new ProgressDialog(getContext());
        pdUpload.setTitle("Menunggah Produk");
        pdUpload.setMessage("Mohon tunggu ...");
        pdUpload.setCancelable(false);

        spSize = view.findViewById(R.id.spSize);
        spColor = view.findViewById(R.id.spColor);

        btnSelectPhoto = view.findViewById(R.id.btnSelectPhoto);
        etProductName = view.findViewById(R.id.etUploadName);
        etPrice = view.findViewById(R.id.etHarga);
        etStock = view.findViewById(R.id.etStock);
        etWeight = view.findViewById(R.id.etWeight);

        etDescription = view.findViewById(R.id.etKeterangan);
        etDescription.setMaxLines(Integer.MAX_VALUE);
        etDescription.setHorizontallyScrolling(false);

        etCategory = view.findViewById(R.id.tvKategoti);
        btnPasang = view.findViewById(R.id.btnPasang);
        multiImageSelected = new ArrayList<>();
        uploaded = new ArrayList<>();
        ArraylistAttr = new ArrayList<DataProduct>();

        listImageSelectedAdapter = new ListImageSelectedAdapter(selectedImages, uploaded);
        final LinearLayoutManager lmPhoto;
        lmPhoto = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvPhoto.setLayoutManager(lmPhoto);
        rvPhoto.setHasFixedSize(true);
        rvPhoto.setAdapter(listImageSelectedAdapter);

//      Terima data setelah pilih kategori produk
        path1 = getActivity().getIntent().getStringExtra("path1");
        path2 = getActivity().getIntent().getStringExtra("path2");
        path3 = getActivity().getIntent().getStringExtra("path3");

        if (path1 != null && path2 != null && path3 != null) {
            etCategory.setText(path1 + " / " + path2 + " / " + path3);
        }

        etCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductCategoryActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
//      Intent ke kamera
        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_IMAGE);

            }
        });
//      Validasi edittext tidak boleh kosong
        btnPasang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etCategory.getText().toString().isEmpty()) {
                    etCategory.setError("Kategori tidak boleh kosong");
                    etCategory.requestFocus();
                } else if (etProductName.getText().toString().isEmpty()) {
                    etProductName.setError("Nama produk tidak boleh kosong");
                    etProductName.requestFocus();
                } else if (etPrice.getText().toString().isEmpty()) {
                    etPrice.setError("Harga tidak boleh kosong");
                    etPrice.requestFocus();
                } else if (etWeight.getText().toString().isEmpty()) {
                    etWeight.setError("berat tidak boleh kosong");
                    etWeight.requestFocus();
                } else if (etDescription.getText().toString().isEmpty()) {
                    etDescription.setError("Keterangan tidak boleh kosong");
                    etDescription.requestFocus();
                } else {
                    if (!selectedImages.isEmpty()) {
                        uploadImageFile(selectedImages);

                    }
                }
            }
        });
//  Insert Atribut
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spSize.getSelectedItem().toString().isEmpty() || spSize.getSelectedItem().toString().equals("Ukuran")) {
                    Snackbar sbSize = Snackbar.make(btnInsert, "Ukuaran produk masih kosong", Snackbar.LENGTH_SHORT).setAction("Action", null);
                    View vSbSize = sbSize.getView();
                    vSbSize.setBackgroundColor(Color.RED);
                    sbSize.show();
                } else if (spColor.getSelectedItem().toString().isEmpty() || spColor.getSelectedItem().toString().equals("Warna")) {
                    Snackbar sbColor = Snackbar.make(btnInsert, "Warna produk masih kosong", Snackbar.LENGTH_SHORT).setAction("Action", null);
                    View vSbColor = sbColor.getView();
                    vSbColor.setBackgroundColor(Color.RED);
                    sbColor.show();
                } else if (etStock.getText().toString().isEmpty()) {
                    etStock.setError("Stok tidak boleh Kosong");
                    etStock.requestFocus();
                } else {

                    colors = spColor.getSelectedItem().toString();
                    sizes = spSize.getSelectedItem().toString();
                    stock = etStock.getText().toString();

                    dataAttr = new DataProduct();
                    dataAttr.setColor(colors);
                    dataAttr.setSize(sizes);
                    dataAttr.setStock(stock);
                    ArraylistAttr.add(dataAttr);

                    LinearLayoutManager lmAttr = new LinearLayoutManager(getContext());
                    lmAttr.setOrientation(LinearLayoutManager.VERTICAL);
                    rvAttr.setLayoutManager(lmAttr);
                    AttrProductAdapter adapter = new AttrProductAdapter(ArraylistAttr);
                    rvAttr.setAdapter(adapter);
                    resetAttr();
                    rvAttr.getAdapter().notifyDataSetChanged();
                }
            }
        });
        return view;
    }

    //  reset field
    private void resetAttr() {
        spSize.setSelection(0);
        spColor.setSelection(0);
        etStock.setText("");
    }

    //  Fungsi Upload gambar
    private void uploadImageFile(List<Uri> imgUrl) {
        pdUpload.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                pdUpload.dismiss();
            }
        },2000);

        for (Uri uri : imgUrl) {
            StorageReference fileToUpload = storageRef.child(productID).child(getFileName(uri));
            fileToUpload.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    listImageSelectedAdapter.notifyDataSetChanged();
                    imagedUploaded.add(taskSnapshot.getDownloadUrl().toString()); // get image downloadurl
                    int arr = -1;
                    for (int i = 0; i < imagedUploaded.size(); i++) {
                        arr++;
                        defImg = imagedUploaded.get(i);
                        productImage = product_Ref.child("product_image").child(productID).child(String.valueOf(arr)).child("image");
                        productImage.setValue(defImg);

                    }
                    if (imagedUploaded.size() >= 0) {
                        uploadProduct();


                    } else {
                        Toast.makeText(getContext(), "Pilih foto produk..!", Toast.LENGTH_SHORT).show();
                    }
                    selectedImages.clear();
                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Upload Image Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }
//        resetFields();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == GALLERY_IMAGE && resultCode == RESULT_OK) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                Selected Multiple Image
                if (intent.getClipData() != null) {
                    for (i = 0; i < intent.getClipData().getItemCount(); i++) {
                        selectedImages.add(intent.getClipData().getItemAt(i).getUri());
                        listImageSelectedAdapter.notifyDataSetChanged();
                    }
                }
//                Selected Single Image
                else if (intent.getData() != null) {
                    Uri selected = intent.getData();
                    multiImageSelected.add(selected);
//                    Toast.makeText(getContext(), "Pilih Photo Lebih dari 1..!", Toast.LENGTH_SHORT).show();
//                    Uri singleUri = intent.getData();
//                    fileName = getFileName(singleUri);
////                    cropImage(singleUri);
//                    multiImageSelected.add(singleUri);
//                    listImageSelectedAdapter.notifyDataSetChanged();
                }
            }
        }
        //        Hasil setelah Cropping
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(intent);
//            if (resultCode == RESULT_OK) {
//                Uri resultUri = result.getUri();
//                resultPath = new File(resultUri.getPath());
//                multiImageSelected.add(resultUri);
//                listImageSelectedAdapter.notifyDataSetChanged();
//
//            }
//        }
    }

    //     Upload to FirebaseDatabase
    private void uploadProduct() {
        product = product_Ref.child(path1).child(path2).child(path3).child(productID);
        DataProduct data = new DataProduct();

        data.setpID(productID);
        data.setName(etProductName.getText().toString());
        data.setPrice(etPrice.getText().toString());
        data.setDef_image(defImg);
        data.setWeight(etWeight.getText().toString());
        data.setDescription(etDescription.getText().toString());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        data.setDateTime(timestamp.toString());
        product.setValue(data);

        int iz = -1;
        for (int i = 0; i < ArraylistAttr.size(); i++) {
            DataProduct atp = ArraylistAttr.get(i);
            iz++;

            product = atttribut_Ref.child(productID).child(String.valueOf(iz));
            AttrProduk attr = new AttrProduk();
            attr.setSize(atp.getSize());
            attr.setColor(atp.getColor());
            attr.setStock(atp.getStock());
            product.setValue(attr);
//            product = product_Ref.child("sizes").child(productID).child(String.valueOf(iz)).child("size");
//            product.setValue(atp.getSize());
//
//            product = product_Ref.child("colors").child(productID).child(String.valueOf(iz)).child("color");
//            product.setValue(atp.getColor());
//
//            product = product_Ref.child("stock_product").child(productID).child(atp.getColor()).child(atp.getSize()).child("stocks");
//            product.setValue(atp.getStock());
        }

    }

    //  reset field
    private void resetFields() {
        etPrice.setText("");
        etCategory.setText("");
        etDescription.setText("");
        spSize.setSelection(0);
        spColor.setSelection(0);
        etProductName.setText("");
        etWeight.setText("");
        etStock.setText("");
        multiImageSelected.clear();
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            if (getContext() != null) {
                Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
                try {
                    if (cursor != null && cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }

        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

}
