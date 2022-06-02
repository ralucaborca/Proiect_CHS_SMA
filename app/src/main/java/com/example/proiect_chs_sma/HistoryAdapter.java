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
    protected RecyclerInterface recyclerInterface1;

    public HistoryAdapter(Context hContext, ArrayList<History> histories, RecyclerInterface recyclerInterface1) {
        this.hContext = hContext;
        this.histories = histories;
        this.recyclerInterface1 = recyclerInterface1;
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder {
        TextView id1, greutate1, varsta1, inaltime1, fumat1, sport1, probleme_sanatate1, button;

        public HistoryViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface1) {
            super(itemView);
            varsta1 = itemView.findViewById(R.id.valoare_varsta1);
            inaltime1 = itemView.findViewById(R.id.valoare_inaltime1);
            greutate1 = itemView.findViewById(R.id.valoare_greutate1);
            fumat1 = itemView.findViewById(R.id.valoare_fumat1);
            sport1 = itemView.findViewById(R.id.valoare_sport1);
            probleme_sanatate1 = itemView.findViewById(R.id.valoare_sanatate1);
            id1 = itemView.findViewById(R.id.valoare_id1);

            button = itemView.findViewById(R.id.button_sugestiune);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerInterface1 != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerInterface1.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(hContext).inflate(R.layout.history_list, parent, false);
        return new HistoryAdapter.HistoryViewHolder(v, recyclerInterface1);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        History histori= histories.get(position);
        holder.id1.setText(histori.getIdPacient1());
        holder.greutate1.setText(histori.getGreutate1());
        holder.inaltime1.setText(histori.getInaltime1());
        holder.varsta1.setText(histori.getVarsta1());
        holder.fumat1.setText(histori.getFumat1());
        holder.sport1.setText(histori.getSport1());
        holder.probleme_sanatate1.setText(histori.getSanatate1());
    }

    @Override
    public int getItemCount() {

        return histories.size();
    }
}
