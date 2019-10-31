package com.example.webview_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button button;
    WebView webview;
    String a = "C,D";
    String b = "E";
    String c = "3000,1000";


    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        webview =(WebView) findViewById(R.id.web_view);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new JavaScriptInterface(MainActivity.this), "JSInterface");



//        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);



        webview.loadUrl("http://140.113.65.235/test.php");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                webview.post(new Runnable() {
                    @Override
                    public void run() {

                        //webview.loadUrl("javascript:test('"+a+"','"+b+"','"+c+"')");
                        webview.loadUrl("javascript:updateMP");
                    }
                });

            }
        });

        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });







    }

    public class JavaScriptInterface {
        private Activity activity;
        public JavaScriptInterface(Activity activiy) {
            this.activity = activiy;
        }
        @JavascriptInterface
        public void get_result()
        {

            //Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();
        }
    }
}
