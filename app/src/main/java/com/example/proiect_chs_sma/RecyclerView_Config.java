package com.example.proiect_chs_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerView_Config {
    private Context mContext;
    private PacientsAdapter pacientsAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Pacients> pacients, List<String> keys){
        mContext = context;
        pacientsAdapter = new PacientsAdapter(pacients,keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(pacientsAdapter);
    }

    class PacientsView extends RecyclerView.ViewHolder {
        private TextView cNume, cGreutate, cInaltime, cVarsta, cSanatate, cPuls;
        private String key;

        public PacientsView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.pacient_list,parent,false));

            cNume = (TextView) itemView.findViewById(R.id.nume_txt);
            cGreutate = (TextView) itemView.findViewById(R.id.greutate_txt);
            cInaltime = (TextView) itemView.findViewById(R.id.inaltime_txt);
            cVarsta = (TextView) itemView.findViewById(R.id.varsta_txt);
            cSanatate = (TextView) itemView.findViewById(R.id.sanatate_txt);
            cPuls = (TextView) itemView.findViewById(R.id.puls_txt);
        }
        public void bind(Pacients pacients, String key){
            cNume.setText(pacients.getNume());
            cGreutate.setText(pacients.getGreutate());
            cInaltime.setText(pacients.getInaltime());
            cVarsta.setText(pacients.getVarsta());
            cSanatate.setText(pacients.getSanatate());
            cPuls.setText(pacients.getPuls());
            this.key = key;
        }
    }
    class PacientsAdapter extends RecyclerView.Adapter<PacientsView>{
        private List<Pacients> pacientsList;
        private List<String> ckeys;

        public PacientsAdapter(List<Pacients> pacientsList, List<String> ckeys) {
            this.pacientsList = pacientsList;
            this.ckeys = ckeys;
        }

        @NonNull
        @Override
        public PacientsView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PacientsView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PacientsView holder, int position) {
            holder.bind(pacientsList.get(position), ckeys.get(position));
        }

        @Override
        public int getItemCount() {
            return pacientsList.size();
        }
    }
}
