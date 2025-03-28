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
        PreparedStatement pst = null;

        try{
            pst = conn.prepareStatement("UPDATE department SET DepName = ? WHERE id = ?");
            pst.setString(1, obj.getNome());
            pst.setInt(2, obj.getId());

            pst.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pst = null;

        try{
            // Exclui o vendedor associado ao departamento antes de excluir o departamento
            // poderia ser feito da seguinte forma : transformar o departmentid do vendedor em nulo (se o bd permitir)
            // e em seguida excluir o departamento, para nao perder os dados do(s) vendedor(es) em questao
            pst = conn.prepareStatement("DELETE FROM seller WHERE DepartmentId = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            pst = conn.prepareStatement("DELETE FROM department WHERE Id = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(pst);
        }
    }

    @Override
    public Departamento findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            pst = conn.prepareStatement("SELECT * from department WHERE id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if(rs.next()){
                Departamento dep = instantiateDepartamento(rs);
                return dep;
            }
            return null;
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Departamento> findAll() {
        return List.of();
    }

    private Departamento instantiateDepartamento(ResultSet rs) throws SQLException{
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("Id"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }
}
