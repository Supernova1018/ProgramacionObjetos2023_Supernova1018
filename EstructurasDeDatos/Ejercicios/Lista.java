package EstructurasDeDatos.Ejercicios;

public class Lista {
    Nodo cabezon;

    class Nodo {
        int valor;
        Nodo enlace;

        public Nodo(int n) {
            this.valor = n;
            this.enlace = null;
        }
    }

    public void agregar(int add) {
        Nodo new_nodo = new Nodo(add);
        new_nodo.enlace = cabezon;
        cabezon = new_nodo;
    }

    public void ver() {
        Nodo new_nodo = cabezon;
        while (new_nodo.enlace != null) {
            System.out.print(new_nodo.valor+ ", ");
            new_nodo = new_nodo.enlace;
        }
    }

public int sumar(int sum, Nodo nodo){
    if (nodo.enlace == null) {
    return sum;
    }
    else {
        return sumar(sum + nodo.valor, nodo.enlace);
    }
}
}
