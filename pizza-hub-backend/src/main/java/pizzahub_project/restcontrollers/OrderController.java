package pizzahub_project.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pizzahub_project.models.CartOrder;
import pizzahub_project.services.contracts.OrderService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ResponseBody
    public String getAllCarts() {
        List<CartOrder> cartOrders = orderService.showAll();
        StringBuilder resp = new StringBuilder("[");
        CartOrder lastCartOrder = cartOrders.get(cartOrders.size() - 1);
        for (CartOrder cartOrder : cartOrders) {
            resp.append("{\"orderId\":\"" + cartOrder.getOrderId() + "\", " +
                    "\"date\":\"" + cartOrder.getDate() + "\", " +
                    "\"checkedOut\":\"" + cartOrder.isCheckedOut() + "\", " +
                    "\"orderPizzasList\":" + cartOrder.getOrderPizzasList().toString() +
                    "}");
            if (cartOrder != lastCartOrder) {
                resp.append(",");
            }
        }
        resp.append("]");
        return resp.toString();
    }

    @GetMapping(path = "/current-order")
    @ResponseBody
    public String getCurrentCart() {
        CartOrder cartOrder = orderService.getCurrentOrder();

        StringBuilder resp = new StringBuilder();
        resp.append("{\"orderId\":\"" + cartOrder.getOrderId() + "\", " +
                "\"date\":\"" + cartOrder.getDate() + "\", " +
                "\"checkedOut\":\"" + cartOrder.isCheckedOut() + "\", " +
                "\"orderPizzasList\":" + cartOrder.getOrderPizzasList().toString() +
                "}");
        return resp.toString();
    }

    @PostMapping("/add-pizza")
    @ResponseBody
    public void addPizzaToCart(@RequestBody String pizzaId){
        orderService.addPizzaToCartOrder(Integer.parseInt(pizzaId));
    }

    @PutMapping("/edit-pizza")
    @ResponseBody
    public void editPizzaInCart(@RequestBody String pizzaIdAmount){
        String[] info = pizzaIdAmount.split("#");
        int pizzaId = Integer.parseInt(info[0]);
        int amount = Integer.parseInt(info[1]);
        if(amount == 0){
            orderService.deletePizzaFromCartOrder(pizzaId);
        } else {
            orderService.editPizzaInCartOrder(pizzaId, amount);
        }
    }

    @PostMapping("/delete-pizza")
    @ResponseBody
    public void deletePizzaFromCart(@RequestBody String pizzaId){
        orderService.deletePizzaFromCartOrder(Integer.parseInt(pizzaId));
    }

    @PostMapping("/check-out")
    @ResponseBody
    public void checkOutCart(@RequestBody String orderId){
        orderService.checkOutCart(Integer.parseInt(orderId));
    }

}