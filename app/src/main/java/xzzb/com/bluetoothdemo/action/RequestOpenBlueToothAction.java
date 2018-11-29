package xzzb.com.bluetoothdemo.action;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;

import xzzb.com.bluetoothdemo.contanst.BlueToothConstanst;
import xzzb.com.bluetoothdemo.util.LockManager;

/**
 * create by lei on 2018/11/6/006
 * desc:询问打开蓝牙
 */
public class RequestOpenBlueToothAction extends BaseAction {

    private boolean isOpened;//蓝牙是否打开
    /**
     * 请求打开蓝牙
     */
    public boolean requestOpenBlueTooth(Activity activity){
        BluetoothAdapter blueAdapter = BluetoothAdapter.getDefaultAdapter();
        if (blueAdapter != null
                && blueAdapter.isEnabled()){
            //蓝牙已经是开启状态 直接返回ture
            isOpened = true;
            return isOpened;
        }
        if (blueAdapter != null && blueAdapter.enable()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(intent, BlueToothConstanst.Res_ACTION_REQUEST_ENABLE);
            waitAction();
        }
        return isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public void waitAction() {
        super.waitAction();
        LockManager.getInstance()._wait(RequestOpenBlueToothAction.class,Default_TimeOut);
    }

    @Override
    public void notifyAction() {
        super.notifyAction();
        LockManager.getInstance()._notify(RequestOpenBlueToothAction.class);
    }
}
