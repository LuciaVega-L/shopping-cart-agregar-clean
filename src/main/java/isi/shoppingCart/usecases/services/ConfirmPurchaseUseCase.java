package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.CartItem;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;
import java.util.List;

public class ConfirmPurchaseUseCase {
    private ProductRepository productRepository;
    private CartRepository cartRepository;

    public ConfirmPurchaseUseCase(ProductRepository productRepository, CartRepository cartRepository){
        this.productRepository=productRepository;
        this.cartRepository=cartRepository;
    }
    public OperationResult ValidarCarrito(){
        Cart cart = cartRepository.getCart();
        if(cart.getTotal()==0){
            return OperationResult.fail("No se puede realizar la compra, el carrito esta vacio. Por favor agregue productos si desea comprar");
        }
        List<CartItem> listaProductosCarrito = cart.getItems();

        for (int i = 0; i < listaProductosCarrito.size(); i++) {
            CartItem item = listaProductosCarrito.get(i);
            Product productosTienda = productRepository.findById(item.getProduct().getId());

            if (productosTienda != null) {
                productosTienda.decreaseAvailableQuantity(item.getQuantity());
            }
        }
        cartRepository.save(new Cart());
        return OperationResult.ok("Felicidades, tu compra ha sido realizada! Disfruta!");
    }
}
