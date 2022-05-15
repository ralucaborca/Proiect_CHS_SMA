package com.example.proiect_chs_sma;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerView_Config extends RecyclerView.Adapter<RecyclerView_Config.mViewHolder> {
    protected RecyclerInterface recyclerInterface;
    private Context mContext;
    ArrayList<Pacients> lista_pacients;
    ImageView img;

    public  RecyclerView_Config(Context mContext, ArrayList<Pacients> lista_pacients, RecyclerInterface recyclerInterface){
        this.mContext = mContext;
        this.lista_pacients = lista_pacients;
        this.recyclerInterface = recyclerInterface;

    }

    public RecyclerView_Config(ListaPacienti_activity mContext, ArrayList<Pacients> pacientsArrayList) {
    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView puls, greutate, varsta, inaltime, fumat, sport, probleme_sanatate, button_like, poza_puls_denumire;
        ImageView imageViewPuls;

        public mViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            varsta = itemView.findViewById(R.id.valoare_varsta);
            inaltime = itemView.findViewById(R.id.valoare_inaltime);
            greutate = itemView.findViewById(R.id.valoare_greutate);
            fumat = itemView.findViewById(R.id.valoare_fumat);
            sport = itemView.findViewById(R.id.valoare_sport);
            puls = itemView.findViewById(R.id.valoare_puls);
            probleme_sanatate = itemView.findViewById(R.id.valoare_sanatate);
            button_like = itemView.findViewById(R.id.button_like);
            poza_puls_denumire = itemView.findViewById(R.id.nume_poza);
            imageViewPuls = itemView.findViewById(R.id.puls_imagine);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
            button_like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pacient_list, parent, false);
        return new mViewHolder(v, recyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Pacients lista= lista_pacients.get(position);
        holder.puls.setText(lista.getPuls());
        holder.greutate.setText(lista.getGreutate());
        holder.inaltime.setText(lista.getInaltime());
        holder.varsta.setText(lista.getVarsta());
        holder.fumat.setText(lista.getFumat());
        holder.sport.setText(lista.getSport());
        holder.probleme_sanatate.setText(lista.getSanatate());
        holder.poza_puls_denumire.setText(lista.getNumePoza());
        Glide.with(mContext).load(lista.getLinkImagine1()).into(holder.imageViewPuls);
    }

    @Override
    public int getItemCount() {

        return lista_pacients.size();
    }


}


