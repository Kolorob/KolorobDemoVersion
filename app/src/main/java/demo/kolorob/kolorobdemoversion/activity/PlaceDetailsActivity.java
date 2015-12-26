package demo.kolorob.kolorobdemoversion.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.Lg;

/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid
 */
public class PlaceDetailsActivity extends BaseActivity {

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();
    private static final int ANIM_INTERVAL = 100;

    private static double VIEW_WIDTH;
    private ScrollView svCatList;
    private LinearLayout llCatListHolder;
    private LinearLayout llSubCatListHolder;
    private TextView categoryHeader;
    private ImageView categoryHeaderIcon;

    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);
        categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        VIEW_WIDTH = AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC;
        isCatExpandedOnce = false;
        primaryIconWidth = (int) Math.floor(VIEW_WIDTH * 0.80); // 80% of the view width

        svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
        llSubCatListHolder = (LinearLayout) findViewById(R.id.llSubCatListHolder);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        lp.width = (int) (VIEW_WIDTH);

        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivity.this);
        constructCategoryList(categoryTable.getAllCategories());
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        rlSubCatHolder.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Ignoring the device orientation change (always on portrait ensured) :: #HARD_CODED_(:()
        // super.onConfigurationChanged(newConfig);
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList) {
        constructCategoryList(categoryList, 1.0);
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList, double dwPercentage) {
        llCatListHolder.removeAllViews();
        for (CategoryItem ci : categoryList) {
            llCatListHolder.addView(getCategoryListItemView(ci, dwPercentage));

        }
    }

    private View getCategoryListItemView(final CategoryItem ci, double dwPercentage) {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);
        TextView tvName = (TextView) v.findViewById(R.id.tvNameCatList);

        // BE CAREFUL :: Category ID is being mapped as to the icon serial no.
        // in the AppConstants.ALL_CAT_ICONS array
        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        tvName.setText(ci.getCatName());
        tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(ci.getId());
                categoryHeader.setText(ci.getCatName());
                categoryHeaderIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);

                Lg.d(TAG, "Sub-category populated: " + subCatList.size()
                        + ", first item=" + subCatList.get(0));
                if (isCatExpandedOnce)
                    showAnimatedSubcategories(subCatList, .3,AppConstants.ALL_CAT_ICONS[ci.getId() - 1]); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                else
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS[ci.getId() - 1]); //AppConstants.CAT_LIST_LG_WIDTH_PERC);
            }
        });

        return v;
    }
    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList,double dwPercentage) {
        llSubCatListHolder.removeAllViews();
        for (SubCategoryItem si : subCategoryList) {
            llSubCatListHolder.addView(getSubCategoryListItemView(si,dwPercentage));

        }
    }
    private View getSubCategoryListItemView(SubCategoryItem si,double dwPercentage)
    {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.sub_cat_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_sub_cat_icon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        int img_pin = R.drawable.pin_red;
        ivIcon.setImageResource(img_pin);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        tvName.setText(si.getSubCatName());
        tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));
        return v;
    }

    private ArrayList<SubCategoryItem> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE

        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivity.this);
        return subCategoryTable.getAllSubCategories(id);
    }

    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc,int iconId) {
        isCatExpandedOnce = true;
        decCatListWidth(dwPerc);

        // TODO Inflate the sub-category list from right
        final RelativeLayout rlSubCatHolder = (RelativeLayout) findViewById(R.id.rlSubCatHolder);
        if(subCatShowFlag==1)
        {
            rlSubCatHolder.startAnimation(slideOutFromLeftAnim());
        }
        subCatShowFlag=1;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rlSubCatHolder.setVisibility(View.VISIBLE);
                rlSubCatHolder.startAnimation(slideInFromRightAnim());
                constructSubCategoryList(subCatList,1.0);
            }
        }, ANIM_INTERVAL *
                (int) (200 *
                        (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                ));

    }

    private void decCatListWidth(final double dwPerc) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Lg.i(TAG, "decCatListWidth : dwPerc = " + dwPerc);
                if (dwPerc < .6)
                    return;

                // Decrease category-list width
                ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
                lp.width = (int) (VIEW_WIDTH * dwPerc);
                llCatListHolder.setLayoutParams(lp);

                int csz = llCatListHolder.getChildCount();
                for (int i = 0; i < csz; i++) {
                    View v = llCatListHolder.getChildAt(i);
                    ImageView iv = (ImageView) v.findViewById(R.id.ivIconCatList);
                    ViewGroup.LayoutParams lpIv = iv.getLayoutParams();
                    lpIv.width = (int) (primaryIconWidth * dwPerc);
                    iv.setLayoutParams(lpIv);

                    /*TextView tv = (TextView) v.findViewById(R.id.tvNameCatList);
                    tv.setTextSize();*/
                }
                decCatListWidth(dwPerc - 0.05);
            }
        }, ANIM_INTERVAL);
    }

    private Animation slideInFromRightAnim() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f
        );
        inFromRight.setDuration(ANIM_INTERVAL *
                        (int) (200 *
                                (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                        - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                        )
        );
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    private Animation slideOutFromLeftAnim() {
        Animation outToLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +0.95f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outToLeft.setDuration(ANIM_INTERVAL *
                        (int) (200 *
                                (AppConstants.CAT_LIST_LG_WIDTH_PERC
                                        - AppConstants.CAT_LIST_SM_WIDTH_PERC)
                        )
        );
        outToLeft.setInterpolator(new AccelerateInterpolator());
        return outToLeft;
    }
}
