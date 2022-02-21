package com.example.proiect_chs_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerView_Config extends RecyclerView.Adapter<RecyclerView_Config.mViewHolder> {

    private Context mContext;
    ArrayList<Pacients> lista_pacients;

    public  RecyclerView_Config(Context mContext, ArrayList<Pacients> lista_pacients){
        this.mContext = mContext;
        this.lista_pacients = lista_pacients;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        TextView CNP, puls, greutate, varsta, inaltime, probleme_sanatate;

        public mViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            CNP = itemView.findViewById(R.id.editcnp);
            puls = itemView.findViewById(R.id.alegerepuls);
            greutate = itemView.findViewById(R.id.alegeregreutate);
            varsta = itemView.findViewById(R.id.alegerevarsta);
            inaltime = itemView.findViewById(R.id.alegereinaltime);
            probleme_sanatate = itemView.findViewById(R.id.editprobleme);

        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.pacient_list, parent, false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Pacients lista= lista_pacients.get(position);
        holder.CNP.setText(lista.getCNP());
        holder.puls.setText(lista.getPuls());
        holder.greutate.setText(lista.getGreutate());
        holder.inaltime.setText((lista.getInaltime()));
        holder.varsta.setText(lista.getVarsta());
        holder.probleme_sanatate.setText(lista.getSanatate());
    }

    @Override
    public int getItemCount() {

        return lista_pacients.size();
    }


}


