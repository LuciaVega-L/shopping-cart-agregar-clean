package isi.shoppingCart.infrastructure.repositories;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class InMemoryCartRepository implements CartRepository {
    private Cart cart;

    public InMemoryCartRepository(ProductRepository productRepository) {
        cart = new Cart();

        Product product1 = productRepository.findById(1);
        Product product2 = productRepository.findById(2);
        Product product3 = productRepository.findById(3);

        addInitialProduct(product1);
        addInitialProduct(product2);
        addInitialProduct(product2);
        addInitialProduct(product3);
    }

    private void addInitialProduct(Product product) {
        if (product != null && product.hasAvailableQuantity()) {
            cart.addProduct(product);
            product.decreaseAvailableQuantity();
        }
    }

    public Cart getCart() {
        return cart;
    }

    public void save(Cart cart) {
        this.cart = cart;
    }
}
