package gui.controller;

import controller.Controller;
import exception.InterpreterException;
import gui.GUI;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.ProgramState;
import model.adt.*;
import model.statement.IStatement;
import model.value.IValue;
import model.value.StringValue;
import repository.IRepository;
import repository.Repository;

import javax.naming.Binding;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainWindowController {
    private Controller controller;

    private ObservableList<ProgramState> threads;
    private IHeap<Integer, IValue> heap;
    private IFileTable<StringValue, BufferedReader> fileTable;
    private IOutput<IValue> output;

    @FXML
    private Label gui_threadCount;
    @FXML
    private Button gui_runAll;
    @FXML
    private Button gui_oneStep;
    @FXML
    private ListView<Integer> gui_threads;
    @FXML
    private ListView<IStatement> gui_exeStack;
    @FXML
    private TableView<Map.Entry<String, IValue>> gui_symTable;
    @FXML
    private TableColumn<Map.Entry<String, IValue>, String> gui_symTableId;
    @FXML
    private TableColumn<Map.Entry<String, IValue>, IValue> gui_symTableValue;
    @FXML
    private TableView<Map.Entry<Integer, IValue>> gui_heap;
    @FXML
    private TableColumn<Map.Entry<Integer, IValue>, Integer> gui_heapAddress;
    @FXML
    private TableColumn<Map.Entry<Integer, IValue>, IValue> gui_heapValue;
    @FXML
    private ListView<StringValue> gui_fileTable;
    @FXML
    private ListView<IValue> gui_output;

    public void init(IStatement program) {
        // create program state and controller
        IExeStack<IStatement> exeStack = new ExeStack<>();
        ISymTable<String, IValue> symTable = new SymTable<>();
        IOutput<IValue> output = new Output<>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeap<Integer, IValue> heap = new Heap();

        String logFilePath = "examples/log.txt";
        ProgramState prgState = new ProgramState(exeStack, symTable, output, fileTable, heap, program);
        IRepository repo = new Repository(prgState, logFilePath);
        controller = new Controller(repo, false);

        // init symbol table columns
        gui_symTableId.setCellValueFactory(entry -> new ReadOnlyObjectWrapper<>(entry.getValue().getKey()));
        gui_symTableValue.setCellValueFactory(entry -> new ReadOnlyObjectWrapper<>(entry.getValue().getValue()));

        // init heap columns
        gui_heapAddress.setCellValueFactory(entry -> new ReadOnlyObjectWrapper<>(entry.getValue().getKey()));
        gui_heapValue.setCellValueFactory(entry -> new ReadOnlyObjectWrapper<>(entry.getValue().getValue()));

        // init threads and thread count label
        threads = FXCollections.observableList(controller.getProgramStateList());
        threads.addListener(new ListChangeListener<ProgramState>() {
            @Override
            public void onChanged(Change<? extends ProgramState> change) {
                onThreadsChanged();
            }
        });
        gui_threadCount.setText(String.valueOf(threads.size()));
        gui_threads.getItems().setAll(threads.stream().map(ProgramState::getId).toList());

        // init common program data structures
        this.heap = prgState.getHeap();
        this.fileTable = prgState.getFileTable();
        this.output = prgState.getOutput();
    }

    private void changeToSelectWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../resources/SelectWindow.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);

        SelectWindowController sceneController = fxmlLoader.getController();
        sceneController.addPrograms(GUI.programs);
    }

    private ProgramState getCurrentThread() {
        int threadId = gui_threads.getSelectionModel().getSelectedIndex();
        if (threadId == -1)
            return null;
        ProgramState prgState = threads.get(threadId);
        return prgState;
    }

    private void updateCurrentThread() {
        // get current selected thread
        ProgramState prgState = getCurrentThread();
        if (prgState == null)
            return;

        // get thread state
        Stack<IStatement> exeStack = prgState.getExeStack().getContent();
        //Collections.reverse(exeStack);
        List<Map.Entry<String, IValue>> symTable = prgState.getSymTable().getContent().entrySet().stream().toList();
        List<Map.Entry<Integer, IValue>> heap = this.heap.getContent().entrySet().stream().toList();
        List<StringValue> fileTable = this.fileTable.getContent().keySet().stream().toList();
        List<IValue> output = this.output.getContent();

        // update gui thread state
        gui_exeStack.getItems().setAll(exeStack);
        gui_symTable.getItems().setAll(symTable);
        gui_heap.getItems().setAll(heap);
        gui_fileTable.getItems().setAll(fileTable);
        gui_output.getItems().setAll(output);
    }

    @FXML
    private void onRunAllClicked() throws InterpreterException {
        // run all
        while (!controller.getProgramStateList().isEmpty())
            controller.oneStepGUI(controller.getProgramStateList());
        threads.setAll(controller.getProgramStateList());
    }

    @FXML
    private void onOneStepClicked() throws InterpreterException {
        // run one step
        controller.oneStepGUI(controller.getProgramStateList());
        if (!threads.equals(controller.getProgramStateList()))
            threads.setAll(controller.getProgramStateList());

        // update thread state
        updateCurrentThread();
    }

    @FXML
    private void onChooseProgramClicked(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        changeToSelectWindow(stage);
    }

    @FXML
    private void onThreadSelected() {
        updateCurrentThread();
    }

    private void onThreadsChanged() {
        if (threads.isEmpty()) {
            // disable run all and one step buttons
            gui_runAll.setDisable(true);
            gui_oneStep.setDisable(true);

            // clear execution heap and file table
            gui_heap.getItems().clear();
            gui_fileTable.getItems().clear();

            // update output
            gui_output.getItems().setAll(output.getContent());
        }

        // update threads and thread count
        gui_threadCount.setText(String.valueOf(threads.size()));
        gui_threads.getItems().setAll(threads.stream().map(ProgramState::getId).toList());

        // clear execution stack and symbol table
        gui_exeStack.getItems().clear();
        gui_symTable.getItems().clear();
    }
}
