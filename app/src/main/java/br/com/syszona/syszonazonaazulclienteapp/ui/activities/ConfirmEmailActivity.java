package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import br.com.syszona.syszonazonaazulclienteapp.R;

import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivityConfirmEmailBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Success;
import br.com.syszona.syszonazonaazulclienteapp.models.UserInitialData;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;


public class ConfirmEmailActivity extends AppCompatActivity {

    ActivityConfirmEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_confirm_email);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(binding.edtCode.getText().toString())){
                    confirmEmail(binding.edtCode.getText().toString());
                }else{
                    message("Digite o código",getApplicationContext(),1,null);
                }
            }
        });

        binding.footer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConfirmEmailActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    private void confirmEmail(String token){
        binding.pb.setVisibility(View.VISIBLE);
        binding.btnConfirm.setEnabled(false);
        Call<UserInitialData> call = new RetrofitConfig(1).getUserService().confirmAccount(token);

        call.enqueue(new Callback<UserInitialData>() {
            @Override
            public void onResponse(Call<UserInitialData> call, Response<UserInitialData> response) {

                if(response.code()>=200 && response.code()<300){
                    message("Email Validado com Sucesso",getApplicationContext(),1,null);
                    Intent intent = new Intent(ConfirmEmailActivity.this, FinalRegisterActivity.class);
                    intent.putExtra("name",response.body().getName());
                    intent.putExtra("email",response.body().getEmail());
                    intent.putExtra("token",response.body().getConfirmToken());
                    startActivity(intent);
                    finish();

                }else if(response.code()>=400 && response.code()<500){
                    message("Erro, tente novamente",getApplicationContext(),1,null);
                }else{
                    Log.e("confirm",response.message());
                }

                binding.pb.setVisibility(View.GONE);
                binding.btnConfirm.setEnabled(true);
            }

            @Override
            public void onFailure(Call<UserInitialData> call, Throwable t) {
                message(t.getMessage(),getApplicationContext(),1,null);
                binding.pb.setVisibility(View.GONE);
                binding.btnConfirm.setEnabled(true);
            }
        });
    }
}
