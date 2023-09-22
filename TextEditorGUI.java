package Proyecto1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

import javax.swing.JOptionPane;

public class TextEditorGUI extends Application {

    TextArea areaTexto = new TextArea();
    nodo Nodo = new nodo();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Button undoButton = new Button("Deshacer");
        undoButton.setOnAction(e -> Nodo.undo());

        Button redoButton = new Button("Rehacer");
        redoButton.setOnAction(e -> Nodo.redo());

        Button saveButton = new Button("Guardar");
        saveButton.setOnAction(e -> Nodo.guardarTexto());

        Button openButton = new Button("Abrir");
        openButton.setOnAction(e -> Nodo.abrirTexto());

        VBox root = new VBox();
        root.getChildren().addAll(areaTexto, undoButton, redoButton, saveButton, openButton);

        Scene scene = new Scene(root, 600, 400);

        primaryStage.setTitle("Editor De Texto");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public class nodo {

        Nodo redo = new Nodo(null);
        Nodo undo = new Nodo(null);

        class Nodo {
            String text;
            Nodo siguiente;
            Nodo anterior;

            public Nodo(String text) {
                this.text = text;
                this.siguiente = null;
                this.anterior = null;

            }
        }

        public void undo() {
            if (undo.text == null && areaTexto.getText() != null) {
                undo.text = areaTexto.getText();
                areaTexto.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Que se supone que debo deshacer?");
                
            }
            if (undo.text != null && areaTexto.getText() != "") {

                undo.text = areaTexto.getText();
                areaTexto.clear();

            }
        }

        public void redo() {
            if (undo.text != null) {
                if (areaTexto.getText() != "" || areaTexto.getText() == "") {
                    redo.text = undo.text;
                    areaTexto.insertText(0, redo.text + " ");
                    undo.text = null;
                    redo.text = null;
                }
            } else if (undo.text == null) {
             JOptionPane.showMessageDialog(null, "No puedo Rehacer lo que no existe");
            }
        }

        public void guardarTexto() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Guardar Archivo");
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(areaTexto.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void abrirTexto() {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir Archivo");
            File file = fileChooser.showOpenDialog(null);

            if (file != null) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    StringBuilder contenido = new StringBuilder();
                    String linea;
                    while ((linea = reader.readLine()) != null) {
                        contenido.append(linea).append("\n");
                    }
                    areaTexto.setText(contenido.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
