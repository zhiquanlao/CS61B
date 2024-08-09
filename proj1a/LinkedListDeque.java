public class LinkedListDeque<T>{
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
    public int size(){
        return size;
    }
    public void printDeque(){
        Node ptr = sentinel.next;
        while (ptr.next != sentinel) {
            System.out.print(ptr.data+" ");
        }
    }
    public boolean isEmpty(){
        return (size == 0);
    }
    public void addFirst(T it){
        Node first = new Node();
        first.data = it;
        first.prev = sentinel;
        first.next = sentinel.next;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }
    public void addLast(T it){
        Node last=new Node();
        last.data = it;
        last.next = sentinel;
        last.prev = sentinel.prev;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }
    public T removeFirst(){
        if(isEmpty())return null;
        T result=sentinel.next.data;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return result;
    }
    public T removeLast(){
        if(isEmpty())return null;
        Node Last = sentinel.prev;
        Last.prev.next = sentinel;
        sentinel.prev = Last.prev;
        size -= 1;
        return Last.data;
    }
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
