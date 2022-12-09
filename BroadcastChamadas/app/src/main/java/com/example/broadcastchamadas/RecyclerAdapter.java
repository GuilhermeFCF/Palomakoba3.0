package com.example.broadcastchamadas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
{
    private ArrayList<IncomingNumber> numeros = new ArrayList<IncomingNumber>();


    public RecyclerAdapter(ArrayList<IncomingNumber> numeros) { //construtor da RecyclerAdapter
        this.numeros = numeros;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id,numero;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtid);
            numero = itemView.findViewById(R.id.txtNumber);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);  // Estrutural, so altera o xml ^^
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(this.numeros.get(position).getId()));
        holder.numero.setText(this.numeros.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
