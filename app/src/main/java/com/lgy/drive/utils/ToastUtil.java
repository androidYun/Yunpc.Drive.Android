package com.lgy.drive.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static Toast toast;

    public static void show(Context context, String message) {

        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }
        toast.setText(message);
        toast.show();
    }

    public static void showLong(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        }
        toast.setText(message);
        toast.show();
    }

}
