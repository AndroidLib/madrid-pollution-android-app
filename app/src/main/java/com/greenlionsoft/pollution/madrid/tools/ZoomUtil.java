package com.greenlionsoft.pollution.madrid.tools;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

public class ZoomUtil {


    private ZoomUtil() {

    }

    public static void adjustZoomToShowAllLocations(GoogleMap map, List<LatLng> locations) {

        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();

        if (null != locations && !locations.isEmpty() && null != map) {

            for (LatLng location : locations) {
                boundsBuilder.include(location);
            }

            LatLngBounds bounds = boundsBuilder.build();
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));

        }
    }

}
