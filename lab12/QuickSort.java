import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
            int size = unsorted.size();
            for (int i = 0; i < size; ++i) {
                if (unsorted.peek().compareTo(pivot) < 0) {
                    less.enqueue(unsorted.dequeue());
                }   
                else if (unsorted.peek().compareTo(pivot) > 0) {
                    greater.enqueue(unsorted.dequeue());
                }
                else {
                    equal.enqueue(unsorted.dequeue());
                }
            }
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
            if (items.size() == 1 || items.size() == 0) {
                return items;
            }
            Item pivot = getRandomItem(items);
            Queue<Item> less = new Queue();
            Queue<Item> equal = new Queue();
            Queue<Item> great= new Queue();
            partition(items, pivot, less, equal, great);
            less = quickSort(less);
            great = quickSort(great);
            items = catenate(less, equal);
            items = catenate(items, great);
            return items;
    }
    public static void main(String[] args) {
        Queue<String> students = new Queue<String>();
        students.enqueue("a");
        students.enqueue("k");
        students.enqueue("d");
        students.enqueue("f");
        students.enqueue("g");
        students.enqueue("z");
        students.enqueue("g");
        for (String s:students) {
            System.out.print(s+", ");
        }
        System.out.println();
                Queue<String>sortStudents = quickSort(students);
        for (String s: sortStudents) {
            System.out.print(s+", ");
        }
    }
}
