package com.seugrupo;

import java.util.List;
import java.util.Scanner;

public class DatabaseConnection {
    public static void main(String[] args) {
        ContatoDao dao = new ContatoDao();

        Contato novoContato = new Contato("João Silva", "joao.silva@email.com", "123456789");
        dao.inserirContato(novoContato);

        System.out.println("\nContatos que começam com 'J':");
        List<Contato> contatos = dao.listarContatos("J");
        for (Contato c : contatos) {
            System.out.println(c);
        }

        System.out.println("\nContatos ordenados por nome:");
        List<Contato> contatosOrdenados = dao.listarOrdenadoPorNome();
        for (Contato c : contatosOrdenados) {
            System.out.println(c);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nDigite o ID do contato para atualizar o email: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Digite o novo email: ");
        String novoEmail = scanner.nextLine();
        dao.atualizarEmail(id, novoEmail);

        System.out.print("\nDigite o domínio de email para buscar (ex: @email.com): ");
        String dominio = scanner.nextLine();
        List<Contato> contatosPorDominio = dao.listarPorDominioEmail(dominio);
        System.out.println("Contatos com domínio " + dominio + ":");
        for (Contato c : contatosPorDominio) {
            System.out.println(c);
        }

        scanner.close();
    }
}
