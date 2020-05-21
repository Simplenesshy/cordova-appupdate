package com.jdata.nhzhjg;

import android.view.View;

import com.king.app.dialog.AppDialog;
import com.king.app.dialog.AppDialogConfig;
import com.king.app.updater.AppUpdater;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppUpdate extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        JSONObject json = args.getJSONObject(0);
        final String url = json.getString("url");
        final String filename = json.getString("filename");
        String title = json.getString("title");
        String content = json.getString("content");
        String okText = json.getString("okText");

        if ("dialog".equals(action)) {
            AppDialogConfig config = new AppDialogConfig();
            config.setTitle(title)
                    .setOk(okText)
                    .setContent(content)
                    .setOnClickOk(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new AppUpdater.Builder()
                                    .serUrl(url)
                                    .setFilename(filename)
                                    .build(cordova.getActivity())
                                    .start();
                            AppDialog.INSTANCE.dismissDialog();
                        }
                    });
            AppDialog.INSTANCE.showDialog(cordova.getActivity(), config);
        } else  {
            new AppUpdater(cordova.getActivity(), url).start();
        }

        return true;
    }
}
