package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc:这个广播只有在远程设备的uuid需要请求服务协议获取设备接收时才接收
 * 对应的广播通知：ACTION_UUID
 */
public class ServiceUUidFoundListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public ServiceUUidFoundListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {
        if (null != blueEventListener ){
            blueEventListener.onServiceUUidFound();
        }
    }
}
