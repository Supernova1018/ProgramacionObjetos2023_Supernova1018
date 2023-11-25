import java.util.Vector;

class Node {
    int data;
    Node left, right;

    public Node(int item) {
        data = item;
        left = null;
        right = null;
    }
}

class ArbolBinario {
    Vector<Node> VectorArboles = new Vector<>();

    
    void preOrden(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preOrden(root.left);
        preOrden(root.right);
    }

    
    void obtenerArboles(int[] inorden, int inicio, int fin) {
        if (inicio > fin) {
            VectorArboles.add(null);
            return;
        }

        
        for (int i = inicio; i <= fin; i++) {
            
            obtenerArboles(inorden, inicio, i - 1);
            obtenerArboles(inorden, i + 1, fin);

            Node nodo = new Node(inorden[i]);
            VectorArboles.add(nodo);

            for (int j = 0; j < VectorArboles.size(); j++) {
                Node izquierda = VectorArboles.get(j);
                for (int k = 0; k < VectorArboles.size(); k++) {
                    Node derecha = VectorArboles.get(k);
                    if (j != k) {
                        nodo.left = izquierda;
                        nodo.right = derecha;
                    }
                }
            }
        }
    }
}

public class vector {
    public static void main(String[] args) {
        ArbolBinario arbolBinario = new ArbolBinario();
        int[] inorden = {4, 5, 7};
        int n = inorden.length;

        arbolBinario.obtenerArboles(inorden, 0, n - 1);

        System.out.println("Preórden de los árboles posibles:");

        for (int i = 0; i < arbolBinario.VectorArboles.size(); i++) {
            arbolBinario.preOrden(arbolBinario.VectorArboles.get(i));
            System.out.println();
        }
    }
}
