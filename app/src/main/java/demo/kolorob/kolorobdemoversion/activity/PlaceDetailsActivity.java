package demo.kolorob.kolorobdemoversion.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;
import demo.kolorob.kolorobdemoversion.utils.AppUtils;

/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid
 */
public class PlaceDetailsActivity extends BaseActivity {

    private static final String TAG = PlaceDetailsActivity.class.getSimpleName();

    private ScrollView svCatList;
    private LinearLayout llCatListHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);
        ViewGroup.LayoutParams lp = llCatListHolder.getLayoutParams();
        lp.width = (int)(AppUtils.getScreenWidth(this) * AppConstants.CAT_LIST_LG_WIDTH_PERC);

        svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivity.this);
        constructCategoryList(categoryTable.getAllCategories());
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList) {
        // constructCategoryList(categoryList, 1.0);
        llCatListHolder.removeAllViews();

        for (CategoryItem ci:categoryList) {
            // Lg.d(TAG, ci.toString());
            llCatListHolder.addView(getCategoryListItemView(ci, 1.0));
        }
    }

    private View getCategoryListItemView(CategoryItem ci, double dwPercentage) {
        LayoutInflater li = LayoutInflater.from(this);
        View v = li.inflate(R.layout.cat_side_list_item, llCatListHolder, false);
        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIconCatList);
        TextView tvName = (TextView) v.findViewById(R.id.tvNameCatList);

        ivIcon.setImageResource(AppConstants.ALL_CAT_ICONS[ci.getId()-1]);

        tvName.setText(ci.getCatName());

        return v;
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList, double dwPercentage) {
        /*llCatListHolder.getChi
        for (CategoryItem ci: categoryList) {
            llCatListHolder.addView();
        }*/
    }
}
