package xzzb.com.bluetoothdemo.broadcast;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * des：蓝牙的广播接收器
 */
public class BlueBroadCastReceeive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_NAME);//获取蓝牙设备信息
        switch (action){

        }
    }
}
