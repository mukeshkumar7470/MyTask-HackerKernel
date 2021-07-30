package com.mukeshkpdeveloper.mytask.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mukeshkpdeveloper.mytask.R;
import com.mukeshkpdeveloper.mytask.UI.activity.MainActivity;


import java.util.HashMap;

public class Util {

    private static Dialog progressDialog = null;
    public static boolean IS_BALANCE =  false;

    public static void showShortToast(Context ctx,String msg){
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void logoutMethod(Context ctx){
        AppPreference.clearAllPreferences(ctx);
        Intent intent  = new Intent(ctx, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);

    }

    public static HashMap<String, String> getHeaders(Context ctx){
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Sessionkey","");
        return headers;
    }


    public static void showProgressBar(Context ctx,Boolean isShowing) {
        if (isShowing == true) {
            progressDialog = new Dialog(ctx);
            progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            progressDialog.setContentView(R.layout.progressbar_layout);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(
                    new ColorDrawable(Color.TRANSPARENT));

            ProgressBar progressBar = (ProgressBar) progressDialog
                    .findViewById(R.id.progress_circular);
           /* progressBar.getIndeterminateDrawable().setColorFilter(
                    Color.parseColor("#ff6700"),
                    android.graphics.PorterDuff.Mode.MULTIPLY);*/
            progressDialog.show();
        } else if (isShowing == false) {
            progressDialog.dismiss();
        }
    }

    public static void log(String msg){
        Log.e("Check", msg );
    }

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo==null || !activeNetworkInfo.isConnected()){
            Toast.makeText(ctx, "Internet connection not available!!!", Toast.LENGTH_SHORT).show();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static Boolean ifEmpty(Context ctx,String string,String msg){
        if (string.isEmpty()){
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}