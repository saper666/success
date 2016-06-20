package ru.udevs.success;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

public class ParseTask extends AsyncTask<Void, Void, String> {
    public static final String APP_CLEARTABS = "Cleartabssettings";
    public static final String APP_PREFERENCES = "Checksettings";
    public static final String APP_JOURNALPREF = "JournalPref";
    public static final String APP_STAR = "Starsettings";
    SharedPreferences Checksettings;
    SharedPreferences JournalPref;
    SharedPreferences Cleartabssettings;
    SharedPreferences Starsettings;

    Context ctx;
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String resultJson = "";
    InputStream inputStream;
    public static String LOG_TAG = "my_log";
    String mon="01";
    public ParseTask(Context ctx)
    {
        this.ctx=ctx;
    }

    @Override
    protected String doInBackground(Void... params) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        // получаем данные с внешнего ресурса
        JournalPref = ctx.getSharedPreferences(APP_JOURNALPREF, Context.MODE_PRIVATE);
        Cleartabssettings = ctx.getSharedPreferences(APP_CLEARTABS, Context.MODE_PRIVATE);
        Starsettings = ctx.getSharedPreferences(APP_STAR, Context.MODE_PRIVATE);

        String clearkey = Cleartabssettings.getString("1","0");

        mon = String.valueOf(month);
        if (mon.length()==1)
            mon = "0"+mon;

        if(Article.isOnline(ctx)) {
            try {
                Checksettings = ctx.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

               //   URL url = new URL("http://addvural.pe.hu/"+mon+String.valueOf(year)+".json");

              //  URL url = new URL("http://addvural.pe.hu/062016.json");
                URL url = new URL("http://addvural.pe.hu/052013.json");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                Log.d(LOG_TAG, "http://addvural.pe.hu/"+mon+String.valueOf(year)+".json");
                inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                resultJson = buffer.toString();
                if (resultJson!="")
                {
                    SharedPreferences.Editor editor = JournalPref.edit();
                    editor.putString(String.valueOf(month) + String.valueOf(year), resultJson);
                    editor.apply();

                    if(!(mon+String.valueOf(year)).equals(clearkey))
                    {
                    SharedPreferences.Editor editorClear = Cleartabssettings.edit();
                    editorClear.putString("1",mon+String.valueOf(year));
                    editorClear.apply();
                        Starsettings.edit().clear().commit();
                    }
                }

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
                return resultJson = JournalPref.getString(String.valueOf(month) + String.valueOf(year), "");
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
        // выводим целиком полученную json-строку
        Log.d(LOG_TAG, strJson);

        JSONObject dataJsonObj;
        String secondName;

        try {
            dataJsonObj = new JSONObject(strJson);
            JSONArray friends = dataJsonObj.getJSONArray("Films");
            Article.Articles.clear();

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
        }
    }
}

