package com.example.proiect_chs_sma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerVi_Config extends  RecyclerView.Adapter<RecyclerVi_Config.mViewHold>{

    private Context mContext;
    ArrayList<Feedback> lista_feedbacks;
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public RecyclerVi_Config(Context mContext, ArrayList<Feedback> lista_feedbacks){
        this.mContext = mContext;
        this.lista_feedbacks = lista_feedbacks;
    }
    public static class mViewHold extends RecyclerView.ViewHolder {
        TextView nume_medic,caz_puls, sugestie,button_like;

        public mViewHold(@NonNull View itemView){
            super(itemView);
            nume_medic = itemView.findViewById(R.id.valoare_nume);
            caz_puls = itemView.findViewById(R.id.valoare_caz_puls);
            sugestie = itemView.findViewById(R.id.valoare_sugestie);

        }
    }

    @NonNull
    @Override
    public RecyclerVi_Config.mViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.sugestii_list, parent, false);
        return new RecyclerVi_Config.mViewHold(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerVi_Config.mViewHold holder, int position) {
        Feedback lista= lista_feedbacks.get(position);
        holder.nume_medic.setText(lista.getNume());
        holder.caz_puls.setText(lista.getCaz());
        holder.sugestie.setText(lista.getFeedback());
    }

    @Override
    public int getItemCount() {

        return lista_feedbacks.size();
    }
}
