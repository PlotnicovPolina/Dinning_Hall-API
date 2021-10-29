package Dinning_HallAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class Table {

    private static int count = 0;
    private final int id = count++;
    private Status status;
    private Order order;
    private final ReentrantLock lock = new ReentrantLock();


    private static final ArrayList<Table> tables = new ArrayList();

    Table (Status status){
        this.status =status;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static ArrayList<Table> getTables() {
        return tables;
    }

    public Order createOrder () {
        int numberOfItems = (int) (Math.random()*5+1);
        int[] items = new int[numberOfItems];
        for (int i = 0; i < numberOfItems; i++){
            items[i] = (int)(Math.random()*10+1);
        }
        int priority = (int) (Math.random()*5+1);
        int maxTimePreparation = 0;
        for (int item: items){
            if (TimePreparation(item) > maxTimePreparation) maxTimePreparation = TimePreparation(item);
        }
        float maxWait = (float) (maxTimePreparation * 1.3);
        return order = new Order(items, priority, maxWait, id);
    }

    public int evaluation(Order order, long timestamp){
        int mark = 0;
        long Order_total_preparing_time = (timestamp -order.getPick_up_time()) / 1000 ;
        System.out.println(Order_total_preparing_time);
        double[] coefficients = new  double[]{ 1, 1.1, 1.2, 1.3, 1.4};
        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            if ((order.getMax_wait() * coefficient) >= Order_total_preparing_time){
                switch (i){
                    case 0: mark = 5; break;
                    case 1: mark = 4; break;
                    case 2: mark = 3; break;
                    case 3: mark = 2; break;
                    case 4: mark = 1; break;
                    default: mark = 0;
                }
                break;
            }

        }
        System.out.println(mark);
        return mark;
    }

    private int TimePreparation (int id){
        int timePreparation = 0;
        switch (id){
            case 1:
            case 7:
                timePreparation = 20; break;
            case 2:
            case 6:
                timePreparation = 10; break;
            case 3: timePreparation = 7; break;
            case 4: timePreparation = 32; break;
            case 5: timePreparation = 35; break;
            case 8: timePreparation = 30; break;
            case 9:
            case 10:
                timePreparation = 15;
        }
        return timePreparation;
    }

    public boolean tryLock(){
        return lock.tryLock();
    }

    public void unLock(){
        lock.unlock();
    }
}

