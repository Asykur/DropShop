package asykurkhamid.dropshop.Activity.Mens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Mens.MensBottom.MensFormalPantsFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensBottom.MensJeanFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensBottom.MensLongPantsFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensBottom.MensShortsPantsFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class MensBottomsCatalogActivity extends ParentClass {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG= "MensBottomsCatalogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_bottoms_catalog);

        setDefaultToolbar(true,"Bawahan Pria");
        viewPager = findViewById(R.id.containerBottomMens);
        tabLayout = findViewById(R.id.tabsBottomMens);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        MensJeanFragment MJF = new MensJeanFragment();
        MensLongPantsFragment MLPF = new MensLongPantsFragment();
        MensShortsPantsFragment MSPF = new MensShortsPantsFragment();
        MensFormalPantsFragment MFPF = new MensFormalPantsFragment();

        adapter.addFragment(MJF,"Jeans");
        adapter.addFragment(MLPF,"Celana Panjang");
        adapter.addFragment(MSPF,"Celana Pendek");
        adapter.addFragment(MFPF,"Celana Formal");

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
                    case 3:
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
