package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;

import br.com.syszona.syszonazonaazulclienteapp.R;

import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivityFinalRegisterBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.CitiesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Success;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.UserData;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.adapters.CitiesAdapter;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MaskEditUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class FinalRegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ActivityFinalRegisterBinding binding;
    CitiesList citiesList;
    String token;
    int vehicle_id = 1;
    int city_actual = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_final_register);

        binding.edtCpf.addTextChangedListener(MaskEditUtil.mask(binding.edtCpf, MaskEditUtil.FORMAT_CPF));
        //binding.edtCep.addTextChangedListener(MaskEditUtil.mask(binding.edtCep, MaskEditUtil.FORMAT_CEP));
        binding.edtDateBir.addTextChangedListener(MaskEditUtil.mask(binding.edtDateBir, MaskEditUtil.FORMAT_DATE));
        binding.edtPhone.addTextChangedListener(MaskEditUtil.mask(binding.edtPhone, MaskEditUtil.FORMAT_FONE));
        binding.edtPlaque.addTextChangedListener(MaskEditUtil.mask(binding.edtPlaque, "###-####"));

        token = getIntent().getStringExtra("token");
        String name = getIntent().getStringExtra("name");

        String hello = binding.hello.getText().toString();
        hello = hello.replace("name", name);
        binding.hello.setText(hello);

        if(ConnectionUtil.isOnline(getApplicationContext())){
            getCities();
        }else{
            MessageUtil.message("Você não esta conectado à Internet",getApplicationContext(),1,null);
            startActivity(new Intent(getApplicationContext(), NoInternetActivity.class));
        }

        binding.btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidData()){
                    finalizeRegister();
                }
            }
        });

    }

    private void finalizeRegister() {
        // Visible the progress bar
        binding.pb.setVisibility(View.VISIBLE);
        binding.btnFinalize.setEnabled(false);

        /*Call<Success> call = new RetrofitConfig(1).getUserService().finalizeRegister(token,vehicle_id,binding.edtPhone.getText().toString(),
                binding.edtPlaque.getText().toString(),binding.edtCpf.getText().toString(),binding.edtCep.getText().toString(),
                Integer.parseInt(binding.edtNumber.getText().toString()),
                binding.edtCity.getText().toString().trim(),binding.edtUF.getText().toString(),
                binding.edtStreet.getText().toString().trim(),binding.edtNei.getText().toString().trim(),
                binding.edtComp.getText().toString().trim(),binding.edtDateBir.getText().toString(),city_actual);*/
        Call<Success> call = new RetrofitConfig(1).getUserService().finalizeRegister(token,vehicle_id,binding.edtPhone.getText().toString(),
                binding.edtPlaque.getText().toString(),binding.edtCpf.getText().toString()
                ,binding.edtDateBir.getText().toString(),city_actual);

        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {

                if(response.code()>=200 && response.code()<300){

                    message(response.body().getMessage(),getApplicationContext(),1,null);
                    startActivity(new Intent(FinalRegisterActivity.this, LoginActivity.class));
                    finish();

                }else if(response.code()>=400 && response.code()<500){
                    message("Erro ao cadastrar tente novamente",getApplicationContext(),1,null);
                }else{
                    Log.e("register",response.message());
                }

                binding.pb.setVisibility(View.GONE);
                binding.btnFinalize.setEnabled(true);
            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                message(t.getMessage(),getApplicationContext(),1,null);
                binding.pb.setVisibility(View.GONE);
                binding.btnFinalize.setEnabled(true);
            }
        });
    }

    private boolean isValidData() {
        /*if(TextUtils.isEmpty(binding.edtCep.getText().toString())|| !validCpf(binding.edtCpf.getText().toString())) {
            binding.edtCep.setError("Digite o CPF corretamente");
            return false;
        }else*/
        if(TextUtils.isEmpty(binding.edtDateBir.getText().toString()) || !validDate(binding.edtDateBir.getText().toString())){
            binding.edtDateBir.setError("Preencha a Data de Nascimento corretamente");
            return false;
        }else if(TextUtils.isEmpty(binding.edtPhone.getText().toString()) || !validPhone(binding.edtPhone.getText().toString())){
            binding.edtPhone.setError("Preencha o campo Telefone corretamente");
            return false;
        }else if(TextUtils.isEmpty(binding.edtPlaque.getText().toString()) || !validPlaque(binding.edtPlaque.getText().toString())) {
            binding.edtPlaque.setError("Preencha o campo Placa corretamente");
            return false;
        }
        /*}else if(TextUtils.isEmpty(binding.edtCep.getText().toString())|| !validCep(binding.edtCep.getText().toString())){
            binding.edtCep.setError("Preencha o campo CEP corretamente");
            return false;
        }else if(TextUtils.isEmpty(binding.edtUF.getText().toString())){
            binding.edtUF.setError("Preencha o campo UF");
            return false;
        }else if(TextUtils.isEmpty(binding.edtCity.getText().toString())){
            binding.edtCity.setError("Preencha o campo Cidade");
            return false;
        }else if(TextUtils.isEmpty(binding.edtStreet.getText().toString())){
            binding.edtStreet.setError("Preencha o campo Rua");
            return false;
        }else if(TextUtils.isEmpty(binding.edtNumber.getText().toString())){
            binding.edtNumber.setError("Preencha o campo Nº");
            return false;
        }else if(TextUtils.isEmpty(binding.edtNei.getText().toString())){
            binding.edtNei.setError("Preencha o campo Bairro");
            return false;
        }else if(TextUtils.isEmpty(binding.edtComp.getText().toString())){
            binding.edtComp.setError("Preencha o campo Complemento");
            return false;
        }
        */

        return true;
    }

    private boolean validCpf(String cpf) {
        String c = cpf.replace(".","");
        c = c.replace("-","");
        if(!isNumeric(c)){
            return false;
        }
        return true;
    }

    private boolean validDate(String date) {
        String d = date.replace("/","");
        if(!isNumeric(d)){
            return false;
        }
        return true;
    }

    private boolean validPhone(String phone) {
        String pho = phone.replace("(","");
        pho = pho.replace(")","");
        pho = pho.replace("-","");

        if(!isNumeric(pho)){
            return false;
        }
        return true;
    }

    private boolean validCep(String cep) {
        String c = cep.replace("-","");
        if(!isNumeric(c)){
            return false;
        }
        return true;
    }

    public static boolean isNumeric(String str){
        try{
            double d = Double.parseDouble(str);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    private boolean validPlaque(String plaque) {
        String[] pla = plaque.split("-");
        if(isNumeric(pla[0])){
            return false;
        }
        if(!isNumeric(pla[1])){
            return false;
        }
        return true;
    }

    private void getCities(){
        binding.pb.setVisibility(View.VISIBLE);
        Call<CitiesList> call = new RetrofitConfig(1).getUserService().getCitiesRegister();
        call.enqueue(new Callback<CitiesList>() {
            @Override
            public void onResponse(Call<CitiesList> call, Response<CitiesList> response) {
                if(response.code()>=200 && response.code()<300){
                    citiesList = response.body();
                    addItemsOnSpinnerCities(citiesList);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()>=400 && response.code()<=500){
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                    getCities();
                }else if(response.message().equals("")){
                    getCities();
                }else{
                    Log.i("getCities",response.message());
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    getCities();
                }
            }
            @Override
            public void onFailure(Call<CitiesList> call, Throwable t) {
                Log.i("getCities",t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getCities();
            }
        });
    }

    public void addItemsOnSpinnerCities(CitiesList citiesList) {
        binding.spinnerCities.setOnItemSelectedListener(this);
        CitiesAdapter citiesAdapter = new CitiesAdapter(getApplicationContext(),citiesList);
        binding.spinnerCities.setAdapter(citiesAdapter);
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_car:
               if (checked)
                    vehicle_id = 1 ;
                break;
            case R.id.radio_moto:
                if (checked)
                    vehicle_id = 2 ;
                break;
            case R.id.add_truck:
                if (checked)
                    vehicle_id = 3 ;
                break;
            case R.id.radio_bus:
                if (checked)
                    vehicle_id = 4 ;
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        city_actual = citiesList.getCities().get(position).getId();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
