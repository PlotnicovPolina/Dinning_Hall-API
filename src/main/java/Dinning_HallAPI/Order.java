package Dinning_HallAPI;

import java.util.Arrays;

public class Order {
  private int id = count++;
  private int[] items;
  private int priority;
  private float max_wait;

  private static int count = 0;

    public Order( int[] items, int priority, float max_wait) {
        this.items = items;
        this.priority = priority;
        this.max_wait = max_wait;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float  getMax_wait() {
        return max_wait;
    }

    public void setMax_wait(float max_wait) {
        this.max_wait = max_wait;
    }
}
