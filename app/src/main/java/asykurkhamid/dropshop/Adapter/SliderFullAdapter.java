package asykurkhamid.dropshop.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import java.io.File;
import java.util.Collections;
import java.util.List;

import asykurkhamid.dropshop.R;

public class SliderFullAdapter extends PagerAdapter {
    private LayoutInflater mLayoutInflater;
    private List<String> mResources;
    private Context context;

    public SliderFullAdapter(Context context, List<String> mResources) {
        this.mResources = mResources;
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.item_full_image_slider, container, false);
        container.addView(itemView);
        List<String> data = Collections.singletonList(mResources.get(position));

        ImageView imageView = itemView.findViewById(R.id.imgViewFull);

            Glide.with(context)
                    .load(mResources.get(position))
                    .into(imageView);

        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
