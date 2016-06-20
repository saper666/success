package ru.udevs.success;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class ListLectionsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    ListArticleAdapter articleAdapter;
    Toolbar toolbar;
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_article11);
        articleAdapter = new ListArticleAdapter(this);
        ListView lvMain = (ListView) findViewById(R.id.lvMain);
        lvMain.setAdapter(articleAdapter);
        lvMain.setOnItemClickListener(this);
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String colorBack = "#"+ Checksettings.getString("colorBack", "00E676");
        rl = (RelativeLayout)findViewById(R.id.rl);
        try {
            rl.setBackgroundColor(Color.parseColor(colorBack));
        }
        catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Intent i = new Intent(this,LectionsActivity.class);
       // i.putExtra("pos", position);
       // startActivity(i);
        watchYoutubeVideo(position);
    }

    public void watchYoutubeVideo(int pos){
        Article art = Article.sortArt.get(pos);
        String id = art.Description.substring(art.Description.lastIndexOf("=") + 1);
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + id));
            startActivity(intent);
            }
            catch (Exception e)
            {
                Intent i = new Intent(this,LectionsActivity.class);
                i.putExtra("pos", pos);
                startActivity(i);
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(Article.searchInSort(getApplicationContext(),query))
                {
                    Article.copySearch();
                    articleAdapter.notifyDataSetChanged();
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
