package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {
    public static void main(String[] args) {
        System.out.println("=== Teste 1: departament insert: ===");
        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
        Departamento novoDepartamento = new Departamento(null, "Cinema");
        departamentoDao.insert(novoDepartamento);


    }
}
