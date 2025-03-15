package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class VendedorDaoJDBC implements VendedorDao {

    private Connection conn;

    public VendedorDaoJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Vendedor obj) {

    }

    @Override
    public void update(Vendedor obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Vendedor findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        try{
            pst = conn.prepareStatement(
                    "SELECT sl.*, dp.DepName"
                    + " FROM seller sl"
                    + " INNER JOIN department dp ON sl.DepartmentId = dp.id"
                    + " WHERE sl.id = ?");

            pst.setInt(1, id);
            rs = pst.executeQuery();

            // esse if é para testar se veio algum resultado
            if(rs.next()){
                Departamento dep = instantiateDepartamento(rs);

                Vendedor obj = instantiateVendedor(rs, dep);

                return obj;
            }
            return null; // vai retornar nulo caso nao ache alguem com o id informado
        }catch (SQLException e){
            throw new DbException(e.getMessage());
        }finally {
            DB.closeStatement(pst);
            DB.closeResultSet(rs);
        }

    }

    @Override
    public List<Vendedor> findAll() {
        return List.of();
    }

    private Departamento instantiateDepartamento(ResultSet rs) throws SQLException{
        Departamento dep = new Departamento();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setNome(rs.getString("DepName"));
        return dep;
    }

    private Vendedor instantiateVendedor(ResultSet rs, Departamento dep) throws SQLException{
        Vendedor obj = new Vendedor();
        obj.setId(rs.getInt("Id"));
        obj.setNome(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setSalarioBase(rs.getDouble("BaseSalary"));
        obj.setDataNascimento(rs.getDate("BirthDate"));
        obj.setDepartamento(dep);
        return obj;
    }
}
