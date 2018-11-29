package xzzb.com.bluetoothdemo.adapter;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import xzzb.com.bluetoothdemo.R;

/**
 * create by lei on 2018/11/28/028
 * desc:
 */
public class DeviceAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<BluetoothDevice> mData;

    public DeviceAdapter(Context mContext, ArrayList<BluetoothDevice> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData == null || mData.size() == 0){
            return 0;
        }
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view ;
        ViewHolder holder;
        if (convertView == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.device_adapter_item,null);
            holder = new ViewHolder();
            holder.deviceName = view.findViewById(R.id.device_name);
            holder.deviceAddr = view.findViewById(R.id.device_addr);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        BluetoothDevice device = mData.get(position);
        if (null != device){
            holder.deviceName.setText(device.getName());
            holder.deviceAddr.setText(device.getAddress());
        }
        return view;
    }


    class ViewHolder{
        TextView deviceName,deviceAddr;
    }

}
