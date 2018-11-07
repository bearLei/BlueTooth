package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc: 扫描状态监听
 */
public class ScanStatusListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public ScanStatusListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {
            if (blueEventListener != null){
                blueEventListener.onScanStateChange();
            }
    }
}
