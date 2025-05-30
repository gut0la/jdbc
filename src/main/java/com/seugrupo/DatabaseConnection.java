package com.seugrupo;

import java.util.List;

public class DatabaseConnection {
    public static void main(String[] args) {
        ContatoDao dao = new ContatoDao();

        // Inserir contato
        Contato novoContato = new Contato("João Silva", "joao.silva@email.com", "123456789");
        dao.inserirContato(novoContato);

        // Listar contatos que começam com "J"
        List<Contato> contatos = dao.listarContatos("J");
        for (Contato c : contatos) {
            System.out.println(c);
        }
    }
}
