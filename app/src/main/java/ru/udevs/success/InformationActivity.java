package ru.udevs.success;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


public class InformationActivity extends ActionBarActivity implements View.OnClickListener{
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    RelativeLayout rl;
    Button aboutus,contakts,placesbuy,submit,udevs;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String colorBack = "#"+ Checksettings.getString("colorBack", "00E676");
        rl = (RelativeLayout)findViewById(R.id.rl);
        try {
            rl.setBackgroundColor(Color.parseColor(colorBack));
        }
        catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}
       // rl = (RelativeLayout)findViewById(R.id.rl);
      //  try {
      //      rl.setBackgroundColor(Color.parseColor(colorBack));
      //  }
     //   catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}

        aboutus = (Button)findViewById(R.id.aboutus);aboutus.setOnClickListener(this);
        contakts = (Button)findViewById(R.id.contakts);contakts.setOnClickListener(this);
        placesbuy = (Button)findViewById(R.id.placesbuy);placesbuy.setOnClickListener(this);
        submit = (Button)findViewById(R.id.submit);submit.setOnClickListener(this);
        udevs = (Button)findViewById(R.id.udevs);udevs.setOnClickListener(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.aboutus:
                Intent n = new Intent(this,AboutusActivity.class);
                n.putExtra("info","О нас");
                // Article.sort("Проза");
               // if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.contakts:
              //  Article.sort("Поэзия");
              //  if(Article.isContainsSort(this))
                Intent g = new Intent(this,AboutusActivity.class);
                g.putExtra("info","Контакты");
                startActivity(g);
                break;
            case R.id.placesbuy:
                Intent b = new Intent(this,AboutusActivity.class);
                b.putExtra("info","Места распространения");
                startActivity(b);
                break;
            case R.id.submit:
                Intent f = new Intent(this,AboutusActivity.class);
                f.putExtra("info","О подписке");
                startActivity(f);
                break;
            case R.id.udevs:
                Intent h = new Intent(this,AboutusActivity.class);
                h.putExtra("info","О проекте");
                startActivity(h);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        menu.removeItem(R.id.action_search);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                return false;
        }
        return true;
    }



}
