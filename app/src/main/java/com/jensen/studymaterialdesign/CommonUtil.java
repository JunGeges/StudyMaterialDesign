package com.jensen.studymaterialdesign;

import android.content.Context;
import android.content.Intent;

/**
 * Created by Administrator on 2017/8/29.
 */

public class CommonUtil {

    public static void startActivity(Context context, Class clas, int flag) {
        Intent intent = new Intent(context, clas);
        intent.setFlags(flag);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class clas, int flag, String [] keys,Integer... values) {
        Intent intent = new Intent(context, clas);
        for (int i = 0; i < keys.length; i++) {
            intent.putExtra(keys[i],values[i]);
        }
        intent.setFlags(flag);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class clas) {
        Intent intent = new Intent(context, clas);
        context.startActivity(intent);
    }
}
