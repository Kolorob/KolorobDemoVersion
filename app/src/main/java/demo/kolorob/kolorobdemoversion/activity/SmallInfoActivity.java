package demo.kolorob.kolorobdemoversion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;

public class SmallInfoActivity extends Activity {

    private ImageView closeWindow,detailsWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_info);
        closeWindow = (ImageView) findViewById(R.id.iv_close);
        detailsWindow = (ImageView) findViewById(R.id.iv_details);

        closeWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        detailsWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
