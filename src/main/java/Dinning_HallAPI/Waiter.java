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
    private ArrayList<Order> requestOrders = Handler.getOrders();
    private static final  String POST_API_URL = "http://kitchen:8081/order";
    private static final TimeUnit unit = DinningHallApiApplication.getUnit();
    private static final ArrayList<Integer> marks = new ArrayList<Integer>();

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


            for (int i = 0; i < requestOrders.size(); i++) {
                Order reqOrder = requestOrders.get(i);
                for (int j = 0; j < orders.size(); j++) {
                    Order order = orders.get(j);
                    if( order.equals(reqOrder)){
                        long timestamp = System.nanoTime()/1000000L;
                        Table table = findTable(order.getTable_id());
                        marks.add(table.evaluation(order,timestamp));
                        System.out.println("Reputation = " + reputationCalculation());
                        table.setStatus(Status.FREE);
                        requestOrders.remove(reqOrder);
                        orders.remove(order);
                    }
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

    private Table findTable(int table_id) {
        Table init = null;
        for (Table table : tables) {
            if (table.getId() == table_id) {
                init = table;
                break;
            }
        }
        return init;
    }

    private int reputationCalculation(){
        int reputation = 0;
        int sum = marks.stream().mapToInt(a -> a).sum();
        if (marks.size() != 0){
            reputation = sum /marks.size();
        }
        return reputation;
    }
}
