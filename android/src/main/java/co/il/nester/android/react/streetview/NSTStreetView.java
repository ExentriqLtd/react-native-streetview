//
//  NSTStreetView.java
//  react-native-streetview
//
//  Created by Amit Palomo on 26/04/2017.
//  Copyright © 2017 Nester.co.il.
//


package co.il.nester.android.react.streetview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.StreetViewPanoramaView;
import com.google.android.gms.maps.model.StreetViewPanoramaLocation;

import android.content.Context;
import android.util.Log;

public class NSTStreetView extends StreetViewPanoramaView implements OnStreetViewPanoramaReadyCallback, StreetViewPanorama.OnStreetViewPanoramaChangeListener {

    private StreetViewPanorama panorama;
    private Boolean allGesturesEnabled = true;
    private LatLng coordinate = null;
    private NSTStreetViewManager.Callback callback;

    public NSTStreetView(Context context) {
        super(context);
        super.onCreate(null);
        super.onResume();
        super.getStreetViewPanoramaAsync(this);
    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

        this.panorama = panorama;
        this.panorama.setOnStreetViewPanoramaChangeListener(this);
        this.panorama.setPanningGesturesEnabled(allGesturesEnabled);
        if (coordinate != null) {
            this.panorama.setPosition(coordinate);
        }
    }

    @Override
    public void onStreetViewPanoramaChange(StreetViewPanoramaLocation location) {
        this.callback.onEvent(location == null || location.links == null);
    }

    public void setAllGesturesEnabled(boolean allGesturesEnabled) {
        // Saving to local variable as panorama may not be ready yet (async)
        this.allGesturesEnabled = allGesturesEnabled;
    }

    public void setCoordinate(ReadableMap coordinate) {
        if (coordinate == null) return;
        Double lng = coordinate.getDouble("longitude");
        Double lat = coordinate.getDouble("latitude");

        // Saving to local variable as panorama may not be ready yet (async)
        this.coordinate = new LatLng(lat, lng);
    }

    public void setCallback(NSTStreetViewManager.Callback callback) {
        this.callback = callback;
    }
}
