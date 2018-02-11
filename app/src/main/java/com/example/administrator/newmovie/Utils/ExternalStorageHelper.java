package com.example.administrator.newmovie.Utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by huangweiliang on 18/02/11.
 * Des:内存管理工具
 */
public class ExternalStorageHelper {
    private static final String TAG = "ExternalStorageHelper";

    public static final String WEB_CACHE_DIR = "WebCache";
    public static final String APP_ROOT_DIR_NAME = "100tutor";

    public static final String WEB_CACHE_PATH_IN_SDCARD = getAppAbsolutePath() + File.separator + WEB_CACHE_DIR;

    public static String getAppAbsolutePath() {

        final File storageDir = Environment.getExternalStorageDirectory();
        if (storageDir == null) {
            return null;
        }

        final String rootPath = storageDir.getAbsolutePath() + File.separator + APP_ROOT_DIR_NAME;
        final File result = new File(rootPath);
        if (!result.exists()) {
            if (!result.mkdirs()) {
                Log.w(TAG, "make dir failed, path: " + rootPath);
                return null;
            }
        }
        return rootPath;
    }

    public static boolean isWritable() {
        try {
            final String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        } catch (Throwable t) {
            Log.e(TAG, "Can not get writable external storage", t);
        }
        return false;
    }
}
