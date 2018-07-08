package com.example.dell.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ProgressBar superprogressbar;
    ImageView superimageview;
    WebView superwebview;

    private WebView wv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superprogressbar = findViewById(R.id.myProgressBar);
        superimageview = findViewById(R.id.myImageView);
        superwebview = findViewById(R.id.myWebView);
        superprogressbar.setMax(100);
        superwebview.loadUrl("https://www.google.com");
        superwebview.getSettings().setJavaScriptEnabled(true);


        superwebview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                superprogressbar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                superimageview.setImageBitmap(icon);
            }
        });

        }

        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.super_menu, menu);
            return  super.onCreateOptionsMenu(menu);

        }


        public boolean onOptionsItemSelected(MenuItem item){
               switch (item.getItemId()){
                   case R.id.menuback :
                   onBackPressed();
                   break;
                   case R.id.menuforward :
                   onForwardPressed();
                   break;
                   case R.id.menurefresh :
                   superwebview.reload();
                   break;
               }
               return super.onOptionsItemSelected(item);
        }
        private void onForwardPressed(){
        if(superwebview.canGoForward())
        {
            superwebview.goForward();
        }
        else{
            Toast.makeText(this,"cant go further",Toast.LENGTH_SHORT).show();
        }
        }
         public void onBackPressed() {
        if(superwebview.canGoBack()){
            superwebview.goBack();
         }
         else{
            finish();
        }
         }

    }

