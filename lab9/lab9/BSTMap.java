package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }
        else if (key.compareTo(p.key) == 0) {
            return p.value;
        }
        else{
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            p = new Node(key, value);
            return p;
        }
        if (key.compareTo(p.key) < 0) {
            if (p.left == null) {
                p.left = new Node(key, value);
                return p;
            }
            return putHelper(key, value, p.left);
        }
        else if (key.compareTo(p.key) == 0) {
            throw new UnsupportedOperationException();
        }
        else {
            if (p.right == null) {
                p.right = new Node(key, value);
                return p;
            }
            return putHelper(key, value, p.right);
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        putHelper(key, value, root);
        size += 1;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private V removeHelper(K key, Node p, Node prev) {
        if (key.compareTo(p.key) < 0) {
            return removeHelper(key, p.left, prev);
        }
        else if (key.compareTo(p.key) > 0) {
            return removeHelper(key, p.right, prev);
        }
        else {
            V ans = p.value;
            Node new_Node;
            Node prev_node = p;
            if (p.left != null) {
                new_Node = p.left;
                while (new_Node.right != null) {
                    prev_node = new_Node;
                    new_Node = new_Node.right;
                }
                p.value = new_Node.value;
                p.key = new_Node.key;
                prev_node.right = new_Node.left;
                new_Node = null;
            }
            else if (p.right != null) {
                if (prev == null) {
                    p = p.right;
                    p = null;
                } else if (prev.key.compareTo(key) < 0) {
                    prev.right = p.right;
                    p = null;
                } else {
                    prev.left = p.right;
                    p = null;
                }
            }
            else {
                p = null;
            }
            return ans;
        }
    }
    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        return removeHelper(key, root, null);
    }

    private V removeHelper(K key, V value, Node p, Node prev) {
        if (key.compareTo(p.key) < 0) {
            return removeHelper(key, p.left, prev);
        }
        else if (key.compareTo(p.key) > 0) {
            return removeHelper(key, p.right, prev);
        }
        else {
            V ans = p.value;
            if (ans != value) {
                return null;
            }
            Node new_Node;
            Node prev_node = p;
            if (p.left != null) {
                new_Node = p.left;
                while (new_Node.right != null) {
                    prev_node = new_Node;
                    new_Node = new_Node.right;
                }
                p.value = new_Node.value;
                p.key = new_Node.key;
                prev_node.right = new_Node.left;
                new_Node = null;
            }
            else if (p.right != null) {
                if (prev == null) {
                    p = p.right;
                    p = null;
                } else if (prev.key.compareTo(key) < 0) {
                    prev.right = p.right;
                    p = null;
                } else {
                    prev.left = p.right;
                    p = null;
                }
            }
            else {
                p = null;
            }
            return ans;
        }
    }
    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        return removeHelper(key, value, root, null);
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
