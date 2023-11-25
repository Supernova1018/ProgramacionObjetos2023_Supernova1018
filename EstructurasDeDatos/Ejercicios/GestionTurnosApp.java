import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

public class GestionTurnosApp extends Application {
    private PriorityQueue<Cita> colaCitas;
    private Stack<Integer> pilaPines;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        colaCitas = new PriorityQueue<>(Comparator.reverseOrder());
        pilaPines = new Stack<>();

        // Inicializar la pila de pines con valores del 1 al 100 (puedes ajustar según
        // necesidades)
        for (int i = 100; i >= 1; i--) {
            pilaPines.push(i);
        }

        primaryStage.setTitle("Gestión de Turnos y Citas");

        Button btnAgregarCita = new Button("Agregar Cita");
        Button btnAtenderCita = new Button("Atender Cita");
        Button btnVerCitas = new Button("Ver Citas");

        btnAgregarCita.setOnAction(e -> agregarCita());
        btnAtenderCita.setOnAction(e -> atenderCita());
        btnVerCitas.setOnAction(e -> verCitas());

        HBox buttonBox = new HBox(10, btnAgregarCita, btnAtenderCita, btnVerCitas);
        buttonBox.setPadding(new Insets(20));

        VBox root = new VBox(buttonBox);
        root.setSpacing(20);
        root.setPadding(new Insets(20));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void agregarCita() {
        // Formulario para ingresar datos
        TextField nombreField = new TextField();
        TextField edadField = new TextField();
        TextField pesoField = new TextField();
        TextField alturaField = new TextField();
        CheckBox antecedentesCheckBox = new CheckBox("Antecedentes Médicos");

        VBox formBox = new VBox(10, new Label("Nombre:"), nombreField, new Label("Edad:"), edadField,
                new Label("Peso:"), pesoField, new Label("Altura:"), alturaField, antecedentesCheckBox);

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Agregar Cita");
        dialog.setHeaderText("Ingrese los datos de la cita:");
        dialog.getDialogPane().setContent(formBox);

        ButtonType addButton = new ButtonType("Agregar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        dialog.showAndWait().ifPresent(dialogButton -> {
            if (dialogButton == addButton) {
                // Obtener los datos del formulario
                String nombre = nombreField.getText();
                int edad = Integer.parseInt(edadField.getText());
                double peso = Double.parseDouble(pesoField.getText());
                double altura = Double.parseDouble(alturaField.getText());
                boolean antecedentes = antecedentesCheckBox.isSelected();

                // Calcular prioridad (puede ser personalizado según las reglas específicas)
                int prioridad = calcularPrioridad(edad, peso, altura, antecedentes);

                // Obtener el PIN de la pila
                if (!pilaPines.isEmpty()) {
                    int pin = pilaPines.pop();

                    // Agregar la cita a la cola
                    Cita nuevaCita = new Cita(nombre, prioridad, pin, antecedentes);
                    colaCitas.add(nuevaCita);

                    showAlert("Cita agregada correctamente\nPIN de Cita: " + pin +
                            "\nGrado de Prioridad: " + prioridad);
                } else {
                    showAlert("No hay pines disponibles. No se puede agregar la cita.");
                }
            }
        });
    }

    private void atenderCita() {
        if (!colaCitas.isEmpty()) {
            Cita citaActual = colaCitas.poll();
            showAlert("Atendiendo a " + citaActual.getNombre() +
                    "\nPIN de Cita: " + citaActual.getPin() +
                    "\nGrado de Prioridad: " + citaActual.getPrioridad() +
                    "\nAntecedentes Médicos: " + (citaActual.tieneAntecedentes() ? "Sí" : "No"));
            // Devolver el PIN a la pila para su reutilización
            pilaPines.push(citaActual.getPin());
        } else {
            showAlert("No hay citas pendientes");
        }
    }

    private void verCitas() {
        if (!colaCitas.isEmpty()) {
            StringBuilder citasTexto = new StringBuilder("Citas en orden de prioridad:\n");
            for (Cita cita : colaCitas) {
                citasTexto.append("Nombre: ").append(cita.getNombre()).append(", PIN: ").append(cita.getPin())
                        .append(", Prioridad: ").append(cita.getPrioridad()).append(", Antecedentes: ")
                        .append(cita.tieneAntecedentes() ? "Sí" : "No").append("\n");
            }
            showAlert(citasTexto.toString());
        } else {
            showAlert("No hay citas pendientes");
        }
    }

    private int calcularPrioridad(int edad, double peso, double altura, boolean antecedentes) {
        int prioridad = 0;

        // Regla 1: Personas de 50 años o más tienen mayor prioridad
        if (edad >= 50) {
            prioridad += 3;
        }

        // Regla 2: Personas de 14 años o menos tienen segundo grado de prioridad
        if (edad <= 14) {
            prioridad += 2;
        }

        // Regla 3: Personas de mediana edad tienen tercer grado de prioridad
        if (edad > 14 && edad < 50) {
            prioridad += 1;
        }

        // Regla 4: Personas que miden menos de 1.61 m y pesan más de 85 kg tienen mayor
        // prioridad
        if (altura < 1.61 && peso > 79) {
            prioridad += 2;
        }

        // Añadir 2 grados de prioridad si hay antecedentes médicos
        if (antecedentes) {
            prioridad += 2;
        }

        if (edad >= 60 && altura < 1.61 && peso < 45) {
            prioridad += 3;
        }
        if (edad <= 8) {
            prioridad += 2;
        }

        return prioridad;
    }

    private void showAlert(String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Clase Cita para almacenar información de la cita
    private class Cita implements Comparable<Cita> {
        private String nombre;
        private int prioridad;
        private int pin;
        private boolean antecedentes;

        public Cita(String nombre, int prioridad, int pin, boolean antecedentes) {
            this.nombre = nombre;
            this.prioridad = prioridad;
            this.pin = pin;
            this.antecedentes = antecedentes;
        }

        public String getNombre() {
            return nombre;
        }

        public int getPrioridad() {
            return prioridad;
        }

        public int getPin() {
            return pin;
        }

        public boolean tieneAntecedentes() {
            return antecedentes;
        }

        @Override
        public int compareTo(Cita otraCita) {
            // Ordenar las citas por prioridad de manera ascendente
            return Integer.compare(this.prioridad, otraCita.prioridad);
        }
    }
}
