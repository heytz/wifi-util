package com.heytz.wifiutility;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * This class enable the wifi utility
 */
public class Wifi extends CordovaPlugin {

    private Context context;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        context = cordova.getActivity().getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getCurrentSSID")) {
            this.getCurrentSSID(callbackContext);
            return true;
        }
        return false;
    }

    private void getCurrentSSID(CallbackContext callbackContext) {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String connectedSSID = wifiInfo.getSSID().replace("\"", "");
            if (connectedSSID != null && connectedSSID.length() > 0) {
                callbackContext.success(connectedSSID);
            } else {
                callbackContext.error("SSID is empty");
            }
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
    }
}
