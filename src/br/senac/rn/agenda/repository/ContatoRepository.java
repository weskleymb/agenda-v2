package br.senac.rn.agenda.repository;

import br.senac.rn.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContatoRepository implements DataBase<Contato> {

    private AgendaDataBase db = new AgendaDataBase();

    @Override
    public List<Contato> findAll() {
        List<Contato> contatos = new ArrayList();
        try {
            db.openConnection();
            String sql = "SELECT * FROM tb_contatos";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));
                contatos.add(contato);
            }
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.closeConnection();
            return contatos;
        }
    }

    @Override
    public Optional<Contato> findById(Long id) {
        Optional<Contato> contato = Optional.of(new Contato());
        try {
            db.openConnection();
            String sql = "SELECT * FROM tb_contatos WHERE con_id = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                contato.get().setId(rs.getLong("con_id"));
                contato.get().setNome(rs.getString("con_nome"));
                contato.get().setFone(rs.getString("con_fone"));
            } else {
                contato = Optional.empty();
            }
        } catch (SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.closeConnection();
            return contato;
        }
    }

    @Override
    public void save(Contato contato) {
        if (contato.getId() == null) {
            insert(contato);
        } else {
            update(contato);
        }
    }

    @Override
    public void delete(Contato contato) {
        try {
            db.openConnection();
            String sql = "DELETE FROM tb_contatos WHERE con_id = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setLong(1, contato.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.closeConnection();
        }
    }

    @Override
    public void deleteById(Long id) {
        Optional<Contato> contato = findById(id);
        if (contato.isPresent()) {
            delete(contato.get());
        }
    }

    private void insert(Contato contato) {
        try {
            db.openConnection();
            String sql = "INSERT INTO tb_contatos (con_nome, con_fone) VALUES (?, ?)";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.closeConnection();
        }
    }

    private void update(Contato contato) {
        try {
            db.openConnection();
            String sql = "UPDATE tb_contatos SET con_nome = ?, con_fone = ? WHERE con_id = ?";
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getFone());
            ps.setLong(3, contato.getId());
            ps.executeUpdate();
        } catch(SQLException error) {
            System.out.println("ERRO: " + error);
        } finally {
            db.closeConnection();
        }
    }

}
