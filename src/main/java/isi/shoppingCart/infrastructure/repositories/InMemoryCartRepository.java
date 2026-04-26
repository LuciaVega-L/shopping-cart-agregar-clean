package isi.shoppingCart.infrastructure.repositories;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.usecases.ports.CartRepository;

public class InMemoryCartRepository implements CartRepository {
    private Cart cart;

    public Cart getCart() {
        return cart;
    }

    public void save(Cart cart) {
        this.cart = cart;
    }
}
