package code.Bag;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Bag {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numItems = scanInput(scanner, 1);
        if(numItems == -1) {
            scanner.close(); 
            System.exit(0);
        }
        Item[] items = new Item[numItems];
        final int SCALE = 10;
        int min = -1;
        for(int n = 0; n < numItems; n++) {
            int iWeight = (int) (Math.random() * SCALE + 1);
            if(min == -1 || iWeight < min) { min = iWeight; }
            Item i = new Item((int) (Math.random() * SCALE + 1), iWeight);
            System.out.println((n + 1) + ". " + i.toString());
            items[n] = i;
        }
        // Can dynamically add items by ArrayList items, adding to index since array is sorted.
        quickSort(items, 0, numItems - 1);
        HashMap<Integer, ArrayList<Item>> map = new HashMap<>();
        while(true) {
            int weight = scanInput(scanner, 0);
            if(weight == -1) { break; }
            ArrayList<Item> ret = map.get(weight);
            if(ret == null) {
                int temp = weight;
                ret = new ArrayList<Item>();
                for(int num = 0; num < numItems; num++) {
                    int itemWeight = items[num].getWeight();
                    if(itemWeight <= temp) {
                        ret.add(items[num]);
                        temp -= itemWeight;
                        if(temp < min) {
                            break;
                        }
                    }
                }
                map.put(weight, ret);
            }
            System.out.println("For weight " + weight + ", the items are:");
            if(ret.isEmpty()) { System.out.println("None."); }
            int index = 1, totalWeight = 0, totalValue = 0;
            for(Item item : ret) {
                totalWeight += item.getWeight();
                totalValue += item.getValue();
                System.out.println(index++ + ". " + item.toString());
            }
            System.out.println("Total Weight: " + totalWeight + " and total value: " + totalValue + ".");
        }
        scanner.close();
        System.exit(0);
    }

    private static int scanInput(Scanner scanner, int mode){
        int ret = -1;
        while(true) {
            switch(mode) {
                case 0:
                System.out.println("What is the requested weight?\nType 'D' for default and 'E' to exit.");
                ret = 10;
                break;
                case 1:
                System.out.println("How many items do you have?\nType 'D' for default and 'E' to exit.");
                ret = 5;
                break;
                default:
                System.out.println("Error: Mode not specified for scanner.");
                scanner.close();
                System.exit(1);
            }
            String value = scanner.nextLine();
            if(value.equalsIgnoreCase("D")) { break; }
            if(value.equalsIgnoreCase("E")) { return -1; }
            try {
                int temp = Integer.parseInt(value);
                if(temp <= 0) {
                    throw new Exception("Value must be more than 0.");
                }
                ret = temp;
                break;
            } catch(Exception e) {
                if(e instanceof NumberFormatException) {
                    System.out.println("Error: Input was not a number or an escape.");
                } else { System.out.println(e); }
                continue;
            }
        }
        if(ret == -1) {
            System.out.println("Error: Number wasn't set properly.");
            scanner.close();
            System.exit(2);
        }
        return ret;
    }

    private static void quickSort(Item[] items, int start, int end) {
        if(start < end) {
            int partition = partition(items, start, end);
            quickSort(items, start, partition - 1);
            quickSort(items, partition + 1, end);
        }
    }
    private static int partition(Item[] items, int start, int end) {
        int pivot = end;
        int i = start - 1;
        for(int j = start; j <= end; j++) {
            if(j == end || items[j].getValuePerWeight() >= items[pivot].getValuePerWeight()) {
                if(++i == j) { continue; }
            } else { continue; }
            Item temp = items[j];
            items[j] = items[i];
            items[i] = temp;
        }
        return i;
    }
}