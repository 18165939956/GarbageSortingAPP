package com.example.garbagesortingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class top_search_Adapter extends RecyclerView.Adapter<top_search_Adapter.ViewHolder>{

    private final List<top_search> mtop_search;
    private OnItemClickListener mOnItemClickListener = null;

    //定义一个接口——————————————————————————————————————————————————————————————————————————————————————————————————————————————————
    public interface OnItemClickListener{
        void onItemClick(View view,int position,String top_search);
    }
    //定义一个接口——————————————————————————————————————————————————————————————————————————————————————————————————————————————————

    //将接口外放，供其他类使用—————————————————————————————————————————————————————————————————————————————————————————————————————————
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    //将接口外放，供其他类使用—————————————————————————————————————————————————————————————————————————————————————————————————————————————

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView top_search_image;
        TextView top_search_name;
        View topsearchView;

        public ViewHolder(View view){
            super(view);
            topsearchView = view.findViewById(R.id.recycle_view);
            top_search_image = view.findViewById(R.id.top_search_imagine);
            top_search_name = view.findViewById(R.id.top_search_name);
        }
    }


    public top_search_Adapter(List<top_search> topSearchList){
        mtop_search = topSearchList;
    }
    @NonNull
    @Override
    public top_search_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_search,parent,false);
        ViewHolder holder = new ViewHolder(view);


        /*view.setOnClickListener((View.OnClickListener) this);*/



        /*holder.topsearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                top_search topsearch = mtop_search.get(position);
                Toast.makeText(view.getContext(),"searching for "+topsearch.getName(),Toast.LENGTH_LONG).show();
            }
        });*/

        holder.top_search_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null){
                    int position = holder.getAdapterPosition();
                    top_search topsearch = mtop_search.get(position);
                    mOnItemClickListener.onItemClick(view,position,topsearch.getName());
                }
            }
        });

        holder.top_search_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                top_search topsearch = mtop_search.get(position);
                Toast.makeText(view.getContext(),"searching for "+topsearch.getName(),Toast.LENGTH_LONG).show();

            }
        });
        return holder;
    }





    @Override
    public void onBindViewHolder(@NonNull top_search_Adapter.ViewHolder holder, int position) {
        top_search topSearch = mtop_search.get(position);
        holder.top_search_image.setImageResource(topSearch.getImageId());
        holder.top_search_name.setText(topSearch.getName());
    }

    @Override
    public int getItemCount() {
        return mtop_search.size();
    }


}
