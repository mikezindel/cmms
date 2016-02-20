package ca.on.conestogac.cmms;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by user on 2016-02-19.
 */
public class DebugUtility {
    public static final String TAG = "@] CMMS";
    public  static final String BR = System.getProperty("line.separator");
    private static final boolean IS_DETAIL = true;

    public static void logDebug(String msg){
        if(DebugUtility.IS_DETAIL){
            StackTraceElement callStack = Thread.currentThread().getStackTrace()[3];
            Log.d(TAG
                    + callStack.getFileName() + "#" + callStack.getMethodName() + ":" + callStack.getLineNumber(), msg);
        } else {
            Log.d(TAG, msg);
        }
    }

    public static void logInfo(String msg){
        if(DebugUtility.IS_DETAIL){
            StackTraceElement callStack = Thread.currentThread().getStackTrace()[3];
            Log.i(TAG
                    + callStack.getFileName() + "#" + callStack.getMethodName() + ":" + callStack.getLineNumber(), msg);
        } else {
            Log.i(TAG, msg);
        }
    }

    public static void logWarning(String msg){
        if(DebugUtility.IS_DETAIL){
            StackTraceElement callStack = Thread.currentThread().getStackTrace()[3];
            Log.w(TAG
                    + callStack.getFileName() + "#" + callStack.getMethodName() + ":" + callStack.getLineNumber(), msg);
        } else {
            Log.w(TAG, msg);
        }
    }

    public static void logError(String msg){
        if(DebugUtility.IS_DETAIL){
            StackTraceElement callStack = Thread.currentThread().getStackTrace()[3];
            Log.e(TAG
                    + callStack.getFileName() + "#" + callStack.getMethodName() + ":" + callStack.getLineNumber(), msg);
        } else {
            Log.e(TAG, msg);
        }
    }

    public static void showToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToastDetail(Context context, String msg){

        StackTraceElement callStack = Thread.currentThread().getStackTrace()[3];
        Toast.makeText(context, callStack.getFileName() + "#" + callStack.getMethodName() + ":"+ callStack.getLineNumber()
                + BR + msg, Toast.LENGTH_LONG).show();
    }
}
