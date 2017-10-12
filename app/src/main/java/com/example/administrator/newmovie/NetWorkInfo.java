package com.example.administrator.newmovie;

import android.telephony.TelephonyManager;


/**
 * Created by huqiuyun on 2017/2/9.
 */

public class NetWorkInfo {

    public int type = NET_TYPE_INVALID;//网络类型
    public int state = NetWorkInfo.NET_STATE_UNK; //好坏
    public int rssi = BAD_RSSI; //信号强度
    public String detail; //wifiinfo的祥细信息

    public static int getNetworkType(int type) {
        switch (type) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NetWorkInfo.NET_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NetWorkInfo.NET_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NetWorkInfo.NET_TYPE_4G;
            default:
                return NetWorkInfo.NET_TYPE_NO_NETWORK;
        }
    }

    @Override
    public String toString() {
        return "NetDetail: " + detail +
                ", NetType: " + type +
                ", NetState: " + state +
                ", NetRssi: " + rssi;
    }
    public static final int BAD_RSSI = -70;
    // net type
    public final static int NET_TYPE_INVALID = -1;
    public final static int NET_TYPE_WIFI = 0;
    public final static int NET_TYPE_4G = 1;
    public final static int NET_TYPE_2G = 2;
    public final static int NET_TYPE_3G = 3;
    public final static int NET_TYPE_NO_NETWORK = 4;

    // state
    public final static int NET_STATE_UNK = 0;
    public final static int NET_YY_DISCONNECT = 5;
    public final static int NET_YY_CONNECTED = 6;
    public final static int NET_RSSI_BAD = 7;
    public final static int NET_RSSI_GOOD = 8;
}
