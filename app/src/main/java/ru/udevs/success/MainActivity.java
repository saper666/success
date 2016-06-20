package ru.udevs.success;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
Button newjournal,lections,events,information,elect;
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

      //  rl = (RelativeLayout)findViewById(R.id.rl);
      //  try {
      //      rl.setBackgroundColor(Color.parseColor(colorBack));
      //  }
      //  catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String colorBack = "#" + Checksettings.getString("colorBack", "00E676");
        rl = (RelativeLayout)findViewById(R.id.rl);
        try {
            rl.setBackgroundColor(Color.parseColor(colorBack));
        }
        catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}
        newjournal = (Button)findViewById(R.id.newjournal);newjournal.setOnClickListener(this);
        lections = (Button)findViewById(R.id.lections);lections.setOnClickListener(this);
        events = (Button)findViewById(R.id.events);events.setOnClickListener(this);
        information = (Button)findViewById(R.id.information);information.setOnClickListener(this);
        elect = (Button)findViewById(R.id.elect);elect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newjournal:
                Intent n = new Intent(this,NewJournalActivity.class);
                startActivity(n);
                break;
            case R.id.lections:
               Intent l = new Intent(this,ListLectionsActivity.class);
                Article.sort(this,"Лекция");
                if(Article.isContainsSort(this))
                    startActivity(l);
             //   startActivity(l);
                break;
            case R.id.events:
                Intent ev = new Intent(this,ListArticleActivity.class);
                Article.sort(this,"События");
                if(Article.isContainsSort(this))
                    startActivity(ev);
                break;
            case R.id.information:
                Intent f = new Intent(this,InformationActivity.class);
                //if(Article.isContainsSort(this))
                    startActivity(f);
                break;
            case R.id.elect:
                if(Article.getStars(this)) {
                    Intent s = new Intent(this, ListArticleActivity.class);
                    startActivity(s);
                }
                break;
        }
    }
}
