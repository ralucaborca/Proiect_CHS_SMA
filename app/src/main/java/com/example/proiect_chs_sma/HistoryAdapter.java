package com.example.proiect_chs_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {
    private ArrayList<History> histories;
    protected RecyclerInterface recyclerInterface;
    private Context hContext;

    public HistoryAdapter(Context hContext, ArrayList<History> histories) {
        this.hContext = hContext;
        this.histories = histories;
        this.recyclerInterface = recyclerInterface;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView puls1, greutate1, varsta1, inaltime1, fumat1, sport1, probleme_sanatate1, poza_puls_denumire1;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            varsta1 = itemView.findViewById(R.id.valoare_varsta1);
            inaltime1 = itemView.findViewById(R.id.valoare_inaltime1);
            greutate1 = itemView.findViewById(R.id.valoare_greutate1);
            fumat1 = itemView.findViewById(R.id.valoare_fumat1);
            sport1 = itemView.findViewById(R.id.valoare_sport1);
            puls1 = itemView.findViewById(R.id.valoare_puls1);
            probleme_sanatate1 = itemView.findViewById(R.id.valoare_sanatate1);
            poza_puls_denumire1 = itemView.findViewById(R.id.nume_poza1);
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(hContext).inflate(R.layout.history_list, parent, false);
        return new HistoryAdapter.HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        History histori= histories.get(position);
        holder.puls1.setText(histori.getPuls1());
        holder.greutate1.setText(histori.getGreutate1());
        holder.inaltime1.setText(histori.getInaltime1());
        holder.varsta1.setText(histori.getVarsta1());
        holder.fumat1.setText(histori.getFumat1());
        holder.sport1.setText(histori.getSport1());
        holder.probleme_sanatate1.setText(histori.getSanatate1());
        holder.poza_puls_denumire1.setText(histori.getNumePoza1());
    }

    @Override
    public int getItemCount() {

        return histories.size();
    }
}
