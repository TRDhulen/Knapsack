package code.Bag;

public class Item {
    private int weight;
    private int value;
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
    public int getWeight(){ return weight; }
    public int getValue(){ return value; }
    public void setWeight(int weight) { this.weight = weight; }
    public void setValue(int value) { this.value = value; }
    public double getValuePerWeight() { return (double) value/weight; }
    public String toString() { return "Item[Weight and Value] = " + weight + " and " + value + "."; }
}
