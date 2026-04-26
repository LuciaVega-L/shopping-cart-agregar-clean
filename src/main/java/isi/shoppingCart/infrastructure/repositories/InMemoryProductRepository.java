package isi.shoppingCart.infrastructure.repositories;

import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    private List<Product> products;

    public InMemoryProductRepository() {
        products = new ArrayList<Product>();
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Product findById(int id) {
        int i;

        for (i = 0; i < products.size(); i++) {
            Product product = products.get(i);

            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    public void save(Product product) {
        products.add(product);
    }
}
