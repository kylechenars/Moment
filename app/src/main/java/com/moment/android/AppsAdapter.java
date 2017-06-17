package com.moment.android;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder1>{
    private List<Apps> mAppsList;

    static class ViewHolder1 extends RecyclerView.ViewHolder{
        TextView appsName;
        TextView ForegroundTime;

        public ViewHolder1(View view1){
            super(view1);
            appsName=(TextView)view1.findViewById(R.id.apps_name);
            ForegroundTime=(TextView)view1.findViewById(R.id.ForegroundTime);
        }
    }

    public AppsAdapter(List<Apps> appsList){
        mAppsList=appsList;
    }

    @Override
    public ViewHolder1 onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.apps_item,parent,false);
        ViewHolder1 holder=new ViewHolder1(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder1 holder, int position){
        Apps apps=mAppsList.get(position);
        holder.appsName.setText(apps.getName());
        holder.ForegroundTime.setText(apps.getForegroundTime());
    }

    @Override
    public int getItemCount(){
        return mAppsList.size();
    }

}