package asykurkhamid.dropshop.Activity.Mens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Mens.MensShoes.MensFormalShoesFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensShoes.MensSneakerFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensShoes.MensSportShoesFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class MensShoesCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG = "MensShoesCatalogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_shoes_catalog);

        setDefaultToolbar(true,"Sepatu Pria");
        viewPager = findViewById(R.id.containerShoesMen);
        tabLayout = findViewById(R.id.tabsShoesMens);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        MensFormalShoesFragment MFSF = new MensFormalShoesFragment();
        MensSneakerFragment MSF = new MensSneakerFragment();
        MensSportShoesFragment MSSF = new MensSportShoesFragment();

        adapter.addFragment(MSF,"Sneakers");
        adapter.addFragment(MFSF,"Formal");
        adapter.addFragment(MSSF,"Sport");
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
