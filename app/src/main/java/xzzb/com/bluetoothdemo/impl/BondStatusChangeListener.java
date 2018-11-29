package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc:蓝牙连接状态改变监听
 * 对应广播通知：ACTION_BOND_STATE_CHANGED
 *BluetoothDevice.EXTRA_BOND_STATE 来获取状态
 */
public class BondStatusChangeListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public BondStatusChangeListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {

        int bondState = intent.getIntExtra(BluetoothDevice.EXTRA_BOND_STATE, BluetoothDevice.ERROR);//获取当前连接状态

        if (null != blueEventListener){
            blueEventListener.onDeviceBondStatusChanged(device,bondState);
        }
    }
}
