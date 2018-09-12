package asykurkhamid.dropshop.Adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import asykurkhamid.dropshop.Fragment.UploadProdukFragment;
import asykurkhamid.dropshop.R;

public class ListImageSelectedAdapter extends RecyclerView.Adapter<ListImageSelectedAdapter.ViewHolder>{

    public List<Uri> multiImageSelected;
    public List<String> filDoneList;

    public ListImageSelectedAdapter(List<Uri> multiImageSelected, List<String> filDoneList) {
        this.multiImageSelected = multiImageSelected;
        this.filDoneList = filDoneList;

    }

    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_image,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Uri file = multiImageSelected.get(position);
        ViewHolder vh =(ViewHolder) holder;

        Glide.with(vh.imgProduk.getContext())
                .load(file)
                .into(vh.imgProduk);

    }

    @Override
    public int getItemCount() {
        return (multiImageSelected == null)? 0 : multiImageSelected.size();
    }

    public void delete(int position) { //removes the row
        multiImageSelected.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        private ImageView imgProduk,imgunCheck;
        private TextView tvFileName;
        private RecyclerView rvPhoto;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imgProduk = view.findViewById(R.id.imgProduct);
            imgunCheck = view.findViewById(R.id.imgUncheck);
            tvFileName = view.findViewById(R.id.tvFileName);
            rvPhoto = view.findViewById(R.id.rvSelectedImage);
        }
    }
}
