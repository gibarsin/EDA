package bag;

public interface Bag<T> {
    /** Agrega un elemento a la bolsa. */
    void add(T value);
    /** Obtiene la cantidad de veces que un elemento aparece en la bolsa. */
    int occurencesOf(T value);
    /** Elimina un elemento de la bolsa. */
    void remove(T value);
}
