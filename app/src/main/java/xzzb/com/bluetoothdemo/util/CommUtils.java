package xzzb.com.bluetoothdemo.util;


import android.content.Context;
import android.location.LocationManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CommUtils {

    public static boolean isLocationOpen(final Context context){
        LocationManager manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        //gps定位
        boolean isGpsProvider = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        //网络定位
        boolean isNetWorkProvider = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return isGpsProvider|| isNetWorkProvider;
    }



}
