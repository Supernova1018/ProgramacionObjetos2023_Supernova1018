import java.util.Scanner;

public class GradebookTest {

        public static void main(String[] args) {

            Scanner nombreCurso = new Scanner(System.in);

            gbook planillaNotas = new gbook ();

            System.out.println("El nombre del curso (antes de la asignacion) es : /n" + planillaNotas.getcourseName);

            System.out.println("Por favor ingrese el nombre del curso /n: " + nombreCurso);
            String nombreEntradaCurso = nombreCurso.nextLine();
            planillaNotas.setgradeBook(nombreEntradaCurso);

            System.out.println("El nuevo mobre del curso es : " + planillaNotas.getcourseName);
            planillaNotas.displayMessage();




            //planillaNotas.displayMessage("POO ucaldas 2023-1");
        }

    }


