package ru.udevs.success;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import java.util.Calendar;
import ru.udevs.success.R;
import me.leolin.shortcutbadger.ShortcutBadger;


public class SplashActivity extends Activity {
    public static final String APP_PREFERENCES = "Checksettings";
    SharedPreferences Checksettings;
    public static final String APP_UPDATEPREF = "Updatesettings";
    SharedPreferences Updatesettings;
    private PendingIntent pendingIntent;
    RelativeLayout rl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);
        new ParseTask(this).execute();


       // startService(new Intent(this, NotifyService.class));
        Intent myIntent = new Intent(this, BootBroadReceiv.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent,0);
        Calendar calendar = Calendar.getInstance();
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

        Updatesettings = getSharedPreferences(APP_UPDATEPREF, Context.MODE_PRIVATE);
        int newup = Integer.parseInt(Updatesettings.getString("newup", "0"));
        SharedPreferences.Editor editor = Updatesettings.edit();
        editor.putString("have", String.valueOf(newup));
        editor.apply();


        ShortcutBadger.removeCount(this);
        Checksettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        String colorBack = "#"+ Checksettings.getString("colorBack", "00E676");
        rl = (RelativeLayout)findViewById(R.id.rl);
        try {
            rl.setBackgroundColor(Color.parseColor(colorBack));
        }
        catch (Exception ex){rl.setBackgroundColor(Color.parseColor("#00E676"));}
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        new CountDownTimer(2000,2000)
        {
            public void onFinish()
            {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
            @Override
            public void onTick(long l) {
            }
        }.start();
    }
}
