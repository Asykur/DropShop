package asykurkhamid.dropshop.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import asykurkhamid.dropshop.ParentClass;
import asykurkhamid.dropshop.R;

public class ProductCategoryActivity extends ParentClass implements View.OnClickListener {
    private LinearLayout linKatUtama, linKatPria, linAtasanPria, linBawahanPria, linSepatuPria, linKatWanita, linAtasanWanita, linBawahanWanita, linSepatuWanita, linKatAnak, linAtasanAnak, linBawahanAnak, linSepatuAnak;
    private TextView tvPria, tvWanita, tvAnak, tvAtasanPria, tvBawahanPria, tvJeansPria, tvChinosPria, tvCelFormalPria, tvSepatuPria, tvJaketPria, tvKaosPria, tvKemejaPria, tvKategori, tvSepFormalPria, tvSneakersPria, tvSepSportPria;
    private TextView tvAtasanWanita, tvBawahanWanita, tvSepatuWanita, tvJaketWanita, tvKaosWanita, tvKemejaWanita, tvCardiganWanita, tvRokWanita, tvLeggingWanita, tvJeansWanita, tvSepatuFormalWanita, tvHighHeelsWanita, tvSepSportWanita, tvSneakersWanita;
    private TextView tvAtasanAnak, tvBawahanAnak, tvSepatuAnak, tvJaketAnak, tvKaosAnak, tvKemejaAnak, tvCelJeansAnak, tvCelPanjangAnak, tvCelPendekAnak, tvSepSneakersAnak, tvSepatuSekolahAnak, tvSandalAnak;
    private String path1, path2, path3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_category);
        tvKategori = findViewById(R.id.tvKategoti);
        setDefaultToolbar(true);

        //      Kategori
        linKatUtama = findViewById(R.id.linKategoriUtama);//default visible (others gone)
        tvPria = findViewById(R.id.catPria);
        tvWanita = findViewById(R.id.catWanita);
        tvAnak = findViewById(R.id.catAnak);

        //      PRIA
        linKatPria = findViewById(R.id.linKategoriPria);
        tvAtasanPria = findViewById(R.id.tvAtasanPria);
        tvBawahanPria = findViewById(R.id.tvBawahanPria);
        tvSepatuPria = findViewById(R.id.tvSepatuPria);

        linAtasanPria = findViewById(R.id.linCatAtasanPria);
        tvJaketPria = findViewById(R.id.tvJaketPria);
        tvKaosPria = findViewById(R.id.tvKaosPria);
        tvKemejaPria = findViewById(R.id.tvKemejaPria);

        linBawahanPria = findViewById(R.id.linCatBawahanPria);
        tvJeansPria = findViewById(R.id.tvJeansPria);
        tvChinosPria = findViewById(R.id.tvMensShortPant);
        tvCelFormalPria = findViewById(R.id.tvCelanaFormalPria);

        linSepatuPria = findViewById(R.id.linCatSepatuPria);
        tvSepFormalPria = findViewById(R.id.tvSepatuFormalPria);
        tvSneakersPria = findViewById(R.id.tvSepatuSneakersPria);
        tvSepSportPria = findViewById(R.id.tvSepatuSportPria);

        //      WANITA
        linKatWanita = findViewById(R.id.linKatWanita);
        tvAtasanWanita = findViewById(R.id.tvAtasanWanita);
        tvBawahanWanita = findViewById(R.id.tvBawahanWanita);
        tvSepatuWanita = findViewById(R.id.tvSepatuWanita);

        linAtasanWanita = findViewById(R.id.linAtasanWanita);
        tvJaketWanita = findViewById(R.id.tvJaketWanita);
        tvKaosWanita = findViewById(R.id.tvKaosWanita);
        tvKemejaWanita = findViewById(R.id.tvKemejaWanita);
        tvCardiganWanita = findViewById(R.id.tvCardiganWanita);

        linBawahanWanita = findViewById(R.id.linKatBawahanWanita);
        tvRokWanita = findViewById(R.id.tvRokWanita);
        tvLeggingWanita = findViewById(R.id.tvLeggingWanita);
        tvJeansWanita = findViewById(R.id.tvJeansWanita);

        linSepatuWanita = findViewById(R.id.linKatSepatuWanita);
        tvSepatuFormalWanita = findViewById(R.id.tvSepatuFormalWanita);
        tvSneakersWanita = findViewById(R.id.tvSepatuSneakersWanita);
        tvSepSportWanita = findViewById(R.id.tvSepatuSportWanita);
        tvHighHeelsWanita = findViewById(R.id.tvHighHeelsWanita);

//        ANAK
        linKatAnak = findViewById(R.id.linKategoriAnak);
        tvAtasanAnak = findViewById(R.id.tvAtasanAnak);
        tvBawahanAnak = findViewById(R.id.tvBawahanAnak);
        tvSepatuAnak = findViewById(R.id.tvSepatuAnak);

        linAtasanAnak = findViewById(R.id.linAtasanAnak);
        tvJaketAnak = findViewById(R.id.tvJaketAnak);
        tvKaosAnak = findViewById(R.id.tvKaosAnak);
        tvKemejaAnak = findViewById(R.id.tvKemejaAnak);

        linBawahanAnak = findViewById(R.id.linCatBawahanAnak);
        tvCelJeansAnak = findViewById(R.id.tvCelJeansAnak);
        tvCelPanjangAnak = findViewById(R.id.tvCelPanjangAnak);
        tvCelPendekAnak = findViewById(R.id.tvCelPendekAnak);

        linSepatuAnak = findViewById(R.id.linKategoriSepatuAnak);
        tvSepSneakersAnak = findViewById(R.id.tvSepatuSneakersAnak);
        tvSepatuSekolahAnak = findViewById(R.id.tvSepatuSekolahAnak);
        tvSandalAnak = findViewById(R.id.tvSandalAnak);

        linKatUtama.setOnClickListener(this);
        tvPria.setOnClickListener(this);
        tvWanita.setOnClickListener(this);
        tvAnak.setOnClickListener(this);

        linKatPria.setOnClickListener(this);
        tvAtasanPria.setOnClickListener(this);
        tvBawahanPria.setOnClickListener(this);
        tvSepatuPria.setOnClickListener(this);

        tvJaketPria.setOnClickListener(this);
        tvKaosPria.setOnClickListener(this);
        tvKemejaPria.setOnClickListener(this);

        tvJeansPria.setOnClickListener(this);
        tvChinosPria.setOnClickListener(this);
        tvCelFormalPria.setOnClickListener(this);

        tvSepFormalPria.setOnClickListener(this);
        tvSneakersPria.setOnClickListener(this);
        tvSepSportPria.setOnClickListener(this);

        linKatWanita.setOnClickListener(this);
        tvAtasanWanita.setOnClickListener(this);
        tvBawahanWanita.setOnClickListener(this);
        tvSepatuWanita.setOnClickListener(this);

        tvJaketWanita.setOnClickListener(this);
        tvKaosWanita.setOnClickListener(this);
        tvKemejaWanita.setOnClickListener(this);
        tvCardiganWanita.setOnClickListener(this);

        tvRokWanita.setOnClickListener(this);
        tvLeggingWanita.setOnClickListener(this);
        tvJeansWanita.setOnClickListener(this);

        tvSepatuFormalWanita.setOnClickListener(this);
        tvSneakersWanita.setOnClickListener(this);
        tvSepSportWanita.setOnClickListener(this);
        tvHighHeelsWanita.setOnClickListener(this);

        tvAtasanAnak.setOnClickListener(this);
        tvBawahanAnak.setOnClickListener(this);
        tvSepatuAnak.setOnClickListener(this);

        tvJaketAnak.setOnClickListener(this);
        tvKaosAnak.setOnClickListener(this);
        tvKemejaAnak.setOnClickListener(this);

        tvCelJeansAnak.setOnClickListener(this);
        tvCelPanjangAnak.setOnClickListener(this);
        tvCelPendekAnak.setOnClickListener(this);

        tvSepSneakersAnak.setOnClickListener(this);
        tvSepatuSekolahAnak.setOnClickListener(this);
        tvSandalAnak.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            Get Path1
            case R.id.catPria:
                path1 = tvPria.getText().toString();
                linKatPria.setVisibility(View.VISIBLE);
                linKatUtama.setVisibility(View.GONE);
                break;
            case R.id.catWanita:
                path1 = tvWanita.getText().toString();
                linKatWanita.setVisibility(View.VISIBLE);
                linKatUtama.setVisibility(View.GONE);
                break;
            case R.id.catAnak:
                path1 = tvAnak.getText().toString();
                linKatAnak.setVisibility(View.VISIBLE);
                linKatUtama.setVisibility(View.GONE);
                break;

//            Get Path2
            case R.id.tvAtasanPria:
                path2 = tvAtasanPria.getText().toString();
                linAtasanPria.setVisibility(View.VISIBLE);
                linKatPria.setVisibility(View.GONE);
                break;
            case R.id.tvBawahanPria:
                path2 = tvBawahanPria.getText().toString();
                linBawahanPria.setVisibility(View.VISIBLE);
                linKatPria.setVisibility(View.GONE);
                break;
            case R.id.tvSepatuPria:
                path2 = tvSepatuPria.getText().toString();
                linSepatuPria.setVisibility(View.VISIBLE);
                linKatPria.setVisibility(View.GONE);
                break;

            case R.id.tvAtasanWanita:
                path2 = tvAtasanWanita.getText().toString();
                linAtasanWanita.setVisibility(View.VISIBLE);
                linKatWanita.setVisibility(View.GONE);
                break;
            case R.id.tvBawahanWanita:
                path2 = tvBawahanWanita.getText().toString();
                linBawahanWanita.setVisibility(View.VISIBLE);
                linKatWanita.setVisibility(View.GONE);
                break;
            case R.id.tvSepatuWanita:
                path2 = tvSepatuWanita.getText().toString();
                linSepatuWanita.setVisibility(View.VISIBLE);
                linKatWanita.setVisibility(View.GONE);
                break;

            case R.id.tvAtasanAnak:
                path2 = tvAtasanAnak.getText().toString();
                linAtasanAnak.setVisibility(View.VISIBLE);
                linKatAnak.setVisibility(View.GONE);
                break;
            case R.id.tvBawahanAnak:
                path2 = tvBawahanAnak.getText().toString();
                linBawahanAnak.setVisibility(View.VISIBLE);
                linKatAnak.setVisibility(View.GONE);
                break;
            case R.id.tvSepatuAnak:
                path2 = tvSepatuAnak.getText().toString();
                linSepatuAnak.setVisibility(View.VISIBLE);
                linKatAnak.setVisibility(View.GONE);
                break;

//                Get Path3
//PRIA
            case R.id.tvJaketPria:
                path3 = tvJaketPria.getText().toString();
                Intent intent1 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent1.putExtra("path1", path1);
                intent1.putExtra("path2", path2);
                intent1.putExtra("path3", path3);
                startActivity(intent1);
                finish();
                break;
            case R.id.tvKaosPria:
                path3 = tvKaosPria.getText().toString();
                Intent intent2 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent2.putExtra("path1", path1);
                intent2.putExtra("path2", path2);
                intent2.putExtra("path3", path3);
                startActivity(intent2);
                finish();
                break;
            case R.id.tvKemejaPria:
                path3 = tvKemejaPria.getText().toString();
                Intent intent3 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent3.putExtra("path1", path1);
                intent3.putExtra("path2", path2);
                intent3.putExtra("path3", path3);
                startActivity(intent3);
                finish();
                break;

            case R.id.tvJeansPria:
                path3 = tvJeansPria.getText().toString();
                Intent intent33 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent33.putExtra("path1", path1);
                intent33.putExtra("path2", path2);
                intent33.putExtra("path3", path3);
                startActivity(intent33);
                finish();
                break;
            case R.id.tvMensShortPant:
                path3 = tvChinosPria.getText().toString();
                Intent intent44 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent44.putExtra("path1", path1);
                intent44.putExtra("path2", path2);
                intent44.putExtra("path3", path3);
                startActivity(intent44);
                finish();
                break;
            case R.id.tvCelanaFormalPria:
                path3 = tvCelFormalPria.getText().toString();
                Intent intent55 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent55.putExtra("path1", path1);
                intent55.putExtra("path2", path2);
                intent55.putExtra("path3", path3);
                startActivity(intent55);
                finish();
                break;

            case R.id.tvSepatuFormalPria:
                path3 = tvSepFormalPria.getText().toString();
                Intent intent66 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent66.putExtra("path1", path1);
                intent66.putExtra("path2", path2);
                intent66.putExtra("path3", path3);
                startActivity(intent66);
                finish();
                break;
            case R.id.tvSepatuSneakersPria:
                path3 = tvSneakersPria.getText().toString();
                Intent intent77 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent77.putExtra("path1", path1);
                intent77.putExtra("path2", path2);
                intent77.putExtra("path3", path3);
                startActivity(intent77);
                finish();
                break;
            case R.id.tvSepatuSportPria:
                path3 = tvSepSportPria.getText().toString();
                Intent intent88 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent88.putExtra("path1", path1);
                intent88.putExtra("path2", path2);
                intent88.putExtra("path3", path3);
                startActivity(intent88);
                finish();
                break;


//WANITA
            case R.id.tvJaketWanita:
                path3 = tvJaketWanita.getText().toString();
                Intent intent4 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent4.putExtra("path1", path1);
                intent4.putExtra("path2", path2);
                intent4.putExtra("path3", path3);
                startActivity(intent4);
                finish();
                break;
            case R.id.tvKaosWanita:
                path3 = tvKaosWanita.getText().toString();
                Intent intent5 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent5.putExtra("path1", path1);
                intent5.putExtra("path2", path2);
                intent5.putExtra("path3", path3);
                startActivity(intent5);
                finish();
                break;
            case R.id.tvKemejaWanita:
                path3 = tvKemejaWanita.getText().toString();
                Intent intent6 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent6.putExtra("path1", path1);
                intent6.putExtra("path2", path2);
                intent6.putExtra("path3", path3);
                startActivity(intent6);
                finish();
                break;
            case R.id.tvCardiganWanita:
                path3 = tvCardiganWanita.getText().toString();
                Intent intent7 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent7.putExtra("path1", path1);
                intent7.putExtra("path2", path2);
                intent7.putExtra("path3", path3);
                startActivity(intent7);
                finish();
                break;


            case R.id.tvRokWanita:
                path3 = tvRokWanita.getText().toString();
                Intent intentRok = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentRok.putExtra("path1", path1);
                intentRok.putExtra("path2", path2);
                intentRok.putExtra("path3", path3);
                startActivity(intentRok);
                finish();
                break;
            case R.id.tvLeggingWanita:
                path3 = tvLeggingWanita.getText().toString();
                Intent intentLegging = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentLegging.putExtra("path1", path1);
                intentLegging.putExtra("path2", path2);
                intentLegging.putExtra("path3", path3);
                startActivity(intentLegging);
                finish();
                break;
            case R.id.tvJeansWanita:
                path3 = tvJeansWanita.getText().toString();
                Intent intentJeansW = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentJeansW.putExtra("path1", path1);
                intentJeansW.putExtra("path2", path2);
                intentJeansW.putExtra("path3", path3);
                startActivity(intentJeansW);
                finish();
                break;
            case R.id.tvSepatuFormalWanita:
                path3 = tvSepatuFormalWanita.getText().toString();
                Intent intentSepForW = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSepForW.putExtra("path1", path1);
                intentSepForW.putExtra("path2", path2);
                intentSepForW.putExtra("path3", path3);
                startActivity(intentSepForW);
                finish();
                break;
            case R.id.tvSepatuSneakersWanita:
                path3 = tvSneakersWanita.getText().toString();
                Intent intentSneakersW = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSneakersW.putExtra("path1", path1);
                intentSneakersW.putExtra("path2", path2);
                intentSneakersW.putExtra("path3", path3);
                startActivity(intentSneakersW);
                finish();
                break;
            case R.id.tvSepatuSportWanita:
                path3 = tvSepSportWanita.getText().toString();
                Intent intentSportW = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSportW.putExtra("path1", path1);
                intentSportW.putExtra("path2", path2);
                intentSportW.putExtra("path3", path3);
                startActivity(intentSportW);
                finish();
                break;
            case R.id.tvHighHeelsWanita:
                path3 = tvHighHeelsWanita.getText().toString();
                Intent intentHeelsW = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentHeelsW.putExtra("path1", path1);
                intentHeelsW.putExtra("path2", path2);
                intentHeelsW.putExtra("path3", path3);
                startActivity(intentHeelsW);
                finish();
                break;


//ANAK
            case R.id.tvJaketAnak:
                path3 = tvJaketAnak.getText().toString();
                Intent intent8 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent8.putExtra("path1", path1);
                intent8.putExtra("path2", path2);
                intent8.putExtra("path3", path3);
                startActivity(intent8);
                finish();
                break;
            case R.id.tvKaosAnak:
                path3 = tvKaosAnak.getText().toString();
                Intent intent9 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent9.putExtra("path1", path1);
                intent9.putExtra("path2", path2);
                intent9.putExtra("path3", path3);
                startActivity(intent9);
                finish();
                break;
            case R.id.tvKemejaAnak:
                path3 = tvKemejaAnak.getText().toString();
                Intent intent10 = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intent10.putExtra("path1", path1);
                intent10.putExtra("path2", path2);
                intent10.putExtra("path3", path3);
                startActivity(intent10);
                finish();
                break;


            case R.id.tvCelJeansAnak:
                path3 = tvCelJeansAnak.getText().toString();
                Intent intentJeansAnak = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentJeansAnak.putExtra("path1", path1);
                intentJeansAnak.putExtra("path2", path2);
                intentJeansAnak.putExtra("path3", path3);
                startActivity(intentJeansAnak);
                finish();
                break;
            case R.id.tvCelPendekAnak:
                path3 = tvCelPendekAnak.getText().toString();
                Intent intentCelPendek = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentCelPendek.putExtra("path1", path1);
                intentCelPendek.putExtra("path2", path2);
                intentCelPendek.putExtra("path3", path3);
                startActivity(intentCelPendek);
                finish();
                break;
            case R.id.tvCelPanjangAnak:
                path3 = tvCelPanjangAnak.getText().toString();
                Intent intentCelPjgAnak = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentCelPjgAnak.putExtra("path1", path1);
                intentCelPjgAnak.putExtra("path2", path2);
                intentCelPjgAnak.putExtra("path3", path3);
                startActivity(intentCelPjgAnak);
                finish();
                break;

            case R.id.tvSepatuSneakersAnak:
                path3 = tvSepSneakersAnak.getText().toString();
                Intent intentSneakerAnak = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSneakerAnak.putExtra("path1", path1);
                intentSneakerAnak.putExtra("path2", path2);
                intentSneakerAnak.putExtra("path3", path3);
                startActivity(intentSneakerAnak);
                finish();
                break;
            case R.id.tvSepatuSekolahAnak:
                path3 = tvSepatuSekolahAnak.getText().toString();
                Intent intentSepSek = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSepSek.putExtra("path1", path1);
                intentSepSek.putExtra("path2", path2);
                intentSepSek.putExtra("path3", path3);
                startActivity(intentSepSek);
                finish();
                break;
            case R.id.tvSandalAnak:
                path3 = tvSandalAnak.getText().toString();
                Intent intentSandalAnak = new Intent(ProductCategoryActivity.this, AdminActivity.class);
                intentSandalAnak.putExtra("path1", path1);
                intentSandalAnak.putExtra("path2", path2);
                intentSandalAnak.putExtra("path3", path3);
                startActivity(intentSandalAnak);
                finish();
                break;

        }

    }
}
