package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

import java.sql.*;
import java.util.List;

public class DepartamentoDaoJDBC implements DepartamentoDao {
    private Connection conn;

    public DepartamentoDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Departamento obj) {
        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement("INSERT INTO department (DepName) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getNome());

            int qtdLinhasAfetadas = pst.executeUpdate();
            if(qtdLinhasAfetadas > 0){
                ResultSet rs = pst.getGeneratedKeys();
                if(rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            }
            else{
                throw new DbException("Erro. nenhuma linha foi afetada.");
            }

        }catch (SQLException e){
            throw new DbException(e.getMessage());
            }
        finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void update(Departamento obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Departamento findById(Integer id) {
        return null;
    }

    @Override
    public List<Departamento> findAll() {
        return List.of();
    }
}
