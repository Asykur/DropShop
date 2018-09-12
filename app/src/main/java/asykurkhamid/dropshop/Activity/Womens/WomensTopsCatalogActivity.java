package asykurkhamid.dropshop.Activity.Womens;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Womens.WomenTop.WomanCardiganFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenTop.WomenJacketFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenTop.WomenKaosFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenTop.WomenKemejaFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class WomensTopsCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_tops_catalog);

        setDefaultToolbar(true,"Atasan Wanita");
        viewPager = findViewById(R.id.containerTopWomen);
        tabLayout = findViewById(R.id.tabsTopWomens);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        WomenJacketFragment WJF = new WomenJacketFragment();
        WomanCardiganFragment WCF = new WomanCardiganFragment();
        WomenKaosFragment WKF = new WomenKaosFragment();
        WomenKemejaFragment WKMF = new WomenKemejaFragment();

        adapter.addFragment(WJF,"Jaket");
        adapter.addFragment(WCF,"Cardigan");
        adapter.addFragment(WKF,"Kaos");
        adapter.addFragment(WKMF,"Kemeja");

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
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
