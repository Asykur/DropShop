package asykurkhamid.dropshop.Activity.Kids;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Kids.KidsShoes.KidsSandalsFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsShoes.KidsSchoolShoesFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsShoes.KidsSneakerFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class KidsShoesCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_shoes_catalog);

        setDefaultToolbar(true,"Sepatu Anak");
        viewPager = findViewById(R.id.containerShoesKids);
        tabLayout = findViewById(R.id.tabsShoesKids);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        KidsSchoolShoesFragment KSSF = new KidsSchoolShoesFragment();
        KidsSneakerFragment KSF = new KidsSneakerFragment();
        KidsSandalsFragment KSDF = new KidsSandalsFragment();

        adapter.addFragment(KSSF,"Sepatu Sekolah");
        adapter.addFragment(KSF,"Sneaker Anak");
        adapter.addFragment(KSDF,"Sandal");

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tabLayout.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        tabLayout.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        tabLayout.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
