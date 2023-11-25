import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int valor;
    Node left, right;

    public Node(int valor) {
        this.valor = valor;
        this.left = this.right = null;
    }
}

public class GirarArbol {

    // Método para realizar el volteo del árbol
    public static Node Girar(Node nodo) {
        if (nodo == null) {
            return nodo;
        }

        // Llamada recursiva para voltear los subárboles lefts y rights
        Girar(nodo.left);
        Girar(nodo.right);

        // Si el nodo tiene un hijo left, hagamos el intercambio
        if (nodo.left != null) {
            Node temp = nodo.left;
            nodo.left = nodo.right;
            nodo.right = nodo;
            nodo = temp;
            
        }
        return nodo;
    }

    // Método para imprimir el árbol en orden de nivel
    public static void OrdenNivel(Node root) {
        if (root == null) {
            return;
        }
    }
        /* Queue<Node> cola = new LinkedList<>();
        Queue<Integer> lista = new LinkedList<Integer>();
        cola.add(root);

        while (!cola.isEmpty()) {
            Node nodo = cola.poll();
            lista.add(nodo.valor);
            //System.out.print(nodo.valor + " ");

            if (nodo.left != null) {
                cola.add(nodo.left);
            }

            if (nodo.right != null) {
                cola.add(nodo.right);
            }
        }
        lista = reversequeue(lista);
        System.out.println(lista);
    } */

    /* // Método para comprobar el resultado del Girar
    public void Comprobar(Node root) {
        System.out.println("\nÁrbol después del Girar:");
        OrdenNivel(root);
    } */

    public static Queue<Integer> reversequeue(Queue<Integer> queue) {
        Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.add(queue.peek());
            queue.remove();
        }
        while (!stack.isEmpty()) {
            queue.add(stack.peek());
            stack.pop();
        }
        return queue;
    }

    public static void main(String[] args) {
        GirarArbol GirarArbol = new GirarArbol();
        // Crear un árbol de ejemplo
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Árbol antes del Girar:");
        GirarArbol.OrdenNivel(root);

        GirarArbol.Girar(root);
        //GirarArbol.Comprobar(root);
    }
}
