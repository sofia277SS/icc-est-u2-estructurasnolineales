package structures.trees;

import java.util.LinkedList;
import java.util.Queue;
import structures.node.Node;

public class Ejercicio4 {

    // Método principal para iniciar la búsqueda BFS en BinariTree
    public static <T extends Comparable<T>> boolean bfs(BinaryTree<T> tree, T target) {
        if (tree.isEmpty()) {
            return false;
        }

        Queue<Node<T>> cola = new LinkedList<>();
        cola.add(tree.getRoot());

        while (!cola.isEmpty()) {
            Node<T> actual = cola.poll();
            System.out.print(actual.getValue() + " -> ");

            if (actual.getValue().compareTo(target) == 0) {
                return true;
            }

            if (actual.getLeft() != null) cola.add(actual.getLeft());
            if (actual.getRight() != null) cola.add(actual.getRight());
        }
        return false;
    }

    // BFS adaptado para tu IntTree
    public static boolean bfsInt(IntTree tree, Integer target) {
        if (tree.isEmpty()) {
            return false;
        }

        Queue<Node<Integer>> cola = new LinkedList<>();
        cola.add(tree.getRoot());

        while (!cola.isEmpty()) {
            Node<Integer> actual = cola.poll();
            System.out.print(actual.getValue() + " -> ");

            if (actual.getValue().equals(target)) {
                return true;
            }

            if (actual.getLeft() != null) cola.add(actual.getLeft());
            if (actual.getRight() != null) cola.add(actual.getRight());
        }
        return false;
    }
}