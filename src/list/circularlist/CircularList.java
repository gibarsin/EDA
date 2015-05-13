package list.circularlist;

/**
 * Created by Gonzalo on 05/04/2015.
 */
public interface CircularList<T> {
    public void addLast(T elem);

    public T getNext();

    public void reset();

    public CircularList<T> split();

    public void print();
}
