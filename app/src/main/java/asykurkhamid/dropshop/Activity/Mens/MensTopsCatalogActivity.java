package asykurkhamid.dropshop.Activity.Mens;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Mens.MensTop.MensJaketFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensTop.MensKaosFragment;
import asykurkhamid.dropshop.Fragment.Mens.MensTop.MensKemejaFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class MensTopsCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG = "MensTopsCatalogActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mens_tops_catalog);

        setDefaultToolbar(true,"Atasan Pria");
        viewPager = findViewById(R.id.containerTopMens);
        tabLayout = findViewById(R.id.tabsTopMens);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        MensJaketFragment MJF = new MensJaketFragment();
        MensKaosFragment MSF = new MensKaosFragment();
        MensKemejaFragment MKF = new MensKemejaFragment();

        adapter.addFragment(MJF,"Jaket");
        adapter.addFragment(MSF,"Kaos");
        adapter.addFragment(MKF,"Kemeja");

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
