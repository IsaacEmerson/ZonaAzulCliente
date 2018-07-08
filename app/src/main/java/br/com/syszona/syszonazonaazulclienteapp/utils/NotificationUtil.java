package br.com.syszona.syszonazonaazulclienteapp.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.MainActivity;
import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil{

    public static void NotifyUserTime(Context context,int min){
        NotificationManager nm = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker("Alerta Estacionamento");
        builder.setContentTitle("Alerta de tempo estacionamento");
        builder.setContentText("Passaram "+min+" minutos..");
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_user);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_park));
        builder.setContentIntent(p);
        /*NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle();
        String [] descs = new String[]{"Descrição 1", "Descrição 2", "Descrição 3", "Descrição 4"};
        for(int i = 0; i < descs.length; i++){
            style.addLine(descs[i]);
        }
        builder.setStyle(style);*/
        Notification n = builder.build();
        n.vibrate = new long[]{150, 300, 150, 600,150, 300, 150, 600,150, 300, 150, 600,
                150, 300, 150, 600,150, 300, 150, 600,150, 300, 150, 600,
                150, 300, 150, 600,150, 300, 150, 600,150, 300, 150, 600};
        n.flags = Notification.FLAG_AUTO_CANCEL;
        nm.notify(R.drawable.ic_lock, n);
        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            Ringtone toque = RingtoneManager.getRingtone(context, som);

            toque.play();
        }
        catch(Exception e){}
    }
}
