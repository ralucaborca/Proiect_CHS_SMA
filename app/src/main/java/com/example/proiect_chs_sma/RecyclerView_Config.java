package com.example.proiect_chs_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerView_Config extends RecyclerView.Adapter<RecyclerView_Config.mViewHolder> {

    private Context mContext;
    ArrayList<Pacients> lista_pacients;

    public  RecyclerView_Config(Context mContext, ArrayList<Pacients> lista_pacients){
        this.mContext = mContext;
        this.lista_pacients = lista_pacients;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        TextView CNP, puls, greutate, varsta, inaltime, fumat, sport, probleme_sanatate;

        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            CNP = itemView.findViewById(R.id.valoare_cnp);
            varsta = itemView.findViewById(R.id.valoare_varsta);
            inaltime = itemView.findViewById(R.id.valoare_inaltime);
            greutate = itemView.findViewById(R.id.valoare_greutate);
            fumat = itemView.findViewById(R.id.valoare_fumat);
            sport = itemView.findViewById(R.id.valoare_sport);
            puls = itemView.findViewById(R.id.valoare_puls);
            probleme_sanatate = itemView.findViewById(R.id.valoare_sanatate);

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
        holder.inaltime.setText(lista.getInaltime());
        holder.varsta.setText(lista.getVarsta());
        holder.fumat.setText(lista.getFumat());
        holder.sport.setText(lista.getSport());
        holder.probleme_sanatate.setText(lista.getSanatate());
    }

    @Override
    public int getItemCount() {

        return lista_pacients.size();
    }


}


