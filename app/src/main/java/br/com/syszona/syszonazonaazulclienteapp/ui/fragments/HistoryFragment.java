package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.models.HistoricList;
import br.com.syszona.syszonazonaazulclienteapp.models.PlaquesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.LoginActivity;
import br.com.syszona.syszonazonaazulclienteapp.ui.adapters.HistoricAdapter;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class HistoryFragment extends Fragment implements HistoricAdapter.ItemClickListener {
    HistoricList historicList;
    HistoricAdapter adapter;
    ProgressBar pb;
    int page=1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
        final View view = inflater.inflate(R.layout.fragment_history,container,false);
        pb = view.findViewById(R.id.pb);

        view.findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page<historicList.getLastPage()){
                    page++;
                    getHistoric(page,view);
                }else{
                    Toast.makeText(getContext(),"Você esta na ultima pagina",Toast.LENGTH_LONG).show();
                }

            }
        });

        view.findViewById(R.id.btnPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page<=1){
                    Toast.makeText(getContext(),"Você esta na primeira pagina",Toast.LENGTH_LONG).show();
                }else{
                    page--;
                    getHistoric(page,view);
                }
            }
        });

        if(ConnectionUtil.isOnline(getContext())){
            getHistoric(page,view);
        }else{
            MessageUtil.message("Você não esta conectado à Internet",getContext(),1,null);
        }

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getContext(), "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    private void getHistoric(final int page, final View view){
        pb.setVisibility(View.VISIBLE);
        Call<HistoricList> call = new RetrofitConfig().getUserService().getUserHistoric("Bearer "+ UserSession.getInstance(getContext()).getUserToken(),page);
        call.enqueue(new Callback<HistoricList>() {
            @Override
            public void onResponse(Call<HistoricList> call, Response<HistoricList> response) {
                if(response.code()>=200 && response.code()<300){
                    historicList = response.body();
                    setUpRecyclerView(view);
                    pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getHistoric(page,view);
                }else if(response.message().equals("")){
                    getHistoric(page,view);
                }else{
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getHistoric(page,view);
                }
            }
            @Override
            public void onFailure(Call<HistoricList> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getHistoric(page,view);
            }
        });
    }

    private void setUpRecyclerView(View view){
        // set up the RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.rvHistoric);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HistoricAdapter(getContext(), historicList.getData());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),LinearLayoutManager.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Meu Histórico");
    }

}
