package com.example.proiect_chs_sma;

import android.view.ViewGroup;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lista_recycle_adapt extends RecyclerView.Adapter<Lista_recycle_adapt.mViewHolder>{

    Context context;
    ArrayList<Pacients> listaPacienti;


    public Lista_recycle_adapt(Context context, ArrayList<Pacients> listaPacienti) {
        this.context = context;
        this.listaPacienti = listaPacienti;
    }
    public static class mViewHolder extends RecyclerView.ViewHolder{

        TextView nume, varsta, greutate, puls, sanatate, inaltime;
        public mViewHolder(@NonNull View itemView) {
            super(itemView);
            nume = itemView.findViewById(R.id.text_nume_prenume1);
            varsta = itemView.findViewById(R.id.text_varsta1);
            greutate = itemView.findViewById(R.id.text_greutate1);
            inaltime = itemView.findViewById(R.id.text_inaltime1);
            sanatate = itemView.findViewById(R.id.text_probleme1);
            puls = itemView.findViewById(R.id.text_puls1);
        }
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.lista_pacienti, parent, false);
        return new mViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        Pacients prc = listaPacienti.get(position);
        holder.nume.setText(prc.getNume());
        holder.varsta.setText(prc.getVarsta());
        holder.greutate.setText(prc.getGreutate());
        holder.inaltime.setText((prc.getInaltime()));
        holder.sanatate.setText(prc.getSanatate());
        holder.puls.setText(prc.getPuls());
    }
    @Override
    public int getItemCount() {

        return listaPacienti.size();
    }

}
