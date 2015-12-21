package demo.kolorob.kolorobdemoversion.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.kolorob.kolorobdemoversion.R;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;

    LatLng DU = new LatLng(23.7315, 90.3925);
    LatLng BUET = new LatLng(23.7270,90.3929);
    LatLng DMC = new LatLng(23.7256,90.3981);
    LatLng BIRDEM = new LatLng(23.7317108,90.4060514);
    LatLng NEW_MARKET = new LatLng(23.709921,90.407143);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container,
                false);
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        googleMap = mMapView.getMap();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(DU).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition));
        drawMarker(DU,"Dhaka University");
        drawMarker(BUET,"BUET");
        drawMarker(DMC,"Dhaka Medical College");
        drawMarker(NEW_MARKET,"New Market");
        drawMarker(BIRDEM,"BIRDEM Hospital");

        return rootView;
    }
    private void drawMarker(LatLng point,String title){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(point);
        markerOptions.draggable(false);
        markerOptions.title(title);
        googleMap.addMarker(markerOptions).showInfoWindow();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}