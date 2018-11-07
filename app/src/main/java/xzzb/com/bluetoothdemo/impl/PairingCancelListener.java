package xzzb.com.bluetoothdemo.impl;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;

/**
 * create by lei on 2018/11/6/006
 * desc:
 */
public class PairingCancelListener implements BaseBlueEventHandler {

    private BlueEventListener blueEventListener;

    public PairingCancelListener(BlueEventListener blueEventListener) {
        this.blueEventListener = blueEventListener;
    }

    @Override
    public void onReceive(Context context, Intent intent, BluetoothDevice device) {
        if (null != blueEventListener){
            blueEventListener.pairCancle();
        }
    }
}
