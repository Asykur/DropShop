package asykurkhamid.dropshop.Activity.Womens;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Womens.WomenBottom.WomenJeansFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenBottom.WomenLongPantsFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenBottom.WomenRokFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenBottom.WomenShortPantsFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class WomensBottomCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_bottom_catalog);

        setDefaultToolbar(true,"Bawahan Wanita");

        viewPager = findViewById(R.id.containerBottomWomen);
        tabLayout = findViewById(R.id.tabsBottomWomen);
        tabLayout.setupWithViewPager(viewPager);

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        WomenJeansFragment WJF = new WomenJeansFragment();
        WomenLongPantsFragment WLPF = new WomenLongPantsFragment();
        WomenRokFragment WRF = new WomenRokFragment();
        WomenShortPantsFragment WSPF = new WomenShortPantsFragment();

        adapter.addFragment(WJF,"Jeans");
        adapter.addFragment(WLPF,"Celana Panjang");
        adapter.addFragment(WSPF,"Celana Pendek");
        adapter.addFragment(WRF,"Rok");

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
