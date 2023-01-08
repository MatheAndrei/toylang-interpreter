package view.command;

import controller.Controller;
import exception.InterpreterException;
import model.ProgramState;
import model.adt.*;
import model.statement.IStatement;
import model.value.IValue;
import model.value.StringValue;
import repository.IRepository;
import repository.Repository;

import java.io.BufferedReader;

public class RunExample extends Command {
    private final IStatement program;
    
    public RunExample(String key, String description, IStatement program) {
        super(key, description);
        this.program = program;
    }

    private ProgramState getProgramState() {
        IExeStack<IStatement> exeStack = new ExeStack<>();
        ISymTable<String, IValue> symTable = new SymTable<>();
        IOutput<IValue> output = new Output<>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeap<Integer, IValue> heap = new Heap();

        ProgramState prgState = new ProgramState(exeStack, symTable, output, fileTable, heap, program);

        return prgState;
    }

    @Override
    public void execute() {
        String logFilePath = "examples/log.txt";
        ProgramState prgState = getProgramState();
        IRepository repo = new Repository(prgState, logFilePath);
        Controller ctrl = new Controller(repo, true);
        
        try {
            prgState.typeCheck();
            ctrl.allStep();
        } catch (InterpreterException e) {
            System.err.println(e.getMessage());
        }
    }
}
