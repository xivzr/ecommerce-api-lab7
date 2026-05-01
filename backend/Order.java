import java.util.Objects;

public class Order {

    private Long orderId;
    private String description;
    private int amount;

    public Order(Long orderId, String description, int amount) {
        this.orderId = orderId;
        this.description = description;
        this.amount = amount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return amount == order.amount &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, description, amount);
    }
}