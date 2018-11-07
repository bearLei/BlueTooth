package xzzb.com.bluetoothdemo.util;

import android.content.Context;

/**
 * create by lei on 2018/11/6/006
 * desc:
 */
public class AppUtil {

    private static Context mContext;


    public static void setContext(Context context){
        mContext = context;
    }

    public static Context getContext(){
        return mContext;
    }
}
