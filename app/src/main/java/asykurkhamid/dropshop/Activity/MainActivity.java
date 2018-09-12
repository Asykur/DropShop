package asykurkhamid.dropshop.Activity;

import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import asykurkhamid.dropshop.Activity.Kids.KidCategoryActivity;
import asykurkhamid.dropshop.Activity.Mens.MenCategoryActivity;
import asykurkhamid.dropshop.Activity.Womens.WomenCategoryActivity;
import asykurkhamid.dropshop.Adapter.SliderAdapter;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.Pojo.DataUser;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.Utility.TimerReceiver;
import me.relex.circleindicator.CircleIndicator;

public class MainActivity extends ParentClass
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth mAuth;
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static final Integer[] slideBanner = {R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
    private ArrayList<Integer> bannerArray = new ArrayList<Integer>();
    private boolean doubleBackToExitPressedOnce = false;
    private ProgressDialog pdSignout;
    private ImageView pria, wanita, anak;
    private ProgressDialog pdSubmit;
    private AlertDialog dialog;
    private DatabaseReference userReference;

    private TextView tvUserName;
    private TextView tvShopName;
    private Button submit;
    private ArrayList<DataUser> getDataUser;
    private static final String TAG = "MainActivivty";
    private DBHelper dbHelper;
    private DatabaseReference trxRef;
    private String UID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pdSignout = new ProgressDialog(this);
        pdSignout.setMessage("Signing out ...");
        pdSignout.setCancelable(false);
        dbHelper = new DBHelper(this);

        pdSubmit = new ProgressDialog(this);
        pdSubmit.setMessage("Sedang di proses ...");
        pdSubmit.setCancelable(false);

        startService(new Intent(this, TimerReceiver.class));
        Log.i(TAG, "Start TimerReceiver..");

//        setDefaultToolbar(true);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userReference = FirebaseDatabase.getInstance().getReference().child("Users");
        userReference.keepSynced(true);

        mAuth = FirebaseAuth.getInstance();

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                ActivityOptions options = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,
                            Pair.create((View) fab, "bg"));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    startActivity(intent, options.toBundle());
                }

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvShopName = view.findViewById(R.id.tvShopName);

        initBanner();

        pria = findViewById(R.id.imgManFashion);
        wanita = findViewById(R.id.imgWomanFashion);
        anak = findViewById(R.id.imgKidsFashion);

        pria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenCategoryActivity.class);
                startActivity(intent);
            }
        });
        wanita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WomenCategoryActivity.class);
                startActivity(intent);
            }
        });
        anak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KidCategoryActivity.class);
                startActivity(intent);
            }
        });

        checkDataTransaction();

    }

    private void checkDataTransaction() {
        UID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        trxRef = FirebaseDatabase.getInstance().getReference().child("DataCart").child(UID);
        trxRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    Menu item = navigationView.getMenu();
                    item.findItem(R.id.nav_Pembayaran).setVisible(true);
                    item.findItem(R.id.nav_keranjang).setVisible(false);

                } else {
                    NavigationView navigationView = findViewById(R.id.nav_view);
                    Menu item = navigationView.getMenu();
                    item.findItem(R.id.nav_Pembayaran).setVisible(false);
                    item.findItem(R.id.nav_keranjang).setVisible(true);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    //    Cek jika current usernya null (blm register) intent kembali ke halaman login
    @Override
    public void onStart() {
        super.onStart();
        checkDataTransaction();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            gotoLogin();
        }
//        showFormBioData();
        getDataUser();

    }

    private void getDataUser() {
        final String uid = FirebaseAuth.getInstance().getUid();
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getDataUser = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Object ouid = snapshot.getKey();

                    if (uid.equals(ouid)) {
                        DataUser usr = snapshot.getValue(DataUser.class);
                        usr.setKey(snapshot.getKey());
                        getDataUser.add(usr);
                        tvShopName.setText(usr.getShopName());
                        tvUserName.setText(usr.getName());
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });
    }

    private void initBanner() {
        for (int i = 0; i < slideBanner.length; i++)
            bannerArray.add(slideBanner[i]);

        mPager = findViewById(R.id.viewPagerSlider);
        mPager.setAdapter(new SliderAdapter(MainActivity.this, bannerArray));
        CircleIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(mPager);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == slideBanner.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 7000, 7000);
    }


    private void gotoLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuAbout) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem navItem) {
        // Handle navigation view item clicks here.
        int id = navItem.getItemId();
        if (id == R.id.nav_pria) {
            Intent intent = new Intent(MainActivity.this, MenCategoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_wanita) {
            Intent intent = new Intent(MainActivity.this, WomenCategoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_anak) {
            Intent intent = new Intent(MainActivity.this, KidCategoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_Pembayaran) {
            Intent intent = new Intent(MainActivity.this, PaymentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_keranjang) {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            logoutDialog();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Signout");
        dialogBuilder.setMessage("\nDo you want to sign out?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pdSignout.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        FirebaseAuth.getInstance().signOut();
                        pdSignout.dismiss();
                        gotoLogin();
                    }
                }, 1000);

            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = dialogBuilder.create();
        dialog.show();
    }

}
