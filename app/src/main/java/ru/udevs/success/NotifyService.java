package ru.udevs.success;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.IBinder;
import android.text.format.Time;
import android.util.Log;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by Saper on 05.06.2016.
 */
public class NotifyService extends Service {
    NotificationManager nm;
    private static final int NOTIFY_ID = 102;
    Timer timer = new Timer();;
    TimerTask tTask;
    long interval = 1000;
    SharedPreferences Notifysettings;
    public static final String APP_NOTIFY = "Notifysettings";
    int year,month,hours,minutes;
    public static String LOG_TAG = "my_log";
    public static final String APP_UPDATEPREF = "Updatesettings";
    SharedPreferences Updatesettings;
    int badgeCount;
    @Override
    public void onCreate() {
        super.onCreate();
        nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
      //  Notifysettings = getSharedPreferences(APP_NOTIFY, Context.MODE_PRIVATE);
        Updatesettings = getSharedPreferences(APP_UPDATEPREF, Context.MODE_PRIVATE);
    }





    public int onStartCommand(Intent intent, int flags, int startId) {

        Calendar c = Calendar.getInstance();
       // year = c.get(Calendar.YEAR);
       // month = c.get(Calendar.MONTH);
        minutes = c.get(Calendar.MINUTE);
        hours = c.get(Calendar.HOUR_OF_DAY);
     //   Time now = new Time();
     //   now.setToNow();
        //if(Notifysettings.getBoolean("1", true))
        //{

          //  if (now.hour == 15 && now.minute == 30)
          //  {

        new ParseTextUpdate(this).execute();


        int have = Integer.parseInt(Updatesettings.getString("have", "0"));
        int newup = Integer.parseInt(Updatesettings.getString("newup", "0"));
        Log.d(LOG_TAG, String.valueOf(have));
        Log.d(LOG_TAG, String.valueOf(newup));
        if (have<newup)
        {
            badgeCount = newup - have;
            ShortcutBadger.applyCount(getApplicationContext(), badgeCount);

          //  SharedPreferences.Editor editor = Updatesettings.edit();
          //  editor.putString("have", String.valueOf(newup));
         //   editor.apply();
            if (badgeCount>=10)
            {
                sendNotif();
                return super.onStartCommand(intent, flags, startId);
            }
        }

      //  if (hours == 9 && minutes== 0) {
      //      sendNotif();
     //       return super.onStartCommand(intent, flags, startId);
      //  }

        return 0;
    }

    void sendNotif()
    {
        Context context = getApplicationContext();
        Intent notificationIntent = new Intent(context, SplashActivity.class);

        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.logo)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.mipmap.logo))
                .setTicker("Доступны новые статьи!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setNumber(badgeCount)
                .setContentTitle("Журнал Урал")
                .setContentText("Доступны новые статьи!");
        Notification n = builder.build();
        n.ledARGB = Color.RED;
        n.ledOffMS = 0;
        n.ledOnMS = 1;
        n.flags = n.flags | Notification.FLAG_SHOW_LIGHTS;
        n.flags = n.flags | Notification.FLAG_INSISTENT;
        n.flags |= Notification.FLAG_AUTO_CANCEL;
        nm.notify(NOTIFY_ID, n);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
