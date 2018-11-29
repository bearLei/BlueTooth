package xzzb.com.bluetoothdemo;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xzzb.com.bluetoothdemo.adapter.DeviceAdapter;
import xzzb.com.bluetoothdemo.impl.IDeviceScanResultCallBack;
import xzzb.com.bluetoothdemo.util.CommUtils;

public class MainActivity extends FragmentActivity {

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    private static final int REQUEST_LOCATION_PERMISSION = 2;

    @BindView(R.id.deviceList)
    ListView deviceList;

    private BlueToothUtil blueToothUtil;

    private DeviceAdapter mAdapter;

    private ArrayList<BluetoothDevice> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        if (mData == null){
            mData = new ArrayList<>();
        }
        if (mAdapter == null){
            mAdapter = new DeviceAdapter(this,mData);
        }
        deviceList.setAdapter(mAdapter);
        doCheckLocationServer();
        doCheckLocationPermission();
    }

    @OnClick(R.id.search_device)
    public void onViewClicked() {
        blueToothUtil = BlueToothUtil.g();
        blueToothUtil.starScanDevice(this, new IDeviceScanResultCallBack() {
            @Override
            public void result(BluetoothDevice device) {
                if (mData != null && !mData.contains(device)){
                    mData.add(device);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void resultByRssi(BluetoothDevice device, int rssi) {

            }
        });
    }
    private boolean doCheckLocationServer() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !CommUtils.isLocationOpen(getApplicationContext())) {
            Intent enableLocate = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(enableLocate, REQUEST_LOCATION_PERMISSION);
            return true;
        } else {
            return false;
        }
    }

    private boolean doCheckLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (blueToothUtil != null) {
            blueToothUtil.onActivityResult(requestCode, resultCode, data);
        }
    }
}
