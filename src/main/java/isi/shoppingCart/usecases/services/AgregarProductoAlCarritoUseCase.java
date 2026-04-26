package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
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

    public OperationResult execute(int productId) {
        Product product = productRepository.findById(productId);

        if (product == null) {
            return OperationResult.fail("Producto no encontrado.");
        }

        Cart cart = cartRepository.getCart();
        int quantityInCart = cart.getQuantityByProductId(productId);
        int availableQuantity = product.getAvailableQuantity();

        if (quantityInCart >= availableQuantity) {
            return OperationResult.fail("No se puede agregar más unidades. Cantidad disponible: " + availableQuantity + ".");
        }

        cart.addProduct(product);
        cartRepository.save(cart);

        return OperationResult.ok("Producto agregado al carrito.");
    }
}
