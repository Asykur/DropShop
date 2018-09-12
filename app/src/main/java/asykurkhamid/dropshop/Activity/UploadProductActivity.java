package asykurkhamid.dropshop.Activity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

import asykurkhamid.dropshop.Adapter.ListImageSelectedAdapter;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class    UploadProductActivity extends ParentClass {
    private EditText etNamaProduk, etHarga, etStok, etKeterangan, etKategori;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_product);

//        setDefaultToolbar(true);
//        rvPhoto = findViewById(R.id.rvSelectedImage);
//        mStorage = FirebaseStorage.getInstance().getReference();
//        pdUpload = new ProgressDialog(this);
//        pdUpload.setMessage("Uploading ...");
//        pdUpload.setCancelable(false);
//
//        btnSelectPhoto = findViewById(R.id.btnSelectPhoto);
//        etNamaProduk = findViewById(R.id.etNamaProduk);
//        etHarga = findViewById(R.id.etHarga);
//        etStok = findViewById(R.id.etStok);
//        etKeterangan = findViewById(R.id.etKeterangan);
//        etKategori = findViewById(R.id.tvKategoti);
//        btnPasang = findViewById(R.id.btnPasang);
//        fileNameList = new ArrayList<>();
//        fileDoneList = new ArrayList<>();
//
//        listImageSelectedAdapter = new ListImageSelectedAdapter(fileNameList,fileDoneList,s);
//        LinearLayoutManager layoutManager;
//        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        rvPhoto.setLayoutManager(layoutManager);
//        rvPhoto.setHasFixedSize(true);
//        rvPhoto.setAdapter(listImageSelectedAdapter);
//
////      Terima data setelah pilih kategori produk
//        final String path1 = getIntent().getStringExtra("path1");
//        final String path2 = getIntent().getStringExtra("path2");
//        final String path3 = getIntent().getStringExtra("path3");
//
//        if (path1 != null && path2 != null && path3 != null) {
//            etKategori.setText(path1 + " / " + path2 + " / " + path3);
//        }
//
//        etKategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(UploadProductActivity.this, ProductCategoryActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btnSelectPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UploadProductActivity.this);
//                View dialogView = getLayoutInflater().inflate(R.layout.dialog_select_photo, null);
//                dialogBuilder.setView(dialogView);
//                dialogBuilder.setTitle("Upload Photo");
//                TextView tvGallery = dialogView.findViewById(R.id.tvGallery);
//                TextView tvKamera = dialogView.findViewById(R.id.tvKamera);
//                tvGallery.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//                    }
//                });
//                tvKamera.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(intentCam, CAMERA_IMAGE);
//                    }
//                });
//
//                AlertDialog dialog = dialogBuilder.create();
//                dialog.show();
//
//            }
//        });
//
//        btnPasang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (etKategori.getText().toString().isEmpty()) {
//                    etKategori.setError("Kategori tidak boleh kosong");
//                    etKategori.requestFocus();
//                } else if (etNamaProduk.getText().toString().isEmpty()) {
//                    etNamaProduk.setError("Nama produk tidak boleh kosong");
//                    etNamaProduk.requestFocus();
//                } else if (etHarga.getText().toString().isEmpty()) {
//                    etHarga.setError("Harga tidak boleh kosong");
//                    etHarga.requestFocus();
//                } else if (etStok.getText().toString().isEmpty()) {
//                    etStok.setError("Stok tidak boleh Kosong");
//                    etStok.requestFocus();
//                } else if (etKeterangan.getText().toString().isEmpty()) {
//                    etKeterangan.setError("Keteterangan tidak boleh kosong");
//                    etKeterangan.requestFocus();
//                } else {
//                    pdUpload.show();
//                    StorageReference fileToUpload = mStorage.child("Images").child(path1).child(path2).child(path3).child(String.valueOf(fileNameList));
//
//                    fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            pdUpload.dismiss();
//                            listImageSelectedAdapter.notifyDataSetChanged();
//                            Toast.makeText(UploadProductActivity.this, "Produk Berhasil dipasang d(' ',)", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
//                    });
//                }
//            }
//        });

    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
////      Hasil setelah ambil photo dari gallery
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
//            if (data.getClipData() != null) {
//                int totalItemsSelected = data.getClipData().getItemCount();
//                for (i = 0; i < totalItemsSelected; i++) {
//                    fileUri = data.getClipData().getItemAt(i).getUri();
//                    fileName = getFileName(fileUri);
//                    fileNameList.add(fileUri);
//                    listImageSelectedAdapter.notifyDataSetChanged();
//                }
//            } else if (data.getData() != null) {
//                Toast.makeText(UploadProductActivity.this, "Selected Single File", Toast.LENGTH_SHORT).show();
//            }
//        }
////        Hasil setelah ambil photo dari Kamera
//        else if (requestCode == CAMERA_IMAGE && resultCode == RESULT_OK){
//            Bitmap photo = (Bitmap) data.getExtras().get("data");
//
//        }
//    }
//
////    Untuk mengambil nama dari image
//    public String getFileName(Uri uri) {
//        String result = null;
//        if (uri.getScheme().equals("content")) {
//            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//        if (result == null) {
//            result = uri.getPath();
//            int cut = result.lastIndexOf('/');
//            if (cut != -1) {
//                result = result.substring(cut + 1);
//            }
//        }
//        return result;
//    }
}
