package pizzahub_project.services.contracts;

import pizzahub_project.models.CartOrder;

import java.util.List;

public interface OrderService {
    List<CartOrder> showAll();

    CartOrder getCurrentOrder();

    void addPizzaToCartOrder(int pizzaId);

    void editPizzaInCartOrder(int pizzaId, int amount);

    void deletePizzaFromCartOrder(int pizzaId);

    void checkOutCart(int orderId);
}
