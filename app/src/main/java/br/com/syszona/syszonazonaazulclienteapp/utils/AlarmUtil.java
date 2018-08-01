package br.com.syszona.syszonazonaazulclienteapp.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmUtil {

    public static void saveAlarmPreference(Context context, String id, int minutes){
        UserSession.getInstance(context).setAlarm(id,minutes);
    }

    public static void setAlarm(Context context, int rateMinutes){

        //boolean alarmeAtivo = (PendingIntent.getBroadcast(context, 0, new Intent("ALARME_DISPARADO"), PendingIntent.FLAG_NO_CREATE) == null);
        //if(alarmeAtivo){
        for(int i=1;i<4;i++) {
            int minutes = UserSession.getInstance(context).getAlarm(String.valueOf(i));
            if(minutes==0)continue;

            Log.i("Script", "Novo alarme "+String.valueOf(minutes));
            Intent intent = new Intent("ALARME_DISPARADO");
            intent.putExtra("min", minutes);
            PendingIntent p = PendingIntent.getBroadcast(context, i, intent, PendingIntent.FLAG_ONE_SHOT);
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(System.currentTimeMillis());
            c.add(Calendar.MINUTE,  rateMinutes-minutes);
            AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            alarme.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), p);
        }
        //}
        //else{
          //  Log.i("Script", "Alarme já ativo");
        //}
    }

    public static void cancelAlarm(Context context){
        Intent intent = new Intent("ALARME_DISPARADO");
		PendingIntent p = PendingIntent.getBroadcast(context, 0, intent, 0);
		AlarmManager alarme = (AlarmManager) context.getSystemService(ALARM_SERVICE);
		alarme.cancel(p);
    }

}