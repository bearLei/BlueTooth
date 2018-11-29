package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

/**
 * create by lei on 2018/11/28/028
 * desc:
 */
public interface IDeviceScanResultCallBack {

    void result(BluetoothDevice device);
    void resultByRssi(BluetoothDevice device,int rssi);//设备的rssi值，如果无可用的则为0  Rssi:蓝牙信号强度
}
