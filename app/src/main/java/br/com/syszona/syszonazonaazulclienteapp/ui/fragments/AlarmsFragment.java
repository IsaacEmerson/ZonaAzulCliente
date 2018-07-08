package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdsmdg.harjot.crollerTest.Croller;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentAlarmsBinding;
import br.com.syszona.syszonazonaazulclienteapp.utils.AlarmUtil;

public class AlarmsFragment extends Fragment implements View.OnClickListener{

    FragmentAlarmsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_alarms,container,false);
        View view = binding.getRoot();

        binding.setAlarm.setOnClickListener(this);

        binding.croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                binding.croller.setLabel(String.valueOf(progress));
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Meus Alarmes");
    }

    @Override
    public void onClick(View v) {
        //int i = binding.croller.getProgress();
        //AlarmUtil.setAlarm(getContext(),i);
    }
}
