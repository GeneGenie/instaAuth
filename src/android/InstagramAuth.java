package com.plugin.instagramAuth;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import br.com.dina.oauth.instagram.InstagramApp;
import br.com.dina.oauth.instagram.InstagramApp.OAuthAuthenticationListener;
import org.apache.cordova.*;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        if("auth".equals(action)){
            mApp = new InstagramApp(this.cordova.getActivity(),"379d744556c743c090c8a2014779f59f",
                    "fd6ec75e44054da1a5088ad2d72f2253", "instagram://connect");
            mApp.setListener(new OAuthAuthenticationListener() {

                @Override
                public void onSuccess() {
                    callbackContext.success("GREAT");
                }

                @Override
                public void onFail(String error) {
                    callbackContext.error(error);
                }
            });

            return true;
        }
        return false;

    }


}