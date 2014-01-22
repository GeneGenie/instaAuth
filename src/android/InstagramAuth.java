package com.plugin.instagramAuth;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import br.com.dina.oauth.instagram.InstagramApp;
import br.com.dina.oauth.instagram.InstagramApp.OAuthAuthenticationListener;
import org.apache.cordova.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Hashtable;

public class InstagramAuth extends CordovaPlugin {
    private InstagramApp mApp;

    /**
     * Sets the context of the Command. This can then be used to do things like
     * get file paths associated with the Activity.
     *
     * @param cordova The context of the main Activity.
     * @param webView The CordovaWebView Cordova is running in.
     */
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

    }


    /**
     * Executes the request and returns PluginResult.
     *
     * @param action          The action to execute.
     * @param args            JSONArry of arguments for the plugin.
     * @param callbackContext The callback context from which we were invoked.
     * @return A PluginResult object with a status and message.
     */
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        PluginResult.Status status = PluginResult.Status.OK;
        String result = "";
        if ("auth".equals(action)) {
            final Activity act = this.cordova.getActivity();
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    mApp = new InstagramApp(act, "379d744556c743c090c8a2014779f59f",
                            "fd6ec75e44054da1a5088ad2d72f2253", "instagram://connect");
                    mApp.setListener(new OAuthAuthenticationListener() {

                        @Override
                        public void onSuccess() {
                            Hashtable output=new Hashtable();
                            output.put("id",mApp.getId());
                            output.put("token",mApp.getToken());
                            output.put("username",mApp.getUserName());
                            callbackContext.success(new JSONObject(output));
                        }

                        @Override
                        public void onFail(String error) {
                            callbackContext.error(error);
                        }
                    });
                    mApp.authorize();
                    //callbackContext.success(); // Thread-safe.
                }
            });
            return true;
        }else if("open".equals(action)){
            String id =(String)args.get(0);
            Intent intent  = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.addCategory(Intent.CATEGORY_BROWSABLE);
            intent.setData(Uri.parse(id));
            intent.setPackage("com.instagram.android");
            this.cordova.getActivity().startActivity(intent);

            callbackContext.success();
            return true;
        }else if("openApp".equals(action)){
            PackageManager pm = this.cordova.getActivity().getApplicationContext().getPackageManager();
            Intent appStartIntent = pm.getLaunchIntentForPackage("com.instagram.android");
            if (null != appStartIntent)
            {
                this.cordova.getActivity().getApplicationContext().startActivity(appStartIntent);
            }
            callbackContext.success();
            return true;
        }
        return false;

    }


}