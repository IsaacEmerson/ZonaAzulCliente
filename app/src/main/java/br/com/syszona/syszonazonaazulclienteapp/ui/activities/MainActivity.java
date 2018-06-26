package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.ActivityMainBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.Menu;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.adapters.MenuAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    User user;
    ActivityMainBinding binding;
    List<Menu> menuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        menuList = Arrays.asList(
                new Menu("Teste",getResources().getDrawable(R.drawable.ic_lock)),
                new Menu("Testando",getResources().getDrawable(R.drawable.ic_eye))
        );

        binding.mainMenu.setLayoutManager(new LinearLayoutManager(this));
        getUserData();
        runAnimation(binding.mainMenu,0);

    }

    private void runAnimation(RecyclerView mainMenu, int type) {
        Context context = mainMenu.getContext();
        LayoutAnimationController controller = null;

        if(type==0){//fall down
            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_fall_down);
            binding.mainMenu.setAdapter(new MenuAdapter(menuList));
            //set animation
            binding.mainMenu.setLayoutAnimation(controller);
            binding.mainMenu.getAdapter().notifyDataSetChanged();
            binding.mainMenu.scheduleLayoutAnimation();
        }

    }

    private void getUserData(){
        Call<User> call = new RetrofitConfig().getUserService().getUser("Bearer "+getUserToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    user = response.body();
                    Toast.makeText(getApplicationContext(),"Bem vindo "+user.getName(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getUserToken(){
        SharedPreferences preferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        return preferences.getString("token","");
    }

}
