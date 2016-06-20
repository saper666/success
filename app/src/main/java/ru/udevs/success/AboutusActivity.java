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
import android.net.Uri;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;


public class AboutusActivity extends ActionBarActivity {
    WebView Rt;
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    Toolbar toolbar;
    String htmlText="";
    Menu menum;
    int year,month;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        int fs = Checksettings.getInt("fontsize", 12);


        Rt = (WebView)findViewById(R.id.Wv);

        String intent = getIntent().getStringExtra("info");
        //intextra = intent;
        if (intent.equals("О нас")) {
             htmlText = "<html>" +
                     "<head>\n" +
                     "<style type=\"text/css\">\n" +
                     "@font-face {\n" +
                     "    font-family: MyFont;\n" +
                     "    src: url(\"file:///android_asset/fonts/gothic.ttf\")\n" +
                     "}\n" +
                     "body {\n" +
                     "    font-family: MyFont;\n" +
                     "background-size: cover;\n"+
                     "padding:5%;"+
                     "}\n" +
                     "</style>\n" +
                     "</head>" +
                     "<body background = \"file:///android_asset/podloweb.png\">" +
                    "<br><br>" +
                    "<h1 align=\"center\"><font size=" + (fs + 2) + " color=\"black\">" + "О НАС" + "</font></h1>" +
                    "<br><br>" +
                    "<p align=\"left\"><font size=" + fs + " color=\"black\">" + "Литературно-художественный и публицистический журнал «Урал» выходит с января 1958 года.\n<br><br>" +
                    "Журнал «Урал» – это и сохранение традиций, и творческая лаборатория литературы, поэтому он рассчитан на самую разнообразную аудиторию: от тонкого ценителя до массового читателя. Это единственный толстый литературный журнал всероссийского уровня, выходящий на Урале.\n<br><br>" +
                    "Журнал не ставит перед своими авторами жестких идеологических или эстетических рамок. В «Урале» принимают рукописи реалистов, модернистов, постмодернистов и так далее. При отборе материалов редакция руководствуется только двумя критериями: талантом и мастерством автора.\n<br><br>" +
                    "Редакция журнала «Урал» приглашает к сотрудничеству прозаиков, поэтов, драматургов, критиков, публицистов.\n<br><br>" +
                    "Рукописи можно приносить или присылать на почтовый и электронный адрес редакции (см. «Контакты»).\n<br><br>" + "</font></p>" +
                    "</body></html>";
        }
        if (intent.equals("Места распространения")) {
             htmlText = "<html>" +
                     "<head>\n" +
                     "<style type=\"text/css\">\n" +
                     "@font-face {\n" +
                     "    font-family: MyFont;\n" +
                     "    src: url(\"file:///android_asset/fonts/gothic.ttf\")\n" +
                     "}\n" +
                     "body {\n" +
                     "background-size: cover;\n"+
                     "    font-family: MyFont;\n" +
                     "padding:5%;"+
                     "}\n" +
                     "</style>\n" +
                     "</head>" +
                     "<body background = \"file:///android_asset/podloweb.png\">" +
                    "<br><br>" +
                    "<h1 align=\"center\"><font size=" + (fs + 2) + " color=\"black\">" + "МЕСТА РАСПРОСТРАНЕНИЯ" + "</font></h1>" +
                    "<br><br>" +
                    "<p align=\"left\"><font size=" + fs + " color=\"black\">" + "Журнал «Урал» Вы можете приобрести в Екатеринбурге:\n<br><br>" +
                    "•\tв редакции (ул. Малышева, 24), \n<br><br>" +
                    "•\tв театральном киоске Дома Актера (ул. 8 Марта, 8); \n<br><br>" +
                    "в магазинах: \n<br><br>" +
                    "•\t«Дом книги» (ул. Антона Валека, 12), \n<br><br>" +
                    "•\t«100000 книг» (ул. Челюскинцев, 23; ул. Декабристов, 51), \n<br><br>" +
                    "•\t«Йозеф Кнехт» (ул. 8 Марта, 7), \n<br><br>" +
                    "•\tсеть магазинов «Живое слово»;\n<br><br>" +
                    "а также \n<br><br>" +
                    "•\tв Музее изобразительных искусств (ул. Воеводина, 5), \n<br><br>" +
                    "•\tв Музее «Литературная жизнь Урала ХХ века» (ул. Пролетарская, 10).\n<br><br>" +
                    "В Нижнем Тагиле журнал продается в магазине «Тагилкнига» (ул. Первомайская, 32; ул. Дзержинского, 47).\n<br><br>" + "</font></p>" +
                    "</body></html>";
        }
        if (intent.equals("О подписке")) {
             htmlText = "<html>" +
                     "<head>\n" +
                     "<style type=\"text/css\">\n" +
                     "@font-face {\n" +
                     "    font-family: MyFont;\n" +
                     "    src: url(\"file:///android_asset/fonts/gothic.ttf\")\n" +
                     "}\n" +
                     "body {\n" +
                     "background-size: cover;\n"+
                     "    font-family: MyFont;\n" +
                     "padding:5%;"+
                     "}\n" +
                     "</style>\n" +
                     "</head>" +
                     "<body background = \"file:///android_asset/podloweb.png\">" +
                    "<br><br>" +
                    "<h1 align=\"center\"><font size=" + (fs + 2) + " color=\"black\">" + "О ПОДПИСКЕ" + "</font></h1>" +
                    "<br><br>" +
                    "<p align=\"left\"><font size=" + fs + " color=\"black\">" + "Подписывайтесь на журнал с любого месяца во всех почтовых отделениях России. Общероссийский индекс 73412.\n<br><br>" +
                    "Льготный индекс для подписчиков Екатеринбурга и Свердловской области 46358.\n<br><br>" +
                    "Подписку на журнал «Урал» можно оформить также в Центре подписки и доставки ООО «Урал-Пресс Город» по адресу: Екатеринбург, ул. Мамина-Сибиряка, 130; телефоны: 26-26-543, 26-27-898.\n<br><br>" +
                    "Подписаться на журнал «Урал» можно в интернет-магазине качественных изданий <a href=\"http://mymagazines.ru/catalog/culture_art/1879/\">MyMagazines.ru</a>.\n<br><br>" + "</font></p>" +
                    "</body></html>";
        }
        if (intent.equals("Контакты")) {
            htmlText = "<html>" +
                    "<head>\n" +
                    "<style type=\"text/css\">\n" +
                    "@font-face {\n" +
                    "    font-family: MyFont;\n" +
                    "    src: url(\"file:///android_asset/fonts/gothic.ttf\")\n" +
                    "}\n" +
                    "body {\n" +
                    "background-size: cover;\n"+
                       "    font-family: MyFont;\n" +
                    "padding:5%;"+
                    "}\n" +
                    "</style>\n" +
                    "</head>" +
                    "<body background = \"file:///android_asset/podloweb.png\">" +
                    "<br><br>" +
                    //"<img width=\"100%\" src=\"file:///android_asset/"+mDrawableName +".jpg\">" +
                    "<h1 align=\"center\"><font size=" + (fs + 2) + " color=\"black\">" + "КОНТАКТЫ" + "</font></h1>" +
                    "<br><br>" +
                    "<p align=\"left\"><font size=" + fs + " color=\"black\">" + "Редакция журнала «Урал»: 620014, г. Екатеринбург, ул. Малышева, 24\n<br><br>" +
                    "Телефоны:\n<br><br>" +
                    "376-57-49 - главный редактор\n<br><br>" +
                    "376-57-41 - заместитель главного редактора по развитию, отдел критики\n<br><br>" +
                    "376-57-54 - заместитель главного редактора по творческим вопросам, отдел публицистики, отдел прозы\n<br><br>" +
                    "376-56-25 - отдел поэзии, бухгалтерия\n<br><br>" +
                    "E-mail: <a href=\"mailto:editor.ural@mail.ru\">editor.ural@mail.ru</a> \n<br><br>" +
                    "Журнал «Урал» в Сети:\n<br><br>" +
                    "<a href=\"http://uraljournal.ru\">Сайт журнала</a>\n<br><br>" +
                    "<a href=\"http://vk.com/zhurnal_ural\">Вконтакте</a>\n<br><br>" +
                    "<a href=\"http://www.facebook.com/uraljournal\">Facebook</a>\n<br><br>" +
                    "Электронная версия журнала «Урал»:\n<br><br>" +
                    "<a href=\"http://magazines.russ.ru/ural/\">Журнальный Зал</a>\n<br><br></font></p>" +
                    "</body></html>";
        }
        if (intent.equals("О проекте")) {
            htmlText = "<html>" +
                    "<head>\n" +
                    "<style type=\"text/css\">\n" +
                    "@font-face {\n" +
                    "    font-family: MyFont;\n" +
                    "    src: url(\"file:///android_asset/fonts/gothic.ttf\")\n" +
                    "}\n" +
                    "body {\n" +
                    "background-size: cover;\n"+
                    "    font-family: MyFont;\n" +
                    "padding:5%;"+
                    "}\n" +
                    "</style>\n" +
                    "</head>" +
                    "<body background = \"file:///android_asset/podloweb.png\">" +
                    "<br><br>" +
                    //"<img width=\"100%\" src=\"file:///android_asset/"+mDrawableName +".jpg\">" +
                    "<h1 align=\"center\"><font size=" + (fs + 2) + " color=\"black\">" + "О ПРОЕКТЕ" + "</font></h1>" +
                    "<br><br>" +
                    "<p align=\"left\"><font size=" + fs + " color=\"black\">" +
                    "Данный проект является первым в своем роде. «Урал» первый литературно-художественный журнал, который имеет свое мобильное приложение.\n" +
                    "<br><br>"+
                    "Мобильный формат позволит вам оперативно узнавать обо всем новом в «Урале».Проект нацелен на предоставленные наиболее полной информации о жизни журнала: здесь мы будем постепенно публиковать материалы из свежего номера, рассказывать о готовящихся мероприятиях, делиться интересными лекциями, проходящими у нас в редакции.\n" +
                    "<br><br>"+
                    "Руководитель проекта: Надежда Колтышева\n<br><br>" +
                    "Автор проекта: Вероника Ряпосова\n<br><br>" +
                    "Дизайн: Анастасия Краснопёрова\n<br><br>" +
                    "Разработка: Useful Developers Studio \n<br><br>" +
                    "Владимир Бирбровер\n<br><br>" +
                    "Евгений Шишков\n<br><br>" +
                    "E-mail: <a href=\"mailto:usefuldevstudio@gmail.com\">usefuldevstudio@gmail.com</a> \n<br><br>" +
                    "Сайт: <a href=\"http://udevs.ru\">udevs.ru</a>\n<br><br>" +
                    "<br><br>" +
                    "<br><br>" +
                    "<br><br>" +
                    "<br><br>" +
                    "<br><br>" +
                    "Журнал \"УРАЛ\" © 2016"+
                    "</body></html>";
        }
        //  Rt.setBackgroundResource(R.drawable.bgi);
        Rt.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        Rt.setBackgroundColor(0x00000000);
        Rt.getSettings().setLoadWithOverviewMode(true);
        Rt.getSettings().setUseWideViewPort(true);
        Rt.getSettings().setDefaultFontSize(fs);
        Rt.setInitialScale(1);
        Rt.getSettings().setUserAgentString("Android");
        Rt.getSettings().setJavaScriptEnabled(true);
        Rt.getSettings().setUseWideViewPort(true);
        Rt.loadDataWithBaseURL(null, htmlText, "text/html", "UTF-8", null);
      //  Rt.setWebViewClient(new HelloWebViewClient());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
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
    private class HelloWebViewClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            menum.removeItem(R.id.action_edit);
            if(url.startsWith("http://magazines.russ.ru")){
                Rt.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
                view.loadUrl(url);
            }
            if(url.startsWith("mailto:")){
                Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
                startActivity(i);
            }
            else{
                view.loadUrl(url);
            }
            return true;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tt_menu, menu);
        menu.removeItem(R.id.action_star);
        menu.removeItem(R.id.action_share);
        menum = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
               case R.id.action_edit:
                ShowDialog();
                break;
          //  case R.id.action_share:
           //     onShareClick();
           //     break;
            case android.R.id.home:
                adAlgorithmshow();
              //  if(Rt.canGoBack()) {
               //     Rt.goBack();
              //  } else {
                finish();//}
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
    @Override
    public void onBackPressed() {
       // if(Rt.canGoBack()) {
      //      Rt.goBack();
      //  } else {
            super.onBackPressed();
        adAlgorithmshow();
            finish();
           // Intent i = new Intent(getApplicationContext(), MainActivity.class);
          //  startActivity(i);
       // }
    }

}