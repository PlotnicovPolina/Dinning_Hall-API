package Dinning_HallAPI;

import java.util.ArrayList;

public class Waiter implements Runnable {
    private int id = count++;
    private static int count = 0;
    private ArrayList<Table> tables;

    Waiter (ArrayList<Table> tables)
    {
        this.tables = tables;
    }

    @Override
    public void run() {
        for (Table table: tables)
            synchronized (table) {
            if (table.getStatus() == Status.READY ){
                int random = (int) (Math.random()*2+2);
                table.createOrder();
                try {
                    wait(random*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
