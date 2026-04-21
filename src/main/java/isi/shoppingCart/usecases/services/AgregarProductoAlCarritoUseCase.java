package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class AgregarProductoAlCarritoUseCase {
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public AgregarProductoAlCarritoUseCase(ProductRepository productRepository,
                                           CartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }

    public String execute(int productId) {
        Product product = productRepository.findById(productId);

        if (product == null) {
            return "Producto no encontrado.";
        }

        if (!product.hasAvailableQuantity()) {
            return "No hay cantidad disponible para agregar este producto.";
        }

        Cart cart = cartRepository.getCart();
        cart.addProduct(product);
        product.decreaseAvailableQuantity();
        cartRepository.save(cart);

        return "";
    }
}
