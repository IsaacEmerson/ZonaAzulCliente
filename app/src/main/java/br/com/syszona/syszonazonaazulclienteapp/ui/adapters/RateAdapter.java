package br.com.syszona.syszonazonaazulclienteapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.models.RateList;

public class RateAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    RateList rateList;

    public RateAdapter(Context context, RateList rateList) {
        this.context = context;
        this.rateList = rateList;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return rateList.getRates().size();
    }

    @Override
    public Object getItem(int position) {
        return rateList.getRates().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_rates, null);
        TextView tvVehicle = (TextView) view.findViewById(R.id.vehicle);
        TextView tvMinutes = (TextView) view.findViewById(R.id.minutes);
        TextView tvRate = (TextView) view.findViewById(R.id.rate);
        tvVehicle.setText(rateList.getRates().get(i).getVehicle());
        tvMinutes.setText(String.valueOf((rateList.getRates().get(i).getMinutes()/60))+" hrs");
        tvRate.setText(rateList.getRates().get(i).getRate()+" R$");
        return view;
    }
}
