package xzzb.com.bluetoothdemo;

import android.app.Application;
import android.content.Context;

import xzzb.com.bluetoothdemo.util.AppUtil;

/**
 * create by lei on 2018/11/6/006
 * desc:
 */
public class IApplication extends Application{

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        AppUtil.setContext(this);
    }

}
