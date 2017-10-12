package com.example.administrator.newmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Administrator on 2017/9/14.
 */

public class BaseActivity extends FragmentActivity implements NetChange {

    public static NetChange netChange;
    /**
     * 网络类型
     */
    private NetworkHelper.NetworkClass netMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netChange = this ;
        checkNetworkState();
    }

    private NetworkHelper.NetworkClass checkNetworkState() {
        return NetworkHelper.getMobileNetworkClass(getApplicationContext()) ;
    }


    @Override
    public void onNetChange(NetworkHelper.NetworkClass netMobile) {
        this.netMobile = netMobile ;
    }
}
