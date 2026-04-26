package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.infrastructure.repositories.InMemoryCartRepository;
import isi.shoppingCart.infrastructure.repositories.InMemoryProductRepository;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;
import java.util.List;

public class ShoppingCartApp {
    private ProductRepository productRepository;
    private CartRepository cartRepository;
    private AgregarProductoAlCarritoUseCase agregarProductoAlCarritoUseCase;
    private ConfirmarCompraUseCase confirmarCompraUseCase;

    public ShoppingCartApp() {
        productRepository = new InMemoryProductRepository();
        cartRepository = new InMemoryCartRepository();
        agregarProductoAlCarritoUseCase = new AgregarProductoAlCarritoUseCase(productRepository, cartRepository);

        cargarDatosIniciales();
    }

    public ShoppingCartApp(ProductRepository productRepository,
                           CartRepository cartRepository,
                           AgregarProductoAlCarritoUseCase agregarProductoAlCarritoUseCase, ConfirmarCompraUseCase confirmarCompraUseCase) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.agregarProductoAlCarritoUseCase = agregarProductoAlCarritoUseCase;
        this.confirmarCompraUseCase= confirmarCompraUseCase;
    }

    private void cargarDatosIniciales() {
        cargarCatalogoInicial();
        cargarCarritoInicial();
    }

    private void cargarCatalogoInicial() {
        productRepository.save(new Product(1, "Laptop", 2500.0, 3));
        productRepository.save(new Product(2, "Mouse", 80.0, 2));
        productRepository.save(new Product(3, "Teclado", 150.0, 5));
        productRepository.save(new Product(4, "Monitor", 900.0, 1));
        productRepository.save(new Product(5, "Audifonos", 200.0, 4));
        productRepository.save(new Product(6, "Webcam", 180.0, 2));
    }

    private void cargarCarritoInicial() {
        Cart cart = new Cart();

        Product product1 = productRepository.findById(1);
        Product product2 = productRepository.findById(2);
        Product product3 = productRepository.findById(3);

        if (product1 != null) {
            cart.addProduct(product1);
        }

        if (product2 != null) {
            cart.addProduct(product2);
            cart.addProduct(product2);
        }

        if (product3 != null) {
            cart.addProduct(product3);
        }

        cartRepository.save(cart);
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

    public OperationResult addProductToCart(int productId) {
        return agregarProductoAlCarritoUseCase.execute(productId);
    }
    public String confirmarCompraUseCase(){
        return confirmarCompraUseCase.ValidarCarrito();
    }
}
