package Dinning_HallAPI;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@RestController
public class Handler {
    private static final ArrayList<Order> orders = new ArrayList<>();

    private static void add(Order order) {
        orders.add(order);
    }

    public static ArrayList getOrders() {
        return orders;
    }

    private static final TimeUnit unit = DinningHallApiApplication.getUnit();

    @PostMapping(value = "/distribution", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String get(@RequestBody Order order) {
        add(order);
        order.printOrderRequest();
        return "200";
    }

}
