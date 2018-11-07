package xzzb.com.bluetoothdemo.util;

import android.os.Handler;
import android.widget.Toast;

import xzzb.com.bluetoothdemo.IApplication;

/**
 * create by lei on 2018/11/6/006
 * desc:吐司管理类
 */
public class ToastUtil {


    private static Toast toast;
    private static Handler handler = new Handler();

    private static Runnable run = new Runnable() {
        public void run() {
            toast.cancel();
        }
    };

    private static class SingleInstance{
        private static ToastUtil single = new ToastUtil();
    }

    public ToastUtil() {
    }

    public static ToastUtil g(){
        return SingleInstance.single;
    }

    public void toast(String msg){
        handler.removeCallbacks(run);
        int delay = 1000;
        if (null != toast){
            toast.setText(msg);
        }else {
            toast = Toast.makeText(AppUtil.getContext(),msg,Toast.LENGTH_SHORT);
        }
        handler.postDelayed(run,delay);
        toast.show();
    }

}
