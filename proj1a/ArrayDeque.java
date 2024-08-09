public class ArrayDeque<T>{
    private T [] items;
    private int size;
    private int next_first;
    private int next_last;
    private final double Usage_factor = 0.25;
    public ArrayDeque(){
        items  =  (T[]) new Object[8];
        size = 0;
        next_first = 7;
        next_last = 1;
    }

    public int size(){
        return size;
    }

    private int index_to_array_index(int index){
        return (next_first + 1 + index) % items.length;
    }

    private void resize(int capacity){
        T[] new_item =  (T []) new Object[capacity];
        for(int i = 0; i < size; ++i) {
            new_item[i] = items[index_to_array_index(i)];
        }
        next_first = capacity - 1;
        next_last = size + 1;
        items = new_item;
    }

    public void addFirst(T it){
        if(size == items.length) {
            resize( (int) Math.ceil(items.length*(1 + Usage_factor)));
        }
        items[next_first] = it;
        size += 1;
        next_first = (next_first - 1) % items.length;
        if(next_first < 0)next_first += items.length;
    }
    public void addLast(T it){
        if(size == items.length){
            resize( (int) Math.ceil(items.length*(1+Usage_factor)));
        }
        items[next_last] = it;
        size += 1;
        next_last = (next_first+1)%items.length;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public void printDeque(){
        for(int i = (next_first + 1) % items.length; i != next_last;i = (i + 1)%items.length){
            System.out.print(items[i]+" ");
        }
    }

    public T removeFirst(){
        T ans = get(0);
        items[index_to_array_index(0)] = null;
        size -= 1;
        next_first = index_to_array_index(1);
        if(((double)size)/items.length<Usage_factor){
            resize(items.length/2+1);
        }
        return ans;
    }
    public T removeLast(){
        T ans = get(size - 1);
        items[index_to_array_index(size - 1)] = null;
        size -= 1;
        next_last = (next_last-1) % items.length;
        if(next_last < 0)next_last += items.length;
        if(((double)size)/items.length<Usage_factor){
            resize(items.length/2+1);
        }
        return ans;
    }
    public T get(int index){
        if(index > size)return null;
        return items[index_to_array_index(index)];
    }
}
