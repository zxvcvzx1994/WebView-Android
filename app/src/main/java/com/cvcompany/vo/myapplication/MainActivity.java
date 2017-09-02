package com.cvcompany.vo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG =MainActivity.class.getSimpleName() ;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.etUrl)
    EditText etUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
    }

    @OnClick(R.id.btnFind)
    public void btnFInd(){
        String url  = etUrl.getText().toString();
        if(url.length()>0)
        loadUrl(url);
        else{
            Toast.makeText(this, "Please input the link", Toast.LENGTH_SHORT).show();
        }
        etUrl.setText(""+webView.getUrl());
    }

    public void loadUrl(String url){
        Log.i(TAG, "loadUrl: "+url);
        String[] condition = url.split("://");
        Log.i(TAG, "loadUrl: "+condition.length);
       if(condition.length==2)
           url= condition[1];

        String link = "http://"+url;

            webView.loadUrl(link);

    }

    @OnClick(R.id.imgBack)
    public void imgBack(){
        if(webView.canGoBack()){
            webView.goBack();
            etUrl.setText(webView.getUrl());
        }
    }

    @OnClick(R.id.imgForward)
    public void imgForward(){
        if(webView.canGoForward()){
            webView.goForward();
            etUrl.setText(webView.getUrl());
        }

    }

    @OnClick(R.id.imgReLoad)
    public void imgReload(){
        webView.reload();
        etUrl.setText(webView.getUrl());
    }
}
