package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentVacanciesBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Address;
import br.com.syszona.syszonazonaazulclienteapp.models.AddressesList;
import br.com.syszona.syszonazonaazulclienteapp.models.CitiesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.LoginActivity;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.MainActivity;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.NoInternetActivity;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import cn.carbs.android.library.MDDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class VacanciesFragment extends Fragment {

    FragmentVacanciesBinding binding;
    AddressesList addressesList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_vacancies,container,false);
        View view = binding.getRoot();

        if(ConnectionUtil.isOnline(getContext())){
            getAddresses(MainFragment.parkCityId);
        }else{
            MessageUtil.message("Você não esta conectado à Internet",getContext(),1,null);
            startActivity(new Intent(getContext(), NoInternetActivity.class));
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Vagas");
    }

    private void getAddresses(final int parkCityId){
        binding.pb.setVisibility(View.VISIBLE);
        Call<AddressesList> call = new RetrofitConfig().getUserService().getAddresses("Bearer "+ UserSession.getInstance(getContext()).getUserToken(),parkCityId);
        call.enqueue(new Callback<AddressesList>() {
            @Override
            public void onResponse(Call<AddressesList> call, Response<AddressesList> response) {
                if(response.code()>=200 && response.code()<300){
                    addressesList = response.body();
                    setupList(addressesList);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getAddresses(parkCityId);
                }else{
                    Log.i("getAddresses",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<AddressesList> call, Throwable t) {
                Log.i("getAddresses",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getAddresses(parkCityId);
            }
        });
    }

    private void setupList(final AddressesList addressesList) {
        final ListView list = new ListView(getContext());
        list.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ArrayAdapter<Address> adapter = new ArrayAdapter<Address>(getContext(),
                android.R.layout.simple_list_item_1, addressesList.getAddresses());
        list.setClickable(true);
        list.setAdapter(adapter);

        binding.ll.addView(list);

    }

    private void refreshToken() {
        Call<Token> call = new RetrofitConfig(1).getUserService().refreshToken("Bearer "+UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {

                if(response.code()>=200 && response.code()<300){
                    Token jwt = response.body();
                    UserSession.getInstance(getContext()).setUserToken(jwt.getToken());
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

    private void responseError(ResponseBody responseBody){
        try {
            String error = responseBody.string();
            if(error.contains("token_expired")){
                refreshToken();
            }else if(error.contains("not_activated")){
                message("Usuário não ativado",getContext(),1,null);
                UserSession.getInstance(getContext()).clearSession();
                startActivity(new Intent(getContext(),LoginActivity.class));
            }else if(error.contains("user_not_found")){
                message("Usuário não ativado",getContext(),1,null);
                UserSession.getInstance(getContext()).clearSession();
                startActivity(new Intent(getContext(),LoginActivity.class));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
