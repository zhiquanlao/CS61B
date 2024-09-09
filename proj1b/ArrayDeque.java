public class ArrayDeque<T> implements Deque<T> {
    private T [] items;
    private int size;
    private int NextFirst;
    private int NextLast;
    private final double UsageFactor = 0.25;
    public ArrayDeque() {
        items  =  (T[]) new Object[8];
        size = 0;
        NextFirst = 0;
        NextLast = 0;
    }
    @Override
    public int size() {
        return size;
    }

    /*
     * Convert the index that human think of to the acutual index in the array
     */
    private int index_to_array_index(int index) {
        return (NextFirst + 1 + index) % items.length;
    }

    /*
     * Helper function to resize the array
     */
    private void resize(int capacity) {
        if (capacity <= 8) {
            return;
        }
        T[] NewItem =  (T []) new Object[capacity];
        if (isEmpty()) {
            items = NewItem;
            NextFirst = NextLast = 0;
            return;
        }
        for (int i = 0; i < size; ++i) {
            NewItem[i] = items[index_to_array_index(i)];
        }

        

        NextFirst = capacity - 1;
        NextLast = size;
        
        items = new_item;

        // System.out.println("resize: NextFirst="+NextFirst+" NextLast="+NextLast);
        // for(int i=0;i<items.length;++i){
        //     System.out.print(items[i]+" ");
        // }
        // System.out.println();
    }

    @Override
    public void addFirst(T it) {
        if (isEmpty()) {
            NextLast = (NextLast + 1) % items.length;
        }
        if (size == items.length) {
            resize((int) Math.ceil(items.length * (1 + UsageFactor)));
        }
        items[NextFirst] = it;
        size += 1;
        NextFirst = (NextFirst - 1) % items.length;
        if (NextFirst < 0) {
            NextFirst += items.length;
        }
    }

    @Override
    public void addLast(T it) {
        if (isEmpty()) {
            NextFirst =  (NextFirst - 1) % items.length;
            if (NextFirst < 0) {
                NextFirst += items.length;
            }
        }
        if (size == items.length) {
            resize((int) Math.ceil(items.length * (1 + UsageFactor)));
        }
        items[NextLast] = it;
        size += 1;
        NextLast = (NextLast + 1) % items.length;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; ++i) {
            System.out.print(items[index_to_array_index(i)] + " ");
        }
    }

    /*
     * Remove the first element
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ans = get(0);
        items[index_to_array_index(0)] = null;
        size -= 1;
        NextFirst = index_to_array_index(0);
        if (((double) size) / items.length < UsageFactor) {
            resize(items.length / 2);
        }
        if (isEmpty()) {
            NextLast = NextFirst;
        }
        return ans;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ans = get(size - 1);
        items[index_to_array_index(size - 1)] = null;
        size -= 1;
        NextLast = (NextLast - 1) % items.length;
        if (NextLast < 0) {
            NextLast += items.length;
        }
        if (((double) size) / items.length < UsageFactor) {
            resize(items.length / 2);
        }
        if (isEmpty()) {
            NextFirst = NextLast;
        }
        return ans;
    }

    @Override
    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[index_to_array_index(index)];
    }
}
