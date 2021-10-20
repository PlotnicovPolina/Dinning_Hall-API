package Dinning_HallAPI;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class Waiter implements Runnable {
    private int id = count++;
    private static int count = 0;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders = new ArrayList<>();
    private static final  String POST_API_URL = "http://localhost:8081/order";

    Waiter (ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    public int getId() {
        return id;
    }

    public void getOrders(){
        for (Order order:orders) {
            System.out.println("Table ID" + order.getTable_id());
            System.out.print("ID: " + order.getOrder_id());
            System.out.print("\nItems: ");
            for (int item: order.getItems()) {
                System.out.print(item + " ");
            }
            System.out.println("\nPriority: " + order.getPriority() + "\nMax_Wait: " + order.getMax_wait() + "\n");
        }
    }

    @Override
    public void run() {
        while (true){
            for (Table table: tables)
            {
                if (table.getStatus() == Status.READY && table.tryLock() ){
                    int random = (int) (Math.random()*2+2);
                    try {
                        Thread.sleep(random*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Order order = table.createOrder();
                    order.setPick_up_time(System.nanoTime()/1000000L);
                    order.setWaiter_id(id);
                    orders.add(order);
                    post(order);
                    table.setStatus(Status.WAIT);
                    System.out.println("Waiter ID:" + id);
                    getOrders();
                    System.out.println("Table " + table.getId() + " is wait!");
                    table.unLock();
                }
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void post(Order order){
        JSONObject json = new JSONObject(order);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(json.toString(),httpHeaders);
        restTemplate.postForObject(POST_API_URL, httpEntity, String.class);
    }
}
