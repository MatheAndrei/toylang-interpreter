package gui.controller;

import exception.InterpreterException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ProgramState;
import model.statement.IStatement;

import java.io.IOException;
import java.util.List;

public class SelectWindowController {
    @FXML
    private ListView<IStatement> listView;

    public void addPrograms(List<IStatement> programs) {
        ProgramState.resetNumProgramStates();
        listView.getItems().addAll(programs);
    }

    private void chooseProgram(Stage stage, IStatement program) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/MainWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        MainWindowController sceneController = fxmlLoader.getController();
        sceneController.init(program);
    }

    @FXML
    private void onListClicked(MouseEvent event) throws IOException {
        IStatement program = listView.getSelectionModel().getSelectedItem();
        if (program == null)
            return;
        if (event.getButton() == MouseButton.PRIMARY) {
            if (event.getClickCount() == 2) {
                Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                chooseProgram(stage, program);
            }
        }
    }
}
