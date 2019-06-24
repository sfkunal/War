import java.util.LinkedList;

public class Stack<E> extends LinkedList<E> {
    public Stack() {}
    
    public void put(E o) {
        addFirst(o);
    }
    
    public E remove() {
        if (!this.isEmpty()) {
            return removeFirst();
        } else {
            System.err.println("You can\'t do that!");
            return null;
        } 
    }
    
    public E peek() {
        return getFirst();
    }
}
