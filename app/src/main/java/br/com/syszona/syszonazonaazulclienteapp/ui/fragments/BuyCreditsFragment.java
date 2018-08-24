package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import br.com.syszona.syszonazonaazulclienteapp.R;


public class BuyCreditsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        View view = inflater.inflate(R.layout.buy_webview,container,false);

        WebView myWebView = (WebView) view.findViewById(R.id.webview);
        myWebView.loadUrl("http://syszona.com.br/credits/app?email="+MainFragment.user.getEmail());

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);

        myWebView.setWebViewClient(new WebViewClient());

        //Token teste = new Token();
        //teste.setToken("kakakakkaka");

        //myWebView.addJavascriptInterface(teste,"Android");

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Comprar Créditos");
    }
}
