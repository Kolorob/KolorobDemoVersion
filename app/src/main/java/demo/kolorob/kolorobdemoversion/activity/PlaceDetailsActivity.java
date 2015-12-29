package demo.kolorob.kolorobdemoversion.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;

import java.util.ArrayList;
import java.util.Collections;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.EducationServiceProviderTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;
import demo.kolorob.kolorobdemoversion.utils.Lg;
import demo.kolorob.kolorobdemoversion.fragment.MapFragment;
/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid
 */
public class PlaceDetailsActivity extends BaseActivity  implements View.OnClickListener {

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();
    private static final int ANIM_INTERVAL = 100;

    private static double VIEW_WIDTH;
    private ScrollView svCatList;
    private LinearLayout llCatListHolder;
    private LinearLayout llSubCatListHolder;
    private FrameLayout placeDetailsLayout;
    private TextView categoryHeader;
    private ImageView categoryHeaderIcon;
    private TextView subCatItemListHeader;
    private ListView subCatItemList;
    private Button showSubCatListItem;
    private Button seeMap;
    private static TextView insSubCat;
    private static FrameLayout map;

    private boolean isCatExpandedOnce = false;
    private int primaryIconWidth;
    private int subCatShowFlag=0;
    private int locationNameId;
    private String locationName;

    private ArrayList<EducationServiceProviderItem> currentEducationServiceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        Intent intent = getIntent();
        if (null != intent)
        {
            locationNameId = intent.getIntExtra(AppConstants.KEY_PLACE,0);
            if(locationNameId==AppConstants.PLACE_BAUNIABADH)
            {
                locationName = AppConstants.BAUNIABADH;
            }
            else if(locationNameId==AppConstants.PLACE_PARIS_ROAD)
            {
                locationName = AppConstants.PARIS_ROAD;
            }
        }


        categoryHeader = (TextView) findViewById(R.id.tv_cat_name);
        categoryHeaderIcon = (ImageView) findViewById(R.id.ivHeadCatIconSubCatList);
        placeDetailsLayout = (FrameLayout) findViewById(R.id.place_details_layout);
        if(locationNameId==AppConstants.PLACE_BAUNIABADH)
        {
            placeDetailsLayout.setBackgroundResource(R.drawable.place_details_bg_baunia);
        }
        else if(locationNameId==AppConstants.PLACE_PARIS_ROAD)
        {
            placeDetailsLayout.setBackgroundResource(R.drawable.place_details_bg);
        }
        subCatItemListHeader = (TextView) findViewById(R.id.tv_sub_cat_item_list_head);
        subCatItemList = (ListView) findViewById(R.id.sub_cat_item_list);
        map = (FrameLayout) findViewById(R.id.map_fragment);
        insSubCat = (TextView) findViewById(R.id.tvInstructionSubCat);
        seeMap = (Button) findViewById(R.id.btn_see_map);
        showSubCatListItem = (Button) findViewById(R.id.btn_show_sub_cat_list_item);
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

        showSubCatListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subCatItemList.setVisibility(View.VISIBLE);
                subCatItemListHeader.setVisibility(View.VISIBLE);
                showSubCatListItem.setVisibility(View.GONE);
                seeMap.setVisibility(View.VISIBLE);
            }
        });
        seeMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subCatItemList.setVisibility(View.GONE);
                subCatItemListHeader.setVisibility(View.GONE);
                showSubCatListItem.setVisibility(View.VISIBLE);
                seeMap.setVisibility(View.GONE);
            }
        });

        callMapFragment();
        subCatItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EducationServiceProviderItem currEduItem=null;
                int i = 0;
                for(EducationServiceProviderItem et : currentEducationServiceProvider)
                {
                    if(i==position)
                    {
                        currEduItem = et;
                    }
                }
                Intent ii = new Intent(PlaceDetailsActivity.this,DetailsInfoActivity.class);
                ii.putExtra(AppConstants.KEY_DETAILS_VIEW,currEduItem);
                startActivity(ii);
            }
        });
    }
    public static void subCatInsGone()
    {
        insSubCat.setVisibility(View.GONE);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.sub_cat_item_list :

                break;

            default:
                break;

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // Ignoring the device orientation change (always on portrait ensured) :: #HARD_CODED_(:()
        // super.onConfigurationChanged(newConfig);
    }


    private void callMapFragment()
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText("");
        mapFragment.setCategoryId(0);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
    }

    private void callMapFragmentWithInfo(String item_name,int cat_id,ArrayList<EducationServiceProviderItem> educationServiceProvider)
    {
        MapFragment mapFragment = new MapFragment();
        mapFragment.setLocationName(locationName);
        mapFragment.setMapIndicatorText(item_name);
        mapFragment.setCategoryId(cat_id);
        mapFragment.setEducationServiceProvider(educationServiceProvider);
        mapFragment.setLocationNameId(locationNameId);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.map_fragment, mapFragment);
        fragmentTransaction.commit();
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
                showSubCatListItem.setEnabled(false);
                showSubCatListItem.setVisibility(View.VISIBLE);
                subCatItemList.setVisibility(View.GONE);
                subCatItemListHeader.setVisibility(View.GONE);
                insSubCat.setVisibility(View.VISIBLE);
                seeMap.setVisibility(View.GONE);
                ArrayList<EducationServiceProviderItem> educationServiceProvider;
                educationServiceProvider = constructSubCategoryListItem(ci.getId());
                callMapFragmentWithInfo(ci.getCatName(),ci.getId(),educationServiceProvider);

                ArrayList<SubCategoryItem> subCatList = getSubCategoryList(ci.getId());
                placeDetailsLayout.setBackgroundResource(R.drawable.cool_crash_ui_backdrop_v2);
                categoryHeader.setText(ci.getCatName());
                categoryHeaderIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId() - 1]);

                Lg.d(TAG, "Sub-category populated: " + subCatList.size()
                        + ", first item=" + subCatList.get(0));
                if (isCatExpandedOnce)
                    showAnimatedSubcategories(subCatList, .3,AppConstants.ALL_CAT_ICONS[ci.getId() - 1],ci.getId()); // AppConstants.CAT_LIST_SM_WIDTH_PERC);
                else
                    showAnimatedSubcategories(subCatList, 1.0, AppConstants.ALL_CAT_ICONS[ci.getId() - 1],ci.getId()); //AppConstants.CAT_LIST_LG_WIDTH_PERC);
            }
        });

        return v;
    }
    public void constructSubCategoryItemList(int cat_id,int sub_cat_id)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        educationServiceProvider = constructSubCategoryListItem(cat_id,sub_cat_id);
        ArrayList<String> itemName = new ArrayList<String>();
        currentEducationServiceProvider = educationServiceProvider;
        for(EducationServiceProviderItem et : educationServiceProvider)
        {
            itemName.add(et.getEduNameEng());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PlaceDetailsActivity.this, R.layout.sub_cat_item_list_item,R.id.textView5, itemName);
        subCatItemList.setAdapter(adapter);
    }
    private ArrayList<EducationServiceProviderItem> constructSubCategoryListItem(int cat_id,int sub_cat_id)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivity.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfo(cat_id,sub_cat_id);
        return educationServiceProvider;
    }
    private ArrayList<EducationServiceProviderItem> constructSubCategoryListItem(int cat_id)
    {
        ArrayList<EducationServiceProviderItem> educationServiceProvider;
        EducationServiceProviderTable educationServiceProviderTable = new EducationServiceProviderTable(PlaceDetailsActivity.this);
        educationServiceProvider = educationServiceProviderTable.getAllEducationSubCategoriesInfo(cat_id);
        return educationServiceProvider;
    }
    private void constructSubCategoryList(ArrayList<SubCategoryItem> subCategoryList,double dwPercentage,int cat_id) {
        llSubCatListHolder.removeAllViews();
        for (SubCategoryItem si : subCategoryList) {
            llSubCatListHolder.addView(getSubCategoryListItemView(si,dwPercentage,cat_id));

        }
    }
    private View getSubCategoryListItemView(final SubCategoryItem si, double dwPercentage, final int cat_id)
    {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.sub_cat_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.iv_sub_cat_icon);
        TextView tvName = (TextView) v.findViewById(R.id.tv_sub_cat_name);
        ivIcon.setImageResource(AppConstants.ALL_CAT_MARKER_ICONS[cat_id-1]);
        ViewGroup.LayoutParams lpIv = ivIcon.getLayoutParams();
        lpIv.width = (int) (primaryIconWidth * dwPercentage);
        ivIcon.setLayoutParams(lpIv);

        tvName.setText(si.getSubCatName());
        tvName.setTextSize((float) (VIEW_WIDTH * .10 * dwPercentage));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EducationServiceProviderItem> educationServiceProvider;
                educationServiceProvider = constructSubCategoryListItem(cat_id,si.getId());
                showSubCatListItem.setEnabled(true);
                callMapFragmentWithInfo(si.getSubCatName(),cat_id,educationServiceProvider);
                subCatItemListHeader.setText(si.getSubCatName());
                constructSubCategoryItemList(cat_id,si.getId());
            }
        });

        return v;
    }

    private ArrayList<SubCategoryItem> getSubCategoryList(int id) {
        // TODO Get sub-categories from the SUB_CATEGORY local table : NEXT PHASE

        SubCategoryTable subCategoryTable = new SubCategoryTable(PlaceDetailsActivity.this);
        return subCategoryTable.getAllSubCategories(id);
    }

    private void showAnimatedSubcategories(final ArrayList<SubCategoryItem> subCatList, double dwPerc, int iconId, final int cat_id) {
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
                constructSubCategoryList(subCatList,1.0,cat_id);
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
