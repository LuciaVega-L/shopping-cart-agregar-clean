package isi.shoppingCart.usecases.ports;

import isi.shoppingCart.entities.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
}
