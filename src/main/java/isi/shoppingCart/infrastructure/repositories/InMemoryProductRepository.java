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

        products.add(new Product(1, "Laptop", 2500.0, 3));
        products.add(new Product(2, "Mouse", 80.0, 3));
        products.add(new Product(3, "Teclado", 150.0, 4));
        products.add(new Product(4, "Monitor", 900.0, 1));
        products.add(new Product(5, "Audifonos", 200.0, 2));
        products.add(new Product(6, "Webcam", 180.0, 2));
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
}
