package asykurkhamid.dropshop.Adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import asykurkhamid.dropshop.R;

public class SliderAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context mContext;

    public SliderAdapter(Context mContext,ArrayList<Integer> images) {
        this.images = images;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public void destroyItem( ViewGroup container, int position, Object object) {
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
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.slider,container,false);
        ImageView imageView = (ImageView) imageLayout.findViewById(R.id.imgSlider);
        imageView.setImageResource(images.get(position));
        container.addView(imageLayout,0);
        return imageLayout;
    }
}
