package ru.udevs.success;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class LectionsActivity extends ActionBarActivity {
    private WebView webView;
    private static final String TAG = "Main";
    private ProgressDialog progressBar;
    WebView Rt;
    int pos = 0;
    public static final String APP_PREFERENCES = "Checksettings";
    public static final String APP_STAR = "Starsettings";
    SharedPreferences Starsettings;
    SharedPreferences Checksettings;
    private FrameLayout customViewContainer;
    Toolbar toolbar;
    Article art;
    private WebChromeClient.CustomViewCallback customViewCallback;
    private View mCustomView;
    private myWebChromeClient mWebChromeClient;
    private myWebViewClient mWebViewClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articlevideo);

        pos = getIntent().getIntExtra("pos",0);
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Starsettings = getSharedPreferences(APP_STAR, Context.MODE_PRIVATE);
        int fs = Checksettings.getInt("fontsize", 12);

        art = Article.sortArt.get(pos);

      //  Rt = (WebView)findViewById(R.id.Wv);

       // requestWindowFeature(Window.FEATURE_NO_TITLE);

      //  setContentView(R.layout.main);

        this.webView = (WebView)findViewById(R.id.Wv);
        customViewContainer = (FrameLayout) findViewById(R.id.customViewContainer);
        mWebViewClient = new myWebViewClient();
        webView.setWebViewClient(mWebViewClient);
        mWebChromeClient = new myWebChromeClient();
        webView.setWebChromeClient(mWebChromeClient);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSaveFormData(true);
        webView.loadUrl(art.Description);
       // webView.loadUrl("http://m.youtube.com");
    /*    WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new WebChromeClient() {
        });
       // WebSettings ws = desc.getSettings();
       // settings.getPluginState();
      //  settings.setPluginState(WebSettings.PluginState.ON);
     //   settings.setUserAgent(0);
     //   settings.setJavaScriptCanOpenWindowsAutomatically(true);
       // settings.setPluginsEnabled(true);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        progressBar = ProgressDialog.show(this, "Лекции", "Загрузка...");

        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
            //    Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
             //   Log.i(TAG, "Finished loading URL: " +url);
                if (progressBar.isShowing()) {
                    progressBar.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            //    Log.e(TAG, "Error: " + description);
               // Toast.makeText(th, "Oh no! " + description, Toast.LENGTH_SHORT).show();
              //  alertDialog.setTitle("Error");
              //  alertDialog.setMessage(description);
             //   alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
             //       public void onClick(DialogInterface dialog, int which) {
             //           return;
             //       }
             //   });
            //    alertDialog.show();
            }
        });
        webview.loadUrl("https://www.youtube.com/watch?v=KV22RJ33UvU");




        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tt_menu, menu);
       // menu.setT
        MenuItem myActionMenuItem = menu.findItem( R.id.action_star);
        menu.removeItem(R.id.action_star);
        menu.removeItem(R.id.action_edit);
       // String id = String.valueOf(Article.Articles.indexOf(art));
       // if (Starsettings.getBoolean(id, false))
       //     myActionMenuItem.setIcon(R.mipmap.ic_bookmark_black_24dp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            /*   case R.id.action_edit:
                ShowDialog();
                break;*/
            case R.id.action_share:
                onShareClick();
                break;
         /*   case R.id.action_star:
                String id = String.valueOf(Article.Articles.indexOf(art));
                if(Starsettings.getBoolean(id, false))
                {
                    item.setIcon(R.mipmap.ic_bookmark_border_black_24dp);
                    SharedPreferences.Editor editor = Starsettings.edit();
                    editor.putBoolean(id, false);
                    editor.apply();
                }
                else
                {
                    item.setIcon(R.mipmap.ic_bookmark_black_24dp);
                    SharedPreferences.Editor editor = Starsettings.edit();
                    editor.putBoolean(id, true);
                    editor.apply();

                }
              //  SharedPreferences.Editor editor = Checksettings.edit();
                //int starPos = Article.Articles.indexOf(art);
              //  e//ditor.putInt(String.valueOf(starPos), starPos);
                //editor.apply();
                break;*/
            case android.R.id.home:
               // webView.stopLoading();
              //  webView.destroy();
                if (inCustomView()) {
                    hideCustomView();
                    return true;
                }
                else{
                    if ((mCustomView == null) && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                    else
                    {
                        super.onBackPressed();
                    }
                }
              //  finish();
                break;
            default:
                return false;
        }
        return true;
    }

    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        seek.setMax(50);
        int fs = Checksettings.getInt("fontsize",24);
        seek.setProgress(fs);
        popDialog.setIcon(R.mipmap.ic_format_size_black_24dp);
        popDialog.setTitle("Размер шрифта");
        popDialog.setView(seek);
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                if (progress <= 5) progress = 6;
                SharedPreferences.Editor editor = Checksettings.edit();
                editor.putInt("fontsize", progress);
                editor.apply();
                Rt.getSettings().setDefaultFontSize(progress);
            }
            public void onStartTrackingTouch(SeekBar arg0) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        popDialog.create();
        popDialog.show();

    }
    public void onShareClick() {
        Resources resources = getResources();
        String shareTxt=art.Description;
       // shareTxt.replace("<br>","\n");
       // shareTxt.replace("</br>","");
        shareTxt = android.text.Html.fromHtml(shareTxt).toString();
        shareTxt=art.Author + "\n" + art.Title + "\n" + shareTxt;
        Intent emailIntent = new Intent();
        emailIntent.setAction(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_TEXT, shareTxt);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Журнал Урал.");
        emailIntent.setType("message/rfc822");

        PackageManager pm = getPackageManager();
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");


        Intent openInChooser = Intent.createChooser(emailIntent, resources.getString(R.string.sh));

        List<ResolveInfo> resInfo = pm.queryIntentActivities(sendIntent, 0);
        List<LabeledIntent> intentList = new ArrayList<LabeledIntent>();
        for (int i = 0; i < resInfo.size(); i++) {
            ResolveInfo ri = resInfo.get(i);
            String packageName = ri.activityInfo.packageName;
            if(packageName.contains("android.email")) {
            } else if(packageName.contains("vk") || packageName.contains("goog") ||packageName.contains("twitter") || packageName.contains("facebook") || packageName.contains("mms")){// || packageName.contains("android.gm")) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(packageName, ri.activityInfo.name));
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("text/plain");

                if(packageName.contains("twitter")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                } else if(packageName.contains("facebook")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                }    else if(packageName.contains("goog")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                }    else if(packageName.contains("vk")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                } else if(packageName.contains("mms")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                } else if(packageName.contains("android.gm")) {
                    intent.putExtra(Intent.EXTRA_TEXT, shareTxt);
                    intent.putExtra(Intent.EXTRA_SUBJECT, ("Журнал Урал."));
                    intent.setType("message/rfc822");
                }

                intentList.add(new LabeledIntent(intent, packageName, ri.loadLabel(pm), ri.icon));
            }
        }

        HashSet<LabeledIntent> hs = new HashSet<>();
        hs.addAll(intentList);
        intentList.clear();
        intentList.addAll(hs);
        LabeledIntent[] extraIntents = intentList.toArray( new LabeledIntent[ intentList.size() ]);



        openInChooser.putExtra(Intent.EXTRA_INITIAL_INTENTS, extraIntents);
        startActivity(openInChooser);
    }

    @Override
    public void onBackPressed() {
     //   webView.stopLoading();
     //   webView.destroy();
        super.onBackPressed();
    }
    public boolean inCustomView() {
        return (mCustomView != null);
    }

    public void hideCustomView() {
        mWebChromeClient.onHideCustomView();
    }

    @Override
    protected void onPause() {
        super.onPause();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.
        webView.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();    //To change body of overridden methods use File | Settings | File Templates.
        if (inCustomView()) {
            hideCustomView();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (inCustomView()) {
                hideCustomView();
                return true;
            }

            if ((mCustomView == null) && webView.canGoBack()) {
                webView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    class myWebChromeClient extends WebChromeClient {
        private Bitmap mDefaultVideoPoster;
        private View mVideoProgressView;

        @Override
        public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
            onShowCustomView(view, callback);    //To change body of overridden methods use File | Settings | File Templates.
        }

        @Override
        public void onShowCustomView(View view,CustomViewCallback callback) {

            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden();
                return;
            }
            mCustomView = view;
            webView.setVisibility(View.GONE);
            customViewContainer.setVisibility(View.VISIBLE);
            customViewContainer.addView(view);
            customViewCallback = callback;
        }

        @Override
        public View getVideoLoadingProgressView() {

            if (mVideoProgressView == null) {
                LayoutInflater inflater = LayoutInflater.from(LectionsActivity.this);
                mVideoProgressView = inflater.inflate(R.layout.video_progress, null);
            }
            return mVideoProgressView;
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();    //To change body of overridden methods use File | Settings | File Templates.
            if (mCustomView == null)
                return;

            webView.setVisibility(View.VISIBLE);
            customViewContainer.setVisibility(View.GONE);

            // Hide the custom view.
            mCustomView.setVisibility(View.GONE);

            // Remove the custom view from its container.
            customViewContainer.removeView(mCustomView);
            customViewCallback.onCustomViewHidden();

            mCustomView = null;
        }
    }

    class myWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

}