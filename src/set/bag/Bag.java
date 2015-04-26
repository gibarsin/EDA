package set.bag;

/**
 * Created by Gonzalo on 05/04/2015.
 */
public interface Bag<T> {
    /** Agrega un elemento a la colecci�n. */
    void add(T elem);

    /** Elimina un elemento de la colecci�n. */
    void remove(T elem);

    /** Imprime los elementos sin repetir, indicando la cantidad de
     * veces que aparece cada uno, y ordenado descendentemente por dicha cantidad. */
    void print();
}
