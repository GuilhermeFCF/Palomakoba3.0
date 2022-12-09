package com.example.listadecontatos.helper;

import com.example.listadecontatos.model.Contato;
import java.util.List;

public interface IContatoDAO {
    boolean salvar(Contato contato);
    boolean atualizar(Contato contato) ;
    boolean deletar(Contato contato);
    public List<Contato> lista();
}
