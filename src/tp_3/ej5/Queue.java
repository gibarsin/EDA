package tp_3.ej5;

public interface Queue<T> {
    public void queue(T elem);

    public T dequeue();

    public void print();

    public boolean isEmpty();
}
