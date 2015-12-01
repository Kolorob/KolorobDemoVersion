package demo.kolorob.kolorobdemoversion.Activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;

public class PlaceChoiceActivity extends Activity {

    private LinearLayout boy;
    private LinearLayout girl;
    private LinearLayout shadowBoy;
    private LinearLayout shadowGirl;
    private ImageView kolorobLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);

        kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        boy = (LinearLayout)findViewById(R.id.boy);
        girl = (LinearLayout)findViewById(R.id.girl);
        shadowBoy = (LinearLayout)findViewById(R.id.boy_shadow);
        shadowGirl = (LinearLayout)findViewById(R.id.girl_shadow);
        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width/3, height/6);
        RelativeLayout.LayoutParams boy_layout=new RelativeLayout.LayoutParams(width/3, height/2);
        RelativeLayout.LayoutParams girl_layout=new RelativeLayout.LayoutParams(width/3, height/2 - height/15);
        RelativeLayout.LayoutParams boy_shadow=new RelativeLayout.LayoutParams(width/3,  height/15);
        RelativeLayout.LayoutParams girl_shadow=new RelativeLayout.LayoutParams(width/3, height/15);

        kolorob_logo.addRule(RelativeLayout.CENTER_HORIZONTAL);
        kolorob_logo.addRule(RelativeLayout.CENTER_VERTICAL);
        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_shadow.addRule(RelativeLayout.CENTER_VERTICAL);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        int m = height/2 - height/15;
        girl_layout.setMargins(0,0,0,m);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);
        boy.bringToFront();
        girl.bringToFront();
        shadowBoy.setLayoutParams(boy_shadow);
        shadowGirl.setLayoutParams(girl_shadow);
        kolorobLogo.setLayoutParams(kolorob_logo);

    }
}
