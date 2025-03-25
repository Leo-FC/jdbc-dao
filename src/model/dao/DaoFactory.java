package model.dao;

import db.DB;
import model.dao.impl.DepartamentoDaoJDBC;
import model.dao.impl.VendedorDaoJDBC;

// Fabrica é uma classe auxiliar responsavel por instanciar os Daos
public class DaoFactory {
    public static VendedorDao createVendedorDao(){
        return new VendedorDaoJDBC(DB.getConnection());
    }

    public static DepartamentoDao createDepartamentoDao(){
        return new DepartamentoDaoJDBC(DB.getConnection());
    }
}
