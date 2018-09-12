package asykurkhamid.dropshop.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import asykurkhamid.dropshop.Pojo.AttrProduk;
import asykurkhamid.dropshop.Pojo.DataProduct;
import asykurkhamid.dropshop.R;

public class AttrProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataProduct> attr;

    public AttrProductAdapter(List<DataProduct> attr) {
        this.attr = attr;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attr_product,parent,false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DataProduct color = attr.get(position);
        VH vh = (VH) holder;

        vh.tvColor.setText(color.getColor());
        vh.tvSize.setText(color.getSize());
        vh.tvStock.setText(color.getStock()+" pcs");
    }

    @Override
    public int getItemCount() {
        return attr.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView tvColor,tvSize,tvStock;

        public VH(View itemView) {
            super(itemView);
            tvColor = itemView.findViewById(R.id.tvAttrColor);
            tvSize = itemView.findViewById(R.id.tvAttrSize);
            tvStock = itemView.findViewById(R.id.tvAttrStock);

        }
    }
}
