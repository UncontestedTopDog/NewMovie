package com.example.administrator.newmovie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.administrator.newmovie.NetWork.NetChange;
import com.example.administrator.newmovie.NetWork.NetworkHelper;
import com.gyf.barlibrary.ImmersionBar;

/**
 * Created by Administrator on 2017/9/14.
 */

public class BaseActivity extends FragmentActivity implements NetChange {

    public static NetChange netChange;
    private ImmersionBar mImmersionBar;
    /**
     * 网络类型
     */
    private NetworkHelper.NetworkClass netMobile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        netChange = this ;
        checkNetworkState();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();   //所有子类都将继承这些相同的属性
    }

    private NetworkHelper.NetworkClass checkNetworkState() {
        return NetworkHelper.getMobileNetworkClass(getApplicationContext()) ;
    }


    @Override
    public void onNetChange(NetworkHelper.NetworkClass netMobile) {
        this.netMobile = netMobile ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
