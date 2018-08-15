package br.com.syszona.syszonazonaazulclienteapp.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);

        Button reload = findViewById(R.id.btn_reload);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testConnection();
            }
        });

    }

    private void testConnection() {
        if(ConnectionUtil.isOnline(getApplicationContext())){
            startActivity(new Intent(getApplicationContext(),SplashScreen.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(),"Sem conexão", Toast.LENGTH_LONG);
        }
    }
}
