package asykurkhamid.dropshop.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toolbar;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Fragment.PaymentConfFragment;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.Fragment.UploadProdukFragment;

public class AdminActivity extends ParentClass {
    //    private Button btnUpload,btnUpdate;
    private Menu adminMenu;
    private TabLayout tabLayout;
    private Toolbar searchToolbar;
    private ViewPager viewPager;
    private static final String TAG = "AdminActivity";
    private int REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        setDefaultToolbar(true, "Admin Page");

        viewPager = findViewById(R.id.container);
//        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        verifyPermissions();

    }
    //  Request Permission
    private void verifyPermissions() {
        Log.d(TAG, "Asking User Permissions");
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permissions[2]) == PackageManager.PERMISSION_GRANTED) {
            setupViewPager(viewPager);
        } else {
            ActivityCompat.requestPermissions(AdminActivity.this, permissions, REQUEST_CODE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        adminMenu = menu;
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        UploadProdukFragment UF = new UploadProdukFragment();
        PaymentConfFragment PF = new PaymentConfFragment();

//        searchInterface = vf.getSearchInstance();

        adapter.addFragment(UF, "Upload");
        adapter.addFragment(PF, "Transaction");
//        adapter.addFragment(new MockupFragment(), getString(R.string.view_pager_test));

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:

                        tabLayout.setVisibility(View.VISIBLE);

                        break;
                    default:

                        tabLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        verifyPermissions();
    }
}
