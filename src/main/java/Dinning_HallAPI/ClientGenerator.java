package Dinning_HallAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ClientGenerator implements Runnable{
    private static final TimeUnit unit = DinningHallApiApplication.getUnit();
    private final ArrayList<Table> tables;
    ClientGenerator(ArrayList<Table>tables){
        this.tables = tables;
    }

    @Override
    public void run() {
        while (true){
            int random = (int) (Math.random()*(tables.size()-1));

            if (tables.get(random).getStatus() == Status.FREE){
                tables.get(random).setStatus(Status.READY);
                System.out.println("Table " + tables.get(random).getId() + " is READY!");
            }
            try {
                unit.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
