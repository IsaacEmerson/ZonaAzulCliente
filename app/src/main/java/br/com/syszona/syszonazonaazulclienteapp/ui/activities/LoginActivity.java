package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.databinding.adapters.SeekBarBindingAdapter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivityLoginBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil.isOnline;
import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences preferences;
    boolean doubleBackToExitPressedOnce = false;
    private final int REQUEST_REGISTER=1;
    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        verifyLoginPreferences();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEmail.getText().toString().trim();
                String pass = binding.password.getText().toString().trim();
                if(isValidEmailAndPass(email,pass)){
                    if(isOnline(getApplicationContext())){
                        login(email,pass);
                    }else{
                        message("Seu Dispositivo não esta conectado à Internet..",getApplicationContext(),1);
                    }
                }
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(register,REQUEST_REGISTER);
            }
        });

    }

    private void login(String email, String password) {

        // Visible the progress bar and text view
        binding.pb.setVisibility(View.VISIBLE);

        Call<Token> call = new RetrofitConfig().getUserService().login(email,password);

        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.code()>=200 && response.code()<300){

                    Token jwt = response.body();

                    addLoginPreferences(preferences,jwt.getToken());

                    goTo(new Intent(getApplicationContext(), MainActivity.class));

                }else if(response.code()==401){
                    message("Usuário ou senha incorretos..",getApplicationContext(),1);
                }else{
                    Log.e("UserService",response.message());
                }

                binding.pb.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                binding.pb.setVisibility(View.GONE);
                message(t.getMessage(),getApplicationContext(),1);
            }
        });
    }

    private void verifyLoginPreferences(){
        preferences = getSharedPreferences("user_preferences",MODE_PRIVATE);
        if(preferences.contains("token") && preferences.getBoolean("keepConected",false)){
            Intent home = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(home);
            finish();
        }
    }

    private void goTo(Intent where){
        startActivity(where);
        finish();
    }

    private void addLoginPreferences(SharedPreferences preferences,String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token",token);
        editor.putBoolean("keepConected", binding.keepConected.isChecked());
        editor.commit();
    }

    private boolean isValidEmailAndPass(String email,String password) {
        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            message("Preencha os campos email e senha..",getApplicationContext(),1);
            return false;
        }else if(!email.contains("@") || !email.contains(".com")){
            message("O email parece não ser válido..",getApplicationContext(),1);
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_REGISTER) {
            if(resultCode == Activity.RESULT_OK){
                String result=data.getStringExtra("result");
                Log.d("login",result);
            }else if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                Log.d("login","no result");
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        message("Pressione novamente para sair",getApplicationContext(),1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
