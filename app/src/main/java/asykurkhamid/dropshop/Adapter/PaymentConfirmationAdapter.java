package asykurkhamid.dropshop.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.zip.Inflater;

import asykurkhamid.dropshop.Activity.DetailPaymentConfActivity;
import asykurkhamid.dropshop.Pojo.DataPaymentConfirmation;
import asykurkhamid.dropshop.R;

public class PaymentConfirmationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<DataPaymentConfirmation> data;
    Context context;
    private int uid;
    private DatabaseReference trxRef;
    private String trxID;

    public PaymentConfirmationAdapter(ArrayList<DataPaymentConfirmation> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_payment_conf,parent,false);
        return new VHPaymentList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final DataPaymentConfirmation dataPay = data.get(position);
        final VHPaymentList vh = (VHPaymentList) holder;
        vh.tvID.setText(dataPay.getID());
        vh.tvFrom.setText(dataPay.getSenderName());

        trxRef = FirebaseDatabase.getInstance().getReference().child("Payment_Confirmation");
        vh.cvList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailPaymentConfActivity.class);
                intent.putExtra("payment",(Parcelable) dataPay);
                v.getContext().startActivity(intent);
            }
        });

        vh.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = position;
                trxID = dataPay.getID();
                data.get(vh.getAdapterPosition());
                popUpMenu(((VHPaymentList)holder).imgMenu, id, trxID);
            }
        });
    }

    private void popUpMenu(View view, int arg,String... trxID) {
        uid = arg;
        PopupMenu popupMenu = new PopupMenu(view.getContext(),view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.conf_payment_list,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new menuClickListener());
        popupMenu.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private class menuClickListener implements PopupMenu.OnMenuItemClickListener{

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.menuHapus:
                    removeItem(uid,trxID);
            }
            return false;
        }
    }
    public void removeItem(int position,String trxID) {
        data.remove(position);
        notifyItemRemoved(position);

        trxRef.child(trxID).removeValue();
        notifyDataSetChanged();

    }

    public class VHPaymentList extends RecyclerView.ViewHolder{

        private CardView cvList;
        private TextView tvID;
        private TextView tvFrom;
        private ImageView imgMenu;

        public VHPaymentList(View itemView) {
            super(itemView);
            cvList = itemView.findViewById(R.id.cardConfPayment);
            tvID = itemView.findViewById(R.id.tvIdPayment);
            tvFrom = itemView.findViewById(R.id.tvBuyer);
            imgMenu = itemView.findViewById(R.id.menuDot);
        }
    }
}
