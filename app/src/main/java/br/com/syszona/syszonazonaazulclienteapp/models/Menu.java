package br.com.syszona.syszonazonaazulclienteapp.models;

import android.graphics.drawable.Drawable;

public class Menu {
    private String option;
    private Drawable icon;

    public Menu(String option, Drawable icon) {
        this.option = option;
        this.icon = icon;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
