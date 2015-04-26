package tp_3.ej5;

/*
**Implementar una cola que internamente almacene los elementos en stacks. Las operaciones
**enqueue y dequeue deben definirse en función de las operaciones push y pop de un stack.
**Determinar la cantidad mínima de stacks requeridos.
*/

import java.util.Deque;
import java.util.LinkedList;

public class QueueStack<T> implements Queue<T> {
    private Deque<T> stack1;
    private Deque<T> stack2;

    public QueueStack() {
        stack1 = new LinkedList<T>();
        stack2 = new LinkedList<T>();
    }

    @Override
    public void queue(T elem) {
        while(!stack2.isEmpty())
            stack1.push(stack2.pop());
        stack1.push(elem);
    }

    @Override
    public T dequeue() {
        while(!stack1.isEmpty())
            stack2.push(stack1.pop());
        return stack2.pop();
    }

    @Override
    public void print() {

    }

    @Override
    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
