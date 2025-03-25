package application;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

import java.sql.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        System.out.println("=== Teste 1: vendedor findById: ===");
        VendedorDao vendedorDao = DaoFactory.createVendedorDao();
        Vendedor vendedor = vendedorDao.findById(3);
        System.out.println(vendedor);

        System.out.println("\n=== Teste 2: vendedor findByDepartamento: ===");
        Departamento departamento = new Departamento(2, null);
        List<Vendedor> listaVendedoresDepartamento = vendedorDao.findByDepartamento(departamento);
        listaVendedoresDepartamento.forEach(System.out::println);

        System.out.println("\n=== Teste 3: vendedor findAll: ===");
        List<Vendedor> listaVendedoresTodos = vendedorDao.findAll();
        listaVendedoresTodos.forEach(System.out::println);


        System.out.println("\n=== Teste 4: vendedor insert: ===");
        Vendedor novoVendedor = new Vendedor(null,
                "Michael de Santa",
                "michaeltownley@gmail.com",
                Date.valueOf("1968-10-4"),
                4000.0,
                new Departamento(1, null));

        //vendedorDao.insert(novoVendedor);

        System.out.println("\n=== Teste 5: vendedor update: ===");
        Vendedor atualizarVendedor = vendedorDao.findById(1);
        atualizarVendedor.setNome("Tommy Vercetti");
        atualizarVendedor.setEmail("tomver@gmail.com");
        vendedorDao.update(atualizarVendedor);

    }
}
