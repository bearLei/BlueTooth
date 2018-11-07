package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc: 设备被查询到的监听
 */
public class DeviceFoundListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public DeviceFoundListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {
        if (null != device && null != blueEventListener){
            blueEventListener.onDeviceAdded(device);
        }
    }
}
