package com.example.proiect_chs_sma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class RecyclerView_Config extends RecyclerView.Adapter<RecyclerView_Config.mViewHolder> {
    protected RecyclerInterface recyclerInterface;
    private Context mContext;
    ArrayList<Pacients> lista_pacients;

    public  RecyclerView_Config(Context mContext, ArrayList<Pacients> lista_pacients, RecyclerInterface recyclerInterface){
        this.mContext = mContext;
        this.lista_pacients = lista_pacients;
        this.recyclerInterface = recyclerInterface;

    }

    public class mViewHolder extends RecyclerView.ViewHolder{
        TextView nume, greutate1, varsta1, inaltime1, fumat1, sport1, probleme_sanatate1, button_like, gen1, afectiune1, data1;

        public mViewHolder(@NonNull View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            nume = itemView.findViewById(R.id.valoare_nume_p);
            varsta1 = itemView.findViewById(R.id.valoare_varsta);
            inaltime1 = itemView.findViewById(R.id.valoare_inaltime);
            greutate1 = itemView.findViewById(R.id.valoare_greutate);
            fumat1 = itemView.findViewById(R.id.valoare_fumat);
            sport1 = itemView.findViewById(R.id.valoare_sport);
            probleme_sanatate1 = itemView.findViewById(R.id.valoare_sanatate);
            gen1 = itemView.findViewById(R.id.valoare_gen);
            afectiune1 = itemView.findViewById(R.id.valoare_afectiune);
            button_like = itemView.findViewById(R.id.button_like);
            data1 = itemView.findViewById(R.id.valoare_data);

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
        Pacients lista = lista_pacients.get(position);
        holder.nume.setText(lista.getNumePacient());
        holder.gen1.setText(lista.getGen());
        holder.varsta1.setText(lista.getVarsta());
        holder.greutate1.setText(lista.getGreutate());
        holder.inaltime1.setText(lista.getInaltime());
        holder.fumat1.setText(lista.getFumat());
        holder.probleme_sanatate1.setText(lista.getSanatate());
        holder.sport1.setText(lista.getSport());
        holder.afectiune1.setText(lista.getAfectiune());
        holder.data1.setText(lista.getData());
    }

    @Override
    public int getItemCount() {

        return lista_pacients.size();
    }


}


