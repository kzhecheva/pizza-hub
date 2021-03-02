package pizzahub_project.models;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "order_pizzas")
public class OrderPizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_pizzas_id")
    private int cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private CartOrder cartOrder;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Column(name = "pizza_amount")
    private int pizzaAmount;

    public OrderPizza() {
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public CartOrder getCartOrder() {
        return cartOrder;
    }

    public void setCartOrder(CartOrder cartOrder) {
        this.cartOrder = cartOrder;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getPizzaAmount() {
        return pizzaAmount;
    }

    public void setPizzaAmount(int pizzaAmount) {
        this.pizzaAmount = pizzaAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderPizza)) return false;
        OrderPizza orderPizza = (OrderPizza) o;
        return getCartId() == orderPizza.getCartId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCartId());
    }

    @Override
    public String toString() {
        return "{" +
//                "\"cartId\":\"" + getCartId() + "\", " +
//                "\"cartOrder\":\"" + getCartOrder().toString() + "\", " +
                "\"pizza\":" + getPizza() + ", " +
                "\"pizzaAmount\":\"" + getPizzaAmount() + "\"" +
                "}";
    }

}
