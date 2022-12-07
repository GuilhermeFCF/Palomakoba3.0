package com.example.listadecontatos.helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadecontatos.R;
import com.example.listadecontatos.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoAdapter extends RecyclerView.Adapter<ContatoAdapter.MyViewHolder> {

    private List<Contato> contatos = new ArrayList<Contato>();

    public ContatoAdapter(List<Contato> contato){ // Colocando a lista como parametro no construtor
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contatos_adapter_view,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nomeContato.setText(this.contatos.get(position).getNome());
        holder.telefone.setText(this.contatos.get(position).getTelefone());
        holder.email.setText(this.contatos.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return this.contatos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nomeContato,telefone, email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeContato = itemView.findViewById(R.id.txtNome);
            telefone = itemView.findViewById(R.id.txtTelefone);
            email = itemView.findViewById(R.id.txtEmail);
        }
    }
}
