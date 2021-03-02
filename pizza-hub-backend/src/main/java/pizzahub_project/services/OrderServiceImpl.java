package pizzahub_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizzahub_project.models.CartOrder;
import pizzahub_project.models.OrderPizza;
import pizzahub_project.repositories.contracts.OrderRepository;
import pizzahub_project.repositories.contracts.PizzaRepository;
import pizzahub_project.services.contracts.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PizzaRepository pizzaRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, PizzaRepository pizzaRepository) {
        this.orderRepository = orderRepository;
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public List<CartOrder> showAll() {
        return orderRepository.showAll();
    }

    @Override
    public CartOrder getCurrentOrder() {
        return orderRepository.getCurrentOrder();
    }

    @Override
    public void addPizzaToCartOrder(int pizzaId) {
        CartOrder order = getCurrentOrder();
        List<OrderPizza> orderPizzasList = order.getOrderPizzasList();
        boolean hasPizza = false;

        for (OrderPizza orderPizza : orderPizzasList) {
            if (orderPizza.getPizza().getPizzaId() == pizzaId) {
                orderPizza.setPizzaAmount(orderPizza.getPizzaAmount() + 1);
                hasPizza = true;
            }
        }

        if (!hasPizza) {
            OrderPizza newOrderPizza = new OrderPizza();
            newOrderPizza.setCartOrder(order);
            newOrderPizza.setPizza(pizzaRepository.getById(pizzaId));
            newOrderPizza.setPizzaAmount(1);
            orderPizzasList.add(newOrderPizza);
        }

        orderRepository.updateOrder(order);
    }

    @Override
    public void editPizzaInCartOrder(int pizzaId, int amount) {
        if(amount == 0){
            deletePizzaFromCartOrder(pizzaId);
            return;
        }

        CartOrder order = getCurrentOrder();
        List<OrderPizza> orderPizzasList = order.getOrderPizzasList();
        boolean hasPizza = false;

        for (OrderPizza orderPizza : orderPizzasList) {
            if (orderPizza.getPizza().getPizzaId() == pizzaId) {
                orderPizza.setPizzaAmount(amount);
                hasPizza = true;
            }
        }

        if (!hasPizza) {
            OrderPizza newOrderPizza = new OrderPizza();
            newOrderPizza.setPizza(pizzaRepository.getById(pizzaId));
            newOrderPizza.setPizzaAmount(amount);
            orderPizzasList.add(newOrderPizza);
        }

        orderRepository.updateOrder(order);
    }

    @Override
    public void deletePizzaFromCartOrder(int pizzaId) {
        CartOrder order = getCurrentOrder();
        List<OrderPizza> list = order.getOrderPizzasList();
        list.removeIf(orderPizza -> orderPizza.getPizza().getPizzaId() == pizzaId);
        order.setOrderPizzasList(list);
        orderRepository.updateOrder(order);
    }

    @Override
    public void checkOutCart(int orderId) {
        CartOrder order = getCurrentOrder();
        if(orderId == order.getOrderId() && !order.getOrderPizzasList().isEmpty()){
            order.setCheckedOut(true);
            order.setDate(java.time.LocalDateTime.now().toString());
        }
        orderRepository.updateOrder(order);
    }
}
