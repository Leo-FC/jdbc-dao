package model.dao;

import model.entities.Vendedor;

import java.util.List;

public interface VendedorDao {
    void insert(Vendedor obj);
    void update(Vendedor obj);
    void deleteById(Integer id);
    Vendedor findById(Integer id);
    List<Vendedor> findAll();
}
