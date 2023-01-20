package gui;

import gui.controller.SelectWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.statement.IStatement;

import java.util.List;

public class GUI extends Application {

    public static List<IStatement> programs;

    public static void launchApp(String[] args, List<IStatement> programs) {
        GUI.programs = programs;
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/SelectWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        SelectWindowController controller = fxmlLoader.getController();
        controller.addPrograms(programs);
    }
}
