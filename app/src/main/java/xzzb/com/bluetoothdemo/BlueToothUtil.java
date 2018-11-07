package xzzb.com.bluetoothdemo;


import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import xzzb.com.bluetoothdemo.action.RequestOpenBlueToothAction;
import xzzb.com.bluetoothdemo.contanst.BlueToothConstanst;
import xzzb.com.bluetoothdemo.util.LockManager;
import xzzb.com.bluetoothdemo.util.ToastUtil;

/**
 * 蓝牙管理类
 */
public class BlueToothUtil {




    private RequestOpenBlueToothAction mRequestOpenBlueToothAction;//授权打开蓝牙

    private static class SingleInstance{
        private static final BlueToothUtil single = new BlueToothUtil();
    }

    public BlueToothUtil() {
    }

    public static BlueToothUtil g(){
        return SingleInstance.single;
    }


    /**
     * 获取系统蓝牙管理类
     */
    private BluetoothAdapter getBlueAdapter(){
        return BluetoothAdapter.getDefaultAdapter();
    }


    /**
     * 搜索设备
     * @param timeOut 超时时间
     */
    public boolean searchDevice(Activity activity,int timeOut ){
        mRequestOpenBlueToothAction = new RequestOpenBlueToothAction();
        boolean isEnable = mRequestOpenBlueToothAction.requestOpenBlueTooth(activity);//蓝牙是否开启成功
        if (!isEnable){
            ToastUtil.g().toast("请开启蓝牙");
            return false;
        }

        BluetoothAdapter blueAdapter = getBlueAdapter();



        return true;
    }




    /**
     * 搜索回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case BlueToothConstanst.Res_ACTION_REQUEST_ENABLE://蓝牙授权
                if (resultCode == Activity.RESULT_OK){
                    if (mRequestOpenBlueToothAction != null){
                        mRequestOpenBlueToothAction.setOpened(true);
                    }
                    mRequestOpenBlueToothAction.notifyAction();
                    ToastUtil.g().toast("蓝牙开启");
                }else {
                    if (mRequestOpenBlueToothAction != null){
                        mRequestOpenBlueToothAction.setOpened(false);
                    }
                    ToastUtil.g().toast("蓝牙开启失败");
                    mRequestOpenBlueToothAction.notifyAction();
                }
                break;
        }
    }


}
