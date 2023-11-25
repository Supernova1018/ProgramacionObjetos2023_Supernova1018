class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BusquedaNodo {
    public static Node insertar(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insertar(root.left, key);
        } else if (key > root.key) {
            root.right = insertar(root.right, key);
        }

        return root;
    }

    public static int BusquedaMaximoNodo(Node root, int N) {
        int result = -1;
        while (root != null) {
            if (root.key <= N) {
                result = root.key;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return result;
    }
}

public class BusquedaNodo1 {
    public static void main(String[] args) {
        int N = 4;
        Node root = null;
        root = BusquedaNodo.insertar(root, 25);
        BusquedaNodo.insertar(root, 2);
        BusquedaNodo.insertar(root, 1);
        BusquedaNodo.insertar(root, 3);
        BusquedaNodo.insertar(root, 12);
        BusquedaNodo.insertar(root, 9);
        BusquedaNodo.insertar(root, 21);
        BusquedaNodo.insertar(root, 19);
        BusquedaNodo.insertar(root, 25);
        System.out.println(BusquedaNodo.BusquedaMaximoNodo(root, N)); // Debe imprimir 3
}
}