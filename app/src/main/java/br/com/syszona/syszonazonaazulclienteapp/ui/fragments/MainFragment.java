package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentMainBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.MainActivity;
import br.com.syszona.syszonazonaazulclienteapp.utils.AlarmUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;


public class MainFragment extends Fragment implements View.OnClickListener{

    User user;
    FragmentMainBinding binding;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
       View view = binding.getRoot();

       //-------------------------------------------------------------------
       if(ConnectionUtil.isOnline(getContext())){
           getUserData();
       }else{
           MessageUtil.message("Você não esta conectado à Internet",getContext(),1,null);
       }

       binding.btnConfirmPark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                AlarmUtil.setAlarm(getContext());
                message("Alarme Setado",getContext(),1,null);
           }
       });

       binding.alarm01.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("1");
           }
       });
       binding.alarm02.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("2");
           }
       });
       binding.alarm03.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("3");
           }
       });

       binding.showAlarm01.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("1"))+" min");
       binding.showAlarm02.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("2"))+" min");
       binding.showAlarm03.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("3"))+" min");

       //---------------------------------------------------------------------
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

    private void getUserData(){
        binding.pb.setVisibility(View.VISIBLE);

        Call<User> call = new RetrofitConfig().getUserService().getUser("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()>=200 && response.code()<300){
                    user = response.body();
                    binding.setUserData(user);
                    Log.d("main Debug log",String.valueOf(response.code()));
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    refreshToken();
                    Log.d("main Debug unauth",String.valueOf(response.code()));
                    binding.pb.setVisibility(View.GONE);
                }else if(response.message().equals("")){
                    Log.d("main Debug vazi",String.valueOf(response.code()));
                    binding.pb.setVisibility(View.GONE);
                }else{
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    Log.d("main Debug",String.valueOf(response.code()));
                    binding.pb.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("main Debug fail","kk");
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                //getUserData();
            }
        });
    }

    private void refreshToken() {
        Call<Token> call = new RetrofitConfig().getUserService().refreshToken("Bearer "+UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.code()>=200 && response.code()<300){
                    Token jwt = response.body();
                    UserSession.getInstance(getContext()).setUserToken(jwt.getToken());
                    getUserData();
                }else{
                    Log.e("UserService",response.message());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                message(t.getMessage(),getContext(),1,null);
            }
        });
    }

    private void showAlarmDialog(final String id){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_set_alarm, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Alarme "+id);
        dialogBuilder.setIcon(R.drawable.ic_alarm);

        final Croller croller = dialogView.findViewById(R.id.croller);
        Button cancel = dialogView.findViewById(R.id.cancel_action);
        Button confirm = dialogView.findViewById(R.id.confirm_action);

        final AlertDialog b = dialogBuilder.create();

        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                croller.setLabel(String.valueOf(progress));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmUtil.saveAlarmPreference(getContext(),id,croller.getProgress());
                if(id.equals("1")){
                    binding.showAlarm01.setText(String.valueOf(croller.getProgress())+" min");
                }else if(id.equals("2")){
                    binding.showAlarm02.setText(String.valueOf(croller.getProgress())+" min");
                }else{
                    binding.showAlarm03.setText(String.valueOf(croller.getProgress())+" min");
                }
                message("Alarme setado com sucesso !",getContext(),1,null);
                b.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        b.show();
    }
}
