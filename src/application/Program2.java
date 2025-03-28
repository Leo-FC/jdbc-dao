package application;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {
    public static void main(String[] args) {
        DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();

        System.out.println("=== Teste 1: departament insert: ===");
        Departamento novoDepartamento = new Departamento(null, "Cinema");
        //departamentoDao.insert(novoDepartamento);

        System.out.println("=== Teste 2: departament findById: ===");
        Departamento dep1 = departamentoDao.findById(1);
        System.out.println(dep1);

        System.out.println("=== Teste 3: departament update: ===");
        Departamento atualizarDepartamento = departamentoDao.findById(6);
        atualizarDepartamento.setNome("Music");
        departamentoDao.update(atualizarDepartamento);

        System.out.println("=== Teste 4: departament deleteById: ===");
        //departamentoDao.deleteById(8);



    }
}
