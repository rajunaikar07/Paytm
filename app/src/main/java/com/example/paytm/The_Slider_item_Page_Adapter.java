package com.example.paytm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class The_Slider_item_Page_Adapter extends PagerAdapter {
    private Context Mcontext;
    private List<The_Slider_Item_Model_Class>the_slider_tem_odel_classList;

    public The_Slider_item_Page_Adapter(Context context, List<The_Slider_Item_Model_Class> the_slider_tem_odel_classList) {
        this.Mcontext = context;
        this.the_slider_tem_odel_classList = the_slider_tem_odel_classList;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,int position){
        LayoutInflater inflater=(LayoutInflater) Mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout=inflater.inflate(R.layout.text,null);
        ImageView featured_image=sliderLayout.findViewById(R.id.my_featured_image);
//        TextView caption_title=sliderLayout.findViewById(R.id.my_caption_title);
        featured_image.setImageResource(the_slider_tem_odel_classList.get(position).getFeatured_image());
//        caption_title.setText(the_slider_tem_odel_classList.get(position).getThe_caption_title());
        container.addView(sliderLayout);
        return sliderLayout;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container,int position,@NonNull Object object){
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return the_slider_tem_odel_classList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }
}
