package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import br.com.syszona.syszonazonaazulclienteapp.R;

public class MainFragment extends Fragment implements View.OnClickListener{
   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
       View view = inflater.inflate(R.layout.fragment_main,container,false);
       return view;
   }

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
       super.onViewCreated(view, savedInstanceState);
       getActivity().setTitle("Inicio");
   }

    @Override
    public void onClick(View v) {

    }
}
