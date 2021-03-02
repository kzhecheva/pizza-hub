package pizzahub_project.repositories.contracts;

import pizzahub_project.models.CartOrder;

import java.util.List;

public interface OrderRepository {

    List<CartOrder> showAll();

    CartOrder getCurrentOrder();

    void updateOrder(CartOrder cartOrder);
}
