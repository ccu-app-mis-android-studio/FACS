package com.example.jessl.alarm;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View.OnClickListener;
import org.json.JSONObject;
import android.app.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import android.webkit.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Chat extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chat);

            final String str_url = "file:///android_asset/chat.html" ;
            WebView chatBrowser=(WebView)findViewById(R.id.web_view );
            WebSettings chatBrowserSetting = chatBrowser.getSettings();
            //預設是限制JavaScript，所以如有需要必需先開啟。
            chatBrowserSetting.setJavaScriptEnabled(true);
            setContentView(chatBrowser);
            /*
                       //啟用網頁縮放功能
                       chatBrowserSetting.setSupportZoom(true);
                       chatBrowserSetting.setBuiltInZoomControls(true);
                      */
            chatBrowser.setWebViewClient(new WebViewClient());
            chatBrowser.loadUrl(str_url);
        }
}
