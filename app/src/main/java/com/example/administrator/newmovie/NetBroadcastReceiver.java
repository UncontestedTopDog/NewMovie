package com.example.administrator.newmovie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * Created by Administrator on 2017/9/14.
 */

public class NetBroadcastReceiver extends BroadcastReceiver {

    public NetChange netChange = BaseActivity.netChange;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            NetworkHelper.NetworkClass netWorkState = NetworkHelper.getMobileNetworkClass(context);
            // 接口回调传过去状态的类型
            netChange.onNetChange(netWorkState);
        }
    }
}