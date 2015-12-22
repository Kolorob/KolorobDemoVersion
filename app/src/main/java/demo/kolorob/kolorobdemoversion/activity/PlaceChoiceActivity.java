package demo.kolorob.kolorobdemoversion.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

public class PlaceChoiceActivity extends BaseActivity implements View.OnClickListener {
    private AnimationDrawable frameAnimation;
    private AnimationDrawable frameAnimation1;

    private LinearLayout boy;
    private LinearLayout girl;
    private LinearLayout shadowBoy;
    private LinearLayout shadowGirl;
    private ImageView kolorobLogo;
    private ImageView bauniaSelection;
    private ImageView view;
    private ImageView view1;
    private ImageView pariseSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_choice);

        kolorobLogo = (ImageView) findViewById(R.id.iv_kolorob_logo);
        bauniaSelection = (ImageView) findViewById(R.id.iv_baunia);
        pariseSelection = (ImageView) findViewById(R.id.iv_parise);

        bauniaSelection.setOnClickListener(this);
        pariseSelection.setOnClickListener(this);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        boy = (LinearLayout) findViewById(R.id.boy);
        girl = (LinearLayout) findViewById(R.id.girl);
        shadowBoy = (LinearLayout) findViewById(R.id.boy_shadow);
        shadowGirl = (LinearLayout) findViewById(R.id.girl_shadow);

        RelativeLayout.LayoutParams kolorob_logo = new RelativeLayout.LayoutParams(width / 3, height / 6);
        RelativeLayout.LayoutParams baunia_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams parise_img = new RelativeLayout.LayoutParams((int) (2 * width / 3.2), (int) (height / 2.5));
        RelativeLayout.LayoutParams boy_layout = new RelativeLayout.LayoutParams(width / 3, height / 2);
        RelativeLayout.LayoutParams girl_layout = new RelativeLayout.LayoutParams(width / 3, height / 2 - height / 15);
        RelativeLayout.LayoutParams boy_shadow = new RelativeLayout.LayoutParams(width / 3, height / 15);
        RelativeLayout.LayoutParams girl_shadow = new RelativeLayout.LayoutParams(width / 3, height / 15);

        kolorob_logo.addRule(RelativeLayout.CENTER_HORIZONTAL);
        kolorob_logo.addRule(RelativeLayout.CENTER_VERTICAL);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        parise_img.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        girl_shadow.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_shadow.addRule(RelativeLayout.CENTER_VERTICAL);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        girl_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_shadow.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        boy_layout.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        int m = height / 2 - height / 15;
        girl_layout.setMargins(0, 0, 0, m);

        boy.setLayoutParams(boy_layout);
        girl.setLayoutParams(girl_layout);
        boy.bringToFront();
        girl.bringToFront();
        shadowBoy.setLayoutParams(boy_shadow);
        shadowGirl.setLayoutParams(girl_shadow);
        kolorobLogo.setLayoutParams(kolorob_logo);
        bauniaSelection.setLayoutParams(baunia_img);
        pariseSelection.setLayoutParams(parise_img);


        //added some animation on Button
        view = (ImageView) findViewById(R.id.iv_baunia);
        view1 = (ImageView) findViewById(R.id.iv_parise);
        view.setBackgroundResource(R.drawable.frame_animation_list);
        view1.setBackgroundResource(R.drawable.frame_animation_list1);
        frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation1 = (AnimationDrawable) view1.getBackground();
        frameAnimation.setOneShot(true);
        frameAnimation1.setOneShot(true);

        bauniaSelection.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                // PRESSED
                                frameAnimation.start();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(PlaceChoiceActivity.this, ButtonClickBaunia.class);
                                        startActivity(intent);
                                    }
                                }, 300);
                                return true; // if you want to handle the touch event
                        }
                        return false;
                    }
                }
        );


        pariseSelection.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        switch (event.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                // PRESSED
                                frameAnimation1.start();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(PlaceChoiceActivity.this, ButtonClickBaunia.class);
                                        startActivity(intent);
                                    }
                                }, 300);
                                return true; // if you want to handle the touch event
                        }
                        return false;
                    }
                }
        );


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.iv_baunia:

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(PlaceChoiceActivity.this, ButtonClickBaunia.class);
//                        startActivity(intent);
//                    }
//                },1000);


                break;
            case R.id.iv_parise:
                frameAnimation1.start();
                gotoPlaceDetailsView(AppConstants.PLACE_BAUNIABADH);
                break;
            default:
                break;

        }

    }

    private void gotoPlaceDetailsView(int placeId) {
        Intent intent = new Intent(PlaceChoiceActivity.this, PlaceDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(AppConstants.KEY_PLACE, placeId);
        startActivity(intent);
    }
}
