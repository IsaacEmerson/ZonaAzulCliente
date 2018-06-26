package br.com.syszona.syszonazonaazulclienteapp.ui.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.syszona.syszonazonaazulclienteapp.R;
import br.com.syszona.syszonazonaazulclienteapp.models.Menu;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<Menu> menuList;

    public MenuAdapter(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView optionMenu;
        ImageView option_icon;
        public MenuViewHolder(View itemView) {
            super(itemView);
            optionMenu = itemView.findViewById(R.id.optionsMenu);
            option_icon = itemView.findViewById(R.id.option_icon);
        }
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu,parent,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.optionMenu.setText(String.valueOf(menuList.get(position).getOption()));
        holder.option_icon.setImageDrawable(menuList.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }


}

