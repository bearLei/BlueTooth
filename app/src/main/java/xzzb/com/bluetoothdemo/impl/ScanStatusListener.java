package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc: 扫描状态监听
 * 对应的广播通知有：ACTION_DISCOVERY_STARTED  和ACTION_DISCOVERY_FINISHED
 * ACTION_DISCOVERY_STARTED 开始扫描设备的进程，大致需要12秒
 * ACTION_DISCOVERY_FINISHED扫描设备进程结束
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
