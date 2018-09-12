package asykurkhamid.dropshop.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import asykurkhamid.dropshop.Adapter.SliderFullAdapter;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.Utility.ImageDownloader;
import me.relex.circleindicator.CircleIndicator;

public class FullImageActivity extends AppCompatActivity implements ImageDownloader.ImageDownloadListener{
    private FloatingActionButton btnDownload;
    private ViewPager viewPager;
    private List<String> dataImage;
    private int selected = 0;
    public static final String TAG_IMGDATA = "IMG_DATA";
    public static final String TAG_IMGSELECTED = "IMG_SELECTED";
    private StorageReference fileRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_full_image);
        progressDialog = new ProgressDialog(FullImageActivity.this);
        progressDialog.setMessage("Downloading...");


        fileRef = FirebaseStorage.getInstance().getReference();
        viewPager = findViewById(R.id.vPagerFullSlider);
        btnDownload = findViewById(R.id.btnDownloadImage);
        dataImage = getIntent().getExtras().getStringArrayList(TAG_IMGDATA);
        selected = getIntent().getIntExtra(TAG_IMGSELECTED,0);
        initFullImage(dataImage,selected);
        Animation slideUp = AnimationUtils.loadAnimation(this,R.anim.slide_up);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkStoragePermission();

            }
        });
    }

    private void checkStoragePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted
                        progressDialog.show();
                        if (!dataImage.isEmpty() && viewPager != null) {
                            new ImageDownloader(FullImageActivity.this).execute(dataImage.get(viewPager.getCurrentItem()));

                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FullImageActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onComplete(boolean isSuccess) {
        progressDialog.dismiss();
        Toast.makeText(this, "Gambar berhasil diunduh..", Toast.LENGTH_SHORT).show();
    }

    private void initFullImage(List<String> dataImage, int selected) {
        SliderFullAdapter adapter = new SliderFullAdapter(this,dataImage);
        viewPager.setAdapter(adapter);
        CircleIndicator indicator = findViewById(R.id.indicatorFullImage);
        indicator.setViewPager(viewPager);
        viewPager.setCurrentItem(selected,false);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        viewPager.setCurrentItem(selected,true);
        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        setResult(RESULT_OK,new Intent().putExtra(TAG_IMGSELECTED,selected));
    }

}
