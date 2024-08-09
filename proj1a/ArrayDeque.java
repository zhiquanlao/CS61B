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
    private void resize(int capacity){
        T[] new_item =  (T []) new Object[capacity];
        for(int i = (next_first + 1) % items.length, j = 0 ;i != next_last;i = (i + 1)%items.length, ++j){
            new_item[j] = items[i];
        }
        next_first = capacity - 1;
        next_last = size+1;
        items = new_item;
    }
    public void addFirst(T it){
        if(size == items.length){
            resize( (int) Math.round(items.length*(1 + Usage_factor)));
        }
        items[next_first] = it;
        size += 1;
        next_first = (next_first - 1) % items.length;
        if(next_first < 0)next_first += items.length;
    }
    public void addLast(T it){
        if(size == items.length){
            resize( (int) Math.round(items.length*(1+Usage_factor)));
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
        items[(next_first+1)%items.length] = null;
        size -= 1;
        next_first = (next_first+1)%items.length;
        if(((double)size)/items.length<Usage_factor){
            resize(items.length/2);
        }
        return ans;
    }
    public T removeLast(){
        T ans = get(size-1);
        items[(next_first+size)%items.length] = null;
        size -= 1;
        next_last = (next_first-1)%items.length;
        if(next_last<0)next_last += items.length;
        if(((double)size)/items.length<Usage_factor){
            resize(items.length/2);
        }
        return ans;
    }
    public T get(int index){
        return items[(next_first+index+1)%items.length];
    }
}