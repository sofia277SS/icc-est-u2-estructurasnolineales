package structures.node;


// NODO para arboles binarios
// DOS REFERENCIAS: izquierda y derecha
public class Node<T> {
    private T value;
    private Node<T> left;
    private Node<T> right;

    // Constructor ES LO QUE CREA EL NODO
    // NECESTIO SOLO EL VALOR, LAS REFERENCIAS SE INICIALIZAN EN NULL
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node [" + value + "]";
    }
}