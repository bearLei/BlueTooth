package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import xzzb.com.bluetoothdemo.util.LogUtil;

/**
 * create by lei on 2018/11/6/006
 * desc: BlueTooth  Adapter的状态 改变 ，比如蓝牙的开启或者关闭
 * 对应状态：BluetoothAdapter.ACTION_STATE_CHANGED
 * EXTRA_STATE  新的状态
 * EXTRA_PREVIOUS_STATE 旧状态
 */
public class AdapterStatusChangeListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public AdapterStatusChangeListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {
        int status = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);//adapter的状态
        LogUtil.d("lei","AdapterStatusChangeListener---->"+status);
        if (blueEventListener != null){
            blueEventListener.onAdapterStatusChange(status);
        }
    }
}
