package structures.trees;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import structures.node.Node;

public class IntTree {
    private Node<Integer> root;
    private int peso;

    public IntTree() {
        this.root = null;
        this.peso = 0;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Node<Integer> getRoot() {
        return root;
    }

    public void setRoot(Node<Integer> node) {
        root = node;
    }

    public void setRoot(Integer value) {
        root = new Node<Integer>(value);
    }

    public void insert(Integer value) { 
        Node<Integer> node = new Node<Integer>(value);
        root = insertRecursivo(root, node);
        peso++;
    }

    public int getPeso() {
        return peso;
    }

    public void insert(Node<Integer> value) { 
        root = insertRecursivo(root, value);
        peso++;
    }

    private Node<Integer> insertRecursivo(Node<Integer> actual, Node<Integer> nodeInsertar) {
        if (actual == null) {
            return nodeInsertar;
        }

        if (actual.getValue() > nodeInsertar.getValue()) {
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

    private void preOrderRecursivo(Node<Integer> actual) {
        if (actual == null) return;
        System.out.print(actual.getValue() + " ");
        preOrderRecursivo(actual.getLeft());
        preOrderRecursivo(actual.getRight());
    }

    public void posOrder() {
        posOrderRecursivo(root);
        System.out.println();
    }

    private void posOrderRecursivo(Node<Integer> actual) {
        if (actual == null) return;
        posOrderRecursivo(actual.getLeft());
        posOrderRecursivo(actual.getRight());
        System.out.print(actual.getValue() + " ");
    }

    public void inOrder() {
        inOrderRecursivo(root);
        System.out.println();
    }

    private void inOrderRecursivo(Node<Integer> actual) {
        if (actual == null) return;
        inOrderRecursivo(actual.getLeft());
        System.out.print(actual.getValue() + " ");
        inOrderRecursivo(actual.getRight());
    }

    public void niveles() {
        if (root == null) return;
        Queue<Node<Integer>> cola = new LinkedList<>();
        cola.add(root);

        while (!cola.isEmpty()) {
            Node<Integer> actual = cola.poll();
            System.out.print(actual.getValue() + " ");

            if (actual.getLeft() != null) cola.add(actual.getLeft());
            if (actual.getRight() != null) cola.add(actual.getRight());
        }
        System.out.println();
    }
    
    public int height() {
        return heightRecursivo(root);
    }

    private int heightRecursivo(Node<Integer> actual) {
        if (actual == null) return 0;
        
        int leftHeight = heightRecursivo(actual.getLeft());
        int rightHeight = heightRecursivo(actual.getRight());
        
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int peso() {
        return pesoRecursivo(root);
    }

    private int pesoRecursivo(Node<Integer> actual) {
        if (actual == null) return 0;
        
        int leftPeso = pesoRecursivo(actual.getLeft());
        int rightPeso = pesoRecursivo(actual.getRight());
        
        return leftPeso + rightPeso + 1;
    }

    public static void main(String[] args) {
        runIntComparativaPesos();
    }
 
    public static void runIntComparativaPesos() {
        IntTree tree = new IntTree();
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

        System.out.println("Cantidad de nodos insertados: " + TOTAL_NODOS);
        System.out.println("Peso usando variable: " + pesoVariable);
        System.out.println("Peso usando recursion: " + pesoRecursivo);
        System.out.println();
        System.out.println("Tiempo getPeso(): " + tiempoPesoVariableMs + " ms");
        System.out.println("Tiempo pesoRecursivo(): " + tiempoPesoRecursivoMs + " ms");
    }
}