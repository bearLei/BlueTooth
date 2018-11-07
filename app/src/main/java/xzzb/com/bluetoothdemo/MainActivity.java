package xzzb.com.bluetoothdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private BlueToothUtil blueToothUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.search_device)
    public void onViewClicked() {
        blueToothUtil = BlueToothUtil.g();
        blueToothUtil.requestOpenBlueTooth(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (blueToothUtil != null){
            blueToothUtil.onActivityResult(requestCode, resultCode, data);
        }
    }
}
