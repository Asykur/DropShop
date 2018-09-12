package asykurkhamid.dropshop.Activity.Kids;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Kids.KidsTop.KidsJacketFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsTop.KidsKaosFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsTop.KidsKemejaFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class KidsTopsCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_tops_catalog);

        setDefaultToolbar(true,"Atasan Anak");

        viewPager = findViewById(R.id.containerTopKids);
        tabLayout = findViewById(R.id.tabsTopKids);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        KidsJacketFragment KJF = new KidsJacketFragment();
        KidsKaosFragment KKF = new KidsKaosFragment();
        KidsKemejaFragment KKMF = new KidsKemejaFragment();

        adapter.addFragment(KJF,"Jaket Anak");
        adapter.addFragment(KKF,"Kaos Anak");
        adapter.addFragment(KKMF,"Kemeja ANak");

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
