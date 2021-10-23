package Dinning_HallAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Waiter implements Runnable {
    private int id = count++;
    private static int count = 0;
    private ArrayList<Table> tables;
    private ArrayList<Order> orders = new ArrayList<>();
    private static final  String POST_API_URL = "http://localhost:8081/order";
    private static final TimeUnit unit = DinningHallApiApplication.getUnit();

    Waiter (ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        while (true){
            for (Table table: tables)
            {
                if (table.getStatus() == Status.READY && table.tryLock() ){
                    int random = (int) (Math.random()*2+2);
                    try {
                        unit.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Order order = table.createOrder();
                    order.setPick_up_time(System.nanoTime()/1000000L);
                    order.setWaiter_id(id);
                    orders.add(order);
                    post(order);
                    table.setStatus(Status.WAIT);
                    System.out.println(order);
                    System.out.println("Table " + table.getId() + " is wait!");
                    table.unLock();
                }
            }
            try {
                unit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void post(Order order){
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(json,httpHeaders);
        restTemplate.postForObject(POST_API_URL, httpEntity, String.class);
    }
}
