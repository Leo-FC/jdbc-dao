package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("=== Teste 1: vendedor findById: ===");
        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println("\n=== Teste 2: vendedor findByDepartamento: ===");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> listaVendedores = vendedorDao.findByDepartamento(departamento);
        listaVendedores.forEach(System.out::println);


    }
}
