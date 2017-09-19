//
//  NSTStreetViewManager.java
//  react-native-streetview
//
//  Created by Amit Palomo on 26/04/2017.
//  Copyright © 2017 Nester.co.il.
//

package co.il.nester.android.react.streetview;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Map;

public class NSTStreetViewManager extends SimpleViewManager<NSTStreetView> {

    public static final String REACT_CLASS = "NSTStreetView";
    private RCTEventEmitter eventEmitter;
    private ThemedReactContext themedReactContext;

    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected NSTStreetView createViewInstance(ThemedReactContext themedReactContext) {
        this.themedReactContext = themedReactContext;
        this.eventEmitter = themedReactContext.getJSModule(RCTEventEmitter.class);
        final NSTStreetView streetView = new NSTStreetView(themedReactContext);
        streetView.setCallback(new Callback() {
            @Override
            public void onEvent(boolean invalid) {
                final WritableMap event = Arguments.createMap();
                event.putBoolean("invalid", invalid);
                NSTStreetViewManager.this.eventEmitter.receiveEvent(
                        streetView.getId(),
                        "topChange",
                        event);

            }
        });
        return streetView;
    }

    @ReactProp(name = "allGesturesEnabled", defaultBoolean = false)
    public void setAllGesturesEnabled(NSTStreetView view, boolean allGesturesEnabled) {
        view.setAllGesturesEnabled(allGesturesEnabled);
    }

    @ReactProp(name = "coordinate")
    public void setCoordinate(NSTStreetView view, ReadableMap coordinate) {
        view.setCoordinate(coordinate);
    }

    public interface Callback {
        void onEvent(boolean invalid);
    }
}
