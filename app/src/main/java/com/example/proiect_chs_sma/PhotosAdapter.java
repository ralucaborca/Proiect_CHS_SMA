package com.example.proiect_chs_sma;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder> {
    private ArrayList<PhotoDatas> photoDatas;
    private Context context;


    public PhotosAdapter(Context context, ArrayList<PhotoDatas> photoDatas){
        this.context = context;
        this.photoDatas = photoDatas;
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView nume;

        public PhotosViewHolder(@NonNull View itemView) {
            super(itemView);
            nume = itemView.findViewById(R.id.nume_poza123);
            imageView = itemView.findViewById(R.id.poza_puls);
        }
    }
    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.photos_list, parent, false);
        return new PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        PhotoDatas xxx = photoDatas.get(position);
        holder.nume.setText(xxx.getNumePoza());
        Glide.with(context).load(photoDatas.get(position).getLinkImagine()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        return photoDatas.size();
    }

}
