package demo.kolorob.kolorobdemoversion.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by touhid on 12/3/15.
 *
 * @author touhid
 */
public class PlaceDetailsActivity extends BaseActivity {

    private ScrollView svCatList;
    private LinearLayout llCatListHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);

        svCatList = (ScrollView) findViewById(R.id.svCategoryListHolder);
        llCatListHolder = (LinearLayout) findViewById(R.id.llCategoryListHolder);

        CategoryTable categoryTable = new CategoryTable(PlaceDetailsActivity.this);
        constructCategoryList(categoryTable.getAllCategories());
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList) {
        // constructCategoryList(categoryList, 1.0);
        llCatListHolder.removeAllViews();
        for (CategoryItem ci:categoryList) {
            llCatListHolder.addView(getCategoryListItemView(ci));
        }
    }

    private View getCategoryListItemView(CategoryItem ci) {
        return null;
    }

    private void constructCategoryList(ArrayList<CategoryItem> categoryList, double dwPercentage) {
        /*llCatListHolder.getChi
        for (CategoryItem ci: categoryList) {
            llCatListHolder.addView();
        }*/
    }

    /*private ArrayList<CategoryItem> getCategoryList() {
        ArrayList<CategoryItem> catList = new ArrayList<>();
        int l = AppConstants.ALL_CAT_BN.length;
        for (int i = 0; i < l; i++) {
            catList.add(
                    new CategoryItem(
                            AppConstants.ALL_CAT_ICONS[i],
                            AppConstants.ALL_CAT_BN[i],
                            AppConstants.ALL_CAT_DETAIL_BN[i]
                    )
            );
        }
        return catList;
    }*/
}
