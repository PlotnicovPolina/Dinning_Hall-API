package Dinning_HallAPI;

import java.util.ArrayList;

public class Table {

    private static int count = 0;
    private int id = count++;
    private Status status;
    private Order order;

    private static ArrayList<Table> tables = new ArrayList();

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

    public void getOrder(){
        System.out.print("ID: " + order.getId());
        System.out.print("\nItems: ");
        for (int item: order.getItems()) {
            System.out.print(item + " ");
        }
        System.out.println("\nPriority: " + order.getPriority() + "\nMax_Wait: " + order.getMax_wait() + "\n");
    }

    public void createOrder () {
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
        order = new Order(items, priority, maxWait);

    }

    public static ArrayList<Table> GenerateTables (int number){
        for (int i = 0; i <= number; i++) {
            tables.add(new Table(Status.FREE));
        }
        return tables;
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
}

