package com.frink.hackathon.homescreen;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.frink.hackathon.R;

/**
 * Created by amandeepsingh on 16/10/15.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LayoutInflater inflater;
    public static int totalPage = 3;

    public ViewPagerAdapter(Context context) {

        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final View customView = inflater
                .inflate(R.layout.view_pager_layout, container, false);
        int imageId;
        if (position == 0) {
            imageId = R.drawable.one;
        } else if (position == 1) {
            imageId = R.drawable.two;
        } else {
            imageId = R.drawable.three;
        }
        Holder holder = new Holder(customView);
        holder.image.setImageDrawable(context.getResources().getDrawable(imageId));
        container.addView(customView);
        return customView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }


    @Override
    public int getCount() {
        return totalPage;
    }

    class Holder {
        private ImageView image;

        public Holder(View customView) {
            this.image = (ImageView) customView.findViewById(R.id.image);
        }
    }

    static public class FadePageTransformer implements ViewPager.PageTransformer {
        public void transformPage(View view, float position) {
            view.setTranslationX(view.getWidth() * -position);

            if (position <= -1.0F || position >= 1.0F) {
                view.setAlpha(0.0F);
            } else if (position == 0.0F) {
                view.setAlpha(1.0F);
            } else {
// position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setAlpha(1.0F - Math.abs(position));
            }
        }
    }
}



























