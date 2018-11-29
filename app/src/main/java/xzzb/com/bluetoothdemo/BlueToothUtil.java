package xzzb.com.bluetoothdemo;


import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import xzzb.com.bluetoothdemo.action.RequestOpenBlueToothAction;
import xzzb.com.bluetoothdemo.contanst.BlueToothConstanst;
import xzzb.com.bluetoothdemo.impl.IDeviceScanResultCallBack;
import xzzb.com.bluetoothdemo.util.LogUtil;
import xzzb.com.bluetoothdemo.util.PermissionUtil;
import xzzb.com.bluetoothdemo.util.ToastUtil;

/**
 * 蓝牙管理类
 */
public class BlueToothUtil {

    private static final String UUID = "00001101-0000-1000-8000-00805F9B34FB";

    private static final long Time_Out = 30;//默认超时时间 30秒

    private BluetoothAdapter.LeScanCallback mLeScanCallback;//蓝牙扫描设备的回调接口

    private BluetoothAdapter mBluetoothAdapter;//蓝牙适配器

    private BluetoothGattCallback mBluetoothGattCallback;//蓝牙连接的回调接口

    private BluetoothGatt mBluetoothGatt;//

    private BluetoothManager mBluetoothManager;//蓝牙管理器

    private RequestOpenBlueToothAction mRequestOpenBlueToothAction;//授权打开蓝牙

    private static class SingleInstance{
        private static final BlueToothUtil single = new BlueToothUtil();
    }

    public BlueToothUtil() {
    }

    public static BlueToothUtil g(){

        return SingleInstance.single;
    }


    /**
     * 获取系统蓝牙管理类
     */
    private BluetoothAdapter getBlueAdapter(){
        if (null == mBluetoothAdapter) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//            mBluetoothManager = mBluetoothAdapter.
        }
        return mBluetoothAdapter;
    }

    private void getmBluetoothGattCallback(){
        if (null == mBluetoothGattCallback){
            mBluetoothGattCallback = new BluetoothGattCallback() {

                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                    super.onConnectionStateChange(gatt, status, newState);
                    /**
                     * 设备连接状态改变
                     */
                    if (newState == BluetoothGatt.STATE_CONNECTED){
                        LogUtil.d("lei","设备连接成功");
                        gatt.discoverServices();
                    }else if (newState == BluetoothGatt.STATE_DISCONNECTED){
                        LogUtil.d("lei","设备断开连接");
                    }

                }

                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    super.onCharacteristicWrite(gatt, characteristic, status);
                }


                @Override
                public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                    super.onDescriptorWrite(gatt, descriptor, status);
                    /**
                     * BluetoothGattDescriptor:用于描述特征的类，包含1个value值
                     */
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    super.onServicesDiscovered(gatt, status);
                    /**
                     * BluetoothGattService :简称服务，是构成BLE设备协议栈的组成单位
                     * 1个蓝牙设备协议栈一般由1个或者多个BluetoothGattService组成
                     */
                    List<BluetoothGattService> services = gatt.getServices();//获取服务列表
                }

                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    super.onCharacteristicChanged(gatt, characteristic);
                    /**
                     * BluetoothGattCharacteristic 简称特征：1个服务包含1个或多个特征，特征是作为数据的基本单元
                     * 包含1个数据值和附加的关于特征的描述
                     */
                }
            };
        }
    }

    /**
     * 搜索设备
     * @param iDeviceScanResult 回调接口 为null则不回调
     */
    public void  starScanDevice(final Activity activity, final IDeviceScanResultCallBack iDeviceScanResult){

        Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
                mRequestOpenBlueToothAction = new RequestOpenBlueToothAction();
                boolean isEnable = mRequestOpenBlueToothAction.requestOpenBlueTooth(activity);//蓝牙是否开启成功
                e.onNext(isEnable);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Predicate<Boolean>() {
            @Override
            public boolean test(Boolean aBoolean) throws Exception {
                if (!aBoolean){
                    ToastUtil.g().toast("请开启蓝牙");
                    return false;
                }
                return aBoolean;
            }
        }).doOnNext(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean){
                    getBlueAdapter();
                    if (null == mLeScanCallback){
                        mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
                            @Override
                            public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
                                LogUtil.d("lei","扫描到设备--->"+device.getName());
                                if (null != iDeviceScanResult && !TextUtils.isEmpty(device.getName())){
                                    iDeviceScanResult.result(device);
                                }
                            }
                        };
                    }
                    LogUtil.d("lei","开启扫描");
                    mBluetoothAdapter.startLeScan(mLeScanCallback);
                }
            }
        }).flatMap(new Function<Boolean, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Boolean aBoolean) throws Exception {
                //30秒以后停止扫描
                return Observable.timer(Time_Out,TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                LogUtil.d("lei","停止扫描");
                stopScan();
            }
        });
    }


    /**
     * 停止扫描蓝牙设备
     */
    public void stopScan(){
        if (null == mLeScanCallback || null == mBluetoothAdapter){
            return;
        }
        mBluetoothAdapter.stopLeScan(mLeScanCallback);
    }


    /**
     *
     * @param context
     * @param autoConnect 下次是否自动连接
     * @param device 连接的设备
     * @return
     */
    public boolean connection(Context context,boolean autoConnect ,BluetoothDevice device){
        getmBluetoothGattCallback();
        device.connectGatt(context,autoConnect,mBluetoothGattCallback);
        return true;
    }



    /**
     * 搜索回调
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case BlueToothConstanst.Res_ACTION_REQUEST_ENABLE://蓝牙授权
                if (resultCode == Activity.RESULT_OK){
                    if (mRequestOpenBlueToothAction != null){
                        mRequestOpenBlueToothAction.setOpened(true);
                    }
                    mRequestOpenBlueToothAction.notifyAction();
                    ToastUtil.g().toast("蓝牙开启");
                }else {
                    if (mRequestOpenBlueToothAction != null){
                        mRequestOpenBlueToothAction.setOpened(false);
                    }
                    ToastUtil.g().toast("蓝牙开启失败");
                    mRequestOpenBlueToothAction.notifyAction();
                }
                break;
        }
    }


}
