package br.com.syszona.syszonazonaazulclienteapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import br.com.syszona.syszonazonaazulclienteapp.R;

public class MessageUtil {

    public static void message(String text, Context context, int type){
        int gravity = Gravity.CENTER; // the position of toast
        int xOffset = 0; // horizontal offset from current gravity
        int yOffset = 50; // vertical offset from current gravity
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        if(type==2){
            toast.show();
        }else if(type==1){
            toast.setGravity(gravity, xOffset, yOffset);
            toast.getView().setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            toast.show();
        }

    }
}
