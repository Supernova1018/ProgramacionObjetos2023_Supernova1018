import java.util.LinkedList;
import java.util.Queue;

public class BusquedaNodo {
    static class Node {
        int key;
        Node left, right;

        // constructor
        Node(int key) {
            this.key = key;
            left = null;
            right = null;
        }
    }

    static Node root;
    static Node temp = root;

    static void inorder(Node temp) {
        if (temp == null)
            return;

        inorder(temp.left);
        System.out.print(temp.key + " ");
        inorder(temp.right);
    }

    static Queue<Integer> recorrido = new LinkedList<Integer>();
    static void reccoridoIn(Node temp) {
        if (temp == null)
            return;

        reccoridoIn(temp.left);
        recorrido.add(temp.key);
        reccoridoIn(temp.right);
    }

    public static Node insertar(Node temp, int key) {

        if (temp == null) {
            root = new Node(key);
            return root;
        }
        Queue<Node> juanito = new LinkedList<Node>();
        juanito.add(temp);

        while (!juanito.isEmpty()) {
            temp = juanito.peek();
            juanito.remove();
            if (key < temp.key) {
                if (temp.left != null)
                    juanito.add(temp.left);
                else {
                    temp.left = new Node(key);
                    break;
                }
            } else if (key > temp.key) {
                if (temp.right != null)
                    juanito.add(temp.right);
                else {
                    temp.right = new Node(key);
                    break;
                }
            } else
                return temp;
        }
        return root;
    }

    /*
     * public static int BusquedaMaximoNodo() {
     * if (root == null)
     * return -1;
     * int max = 0;
     * Stack<Node> stack = new Stack<>();
     * stack.push(root);
     * while (!stack.empty()) {
     * Node n = stack.pop();
     * if (n != null && !stack.contains(n)) {
     * if (max < n.key)
     * max = n.key;
     * stack.push(n.right);
     * stack.push(n.left);
     * }
     * }
     * return max;
     * }
     */

    public static int BusquedaMaximoNodo(Node root, int key) {

        int resultado = -1;
        if (root == null) {
            return resultado;
        }

        reccoridoIn(root);

        while (!recorrido.isEmpty()){
        System.out.println("lista: " + recorrido.poll());}
        return resultado;
    }

    public static void main(String[] args) {
        int N = 4;
        Node root = null;
        root = BusquedaNodo.insertar(root, 5);
        BusquedaNodo.insertar(root, 2);
        BusquedaNodo.insertar(root, 1);
        BusquedaNodo.insertar(root, 3);
        BusquedaNodo.insertar(root, 12);
        BusquedaNodo.insertar(root, 9);
        BusquedaNodo.insertar(root, 21);
        BusquedaNodo.insertar(root, 19);
        BusquedaNodo.insertar(root, 25);

        System.out.println("arbol: ");
        BusquedaNodo.inorder(root);
        /* System.out.println("Inorder: ");
        BusquedaNodo.reccoridoIn(root);
        System.out.println(recorrido); */
        System.out.println("\n");

        BusquedaNodo.BusquedaMaximoNodo(root, N);

    }
}
