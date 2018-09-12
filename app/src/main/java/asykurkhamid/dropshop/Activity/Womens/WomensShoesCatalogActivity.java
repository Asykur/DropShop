package asykurkhamid.dropshop.Activity.Womens;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Womens.WomenShoes.HighHeelsFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenShoes.WomenFormalShoesFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenShoes.WomenSneakerFragment;
import asykurkhamid.dropshop.Fragment.Womens.WomenShoes.WomenSportShoesFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class WomensShoesCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_womens_shoes_catalog);

        setDefaultToolbar(true,"Sepatu Wanita");
        viewPager = findViewById(R.id.containerShoesWomen);
        tabLayout = findViewById(R.id.tabsShoesWomen);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        HighHeelsFragment HHF = new HighHeelsFragment();
        WomenFormalShoesFragment WFSF = new WomenFormalShoesFragment();
        WomenSneakerFragment WSF = new WomenSneakerFragment();
        WomenSportShoesFragment WSSF = new WomenSportShoesFragment();

        adapter.addFragment(HHF,"Heels");
        adapter.addFragment(WFSF,"Formal");
        adapter.addFragment(WSF,"Sneakers");
        adapter.addFragment(WSSF,"Sport");

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
