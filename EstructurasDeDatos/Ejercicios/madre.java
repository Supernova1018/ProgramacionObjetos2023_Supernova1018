import java.util.LinkedList;
import java.util.Queue;

class Nodo {
    int valor;
    Nodo izquierda, derecha;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierda = this.derecha = null;
    }
}

class ArbolBinario {
    Nodo raiz;

    public ArbolBinario(int raizValor) {
        raiz = new Nodo(raizValor);
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return new Nodo(valor);
        }

        if (valor < nodo.valor) {
            nodo.izquierda = insertarRec(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = insertarRec(nodo.derecha, valor);
        }

        return nodo;
    }

    public void inorder() {
        inorderRec(raiz);
        System.out.println();
    }

    private void inorderRec(Nodo nodo) {
        if (nodo != null) {
            inorderRec(nodo.izquierda);
            System.out.print(nodo.valor + " ");
            inorderRec(nodo.derecha);
        }
    }

    public void preorder() {
        preorderRec(raiz);
        System.out.println();
    }

    private void preorderRec(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.valor + " ");
            preorderRec(nodo.izquierda);
            preorderRec(nodo.derecha);
        }
    }

    public void postorder() {
        postorderRec(raiz);
        System.out.println();
    }

    private void postorderRec(Nodo nodo) {
        if (nodo != null) {
            postorderRec(nodo.izquierda);
            postorderRec(nodo.derecha);
            System.out.print(nodo.valor + " ");
        }
    }

    public void nivelorden() {
        if (raiz == null) {
            return;
        }

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();
            System.out.print(actual.valor + " ");

            if (actual.izquierda != null) {
                cola.add(actual.izquierda);
            }

            if (actual.derecha != null) {
                cola.add(actual.derecha);
            }
        }

        System.out.println();
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return nodo;
        }

        if (valor < nodo.valor) {
            nodo.izquierda = eliminarRec(nodo.izquierda, valor);
        } else if (valor > nodo.valor) {
            nodo.derecha = eliminarRec(nodo.derecha, valor);
        } else {
            if (nodo.izquierda == null) {
                return nodo.derecha;
            } else if (nodo.derecha == null) {
                return nodo.izquierda;
            }

            nodo.valor = encontrarMinimoValor(nodo.derecha);
            nodo.derecha = eliminarRec(nodo.derecha, nodo.valor);
        }

        return nodo;
    }

    private int encontrarMinimoValor(Nodo nodo) {
        int minimoValor = nodo.valor;
        while (nodo.izquierda != null) {
            minimoValor = nodo.izquierda.valor;
            nodo = nodo.izquierda;
        }
        return minimoValor;
    }
}

public class madre   {
    public static void main(String[] args) {
        ArbolBinario arbol = new ArbolBinario(10);
        arbol.insertar(5);
        arbol.insertar(15);
        arbol.insertar(3);
        arbol.insertar(7);
        arbol.insertar(12);
        arbol.insertar(18);

        System.out.print("Inorden: ");
        arbol.inorder();

        System.out.print("Preorden: ");
        arbol.preorder();

        System.out.print("Postorden: ");
        arbol.postorder();

        System.out.print("Nivelorden: ");
        arbol.nivelorden();

        arbol.eliminar(12);

        System.out.print("Después de eliminar 7:\nInorden: ");
        arbol.inorder();
    }
}

class Node {
    int key;
    Node left, right;

    public Node(int item)
    {
        key = item;
        left = right = null;
    }
}
class Arbol{
    public Node Crear_arbol()
    {
      Node root = null;
      root = new Node(53);
      root.left = new Node(20);
      root.right = new Node(84);
      root.left.left = new Node(10);
      root.left.right = new Node(28);
      root.left.right.right = new Node(37);
      root.left.right.right.right = new Node(42);
      root.right.right = new Node(95);
      root.right.left = new Node(72);
      root.right.left.left = new Node(55);
      root.right.left.right = new Node(73);
      root.left.left.right.right = new Node(10);     
      return root;
    }
}