package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentProfileBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);
        View view = binding.getRoot();
        if(ConnectionUtil.isOnline(getContext())){
            getUserData();
        }else{
            MessageUtil.message("Você não esta conectado à Internet",getContext(),1,null);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Meus Dados");
    }

    private void getUserData(){
        Call<User> call = new RetrofitConfig().getUserService().getUser("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()>=200 && response.code()<300){
                    user = response.body();
                    binding.setUserData(user);
                }else if(response.code()==401 && response.message().equals("Unauthorized") ){
                    refreshToken();
                }else{
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
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

}
