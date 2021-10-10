package Dinning_HallAPI;

import java.util.ArrayList;

public class ClientGenerator implements Runnable{
    private ArrayList<Table> tables;
    ClientGenerator(ArrayList<Table>tables){
        this.tables = tables;
    }

    @Override
    public void run() {
        while (true){
            int random = (int) (Math.random()*(tables.size()-1));

            if (tables.get(random).getStatus() == Status.FREE){
                tables.get(random).setStatus(Status.READY);
                System.out.println("Table " + tables.get(random).getId() + " is free!");
            }
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
