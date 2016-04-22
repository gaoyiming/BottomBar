package com.mr_gao.bottombar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr_g on 16/4/21.
 */
public class BottomBarView extends RelativeLayout {
    private static final float SCALEDEFALT = 1;
    private int NAVIGATION_LINE_WIDTH = (int) getResources().getDimension(com.mr_gao.bottombar.R.dimen.bottom_navigation_height);
    private Context context;
    private String bb_active_tvsize;
    private String bb_inactive_tvsize;
    private int itemInactiveColor;
    private FrameLayout container;
    private LinearLayout item;
    private int currentItem = 0;
    private List<View> viewList = new ArrayList<>();
    int navigationWidth = 100;

    private int[] imageResources;
    private int[] colorResources;
    private List<Fragment> fragmentlist;
    private List<BottomNavigationItem> bottomlistlist;
    private int inactive_color;
    private int active_color;
    private String[] textlist;
    private float SCALELEVEL;
    private int bg_clolor;


    public BottomBarView(Context context) {
        this(context, null);

    }


    public BottomBarView(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public BottomBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(attrs);


    }


    /**
     * @param attrs
     */
    private void initView(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BottomBarView);

        bg_clolor = typedArray.getColor(R.styleable.BottomBarView_bb_bg_color, getResources().getColor(R.color.white));
        bb_active_tvsize = typedArray.getString(R.styleable.BottomBarView_bb_active_tvsize);
        bb_inactive_tvsize = typedArray.getString(R.styleable.BottomBarView_bb_inactive_tvsize);
        active_color = typedArray.getColor(R.styleable.BottomBarView_bb_active_color, getResources().getColor(R.color.black));
        inactive_color = typedArray.getColor(R.styleable.BottomBarView_bb_inactive_color, getResources().getColor(R.color.black));
        SCALELEVEL = typedArray.getFloat(R.styleable.BottomBarView_bb_scalelevel, (float) 1.2);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemInactiveColor = ContextCompat.getColor(context, R.color.withoutColoredBackground);
//        ViewGroup.LayoutParams params = getLayoutParams();
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
//        params.height = NAVIGATION_LINE_WIDTH;
//        setLayoutParams(params);
    }

    public void setUpWithView(String[] textlist) {
        setUpWithView(null, textlist);
    }

    public void setUpWithView(int[] imageResources, String[] textlist) {
        setUpWithView(null, imageResources, textlist);
    }

    public void setUpWithView(int[] colorResources, int[] imageResources, String[] textlist) {
        this.textlist = textlist;
        this.colorResources = colorResources;
        this.imageResources = imageResources;

//        if (fragmentlist.size()!= colorResources.length|| fragmentlist.size() != imageResources.length||fragmentlist.size() !=textlist.length)
//            throw new IllegalArgumentException("colorResources , imageResources   must be equal to the ViewPager items : " + fragmentlist.size());
//
//        for (int i = 0; i < textlist.length; i++)
//            addTab(new BottomNavigationItem(textlist[i], colorResources[i], imageResources[i]));

    }

    private void addTab(BottomNavigationItem bottomNavigationItem) {
        bottomlistlist.add(bottomNavigationItem);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        container = new FrameLayout(context);
        LinearLayout items = new LinearLayout(context);
        items.setOrientation(LinearLayout.HORIZONTAL);
          container.setBackgroundColor(bg_clolor);
        ViewGroup.LayoutParams  containerparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < textlist.length; i++) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            item = new LinearLayout(context);
            item.setOrientation(LinearLayout.HORIZONTAL);
            final int index = i;
//            if (!coloredBackground)
//                bottomNavigationItems.get(i).setColor(white);
            int textActivePaddingTop = (int) context.getResources().getDimension(com.mr_gao.bottombar.R.dimen.bottom_navigation_padding_top_active);
            int viewInactivePaddingTop = (int) context.getResources().getDimension(com.mr_gao.bottombar.R.dimen.bottom_navigation_padding_top_inactive);
            int viewInactivePaddingTopWithoutText = (int) context.getResources().getDimension(com.mr_gao.bottombar.R.dimen.bottom_navigation_padding_top_inactive_without_text);
            final View view = inflater.inflate(com.mr_gao.bottombar.R.layout.bottom_navigation, this, false);
            ImageView icon = (ImageView) view.findViewById(com.mr_gao.bottombar.R.id.bottom_navigation_item_icon);
            TextView title = (TextView) view.findViewById(com.mr_gao.bottombar.R.id.bottom_navigation_item_title);
            if (i == 0) {
                title.setTextColor(active_color);
                icon.setColorFilter(active_color);
                icon.setScaleX(SCALELEVEL);
                icon.setScaleY(SCALELEVEL);
                title.setText(textlist[i]);
                icon.setImageResource(imageResources[i]);
            } else {
                title.setTextColor(inactive_color);
                icon.setColorFilter(inactive_color);
                icon.setScaleX(SCALEDEFALT);
                icon.setScaleY(SCALEDEFALT);
                title.setText(textlist[i]);
                icon.setImageResource(imageResources[i]);
            }

            viewList.add(view);
//            icon.setImageResource(bottomNavigationItems.get(i).getImageResource());
//            icon.setColorFilter(i == currentItem ? itemActiveColorWithoutColoredBackground : itemInactiveColor);
            if (i == currentItem) {
//                container.setBackgroundColor(bottomNavigationItems.get(index).getColor());
//                title.setTextColor(currentItem == i ?
//                        itemActiveColorWithoutColoredBackground :
//                        itemInactiveColor);
                icon.setScaleX(SCALELEVEL);
                icon.setScaleY(SCALELEVEL);
            }

//                view.setPadding(view.getPaddingLeft(), i == currentItem ? textActivePaddingTop : withText ? viewInactivePaddingTop : viewInactivePaddingTopWithoutText, view.getPaddingRight(),
            //                       view.getPaddingBottom());
//            title.setTextSize(TypedValue.COMPLEX_UNIT_PX, i == currentItem ?
//                    textActiveSize :
//                    withText ? textInactiveSize : 0);

            LayoutParams itemParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            item.addView(view, itemParams);
            LayoutParams item_params = new LayoutParams(
                    getWidth() / textlist.length, ViewGroup.LayoutParams.MATCH_PARENT);
            items.addView(item, item_params);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBottomNavigationItemClick(index);
                }


            });
        }

        container.addView(items);

        View line = new View(context);
        line.setBackgroundColor(getResources().getColor(R.color.black));
        RelativeLayout.LayoutParams line_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        line_params.addRule(ALIGN_PARENT_TOP);
        addView(line, line_params);


        addView(container, containerparams);


    }




    private void onBottomNavigationItemClick(int index) {
        if (currentItem == index) {
            return;
        }

        for (int i = 0; i < textlist.length; i++) {
            View view = viewList.get(i);
            ImageView icon = (ImageView) view.findViewById(com.mr_gao.bottombar.R.id.bottom_navigation_item_icon);
            TextView title = (TextView) view.findViewById(com.mr_gao.bottombar.R.id.bottom_navigation_item_title);
            if (index == i) {
                title.setTextColor(active_color);
                icon.setColorFilter(active_color);
                scaleView(title, SCALELEVEL);
                scaleView(icon, SCALELEVEL);
            } else if (currentItem == i) {
                title.setTextColor(inactive_color);
                icon.setColorFilter(inactive_color);
                scaleView(title, SCALEDEFALT);
                scaleView(icon, SCALELEVEL);
                icon.setScaleX(SCALEDEFALT);
                icon.setScaleY(SCALEDEFALT);
            }

        }
        currentItem = index;

    }

    private void scaleView(View view, float scalelevel) {
        view.setScaleX( scalelevel);
        view.setScaleY( scalelevel);

    }
}