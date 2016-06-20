package ru.udevs.success;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Article {
    public static final String APP_STAR = "Starsettings";
    static SharedPreferences Starsettings;

    public String Author;
    public String Title;
    public String Genre;
    public String Description;
    public static ArrayList<Article> Articles = new ArrayList<>();
    public static ArrayList<Article> sortArt  = new ArrayList<>();
    public static ArrayList<Article> searchArt  = new ArrayList<>();

    public Article(String A, String T, String G, String D){
        Author = A;
        Title = T;
        Genre = G;
        Description = D;
    }

    public static void sort(Context ctx,String genre)
    {
        sortArt.clear();
        if (Articles.size() == 0)
        {
            new ParseTask(ctx).execute();
        }
        for (Article str : Article.Articles) {
            if (str.Genre.equals(genre))
                sortArt.add(str);
        }
        Set<Article> hs = new LinkedHashSet<>();
        for (Article hart:sortArt)
        {
            hs.add(hart);
        }
        //hs.addAll(sortArt);
        sortArt.clear();
        for (Article hsart:hs)
        {
            sortArt.add(hsart);
        }
     //   sortArt.addAll(hs);
    }
    public static boolean isContainsSort(Context ctx)
    {

        if (sortArt.size() == 0)
        {

            if(! isOnline(ctx))
                Toast.makeText(ctx,"Нет доступа в интернет", Toast.LENGTH_SHORT).show();
              else
                Toast.makeText(ctx,
                    "Данный раздел не содержит записей", Toast.LENGTH_LONG).show();
            return false;
        }
        else return true;
    }

    public static void copySearch()
    {
        sortArt.clear();
        for (Article t:searchArt)
        {
            sortArt.add(t);
        }
    }
    public static boolean searchAll (Context ctx,String find)
    {
        searchArt.clear();
        if (Articles.size() >0)
        {
            for (Article art:Articles)
            {
                if(art.Author.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Description.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Title.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Genre.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
            }
            if (searchArt.size()>0)return true;
            Toast.makeText(ctx,
                    "По запросу ничего не найдено", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(ctx,
                    "По запросу ничего не найдено", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static boolean searchInSort (Context ctx,String find)
    {
        searchArt.clear();
        if (sortArt.size() >0)
        {
            for (Article art:sortArt)
            {
                if(art.Author.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Description.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Title.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
                if(art.Genre.toLowerCase().contains(find.toLowerCase())) {
                    searchArt.add(art);
                    continue;
                }
            }
            if (searchArt.size()>0)return true;
            Toast.makeText(ctx,
                    "По запросу ничего не найдено", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(ctx,
                    "По запросу ничего не найдено", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static  boolean getStars(Context ctx)
    {
        sortArt.clear();
        Starsettings = ctx.getSharedPreferences(APP_STAR, Context.MODE_PRIVATE);
        for	(Article item:Articles)
        {
            if(Starsettings.getBoolean(String.valueOf(Articles.indexOf(item)), false)){//  .contains(item.getId())) {
                sortArt.add(item);
            }
        }

        if (sortArt.size()>0)return true;
        Toast.makeText(ctx,
                "Список избранного пуст", Toast.LENGTH_SHORT).show();
        return false;
    }
    public static boolean isOnline(Context context)
    {
        try
        {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo().isConnectedOrConnecting();
        }
        catch (Exception e)
        {
            return false;
        }

    }
}
