package EstructurasDeDatos.Ejercicios;

import java.util.Random;

public class main {
    public static void main(String[] args) {
        Random random = new Random();
        Lista Lista = new Lista();
        for (int i = 0; i <= 5; i++) {
            Lista.agregar(random.nextInt(10));
        }
        Lista.ver();
        System.out.println("\nSumatorio de los nodos: "+Lista.sumar(0, Lista.cabezon));
    }
}