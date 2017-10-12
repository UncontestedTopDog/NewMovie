package com.example.administrator.newmovie;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;

/**
 * Created by Administrator on 2017/9/13.
 */

public class NetworkHelper {
    public enum NetworkClass{
        WIFI,
        MOBILE_2G,
        MOBILE_3G,
        MOBILE_4G,
        DISCONNECTED,
        UNKNOWN
    }

    public static byte getNetType(@NonNull Context context){
        byte netType = ProtoConst.SYSNET_DISCONNECT ;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null){
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE )
            netType = ProtoConst.SYSNET_MOBILE;
        else if (nType == ConnectivityManager.TYPE_WIFI)
            netType = ProtoConst.SYSNET_WIFI;
        return netType;
    }

    public static NetworkClass getNetworkClass(@NonNull Context context) {
        byte netType = getNetType(context);
        switch (netType) {
            case ProtoConst.SYSNET_DISCONNECT:
                return NetworkClass.DISCONNECTED;
            case ProtoConst.SYSNET_WIFI:
                return NetworkClass.WIFI;
            case ProtoConst.SYSNET_MOBILE:
                return getMobileNetworkClass(context);

        }
        return NetworkClass.UNKNOWN;
    }

    public static boolean isDisconnected(@NonNull Context context) {
        return (NetworkClass.DISCONNECTED == getNetworkClass(context));
    }

    @NonNull
    public static NetworkClass getMobileNetworkClass(@NonNull Context context) {
        TelephonyManager mTelephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetworkClass.MOBILE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetworkClass.MOBILE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetworkClass.MOBILE_4G;
            default:
                return NetworkClass.UNKNOWN;
        }
    }

}
