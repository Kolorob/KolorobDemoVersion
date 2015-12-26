package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.database.CategoryTable;
import demo.kolorob.kolorobdemoversion.database.SubCategoryTable;
import demo.kolorob.kolorobdemoversion.interfaces.VolleyApiCallback;
import demo.kolorob.kolorobdemoversion.model.CategoryItem;
import demo.kolorob.kolorobdemoversion.model.SubCategoryItem;
import demo.kolorob.kolorobdemoversion.parser.VolleyApiParser;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;


public class OpeningActivity extends BaseActivity {

    private final static int SPLASH_TIME_OUT = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);

        ImageView kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        LinearLayout boy = (LinearLayout) findViewById(R.id.boy);
        LinearLayout girl = (LinearLayout) findViewById(R.id.girl);
        LinearLayout shadowBoy = (LinearLayout) findViewById(R.id.shadow_boy);
        LinearLayout shadowGirl = (LinearLayout) findViewById(R.id.shadow_girl);


        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width, height / 3);
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 2, (2 * height) / 3 - height / 15);
        RelativeLayout.LayoutParams boy_shadow = new RelativeLayout.LayoutParams(width / 2, height / 12);
        RelativeLayout.LayoutParams girl_shadow = new RelativeLayout.LayoutParams(width / 2, height / 12);

        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);

        boy.bringToFront();
        girl.bringToFront();

        shadowBoy.setLayoutParams(boy_shadow);
        shadowGirl.setLayoutParams(girl_shadow);

        kolorob_logo.setMargins(0, 15, 0, 0);
        kolorobLogo.setLayoutParams(kolorob_logo);

        VolleyApiParser.getRequest(OpeningActivity.this, "get_categories", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        if (status == AppConstants.SUCCESS_CODE) {
                            try {
                                JSONObject jo = new JSONObject(apiContent);
                                String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                    saveCategoryList(jo.getJSONArray(AppConstants.KEY_DATA));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        VolleyApiParser.getRequest(OpeningActivity.this, "get_sub_categories", new VolleyApiCallback() {
                    @Override
                    public void onResponse(int status, String apiContent) {
                        if (status == AppConstants.SUCCESS_CODE) {
                            try {
                                JSONObject jo = new JSONObject(apiContent);
                                String apiSt = jo.getString(AppConstants.KEY_STATUS);
                                if (apiSt.equals(AppConstants.KEY_SUCCESS))
                                    saveSubCategoryList(jo.getJSONArray(AppConstants.KEY_DATA));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your next activity
                Intent i = new Intent(OpeningActivity.this, LocationAskActivity.class);
                startActivity(i);
            }
        }, SPLASH_TIME_OUT);

    }

    /** Written by : Touhid */
    private void saveCategoryList(JSONArray categoryArray) {
        CategoryTable catTable = new CategoryTable(OpeningActivity.this);
        int catCount = categoryArray.length();
        for (int i = 0; i < catCount; i++) {
            try {
                JSONObject jo = categoryArray.getJSONObject(i);
                CategoryItem ci = CategoryItem.parseCategoryItem(jo);
                catTable.insertItem(ci);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSubCategoryList(JSONArray subCategoryArray) {
        SubCategoryTable subCatTable = new SubCategoryTable(OpeningActivity.this);
        int subCatCount = subCategoryArray.length();
        for (int i = 0; i < subCatCount; i++) {
            try {
                JSONObject jo = subCategoryArray.getJSONObject(i);
                SubCategoryItem si = SubCategoryItem.parseSubCategoryItem(jo);
                subCatTable.insertItem(si);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
