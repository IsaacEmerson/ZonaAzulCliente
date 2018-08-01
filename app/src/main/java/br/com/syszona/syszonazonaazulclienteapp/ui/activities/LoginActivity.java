package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivityLoginBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil.isOnline;
import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class LoginActivity extends AppCompatActivity {
    boolean doubleBackToExitPressedOnce = false;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEmail.getText().toString().trim();
                String pass = binding.password.getText().toString().trim();
                if(isValidEmailAndPass(email,pass)){
                    if(isOnline(getApplicationContext())){
                        login(email,pass);
                    }else{
                        message("Seu Dispositivo não esta conectado à Internet..",getApplicationContext(),1,null);
                    }
                }
            }
        });
    }

    private void login(String email, String password) {
        // Visible the progress bar
        binding.pb.setVisibility(View.VISIBLE);
        binding.btnLogin.setEnabled(false);

        Call<Token> call = new RetrofitConfig(1).getUserService().login(email,password);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.code()>=200 && response.code()<300){

                    Token jwt = response.body();

                    UserSession userSession = UserSession.getInstance(getApplicationContext());
                    userSession.setUserToken(jwt.getToken());
                    userSession.setUserKeepConnected(binding.keepConected.isChecked());

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();

                }else if(response.code()==401){
                    message("Usuário ou senha incorretos..",getApplicationContext(),1,null);
                }else{
                    Log.e("UserService",response.message());
                }

                binding.pb.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                message(t.getMessage(),getApplicationContext(),1,null);
                binding.pb.setVisibility(View.GONE);
                binding.btnLogin.setEnabled(true);
            }
        });
    }

    private boolean isValidEmailAndPass(String email,String password) {
        if(TextUtils.isEmpty(email)){
            binding.userEmail.setError("Preencha o email");
            return false;
        }else if(TextUtils.isEmpty(password)){
            binding.password.setError("Preencha a senha");
            return false;
        }else if(!email.contains("@")){
            message("O email parece não ser válido..",getApplicationContext(),1,null);
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        message("Pressione novamente para sair",getApplicationContext(),1,null);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
