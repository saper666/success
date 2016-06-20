package ru.udevs.success;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class ParseTextUpdate extends AsyncTask<Void, Void, String> {
    public static final String APP_UPDATEPREF = "Updatesettings";
    public static final String APP_CLEARBADGE = "Clearbadgesettings";
    SharedPreferences Updatesettings;
    SharedPreferences Clearbadgesettings;
    Context ctx;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    InputStream inputStream;
    public static String LOG_TAG = "my_log";
    String mon="01";
    public ParseTextUpdate(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(Void... params) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        Updatesettings = ctx.getSharedPreferences(APP_UPDATEPREF, Context.MODE_PRIVATE);
        Clearbadgesettings = ctx.getSharedPreferences(APP_CLEARBADGE, Context.MODE_PRIVATE);
        String clearkey = Clearbadgesettings.getString("1","0");

        mon = String.valueOf(month);
        if (mon.length()==1)
            mon = "0"+mon;

        if(Article.isOnline(ctx)) {
            try {

             //     URL url = new URL("http://addvural.pe.hu/"+mon+String.valueOf(year)+".txt");

                URL url = new URL("http://addvural.pe.hu/052013.txt");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Log.d(LOG_TAG, "http://addvural.pe.hu/"+mon+String.valueOf(year)+".txt");
                inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();

                if (resultJson.equals("")) resultJson="0";

                if(!(mon+String.valueOf(year)).equals(clearkey)) {
                    SharedPreferences.Editor editorClear = Clearbadgesettings.edit();
                    editorClear.putString("1", mon + String.valueOf(year));
                    editorClear.apply();
                    Updatesettings.edit().clear().commit();
                }
                  //  Toast.makeText(ctx,resultJson,Toast.LENGTH_SHORT).show();
                 /*   SharedPreferences.Editor editor = JournalPref.edit();
                    editor.putString(String.valueOf(month) + String.valueOf(year), resultJson);
                    editor.apply();

                    if(!(mon+String.valueOf(year)).equals(clearkey))
                    {
                    SharedPreferences.Editor editorClear = Cleartabssettings.edit();
                    editorClear.putString("1",mon+String.valueOf(year));
                    editorClear.apply();
                        Starsettings.edit().clear().commit();
                    }*/

            } catch (Exception e) {
                Log.d(LOG_TAG, "ошибка: ");
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (Exception se) {
                    Log.d(LOG_TAG, "ошибка: ");
                }
                try {
                    reader.close();
                } catch (Exception se) {
                    Log.d(LOG_TAG, "ошибка: ");
                }
                // try { urlConnection.close(); } catch(Exception se) {}
            }
            return resultJson;
        }
        else {
            try {
                return resultJson = Updatesettings.getString("have", "0");
            }
            catch (Exception e)
            {
                return "";
            }
        }
    }

    @Override
    protected void onPostExecute(String strJson) {
        super.onPostExecute(strJson);
         //   Toast.makeText(ctx,resultJson,Toast.LENGTH_SHORT).show();
            Log.d(LOG_TAG, strJson);
                    SharedPreferences.Editor editor = Updatesettings.edit();
                    editor.putString("newup", resultJson);
                    editor.apply();

               /*     if(!(mon+String.valueOf(year)).equals(clearkey))
                    {
                    SharedPreferences.Editor editorClear = Cleartabssettings.edit();
                    editorClear.putString("1",mon+String.valueOf(year));
                    editorClear.apply();
                        Starsettings.edit().clear().commit();
                    }*/
        // выводим целиком полученную json-строку
      /*

        JSONObject dataJsonObj;
        String secondName;

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray friends = dataJsonObj.getJSONArray("Films");


            for (int i = 1; i < friends.length(); i++) {
                JSONObject friend = friends.getJSONObject(i);


                String author = friend.getString("author");
                String title = friend.getString("title");
                String genre = friend.getString("razdel");
                String description = friend.getString("description");

                Article.Articles.add(new Article(author, title, genre, description));

                Article.sort(ctx,"Цвет обложки");
                if (Article.sortArt.size()>0)
                {
                    try {
                        SharedPreferences.Editor editor = Checksettings.edit();
                        editor.putString("colorBack", Article.sortArt.get(Article.sortArt.size() - 1).Description);
                        editor.apply();
                    }
                    catch (Exception e){}
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d(LOG_TAG, "плохо");
            Log.d(LOG_TAG, e.toString());
        }*/
    }
}

