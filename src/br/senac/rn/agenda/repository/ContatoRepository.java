package br.senac.rn.agenda.repository;

import br.senac.rn.agenda.model.Contato;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoRepository implements DataBase<Contato> {

    private AgendaDataBase db = new AgendaDataBase();

    @Override
    public List<Contato> findAll() {
        db.openConnection();
        List<Contato> contatos = new ArrayList();
        String sql = "SELECT * FROM tb_contatos";
        try {
            PreparedStatement ps = db.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Contato contato = new Contato();
                contato.setId(rs.getLong("con_id"));
                contato.setNome(rs.getString("con_nome"));
                contato.setFone(rs.getString("con_fone"));
                contatos.add(contato);
            }
            db.closeConnection();
            return contatos;
        } catch (SQLException error) {
            System.out.println("Erro: " + error);
        }
        db.closeConnection();
        return null;
    }

    @Override
    public Contato findById(Long id) {
        return null;
    }

    @Override
    public void save(Contato contato) {

    }

    @Override
    public void delete(Contato contato) {

    }

    @Override
    public void deleteById(Long id) {

    }

}
