package com.seugrupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDao {
    private static final String URL = "jdbc:postgresql://localhost:5432/agenda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public void inserirContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, email, telefone) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getTelefone());

            int linhasAfetadas = stmt.executeUpdate();
            System.out.println("Contato inserido com sucesso! Linhas afetadas: " + linhasAfetadas);
        } catch (SQLException e) {
            System.err.println("Erro ao inserir contato: " + e.getMessage());
        }
    }


    public List<Contato> listarContatos(String letra) {
        String sql = "SELECT * FROM contatos WHERE nome ILIKE ?";
        List<Contato> contatos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, letra + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                contatos.add(new Contato(nome, email, telefone));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contatos: " + e.getMessage());
        }

        return contatos;
    }

    public List<Contato> listarOrdenadoPorNome() {
        String sql = "SELECT * FROM contatos ORDER BY nome ASC";
        List<Contato> contatos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                contatos.add(new Contato(nome, email, telefone));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contatos ordenados: " + e.getMessage());
        }

        return contatos;
    }

    public List<Contato> listarPorDominioEmail(String dominio) {
        String sql = "SELECT * FROM contatos WHERE email ILIKE ?";
        List<Contato> contatos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + dominio);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");
                contatos.add(new Contato(nome, email, telefone));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar por domínio de email: " + e.getMessage());
        }

        return contatos;
    }


    public void atualizarEmail(int id, String novoEmail) {
        String sql = "UPDATE contatos SET email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoEmail);
            stmt.setInt(2, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Email atualizado com sucesso!");
            } else {
                System.out.println("Nenhum contato encontrado com o ID fornecido.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar email: " + e.getMessage());
        }
    }
}
