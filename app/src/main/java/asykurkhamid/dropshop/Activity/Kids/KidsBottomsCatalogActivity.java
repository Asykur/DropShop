package asykurkhamid.dropshop.Activity.Kids;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import asykurkhamid.dropshop.Adapter.SectionsPageAdapter;
import asykurkhamid.dropshop.Fragment.Kids.KidsBottom.KidsJeanFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsBottom.KidsLongPantsFragment;
import asykurkhamid.dropshop.Fragment.Kids.KidsBottom.KidsShortsPantsFragment;
import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class KidsBottomsCatalogActivity extends ParentClass {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kids_bottoms_catalog);

        setDefaultToolbar(true,"Bawahan Anak");

        viewPager = findViewById(R.id.containerBottomKids);
        tabLayout = findViewById(R.id.tabsBottomKids);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        KidsJeanFragment KJF = new KidsJeanFragment();
        KidsLongPantsFragment KLPF = new KidsLongPantsFragment();
        KidsShortsPantsFragment KSPF = new KidsShortsPantsFragment();

        adapter.addFragment(KJF,"Jeans Anak");
        adapter.addFragment(KLPF,"Celana Panjang");
        adapter.addFragment(KSPF,"Celana Pendek");

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
