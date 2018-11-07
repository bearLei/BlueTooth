package xzzb.com.bluetoothdemo.action;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import xzzb.com.bluetoothdemo.util.AppUtil;
import xzzb.com.bluetoothdemo.util.LockManager;

/**
 * create by lei on 2018/11/6/006
 * desc:行为封装，使用wait和notify 来衔接 链式调用
 */
public class BaseAction {

    public static final int Default_TimeOut = 30*1000;//30秒默认超时

    protected Context context;

    public BaseAction() {
        context = AppUtil.getContext();
    }

    public void regist(){
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
    }

    public void unRegist(){
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    /**
     * 需要 进行等待别的操作响应才能进行之后的操作
     * 所以就让当前的类进行wait
     * 等待别的操作完成来进行唤醒
     */
    public void waitAction(){

    }

    public void notifyAction(){

    }

}
