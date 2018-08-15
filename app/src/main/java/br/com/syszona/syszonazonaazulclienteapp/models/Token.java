package br.com.syszona.syszonazonaazulclienteapp.models;

import android.webkit.JavascriptInterface;

public class Token {
    private String token;

    @JavascriptInterface
    public String getToken() {
        return token;
    }
    @JavascriptInterface
    public void setToken(String token) {
        this.token = token;
    }
}
