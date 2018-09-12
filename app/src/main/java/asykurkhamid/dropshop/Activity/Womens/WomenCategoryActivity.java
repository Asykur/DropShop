package asykurkhamid.dropshop.Activity.Womens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class WomenCategoryActivity extends ParentClass {

    ImageView top,bottom,shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_category);

        setDefaultToolbar(true,"Wanita");

        top = findViewById(R.id.imgWomensTop);
        bottom = findViewById(R.id.imgWomensBottom);
        shoes = findViewById(R.id.imgWomensShoes);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WomenCategoryActivity.this,WomensTopsCatalogActivity.class);
                startActivity(intent);
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WomenCategoryActivity.this,WomensBottomCatalogActivity.class);
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WomenCategoryActivity.this,WomensShoesCatalogActivity.class);
                startActivity(intent);
            }
        });
    }
}
