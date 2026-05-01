import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static List<Order> orderGenerator(int numberOfOrders) {

        if (numberOfOrders <= 0) {
            throw new RuntimeException("Invalid value");
        }

        List<Order> generatedOrder = new ArrayList<>();

        for (int i = 0; i < numberOfOrders; i++) {

            long orderId = (long) (Math.random() * 10);

            generatedOrder.add(new Order(
                    orderId,
                    "Order " + orderId,
                    (int) (Math.random() * 200)
            ));
        }

        return generatedOrder;
    }

    public static void main(String[] args) {
        

        List<Order> orders = orderGenerator(10);

        List<Order> originalOrders = new ArrayList<>(orders);

        System.out.println("STEP 1: ALL ORDERS");
        for (Order order : orders) {
            System.out.println("ID: " + order.getOrderId()
                    + ", Description: " + order.getDescription()
                    + ", Amount: " + order.getAmount());
        }

        orders.add(new Order(999L, "Special Order", 180));

        List<Order> sortedOrders = orders.stream()
                .sorted(Comparator.comparingInt(Order::getAmount).reversed())
                .toList();

        System.out.println("\nSTEP 2: SORTED ORDERS (DESC)");
        sortedOrders.forEach(o ->
                System.out.println(o.getDescription() + " - " + o.getAmount())
        );

        List<String> highValueOrders = orders.stream()
                .filter(o -> o.getAmount() > 150)
                .map(Order::getDescription)
                .toList();

        System.out.println("\nSTEP 3: >150 ORDERS");
        highValueOrders.forEach(System.out::println);

        double avg = originalOrders.stream()
                .mapToInt(Order::getAmount)
                .average()
                .orElse(0.0);

        System.out.println("\nSTEP 4: AVERAGE = " + avg);

        Map<String, Integer> totals = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getDescription,
                        Collectors.summingInt(Order::getAmount)
                ));

        System.out.println("\nSTEP 5: TOTAL PER DESCRIPTION");
        totals.forEach((k, v) ->
                System.out.println(k + " = " + v)
        );
    }
}