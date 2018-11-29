package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc: 设备被查询到的监听
 * 对应的广播通知：ACTION_FOUND
 * 当远程的蓝牙设备在扫描阶段被发现的时候就会发送这个广播
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
