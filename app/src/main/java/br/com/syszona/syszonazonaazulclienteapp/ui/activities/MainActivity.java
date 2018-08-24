package br.com.syszona.syszonazonaazulclienteapp.ui.activities;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.BuyCreditsFragment;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.HistoryFragment;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.MainFragment;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.MyCreditCardsFragment;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.ProfileFragment;
import br.com.syszona.syszonazonaazulclienteapp.ui.fragments.VacanciesFragment;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        displayFrag(R.id.home);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (doubleBackToExitPressedOnce) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            UserSession.getInstance(getApplicationContext()).clearSession();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayFrag(id);
        return true;
    }

    private void displayFrag(int itemId){
        Fragment fragment = null;
        switch (itemId){
            case R.id.home:
                fragment = new MainFragment();
                break;
            case R.id.creditCards:
                fragment = new MyCreditCardsFragment();
                break;
            case R.id.buyCredit:
                fragment = new BuyCreditsFragment();
                break;
            case R.id.userData:
                fragment = new ProfileFragment();
                break;
            case R.id.myExtract:
                fragment = new HistoryFragment();
                break;
            case R.id.lookForVacancies:
                fragment = new VacanciesFragment();
                break;
            case R.id.logout:
                UserSession.getInstance(getApplicationContext()).clearSession();
                finish();
                break;

        }
        if(fragment!=null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

}
