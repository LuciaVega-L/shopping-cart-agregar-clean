package isi.shoppingCart.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<CartItem>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addProduct(Product product) {
        int i;

        for (i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);

            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity();
                return;
            }
        }

        items.add(new CartItem(product, 1));
    }

    public int getQuantityByProductId(int productId) {
        int i;

        for (i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);

            if (item.getProduct().getId() == productId) {
                return item.getQuantity();
            }
        }

        return 0;
    }

    public double getTotal() {
        double total = 0.0;
        int i;

        for (i = 0; i < items.size(); i++) {
            total = total + items.get(i).getSubtotal();
        }

        return total;
    }
}
