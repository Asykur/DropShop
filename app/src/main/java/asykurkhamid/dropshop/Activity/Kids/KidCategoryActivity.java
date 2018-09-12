
package asykurkhamid.dropshop.Activity.Kids;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class KidCategoryActivity extends ParentClass {
    ImageView top,bottom,shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_category);

        setDefaultToolbar(true,"Anak");
        top = findViewById(R.id.imgKidsTop);
        bottom = findViewById(R.id.imgKidsBottom);
        shoes = findViewById(R.id.imgKidsShoes);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidCategoryActivity.this,KidsTopsCatalogActivity.class);
                startActivity(intent);
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidCategoryActivity.this,KidsBottomsCatalogActivity.class);
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KidCategoryActivity.this,KidsShoesCatalogActivity.class);
                startActivity(intent);
            }
        });
    }
}
