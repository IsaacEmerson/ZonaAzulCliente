package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentBuyCreditsBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.CreditCard;
import cn.carbs.android.library.MDDialog;


public class BuyCreditsFragment extends Fragment implements View.OnClickListener{

    FragmentBuyCreditsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        View view = inflater.inflate(R.layout.fragment_buy_credits,container,false);
        //binding.confirmBuyCredit.setOnClickListener(this);

        showBuyDialog();

        Button confirm = view.findViewById(R.id.confirm_buy_credit);
        confirm.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Comprar Créditos");
    }

    @Override
    public void onClick(View v) {
        showBuyDialog();
    }


    private void showBuyDialog(){
        new MDDialog.Builder(getContext())
                .setTitle("Alerta")
                .setContentView(R.layout.dialog_buy_credits)
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Ir pro site", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String url = "http://zona-azul-teste.herokuapp.com/credits";
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                }).create()
                .show();
    }
}
