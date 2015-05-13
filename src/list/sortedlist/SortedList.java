package list.sortedlist;

/**
 * Created by Gonzalo on 24/04/2015.
 */
public interface SortedList<T> {
    void add(T elem);

    void undo();

    void print();
}
