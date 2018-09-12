package asykurkhamid.dropshop.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import asykurkhamid.dropshop.Activity.CartActivity;
import asykurkhamid.dropshop.Pojo.DataTransaction;
import asykurkhamid.dropshop.R;
import asykurkhamid.dropshop.SQLite.DBHelper;
import asykurkhamid.dropshop.SQLite.DbExecuteLogic;

public class CartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ArrayList<DataTransaction> data;
    Context context;
    DBHelper dbHelper;
    DatabaseReference trxRef;
    DatabaseReference checkTrxRef;
    String UID,productID;

    public CartAdapter(ArrayList<DataTransaction> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_product2, parent, false);
        dbHelper = new DBHelper(context);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final DataTransaction item = data.get(position);
        final VH vh = (VH) holder;
        String size = null;
        UID = item.getUserID();
        productID = item.getProductID();

        vh.name.setText(item.getName());
        if (item.getSize().equalsIgnoreCase("SS")) {
            size = "Super Small (SS)";
        } else if (item.getSize().equalsIgnoreCase("S")) {
            size = "Small (S)";
        } else if (item.getSize().equalsIgnoreCase("M")) {
            size = "Medium (M)";
        } else if (item.getSize().equalsIgnoreCase("L")) {
            size = "Large (L)";
        } else if (item.getSize().equalsIgnoreCase("XL")) {
            size = "Extra Large (XL)";
        }

        Locale locale = new Locale("in", "ID");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(locale);

        vh.size.setText(size);
        vh.color.setText(item.getColor());
        vh.subTotal.setText(rupiah.format((double)item.getSubTotal()));
        vh.order.setText(item.getQty()+"pcs");

        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                AlertDialog dialog;
                builder.setCancelable(false);
                builder.setMessage("Lanjutkan hapus produk ini?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        trxRef = FirebaseDatabase.getInstance().getReference().child("Data_Transaction").child(UID).child(productID);
                        data.remove(position);
                        notifyItemRemoved(position);
                        DbExecuteLogic.deleteProduk(dbHelper, item.getProductID());
                        trxRef.removeValue();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog = builder.create();
                dialog.show();
            }
        });

        Glide.with(vh.image.getContext())
                .load(item.getImage())
                .into(vh.image);

        DbExecuteLogic.cekID(dbHelper,item.getProductID(),item.getQty(), Integer.parseInt(item.getUnitPrice()),item.getSubTotal());
        CartActivity.gtTtl();

    }

    private void showAlert(int posisi, DataTransaction items) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        TextView name, color, size, subTotal, price, order;
        ImageView image;
        ImageButton edit, delete;
        Spinner spSatuan, spGrosir;

        public VH(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvCartName);
            color = itemView.findViewById(R.id.tvCartColor);
            size = itemView.findViewById(R.id.tvCartSize);
            subTotal = itemView.findViewById(R.id.tvSubTotal);
            image = itemView.findViewById(R.id.imgCartItem);
            edit = itemView.findViewById(R.id.btnEditOrder);
            delete = itemView.findViewById(R.id.btnDeleteOrder);
            price = itemView.findViewById(R.id.tvSubTotal);
            spSatuan = itemView.findViewById(R.id.spSatuan);
            spGrosir = itemView.findViewById(R.id.spGrosir);
            order = itemView.findViewById(R.id.tvJmlOrder);
        }
    }
}
