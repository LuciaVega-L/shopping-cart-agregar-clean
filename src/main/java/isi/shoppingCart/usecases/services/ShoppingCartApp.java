package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;
import java.util.List;

public class ShoppingCartApp {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private AgregarProductoAlCarritoUseCase agregarProductoAlCarritoUseCase;

    public ShoppingCartApp(ProductRepository productRepository,
                           CartRepository cartRepository,
                           AgregarProductoAlCarritoUseCase agregarProductoAlCarritoUseCase) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.agregarProductoAlCarritoUseCase = agregarProductoAlCarritoUseCase;
    }

    public List<Product> getCatalogProducts() {
        return productRepository.findAll();
    }

    public List<CartItem> getCartItems() {
        Cart cart = cartRepository.getCart();
        return cart.getItems();
    }

    public double getCartTotal() {
        Cart cart = cartRepository.getCart();
        return cart.getTotal();
    }

    public String addProductToCart(int productId) {
        return agregarProductoAlCarritoUseCase.execute(productId);
    }
}
