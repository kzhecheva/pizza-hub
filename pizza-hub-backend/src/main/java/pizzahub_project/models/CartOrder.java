package pizzahub_project.models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart_order")
public class CartOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_date")
    private String date;

    @Column(name = "checked_out")
    private boolean checkedOut;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "cartOrder")
    private List<OrderPizza> orderPizzasList;

    public CartOrder() {
    }

    public CartOrder(String date, boolean checkedOut) {
        this.date = date;
        this.checkedOut = checkedOut;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public List<OrderPizza> getOrderPizzasList() { return orderPizzasList; }

    public void setOrderPizzasList(List<OrderPizza> orderPizzasList) {
        this.orderPizzasList = orderPizzasList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CartOrder)) return false;
        CartOrder cartOrder = (CartOrder) o;
        return getOrderId() == cartOrder.getOrderId();
    }

    public int hashCode() {
        return Objects.hash(getOrderId());
    }
}
