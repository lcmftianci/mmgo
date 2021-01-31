package com.lcmf.mmgo.recyclemenu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lcmf.mmgo.R;

import java.util.List;

public class HolyMenuAdapter extends RecyclerView.Adapter<HolyMenuAdapter.ViewHolder>
        //,implements View.OnClickListener
{

    private List<HolyMenuItem> mMenuList;

//    @Override
//    public void onClick(View view) {
//
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView menuImage;
        TextView menuName;

        public ViewHolder(View view) {
            super(view);
            menuImage = (ImageView) view.findViewById(R.id.menu_image);
            menuName = (TextView) view.findViewById(R.id.menu_name);
        }

    }

    public HolyMenuAdapter(List<HolyMenuItem> fruitList) {
        mMenuList = fruitList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        HolyMenuItem fruit = mMenuList.get(position);
        holder.menuImage.setImageResource(fruit.getImageId());
        holder.menuName.setText(fruit.getName());
        //holder.menuImage.setOnClickListener(this);
        //holder.menuName.setOnClickListener(this);

        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }

}