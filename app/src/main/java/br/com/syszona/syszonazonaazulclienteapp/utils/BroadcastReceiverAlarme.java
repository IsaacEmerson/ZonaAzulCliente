package br.com.syszona.syszonazonaazulclienteapp.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BroadcastReceiverAlarme extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        int min = intent.getIntExtra("min",0);

        Log.i("Script", "-> Alarme"+String.valueOf(min));

        NotificationUtil.NotifyUserTime(context,min);

    }

}
