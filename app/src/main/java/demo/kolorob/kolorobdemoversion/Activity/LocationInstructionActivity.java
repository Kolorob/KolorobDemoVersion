package demo.kolorob.kolorobdemoversion.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.kolorob.kolorobdemoversion.R;


public class LocationInstructionActivity extends Activity {

    private ImageButton yes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_instruction);
        yes = (ImageButton) findViewById(R.id.img_btn_ins_yes);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LocationInstructionActivity.this,PlaceChoiceActivity.class);
                startActivity(i);
            }
        });
    }
}
