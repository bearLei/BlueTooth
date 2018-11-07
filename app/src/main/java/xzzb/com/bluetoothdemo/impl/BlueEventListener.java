package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;

/**
 * create by lei on 2018/11/6/006
 * desc: 蓝牙扫描回调接口
 */
public interface BlueEventListener {

    void onAdapterStatusChange(int status);
    void onScanStateChange();
    void onDeviceAdded(BluetoothDevice device);
    void onServiceUUidFound();
    void onDeviceBondStatusChanged(BluetoothDevice device,int status);
    void pairCancle();
}
