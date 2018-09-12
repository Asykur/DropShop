package asykurkhamid.dropshop;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import asykurkhamid.dropshop.Activity.DetailProductActivity;


public class ParentClass extends AppCompatActivity {

    protected android.support.v7.widget.Toolbar toolbar;
    private boolean mBackButtonEnable = true;
    TextView tvTitle;
    private StorageReference fileRef;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


    }

    public void setDefaultToolbar(boolean backButtonEnable) {
        setDefaultToolbar(backButtonEnable, null);
    }

    public void detailToolbar(boolean back, String pName,boolean donlud){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View btnBack = findViewById(R.id.btn_actionbar_back);
//        View btnDownload = findViewById(R.id.iv_download);
        TextView name = findViewById(R.id.tvTitleProduct);
        tvTitle = (TextView) findViewById(R.id.tv_title_menu);
        tvTitle.setVisibility(View.GONE);

        if (TextUtils.isEmpty(pName)){
//            name.setText("");
            name.setVisibility(View.GONE);
        }
        else {
            name.setText(pName);
            name.setVisibility(View.VISIBLE);
        }
        if (back){
            btnBack.setVisibility(View.VISIBLE);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
//        if (donlud){
//            btnDownload.setVisibility(View.VISIBLE);
//            btnDownload.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    DetailProductActivity.downloadToLocalFile();
//                }
//            });
//        }

    }

    public void setDefaultToolbar(boolean backButtonEnable, String title) {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tvTitle = (TextView) findViewById(R.id.tv_title_menu);
        View backButton = findViewById(R.id.btn_actionbar_back);

        ImageView imgLogo = findViewById(R.id.imgLogo);

        if (!TextUtils.isEmpty(title)) {
            imgLogo.setVisibility(View.GONE);
        }
        if (TextUtils.isEmpty(title)) {
            tvTitle.setText("");
            tvTitle.setVisibility(View.VISIBLE);

        } else {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        }

        if (backButtonEnable) {
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mBackButtonEnable)
                        finish();
                }
            });
        } else {
            backButton.setVisibility(View.GONE);
            backButton.setOnClickListener(null);
        }
    }
}
