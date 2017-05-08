package com.moment.android;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.moment.android.db.ClockDB;

import org.litepal.crud.DataSupport;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private Context mContext1;
    private boolean ready;
    private List<Card> mCardList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            cardView=(CardView)view;
            fruitImage=(ImageView)view.findViewById(R.id.fruit_image);
            fruitName=(TextView)view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Card> cardList){
        mCardList=cardList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        if (mContext==null){
            mContext=parent.getContext();
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.fruit_item,parent,false);

        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){;
                int position=holder.getAdapterPosition();
                Card card=mCardList.get(position);
                getWIntent(card.getName());
                /*if (getTFReady(card.getName())){
                    Intent intent=new Intent(mContext,Clock.class);
                    intent.putExtra("FruitName",card.getName());
                    mContext.startActivity(intent);
                }else {
                    Intent intent1=new Intent(mContext,NewClock.class);
                    intent1.putExtra("FruitName",card.getName());
                    mContext.startActivity(intent1);
                }*/
            }
        });
        return holder;
    }

    public void getWIntent(String FruitName){
        if (getTFReady(FruitName)==false){
            toNewClock(FruitName);
        }else if (getTFReady(FruitName)==true){
            toClock(FruitName);
        }
        //toNewClock(FruitName);
        //toClock(FruitName);
        //mContext.startActivity(intent1);
    }

    public void toNewClock(String FruitName){
        Intent intent1=new Intent(mContext,NewClock.class);
        intent1.putExtra("FruitName",FruitName);
        mContext.startActivity(intent1);
    }

    public void toClock(String FruitName){
        Intent intent2=new Intent(mContext,Clock.class);
        intent2.putExtra("FruitName",FruitName);
        mContext.startActivity(intent2);
    }

    public boolean getTFReady(String FruitName){
        List<ClockDB> clockDBs=DataSupport.select("Ready")
                .where("FruitName=?",FruitName)
                .find(ClockDB.class);
        for (ClockDB clockDB:clockDBs){
            ready=clockDB.getReady();
        }
        return ready;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Card card=mCardList.get(position);
        holder.fruitName.setText(card.getName());
        Glide.with(mContext).load(card.getImageId()).into(holder.fruitImage);
    }

    @Override
    public int getItemCount(){
        return mCardList.size();
    }
}
