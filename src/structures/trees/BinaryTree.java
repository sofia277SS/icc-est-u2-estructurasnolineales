package structures.trees;
import java.util.Random;

import structures.node.Node;

public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;
    private int peso;
    
    public BinaryTree() {
        this.root = null;
        this.peso = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node<T> getRoot() {
        return root;
    }

    public void setRoot(Node<T> node) {
        root = node;
    }

    public void setRoot(T value) {
        Node<T> node = new Node<T>(value);
        root = node;
    }

    public void insert(T value) { 
        Node<T> node = new Node<T>(value);
        root = insertRecursivo(root, node);
        peso++; 
    }

    public int getPeso(){
        return peso;
    }

    public void insert(Node<T> node) { 
        root = insertRecursivo(root, node);
        peso++; 
    }

    private Node<T> insertRecursivo(Node<T> actual, Node<T> nodeInsertar) {
        if (actual == null) {
            return nodeInsertar;
        }

        if (actual.getValue().compareTo(nodeInsertar.getValue()) > 0) {
            actual.setLeft(insertRecursivo(actual.getLeft(), nodeInsertar));
        } else {
            actual.setRight(insertRecursivo(actual.getRight(), nodeInsertar));
        }

        return actual;
    }

    public void preOrder() {
        preOrderRecursivo(root);
        System.out.println();
    }

    private void preOrderRecursivo(Node<T> actual) {
        if (actual == null) return;
        System.out.print(actual.getValue() + " "); 
        preOrderRecursivo(actual.getLeft());
        preOrderRecursivo(actual.getRight());
    }

    public void posOrder() {
        posOrderRecursivo(root);
        System.out.println();
    }

    private void posOrderRecursivo(Node<T> actual) {
        if (actual == null) return;
        posOrderRecursivo(actual.getLeft());
        posOrderRecursivo(actual.getRight());
        System.out.print(actual.getValue() + " ");
    }
    
    public void inOrder() {
        inOrderRecursivo(root);
        System.out.println();
    }

    private void inOrderRecursivo(Node<T> actual) {
        if (actual == null) return;
        inOrderRecursivo(actual.getLeft());
        System.out.print(actual.getValue() + " ");
        inOrderRecursivo(actual.getRight());
    }

    public int height(){
        return heightRecursivo(root);
    }

    private int heightRecursivo(Node<T> actual) {
        if(actual == null)
            return 0;
        int leftHeight = heightRecursivo(actual.getLeft());
        int rightHeight = heightRecursivo(actual.getRight());
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int peso(){
        return pesoRecursivo(root);
    }
    
    private int pesoRecursivo(Node<T> actual) {
        if(actual == null)
            return 0;
        int leftNodes = pesoRecursivo(actual.getLeft());
        int rightNodes = pesoRecursivo(actual.getRight());
        return leftNodes + rightNodes + 1;
    }
    
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>(); 
        Random random = new Random();

        final int TOTAL_NODOS = 50_000;
        final int MIN = 1;
        final int MAX = 50_000;

        for (int i = 0; i < TOTAL_NODOS; i++) {
            int value = random.nextInt(MAX - MIN + 1) + MIN;
            tree.insert(value);
        }

        long inicioPesoVariable = System.nanoTime();
        int pesoVariable = tree.getPeso();
        long finPesoVariable = System.nanoTime();
        double tiempoPesoVariableMs = (finPesoVariable - inicioPesoVariable) / 1_000_000.0;

        long inicioPesoRecursivo = System.nanoTime();
        int pesoRecursivo = tree.peso();
        long finPesoRecursivo = System.nanoTime();
        double tiempoPesoRecursivoMs = (finPesoRecursivo - inicioPesoRecursivo) / 1_000_000.0;

        System.out.println("Cantidad de operaciones de inserción: " + TOTAL_NODOS);
        System.out.println("Peso usando variable: " + pesoVariable);
        System.out.println("Peso usando recursion: " + pesoRecursivo);

        System.out.println();
        System.out.println("Tiempo getPeso(): " + tiempoPesoVariableMs + " ms");
        System.out.println("Tiempo pesoRecursivo(): " + tiempoPesoRecursivoMs + " ms");
    }
}