package thunderbytes.com.formulanews.Managers;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import thunderbytes.com.formulanews.Activities.DetailRace;
import thunderbytes.com.formulanews.Broadcast.NotificationPublisher;
import thunderbytes.com.formulanews.MainActivity;
import thunderbytes.com.formulanews.Models.Race;
import thunderbytes.com.formulanews.R;

public class NotificationManager {


    public static void setReminder(Context context, Class<?> cls, Class<?> openClass, Race race) {

        long futureInMillis;
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        Intent intent = new Intent(context, DetailRace.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(DetailRace.ITEM_RACE, race);

        PendingIntent pendingIntentOpenClass = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);


        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(race.getRaceName()+" 🏁");
        builder.setContentText("La gara inizierà tra 10 min.");
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setContentIntent(pendingIntentOpenClass);
        builder.setAutoCancel(true);
        builder.setColor(Color.RED);
        builder.setSmallIcon(R.drawable.ic_stat_name);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(NotificationPublisher.CHANNEL_ID);
        }

        Notification notification = builder.build();

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, race.getId());
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        Date dateFormat = calculateTime(race);
        Log.d("TIME","calendar  FORMATTATO: "+ dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat);

        calendar.set(calendar.MINUTE, calendar.get(Calendar.MINUTE)-10 );
        //calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+1 );

        Log.d("TIME","calendar  houar finale non formattato: "+ calendar.getTime());


        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), pendingIntent);

    }


    public static void cancelReminder(Context context,Class<?> cls)
    {
        // Disable a receiver
        ComponentName receiver = new ComponentName(context, cls);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        Intent notificationIntent = new Intent(context, cls);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        pendingIntent.cancel();
    }

    public static Date calculateTime(Race vRace) {
        SimpleDateFormat vInputDateFormat = new SimpleDateFormat("HH:mm:ss'Z'");
        vInputDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date formattedDate = null;

        try {
            Date date = vInputDateFormat.parse(vRace.getTime());
            vInputDateFormat.setTimeZone(TimeZone.getDefault());
            SimpleDateFormat vDateFormat = new SimpleDateFormat("HH:mm");

            if(TimeZone.getDefault().inDaylightTime(calculateDate(0, vRace))){

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.HOUR_OF_DAY, 1);

                String test = android.text.format.DateFormat.format("dd.MM.yyyy", vRace.getDate())  + " " + vDateFormat.format(cal.getTime());

                SimpleDateFormat vFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

                formattedDate = vFormat.parse(test);
            }else{

                String test = android.text.format.DateFormat.format("dd.MM.yyyy", vRace.getDate())  + " " + vDateFormat.format(date);

                SimpleDateFormat vFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

                formattedDate = vFormat.parse(test);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }

    public static Date calculateDate(int amount, Race vRace){
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(vRace.getDate());
        mCalendar.add(Calendar.DATE, amount);

        return mCalendar.getTime();
    }


}
