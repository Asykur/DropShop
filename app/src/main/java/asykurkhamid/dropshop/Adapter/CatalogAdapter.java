package asykurkhamid.dropshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import asykurkhamid.dropshop.Activity.DetailProductActivity;
import asykurkhamid.dropshop.Pojo.CatalogProduk;
import asykurkhamid.dropshop.R;

public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>{

    private Context context;
    List<CatalogProduk> catalogProduks;

    public CatalogAdapter(Context context, List<CatalogProduk> catalogProduks) {
        this.context = context;
        this.catalogProduks = catalogProduks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_catalog_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final CatalogProduk produk = catalogProduks.get(position);
        Log.e("DATA",produk.getId());

        holder.name.setText(produk.getName());
        int priceInt = Integer.parseInt(produk.getPrice());

        Locale locale = new Locale("in","ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(locale);
        holder.price.setText(rupiah.format((double)priceInt));

        Glide.with(this.context)
                .load(produk.getDef_image())
                .into(holder.defImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idProduct = produk.getId();
                String nameforDetail = produk.getName();
                Intent intent = new Intent(new Intent(context, DetailProductActivity.class));
                intent.putExtra("info", produk);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return catalogProduks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView defImage;
        private TextView name,price,pID;

        public ViewHolder(View itemView) {
            super(itemView);
            defImage = itemView.findViewById(R.id.imgItemCatalog);
            name = itemView.findViewById(R.id.tvCatName);
            price = itemView.findViewById(R.id.tvCatPrice);
        }
    }
}
