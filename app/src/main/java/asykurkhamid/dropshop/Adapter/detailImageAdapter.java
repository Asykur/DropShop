package asykurkhamid.dropshop.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import asykurkhamid.dropshop.Activity.FullImageActivity;
import asykurkhamid.dropshop.R;

public class detailImageAdapter extends PagerAdapter {

    private ArrayList<String> images ;
    private LayoutInflater inflater;
    private Context context;
    private Activity activity;

    public detailImageAdapter(Context context, ArrayList<String> images, Activity activity) {
        this.images = images;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.activity = activity;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final View itemView = inflater.inflate(R.layout.slider_img_detail,container,false);
        ImageView imageView = itemView.findViewById(R.id.imgDetailproduct);
        final String dataImg = images.get(position);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImageActivity.class);
                intent.putStringArrayListExtra(FullImageActivity.TAG_IMGDATA,images);
                intent.putExtra(FullImageActivity.TAG_IMGSELECTED,position);
                ActivityOptionsCompat transition = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        activity,
                        itemView,
                        itemView.getContext().getString(R.string.trans_slider_page));
                activity.startActivityForResult(intent,100,transition.toBundle());

            }
        });

        Glide.with(context)
                .load(images.get(position))
                .into(imageView);
//        imageView.setImageResource(Integer.parseInt(images.get(position)));
        container.addView(itemView,0);
        return itemView;

        // dunlute ndi ?

    }

}
