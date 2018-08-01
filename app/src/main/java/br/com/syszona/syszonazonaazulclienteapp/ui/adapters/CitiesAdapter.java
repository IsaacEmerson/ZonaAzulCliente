package br.com.syszona.syszonazonaazulclienteapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.models.CitiesList;

public class CitiesAdapter extends BaseAdapter {
    Context context;
    CitiesList citiesList;
    LayoutInflater inflater;

    public CitiesAdapter(Context applicationContext, CitiesList citiesList) {
        this.context = applicationContext;
        this.citiesList = citiesList;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return citiesList.getTotalCity();
    }

    @Override
    public Object getItem(int position) {
        return citiesList.getCities().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.custom_spinner_items, null);
        TextView ids = (TextView) view.findViewById(R.id.city_id);
        TextView names = (TextView) view.findViewById(R.id.city_name);
        ids.setText(citiesList.getCities().get(i).getId().toString());
        names.setText(citiesList.getCities().get(i).getName());
        return view;
    }
}
