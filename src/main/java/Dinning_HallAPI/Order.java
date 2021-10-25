package Dinning_HallAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;

public class Order {
  private int order_id = count++;
  private int table_id;
  private int waiter_id;
  private int[] items;
  private int priority;
  private float max_wait;
  private long pick_up_time;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private long cooking_time;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private ArrayList<Cooking_Detail> cooking_details = new ArrayList<>();
  @JsonIgnore
  private static int count = 0;


    public Order( int[] items, int priority, float max_wait, int table_id) {
        this.items = items;
        this.priority = priority;
        this.max_wait = max_wait;
        this.table_id = table_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", table_id=" + table_id +
                ", waiter_id=" + waiter_id +
                ", items=" + Arrays.toString(items) +
                ", priority=" + priority +
                ", max_wait=" + max_wait +
                ", pick_up_time=" + pick_up_time +
                "}";
    }

    public  void printOrderRequest (){
        System.out.println(
                "Order{" +
                        "order_id=" + order_id +
                        ", table_id=" + table_id +
                        ", waiter_id=" + waiter_id +
                        ", items=" + Arrays.toString(items) +
                        ", priority=" + priority +
                        ", max_wait=" + max_wait +
                        ", pick_up_time=" + pick_up_time +
                        ", cooking_time=" + cooking_time +
                        ", cooking_details=" + cooking_details +
                        '}'
        );

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

    public long getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(long cooking_time) {
        this.cooking_time = cooking_time;
    }

    public ArrayList<Cooking_Detail> getCooking_details() {
        return cooking_details;
    }

    public void setCooking_details(ArrayList<Cooking_Detail> cooking_details) {
        this.cooking_details = cooking_details;
    }

    @Override
    public boolean equals(Object order) {
        if (this == order) return true;
        if ( order == null || getClass() != order.getClass()) return false;
        Order that = (Order) order;
        return order_id == that.order_id && table_id == that.table_id && waiter_id == that.waiter_id
                &&  Arrays.toString(items).equals( Arrays.toString(that.items)) &&
                priority == that.priority && max_wait == that.max_wait &&
                pick_up_time == that.pick_up_time;
    }
}
