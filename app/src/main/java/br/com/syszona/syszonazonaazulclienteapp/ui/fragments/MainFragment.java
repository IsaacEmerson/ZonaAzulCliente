package br.com.syszona.syszonazonaazulclienteapp.ui.fragments;

import android.animation.StateListAnimator;
import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.harjot.crollerTest.Croller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.databinding.FragmentMainBinding;
import br.com.syszona.syszonazonaazulclienteapp.models.ActiveCard;
import br.com.syszona.syszonazonaazulclienteapp.models.Address;
import br.com.syszona.syszonazonaazulclienteapp.models.AddressesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Balance;
import br.com.syszona.syszonazonaazulclienteapp.models.Card;
import br.com.syszona.syszonazonaazulclienteapp.models.CardList;
import br.com.syszona.syszonazonaazulclienteapp.models.CitiesList;
import br.com.syszona.syszonazonaazulclienteapp.models.City;
import br.com.syszona.syszonazonaazulclienteapp.models.Plaque;
import br.com.syszona.syszonazonaazulclienteapp.models.PlaquesList;
import br.com.syszona.syszonazonaazulclienteapp.models.Rate;
import br.com.syszona.syszonazonaazulclienteapp.models.RateList;
import br.com.syszona.syszonazonaazulclienteapp.models.Token;
import br.com.syszona.syszonazonaazulclienteapp.models.User;
import br.com.syszona.syszonazonaazulclienteapp.providers.RetrofitConfig;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.LoginActivity;
import br.com.syszona.syszonazonaazulclienteapp.ui.activities.MainActivity;
import br.com.syszona.syszonazonaazulclienteapp.ui.adapters.CitiesAdapter;
import br.com.syszona.syszonazonaazulclienteapp.utils.AlarmUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.ConnectionUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MaskEditUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil;
import br.com.syszona.syszonazonaazulclienteapp.utils.UserSession;
import cn.carbs.android.library.MDDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.syszona.syszonazonaazulclienteapp.utils.MessageUtil.message;
import android.widget.LinearLayout.LayoutParams;


public class MainFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    User user;
    Balance balance;
    CardList cards;
    PlaquesList plaquesList;
    RateList rateList;
    CitiesList citiesList;
    Rate parkRate;
    int cardAntVehi = 0;
    int cardAntRate = 0;
    int parkRateId = 0;
    int parkCityId = 2;
    int parkAddressId = 0;
    int parkPlaqueId = 0;
    FragmentMainBinding binding;

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle save) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
       View view = binding.getRoot();

       //-------------------------------------------------------------------
       if(ConnectionUtil.isOnline(getContext())){
           getUser();
           getBalance();
           getCards();
           getPlaques();
           getRates(2);
           getCities();
       }else{
           MessageUtil.message("Você não esta conectado à Internet",getContext(),1,null);
       }

       binding.btnConfirmPark.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                getAddresses(parkCityId);
           }
       });

       binding.addPlaque.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                addNewPlaqueDialog();
           }
       });

       binding.alarm01.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("1");
           }
       });
       binding.alarm02.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("2");
           }
       });
       binding.alarm03.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlarmDialog("3");
           }
       });

       binding.showAlarm01.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("1"))+" min");
       binding.showAlarm02.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("2"))+" min");
       binding.showAlarm03.setText(String.valueOf(UserSession.getInstance(getContext()).getAlarm("3"))+" min");

       //---------------------------------------------------------------------

       binding.cvAlerts.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showAlertsDialog();
           }
       });
       binding.cvBalance.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showBalanceDialog();
           }
       });
       binding.cvCads.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               showCadsDialog();
           }
       });

       return view;
   }

    @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
       super.onViewCreated(view, savedInstanceState);
       getActivity().setTitle("Inicio");
   }

    private void initPlaquesCards(final PlaquesList plaques){
       for(final Plaque plaque : plaques.getPlaques()) {
           final CardView card = new CardView(getContext());
           card.setId(plaque.getId());
           card.setClickable(true);
           // Set the CardView layoutParams
           LayoutParams params = new LayoutParams(160, CardView.LayoutParams.MATCH_PARENT);
           params.setMargins(3,3,3,3);
           card.setLayoutParams(params);
           // Set cardView content padding
           //card.setContentPadding(5, 5, 5, 5);
           // Set a background color for CardView
           card.setCardBackgroundColor(Color.WHITE);
           // Set the CardView maximum elevation
           card.setMaxCardElevation(15);
           // Set CardView elevation
           card.setCardElevation(9);
           LinearLayout ll = new LinearLayout(getContext());
           ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
           ll.setOrientation(LinearLayout.VERTICAL);
           ll.setGravity(Gravity.CENTER);

           int vehicleType = 0;
           if(plaque.getVehicleId()==1){
               vehicleType = R.mipmap.car;
           }else if(plaque.getVehicleId()==2){
               vehicleType = R.mipmap.moto;
           }else if(plaque.getVehicleId()==3){
               vehicleType = R.mipmap.truck;
           }else if(plaque.getVehicleId()==4){
               vehicleType = R.mipmap.bus;
           }


           ImageView img = new ImageView(getContext());
           img.setImageResource(vehicleType);
           img.setLayoutParams(new LayoutParams(60,60));

           TextView tv_placa = new TextView(getContext());
           tv_placa.setTypeface(null, Typeface.BOLD);
           tv_placa.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
           tv_placa.setText(plaque.getPlaque());
           tv_placa.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
           //tv.setTextColor(Color.RED);

           ll.addView(img);
           ll.addView(tv_placa);
           card.addView(ll);

           card.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   parkPlaqueId = plaque.getId();
                   parkRateId = 0;
                   binding.vehiclesList.findViewById(plaque.getId()).setBackgroundColor(Color.LTGRAY);
                   if(cardAntVehi!=plaque.getId()) {
                       if (cardAntVehi != 0)
                           binding.vehiclesList.findViewById(cardAntVehi).setBackgroundColor(Color.WHITE);
                       cardAntVehi = plaque.getId();
                   }

                   if(plaque.getVehicleId()==1){
                       binding.rateType.setText("Carro ( "+plaque.getPlaque()+" )");
                   }else if(plaque.getVehicleId()==2){
                       binding.rateType.setText("Moto ( "+plaque.getPlaque()+" )");
                   }else if(plaque.getVehicleId()==3){
                       binding.rateType.setText("Caminhão ( "+plaque.getPlaque()+" )");
                   }else if(plaque.getVehicleId()==4){
                       binding.rateType.setText("Ônibus ( "+plaque.getPlaque()+" )");
                   }
                   initRatesCards(rateList,plaque.getVehicleId());
               }
           });

           card.setOnLongClickListener(new View.OnLongClickListener() {
                       @Override
                       public boolean onLongClick(View v) {
                           //Toast.makeText(getContext(),String.valueOf(v.getId()),Toast.LENGTH_LONG).show();
                           showDeletePlaquesDialog(plaque);
                           return false;
                       }
                   }
           );

           // Finally, add the CardView in root layout
           binding.vehiclesList.addView(card);
       }
    }

    private void initRatesCards(RateList rateList, int vehicleId){
       binding.ratesList.removeAllViews();
        for(final Rate rate : rateList.getRates()) {
            if(rate.getVehicleId().equals(vehicleId)) {
                CardView card = new CardView(getContext());
                card.setId(rate.getId());
                card.setClickable(true);
                // Set the CardView layoutParams
                LayoutParams params = new LayoutParams(160, CardView.LayoutParams.MATCH_PARENT);
                params.setMargins(3, 3, 3, 3);
                card.setLayoutParams(params);
                // Set cardView content padding
                //card.setContentPadding(5, 5, 5, 5);
                // Set a background color for CardView
                card.setCardBackgroundColor(Color.WHITE);
                // Set the CardView maximum elevation
                card.setMaxCardElevation(15);
                // Set CardView elevation
                card.setCardElevation(9);
                LinearLayout ll = new LinearLayout(getContext());
                ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                ll.setOrientation(LinearLayout.VERTICAL);
                ll.setGravity(Gravity.CENTER);

                ImageView img = new ImageView(getContext());
                img.setImageResource(R.mipmap.rate3);
                img.setLayoutParams(new LayoutParams(60, 60));


                TextView tv_rate = new TextView(getContext());
                tv_rate.setTypeface(null, Typeface.BOLD);
                tv_rate.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tv_rate.setText(rate.getRate());
                tv_rate.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                //tv.setTextColor(Color.RED);
                TextView tv_rate_time = new TextView(getContext());
                tv_rate_time.setTypeface(null, Typeface.BOLD);
                tv_rate_time.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                tv_rate_time.setText(String.valueOf(rate.getMinutes()/60) + " hrs");
                tv_rate_time.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 8);

                ll.addView(img);
                ll.addView(tv_rate);
                ll.addView(tv_rate_time);
                card.addView(ll);

                card.setClickable(true);

                card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        binding.ratesList.findViewById(rate.getId()).setBackgroundColor(Color.LTGRAY);
                        if(cardAntRate!=rate.getId()){
                            if(cardAntRate!=0)
                                if(binding.ratesList.findViewById(cardAntRate)!=null)
                                    binding.ratesList.findViewById(cardAntRate).setBackgroundColor(Color.WHITE);
                            cardAntRate = rate.getId();
                        }
                        parkRateId=rate.getId();
                        parkRate = rate;
                    }
                });

                // Finally, add the CardView in root layout
                binding.ratesList.addView(card);
            }
        }
    }

    private void getUser(){
        binding.pb.setVisibility(View.VISIBLE);
        Call<User> call = new RetrofitConfig().getUserService().getUser("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.code()>=200 && response.code()<300){
                    user = response.body();
                    binding.setUserData(user);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getUser();
                }else if(response.code()==404){
                    message("Usuário não ativado",getContext(),1,null);
                    UserSession.getInstance(getContext()).clearSession();
                    startActivity(new Intent(getContext(),LoginActivity.class));
                }else{
                    Log.i("getUser",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getUser();
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("getUser",t.getMessage());
                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                getUser();
            }
        });
    }

    private void getBalance(){
        binding.pb.setVisibility(View.VISIBLE);
        Call<Balance> call = new RetrofitConfig().getUserService().getUserBalance("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<Balance>() {
            @Override
            public void onResponse(Call<Balance> call, Response<Balance> response) {
                if(response.code()>=200 && response.code()<300){
                    balance = response.body();
                    binding.setBalance(balance);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getBalance();
                }else{
                    Log.i("getBalance",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getBalance();
                }
            }
            @Override
            public void onFailure(Call<Balance> call, Throwable t) {
                Log.i("getBalance",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getBalance();
            }
        });
    }

    private void getCards(){
        binding.pb.setVisibility(View.VISIBLE);
        Call<CardList> call = new RetrofitConfig().getUserService().getUserCards("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<CardList>() {
            @Override
            public void onResponse(Call<CardList> call, Response<CardList> response) {
                if(response.code()>=200 && response.code()<300){
                    cards = response.body();
                    int cardCont = 0;
                    for(Card card : cards.getCards()){
                        if(card.getCityId().equals(parkCityId)){
                            cardCont++;
                        }
                    }
                    binding.cadsCont.setText(String.valueOf(cardCont));
                    //binding.setCards(cards);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getCards();
                }else{
                    Log.i("getCards",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getCards();
                }
            }
            @Override
            public void onFailure(Call<CardList> call, Throwable t) {
                Log.i("getCards",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getCards();
            }
        });
    }

    private void getPlaques(){
        binding.pb.setVisibility(View.VISIBLE);

        Call<PlaquesList> call = new RetrofitConfig().getUserService().getUserPlaques("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<PlaquesList>() {
            @Override
            public void onResponse(Call<PlaquesList> call, Response<PlaquesList> response) {
                if(response.code()>=200 && response.code()<300){
                    plaquesList = response.body();
                    initPlaquesCards(plaquesList);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getPlaques();
                }else{
                    Log.i("getPlaques",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getPlaques();
                }
            }
            @Override
            public void onFailure(Call<PlaquesList> call, Throwable t) {
                Log.i("getPlaques",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getPlaques();
            }
        });
    }

    private void getRates(final int cityId){
        binding.pb.setVisibility(View.VISIBLE);
        Call<RateList> call = new RetrofitConfig().getUserService().getRatesFromCity(cityId,"Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<RateList>() {
            @Override
            public void onResponse(Call<RateList> call, Response<RateList> response) {
                if(response.code()>=200 && response.code()<300){
                    rateList = response.body();
                    //initRatesCards(rateList);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getRates(cityId);
                }else if(response.message().equals("")){
                    getRates(cityId);
                }else{
                    Log.i("getRates",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getRates(cityId);
                }
            }
            @Override
            public void onFailure(Call<RateList> call, Throwable t) {
                Log.i("getRates",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getRates(cityId);
            }
        });
    }

    private void getCities(){
        binding.pb.setVisibility(View.VISIBLE);

        Call<CitiesList> call = new RetrofitConfig().getUserService().getCities("Bearer "+ UserSession.getInstance(getContext()).getUserToken());
        call.enqueue(new Callback<CitiesList>() {
            @Override
            public void onResponse(Call<CitiesList> call, Response<CitiesList> response) {
                if(response.code()>=200 && response.code()<300){
                    citiesList = response.body();
                    addItemsOnSpinnerCities(citiesList);
                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                    getCities();
                }else if(response.message().equals("")){
                    getCities();
                }else{
                    Log.i("getCities",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                    getCities();
                }
            }
            @Override
            public void onFailure(Call<CitiesList> call, Throwable t) {
                Log.i("getCities",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                getCities();
            }
        });
    }

    private void getAddresses(final int parkCityId){
        binding.pb.setVisibility(View.VISIBLE);
        Call<AddressesList> call = new RetrofitConfig().getUserService().getAddresses("Bearer "+ UserSession.getInstance(getContext()).getUserToken(),parkCityId);
        call.enqueue(new Callback<AddressesList>() {
            @Override
            public void onResponse(Call<AddressesList> call, Response<AddressesList> response) {
                if(response.code()>=200 && response.code()<300){
                    //citiesList = response.body();
                    showAdderessesDialog(response.body());
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

    private void showAdderessesDialog(final AddressesList addressesList) {
       final ListView list = new ListView(getContext());
       list.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
       ArrayAdapter<Address> adapter = new ArrayAdapter<Address>(getContext(),
                android.R.layout.simple_list_item_single_choice, addressesList.getAddresses());

        list.setClickable(true);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.setItemChecked(position,true);
                parkAddressId = addressesList.getAddresses().get(position).getId();
                message("Rua "+addressesList.getAddresses().get(position).getAddress()+" selecionada",getContext(),1,null);
            }
        });

        list.setAdapter(adapter);

        new MDDialog.Builder(getContext())
                .setTitle("Lista de Endereços")
                .setContentView(list)
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(parkPlaqueId!=0 && parkRateId!=0 && parkAddressId!=0 && parkCityId!=0){
                            activeCard(parkCityId,parkRateId,parkAddressId,parkPlaqueId);
                        }else{
                            message("Selecione veiculo, taxa e local de estacionamento.",getContext(),2,null);
                        }
                    }
                }).create()
                .show();
    }

    private void activeCard(int parkCityId, final int parkRateId, int parkAddressId, int parkPlaqueId){
        binding.pb.setVisibility(View.VISIBLE);
        Call<ActiveCard> call = new RetrofitConfig().getUserService().activeCard("Bearer "+ UserSession.getInstance(getContext()).getUserToken(),
                parkCityId, parkRateId, parkAddressId, parkPlaqueId);
        call.enqueue(new Callback<ActiveCard>() {
            @Override
            public void onResponse(Call<ActiveCard> call, Response<ActiveCard> response) {
                if(response.code()>=200 && response.code()<300){

                    ActiveCard activeCard = response.body();
                    Toast.makeText(getContext(),activeCard.getMessage(),Toast.LENGTH_LONG).show();

                    if(!activeCard.getMessage().contains("Não foi possível")){
                        AlarmUtil.setAlarm(getContext(),parkRate.getMinutes());
                    }

                    binding.pb.setVisibility(View.GONE);
                }else if(response.code()==401 && response.message().equals("Unauthorized")){
                    responseError(response.errorBody());
                }else if(response.message().equals("")){
                    Toast.makeText(getContext(),String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                }else{
                    Log.i("activeCard",response.message());
                    Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ActiveCard> call, Throwable t) {
                Log.i("activeCard",t.getMessage());
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addVehicle(int vehicleId, String plaque){
        Call<ActiveCard> call = new RetrofitConfig().getUserService().addPlaque("Bearer "+UserSession.getInstance(getContext()).getUserToken(),plaque,vehicleId);
        call.enqueue(new Callback<ActiveCard>() {
            @Override
            public void onResponse(Call<ActiveCard> call, Response<ActiveCard> response) {

                if(response.code()>=200 && response.code()<300){
                    ActiveCard addVehicle = response.body(); // são posts e são iguais message e success
                    message(addVehicle.getMessage(),getContext(),1,null);
                    binding.vehiclesList.removeViews(1, plaquesList.getTotalPlaques());
                    getPlaques();
                }else{
                    message("Não foi possivel cadastrar tal placa",getContext(),1,null);
                    responseError(response.errorBody());
                    Log.e("UserService",response.message());
                }
            }

            @Override
            public void onFailure(Call<ActiveCard> call, Throwable t) {
                message(t.getMessage(),getContext(),1,null);
            }
        });
    }

    private void deletePlaque(final int id) {
        Call<ActiveCard> call = new RetrofitConfig().getUserService().deletePlaque("Bearer "+UserSession.getInstance(getContext()).getUserToken(),id);
        call.enqueue(new Callback<ActiveCard>() {
            @Override
            public void onResponse(Call<ActiveCard> call, Response<ActiveCard> response) {

                if(response.code()>=200 && response.code()<300){
                    ActiveCard delete = response.body(); // são posts e são iguais message e success
                    message(delete.getMessage(),getContext(),1,null);
                    binding.vehiclesList.removeViews(1, plaquesList.getTotalPlaques());
                    if(parkPlaqueId==id)parkPlaqueId=0;
                    getPlaques();
                }else{
                    message("Erro ao deletar",getContext(),1,null);
                    responseError(response.errorBody());
                    Log.e("deletePlaque",response.message());
                }
            }

            @Override
            public void onFailure(Call<ActiveCard> call, Throwable t) {
                message(t.getMessage(),getContext(),1,null);
            }
        });
    }

    private void addNewPlaqueDialog() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_plaque, null);

        final EditText edtPlaca = dialogView.findViewById(R.id.edt_plaque);
        edtPlaca.addTextChangedListener(MaskEditUtil.mask(edtPlaca, "###-####"));

        final int[] vehicle = new int[1];
        vehicle[0]=0;
        dialogView.findViewById(R.id.add_car).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle[0] = 1;
                dialogView.findViewById(R.id.add_car).setBackgroundColor(Color.LTGRAY);
                dialogView.findViewById(R.id.add_moto).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_truck).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_bus).setBackgroundColor(Color.WHITE);
            }
        });
        dialogView.findViewById(R.id.add_moto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle[0] = 2;
                dialogView.findViewById(R.id.add_car).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_moto).setBackgroundColor(Color.LTGRAY);
                dialogView.findViewById(R.id.add_truck).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_bus).setBackgroundColor(Color.WHITE);
            }
        });
        dialogView.findViewById(R.id.add_truck).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle[0] = 3;
                dialogView.findViewById(R.id.add_car).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_moto).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_truck).setBackgroundColor(Color.LTGRAY);
                dialogView.findViewById(R.id.add_bus).setBackgroundColor(Color.WHITE);
            }
        });
        dialogView.findViewById(R.id.add_bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicle[0] = 4;
                dialogView.findViewById(R.id.add_car).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_moto).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_truck).setBackgroundColor(Color.WHITE);
                dialogView.findViewById(R.id.add_bus).setBackgroundColor(Color.LTGRAY);
            }
        });


        new MDDialog.Builder(getContext())
                .setTitle("Adicionar Veiculos")
                .setContentView(dialogView)
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Confirmar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String placa = edtPlaca.getText().toString();
                        if(placa.length()==8 && vehicle[0]!=0){
                            //Toast.makeText(getContext(), String.valueOf(vehicle[0])+" "+placa, Toast.LENGTH_SHORT).show();
                            addVehicle(vehicle[0],placa);
                        }else{
                            Toast.makeText(getContext(), "Digite a placa e selecione o veículo", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create()
                .show();
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

    private void showDeletePlaquesDialog(final Plaque plaque){
       TextView textView = new TextView(getContext());
       textView.setText(plaque.getPlaque());
       textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
       textView.setTextSize(25);
       textView.setGravity(Gravity.CENTER);
       textView.setBackground(getResources().getDrawable(R.drawable.custom_button_white));
        new MDDialog.Builder(getContext())
                .setTitle("Deletar Placa ?")
                .setContentView(textView)
                .setNegativeButton("Cancelar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Deletar", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletePlaque(plaque.getId());
                    }
                }).create()
                .show();
    }

    private void showAlarmDialog(final String id){
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_set_alarm, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Alarme "+id);
        dialogBuilder.setIcon(R.drawable.ic_alarm);

        final Croller croller = dialogView.findViewById(R.id.croller);
        Button cancel = dialogView.findViewById(R.id.cancel_action);
        Button confirm = dialogView.findViewById(R.id.confirm_action);

        final AlertDialog b = dialogBuilder.create();

        croller.setOnProgressChangedListener(new Croller.onProgressChangedListener() {
            @Override
            public void onProgressChanged(int progress) {
                croller.setLabel(String.valueOf(progress));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmUtil.saveAlarmPreference(getContext(),id,croller.getProgress());
                if(id.equals("1")){
                    binding.showAlarm01.setText(String.valueOf(croller.getProgress())+" min");
                }else if(id.equals("2")){
                    binding.showAlarm02.setText(String.valueOf(croller.getProgress())+" min");
                }else{
                    binding.showAlarm03.setText(String.valueOf(croller.getProgress())+" min");
                }
                message("Alarme setado com sucesso !",getContext(),1,null);
                b.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        b.show();
    }

    private void showCadsDialog(){
        final ListView list = new ListView(getContext());
        list.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
        ArrayAdapter<Card> adapter = new ArrayAdapter<Card>(getContext(),
                android.R.layout.simple_list_item_1, cards.getCards());

        list.setAdapter(adapter);

        new MDDialog.Builder(getContext())
                .setTitle("CADS")
                .setContentView(list)
                .setNegativeButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Mais Informações", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).create()
                .show();
    }

    private void showBalanceDialog(){
        new MDDialog.Builder(getContext())
                .setTitle("Créditos")
                .setContentView(R.layout.dialog_info_balance)
                .setNegativeButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Mais Informações", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).create()
                .show();
    }

    private void showAlertsDialog(){
        new MDDialog.Builder(getContext())
                .setTitle("Alertas")
                .setContentView(R.layout.dialog_info_alerts)
                .setNegativeButton("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setPositiveButton("Mais Informações", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).create()
                .show();
    }

    public void addItemsOnSpinnerCities(CitiesList citiesList) {
        binding.spinnerCities.setOnItemSelectedListener(this);
        CitiesAdapter citiesAdapter = new CitiesAdapter(getContext(),citiesList);
        binding.spinnerCities.setAdapter(citiesAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getContext(), citiesList.getCities().get(position).getName(), Toast.LENGTH_LONG).show();
        resetPark();
        cardAntRate = 0;
        cardAntVehi = 0;
        if(plaquesList!=null) {
            binding.vehiclesList.removeViews(1, plaquesList.getTotalPlaques());
            binding.ratesList.removeAllViews();
            initPlaquesCards(plaquesList);
        }

        binding.spinnerCities.setSelection(position);
        parkCityId = citiesList.getCities().get(position).getId();
        getRates(parkCityId);
        getCards();
    }

    private void resetPark(){
       binding.rateType.setText("");
        parkPlaqueId = 0;
        parkRateId = 0;
        parkAddressId = 0;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
