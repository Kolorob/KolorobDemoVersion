package demo.kolorob.kolorobdemoversion.helpers;

import android.content.Context;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.R;
import demo.kolorob.kolorobdemoversion.model.Education.EducationServiceProviderItem;
import demo.kolorob.kolorobdemoversion.utils.AppConstants;

/**
 * Created by Yeakub Hassan Rafi on 26-Dec-15.
 */
public class MapInfoWindowAdapter implements InfoWindowAdapter {

    private final View myContentsView ;
    private static LayoutInflater inflater=null;
    private ArrayList<EducationServiceProviderItem> educationServiceProvider;
    private TextView itemName;
    private TextView itemAddress;
    private TextView itemType;
    private TextView itemContact;
    Context c;
    ImageView close;
    public MapInfoWindowAdapter(final Context context,ArrayList<EducationServiceProviderItem> et){
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        c=context;
        educationServiceProvider = et;
        myContentsView = inflater.inflate(R.layout.small_info, null);
        myContentsView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        close = (ImageView) myContentsView.findViewById(R.id.iv_close);
        itemName = (TextView) myContentsView.findViewById(R.id.tv_heading);
        itemAddress = (TextView) myContentsView.findViewById(R.id.tv_item_location);
        itemType = (TextView) myContentsView.findViewById(R.id.tv_item_type);
        itemContact = (TextView) myContentsView.findViewById(R.id.tv_item_contact);

    }



    @Override
    public View getInfoContents(Marker marker) {
        LatLng loc = marker.getPosition();

       for(EducationServiceProviderItem et:educationServiceProvider)
        {
            Double lat = Double.parseDouble(et.getLatitude());
            Double lon = Double.parseDouble(et.getLongitude());
            System.out.println(lat +"  "+loc.latitude);
            if(loc.latitude== lat && loc.longitude==lon)
            {
                itemName.setText(et.getEduNameEng());
                itemAddress.setText("ঠিকানা ঃ  "+AppConstants.BAUNIABADH);
                itemType.setText("ধরন ঃ  "+et.getEduType());
                itemContact.setText("যোগাযোগের উপায় ঃ  " + et.getContactNo());
            }
        }
        return myContentsView;
    }


    @Override
    public View getInfoWindow(Marker marker) {
        // TODO Auto-generated method stub
        return null;
    }

}
