package ru.udevs.success;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import java.util.Calendar;

public class NewJournalActivity extends ActionBarActivity implements View.OnClickListener{

    Button proza,poezia,drama,bezvimisla,kraevedenie,publicistica;//,critika;
    Toolbar toolbar;
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_journal11);

        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String colorBack = "#"+ Checksettings.getString("colorBack", "00E676");
        rl = (RelativeLayout)findViewById(R.id.rl);
        try {
            rl.setBackgroundColor(Color.parseColor(colorBack));
        }
        catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}

        proza = (Button)findViewById(R.id.samorazvitie);proza.setOnClickListener(this);
        poezia = (Button)findViewById(R.id.biznes);poezia.setOnClickListener(this);
        drama = (Button)findViewById(R.id.psihologia);drama.setOnClickListener(this);
        bezvimisla = (Button)findViewById(R.id.zdorovie);bezvimisla.setOnClickListener(this);
        kraevedenie = (Button)findViewById(R.id.otnoshenia);kraevedenie.setOnClickListener(this);
        publicistica = (Button)findViewById(R.id.prochee);publicistica.setOnClickListener(this);
       // critika = (Button)findViewById(R.id.critika);critika.setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {

        Intent n = new Intent(this,ListArticleActivity.class);
        switch (v.getId()){
            case R.id.samorazvitie:
                Article.sort(this,"Саморазвитие");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.biznes:
                Article.sort(this,"Бизнес");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.psihologia:
                Article.sort(this,"Психология");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.zdorovie:
                Article.sort(this,"Здоровье");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.otnoshenia:
                Article.sort(this,"Отношения");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
            case R.id.prochee:
                Article.sort(this,"Прочее");
                if(Article.isContainsSort(this))
                startActivity(n);
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
      //  MenuItem item = menu.findItem(R.id.action_edit);
      //  item.setVisible(false);
     //   this.invalidateOptionsMenu();
        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
               // Toast.makeText(getApplicationContext(),query,Toast.LENGTH_SHORT).show();
                if(Article.searchAll(getApplicationContext(),query))
                {

                    Intent n = new Intent(getApplicationContext(),ListArticleActivity.class);
                    Article.copySearch();
                   // Article.sortArt.clear();
                   // Article.sortArt = Article.searchArt;
                   // Toast.makeText(getApplicationContext(),String.valueOf(Article.sortArt.size()),Toast.LENGTH_SHORT).show();
                    startActivity(n);
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

       // CharSequence message;

        switch (item.getItemId()) {
         //   case R.id.action_edit:
                //message = "Выбран пункт шрифт";
          //      ShowDialog();
            //    break;
            case android.R.id.home:
                finish();
                break;
            default:
                return false;
        }
        // выводим уведомление о выбранном пункте меню
        //Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);

        //toast.setGravity(Gravity.CENTER, 0, 0);
        //toast.show();
        return true;
    }

    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final SeekBar seek = new SeekBar(this);
        // final TextView tw = new TextView(this);


     /*   seek.setMax(50);
        int fs = Checksettings.getInt("fontsize",24);
        //if (fs == 100)
        seek.setProgress(fs);
        popDialog.setIcon(R.drawable.fontssmall);
        popDialog.setTitle("Размер шрифта");// + fs);

        popDialog.setView(seek);



        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                //Do something here with new value
//                Part1 p1 = new Part1();
//                p1.defFontSize += progress/10;

               // SharedPreferences.Editor editor = Checksettings.edit();
               // editor.putInt("fontsize", progress);
               // editor.apply();
                //  popDialog.notifyAll();
               // FragmentTransaction ftrans = getFragmentManager().beginTransaction();

                //  ftrans.detach(part2);
                //  ftrans.attach(part2);
             //   ftrans.detach(part1);
             //   ftrans.attach(part1);
             //   ftrans.commit();
                // part1.onResume();
                // part2.onResume();
                // popDialog.setTitle("Размер шрифта:" + String.valueOf(progress));
                // popDialog.setMessage("Размер шрифта:" + String.valueOf(progress));
            }

            public void onStartTrackingTouch(SeekBar arg0) {
                // TODO Auto-generated method stub

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
                // int fs = Checksettings.getInt("fontsize",24);
                // popDialog.setTitle("Размер шрифта:" + String.valueOf(fs));
            }
        });


        // Button OK
        popDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                });

        */
        popDialog.create();
        popDialog.show();

    }

}
