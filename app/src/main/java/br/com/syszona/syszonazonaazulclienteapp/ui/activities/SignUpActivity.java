package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivitySignUpBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Success;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil.isOnline;
import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;


public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        binding.moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://syszona.com.br";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        binding.footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.userEmail.getText().toString().trim();
                String pass = binding.password.getText().toString().trim();
                String cPass = binding.confirmPassword.getText().toString().trim();
                String name = binding.userName.getText().toString().trim();
                if(isValidEmailAndPass(name,email,pass)){
                    if(isOnline(getApplicationContext())){
                        if(pass.equals(cPass)){
                            register(name,email,pass);    
                        }else{
                            message("As senhas não correspondem..",getApplicationContext(),1,null);
                        }
                    }else{
                        message("Seu Dispositivo não esta conectado à Internet..",getApplicationContext(),1,null);
                    }
                }
            }
        });


    }

    private void register(String name, String email, String pass) {
        // Visible the progress bar
        binding.pb.setVisibility(View.VISIBLE);
        binding.btnRegister.setEnabled(false);

        Call<Success> call = new RetrofitConfig(1).getUserService().registerClient(name,email,pass,pass);

        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {

                if(response.code()>=200 && response.code()<300){
                    message(response.body().getMessage(),getApplicationContext(),1,null);
                }else if(response.code()>=400 && response.code()<500){
                    message("Erro ao cadastrar tente novamente",getApplicationContext(),1,null);
                }else{
                    Log.e("register",response.message());
                }

                binding.pb.setVisibility(View.GONE);
                binding.btnRegister.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                message(t.getMessage(),getApplicationContext(),1,null);
                binding.pb.setVisibility(View.GONE);
                binding.btnRegister.setEnabled(true);
            }
        });
    }


    private boolean isValidEmailAndPass(String name,String email,String password) {
        if(TextUtils.isEmpty(name)) {
            binding.userName.setError("Preencha o nome");
            return false;
        }else if(TextUtils.isEmpty(email)){
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
