public class LinkedListDeque<T> implements Deque<T>{
    private Node sentinel;
    private int size;
    public LinkedListDeque(){
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    private class Node{
        public T data;
        public Node next;
        public Node prev;
        public T getIndex(int index){
            if(index == 0){
                return data;
            }
            else{
                return next.getIndex(index - 1);
            }
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        Node ptr = sentinel.next;
        while (ptr.next != sentinel) {
            System.out.print(ptr.data+" ");
        }
    }

    @Override
    public boolean isEmpty(){
        return (size == 0);
    }

    /*
     * Add item it to the front of the deque
     */
    @Override
    public void addFirst(T it){
        Node first = new Node();
        first.data = it;
        first.prev = sentinel;
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    /*
     * Add item to the last
     */
    @Override
    public void addLast(T it){
        Node last=new Node();
        last.data = it;
        last.next = sentinel;
        last.prev = sentinel.prev;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public T removeFirst(){
        if(isEmpty())return null;
        T result=sentinel.next.data;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return result;
    }

    @Override
    public T removeLast(){
        if(isEmpty())return null;
        Node Last = sentinel.prev;
        Last.prev.next = sentinel;
        sentinel.prev = Last.prev;
        size -= 1;
        return Last.data;
    }

    @Override
    public T get(int index){
        if(index > size)return null;
        Node ptr = sentinel.next;
        for(int i = 0;i < index; ++i){
            ptr = ptr.next;
        }
        return ptr.data;
    }

    
    public T getRecursive(int index){
        if(index > size)return null;
        return sentinel.next.getIndex(index);
    }
}
