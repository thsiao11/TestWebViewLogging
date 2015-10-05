package com.zeeh.testwebviewlogging;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.zeeh.testwebviewlogging.R;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = getClass().getSimpleName();

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openWebView();
    }

    private void openWebView() {

        String urlStr = "http://www.google.com";

//        Button button = (Button) findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = webView.getUrl();
//                Toast.makeText(MainActivity.this, "current url: " + url, Toast.LENGTH_LONG).show();
//            }
//        });
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Log.e(LOG_TAG, "An error occurred: " + description);
            }

            public void onPageFinished(WebView view, String url) {
                Toast.makeText(MainActivity.this, "Here's the url: " + url, Toast.LENGTH_LONG).show();
            }
        });

        webView.loadUrl(urlStr);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }
}
