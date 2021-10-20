package Dinning_HallAPI;

public class Order {
  private int order_id = count++;
  private int[] items;
  private int priority;
  private float max_wait;
  private int table_id;
  private int waiter_id;
  private long pick_up_time;
  private static int count = 0;


    public Order( int[] items, int priority, float max_wait, int table_id) {
        this.items = items;
        this.priority = priority;
        this.max_wait = max_wait;
        this.table_id = table_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int[] getItems() {
        return items;
    }

    public void setItems(int[] items) {
        this.items = items;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public float getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(float max_wait) {
        this.max_wait = max_wait;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public int getWaiter_id() {
        return waiter_id;
    }

    public void setWaiter_id(int waiter_id) {
        this.waiter_id = waiter_id;
    }

    public long getPick_up_time() {
        return pick_up_time;
    }

    public void setPick_up_time(long pick_up_time) {
        this.pick_up_time = pick_up_time;
    }

}
