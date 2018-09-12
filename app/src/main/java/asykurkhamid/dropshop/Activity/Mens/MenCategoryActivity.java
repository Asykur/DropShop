package asykurkhamid.dropshop.Activity.Mens;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class MenCategoryActivity extends ParentClass {

    CardView top,bottom,shoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men_category);
        setDefaultToolbar(true,"Pria");

        top = findViewById(R.id.imgMensTop);
        bottom = findViewById(R.id.imgMensBottom);
        shoes = findViewById(R.id.imgMensShoes);

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenCategoryActivity.this,MensTopsCatalogActivity.class);
                startActivity(intent);

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenCategoryActivity.this,MensBottomsCatalogActivity.class);
                startActivity(intent);
            }
        });

        shoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenCategoryActivity.this,MensShoesCatalogActivity.class);
                startActivity(intent);
            }
        });
    }
}
