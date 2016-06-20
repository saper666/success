package ru.udevs.success;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class ArticleActivity extends ActionBarActivity {
    WebView Rt;
    int pos = 0;
    public static final String APP_PREFERENCES = "Checksettings";
    public static final String APP_STAR = "Starsettings";
    private InterstitialAd mInterstitialAd;
    SharedPreferences Starsettings;
    SharedPreferences Checksettings;
    Toolbar toolbar;
    Article art;
    int year,month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        pos = getIntent().getIntExtra("pos", 0);
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        Starsettings = getSharedPreferences(APP_STAR, Context.MODE_PRIVATE);
        int fs = Checksettings.getInt("fontsize", 12);

        art = Article.sortArt.get(pos);

        Rt = (WebView)findViewById(R.id.Wv);

        String htmlText = "<html>" +
                "<head>\n" +
                //"<meta  charset=UTF-8\">"+
                "<style type=\"text/css\">\n" +
                "@font-face {\n" +
                "    font-family: MyFont;\n" +
                "    src: url(\"file:///android_asset/fonts/times.ttf\")\n" +
                "}\n" +
                "body {\n" +
                "    font-family: MyFont;\n" +
                "background-size: cover;\n"+
            //    "-ms-word-break: break-all;"+
       // "word-break: break-all;"+

        // Non standard for webkit
     //  " word-break: break-word;"+
       //         "-moz-hyphens: auto;"+
        //        "-webkit-hyphens: auto;"+
         //       "-ms-hyphens: auto;"+
          //      "hyphens: auto;"+
                "padding:5%;"+
                "}\n" +
          //      "p {"+
          //  "-webkit-hyphens: auto;"+
          //  "-moz-hyphens: auto;"+
          //  "-ms-hyphens: auto;"+
          //  " }"+
                "</style>\n" +
                "</head>" +
                "<body background = \"file:///android_asset/podloweb.png\">" +
                "<br><br>" +
                "<h1 align=\"center\"><font size=" + (fs+2) + " color=\"black\">"+art.Title+"</font></h1>" +
                "<br><br>" +
                "<p align=\"left\"><font size=" + fs + " color=\"black\">"+art.Description+"</font></p>" +
                "</body></html>";

        //  Rt.setBackgroundResource(R.drawable.bgi);
        Rt.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Rt.setBackgroundColor(0x00000000);
        Rt.getSettings().setLoadWithOverviewMode(true);
        Rt.getSettings().setUseWideViewPort(true);
        Rt.getSettings().setDefaultFontSize(fs);
        Rt.loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null);
        Rt.setInitialScale(1);
        Rt.getSettings().setUserAgentString("Android");
        Rt.getSettings().setJavaScriptEnabled(true);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        //showInterstitial();
    }

    public void adAlgorithmshow()
    {
        if (month>=6 && month<8 && year==2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3)
                showInterstitial();
        }
        if (month>=8 && month<=11 && year==2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3 || a==4 || a==5)
                showInterstitial();
        }
        if (year>2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3 || a==4 || a==5 || a==6)
                showInterstitial();
        }
    }

   /* public void adViewBannerShow()
    {
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
      //  adView.loadAd(adRequest);

        if (month>=7 && month<9 && year==2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3)
                adView.loadAd(adRequest);
        }
        if (month>=9 && month<=12 && year==2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3 || a==4 || a==5)
                adView.loadAd(adRequest);
        }
        if (year>2016)
        {
            Random random = new Random();
            int a = random.nextInt(10) + 1;
            if (a ==1 || a==2 || a==3 || a==4 || a==5 || a==6)
                adView.loadAd(adRequest);
        }
    }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tt_menu, menu);
       // menu.setT
        MenuItem myActionMenuItem = menu.findItem( R.id.action_star);
        String id = String.valueOf(Article.Articles.indexOf(art));
        if (Starsettings.getBoolean(id, false))
            myActionMenuItem.setIcon(R.mipmap.ic_bookmark_black_24dp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
               case R.id.action_edit:
                ShowDialog();
                break;
            case R.id.action_share:
                onShareClick();
                break;
            case R.id.action_star:
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
                break;
            case android.R.id.home:
               // showInterstitial();
               // showInterstitial();
                adAlgorithmshow();
                finish();
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
                        adAlgorithmshow();
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
    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));

        return interstitialAd;
    }
    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }// else {
          //  Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
          //  goToNextLevel();
       // }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
       // mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        adAlgorithmshow();
        finish();
    }
}