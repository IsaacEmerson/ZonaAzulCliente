package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.models.Balance;
import br.com.syszona.syszonazonaazulclienteapp.models.Success;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity{
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        button = findViewById(R.id.textSplash);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://play.google.com/store/apps/details?id=br.com.syszona.syszonazonaazulclienteapp";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String version = pInfo.versionName;
        getVersion(version);
        //int verCode = pInfo.versionCode;

        //Handler handler = new Handler();
        //handler.postDelayed(this,1000);

    }

    /*@Override
    public void run() {
        UserSession userSession = UserSession.getInstance(getApplicationContext());

        if (userSession.isUserLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
    */

    private void getVersion(final String v){
        Call<Success> call = new RetrofitConfig(1).getUserService().checkVersion(1,v);
        call.enqueue(new Callback<Success>() {
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if(response.code()>=200 && response.code()<300){
                    UserSession userSession = UserSession.getInstance(getApplicationContext());

                    if (userSession.isUserLoggedIn()) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    }
                    finish();

                }else{
                    Log.i("version",response.message());
                    //Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    button.setText("Atualize o Aplicativo");
                    button.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<Success> call, Throwable t) {
                Log.i("version",t.getMessage());
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getVersion(v);
            }
        });
    }
}
