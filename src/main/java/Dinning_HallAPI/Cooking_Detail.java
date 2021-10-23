package Dinning_HallAPI;

public class Cooking_Detail {
    private int food_id;
    private int cook_id;

    public Cooking_Detail(int food_id, int cook_id) {
        this.food_id = food_id;
        this.cook_id = cook_id;
    }

    public int getFood_id() {
        return food_id;
    }

    public int getCook_id() {
        return cook_id;
    }

    public void setFood_id(int food_id) {
        this.food_id = food_id;
    }

    public void setCook_id(int cook_id) {
        this.cook_id = cook_id;
    }

    @Override
    public String toString() {
        return "Cooking_Detail{" +
                "food_id=" + food_id +
                ", cook_id=" + cook_id +
                '}';
    }
}
