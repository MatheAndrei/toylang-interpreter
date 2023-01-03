package repository;

import exception.InterpreterException;
import model.ProgramState;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {
    private List<ProgramState> prgStates;
    private final String logFilePath;

    public Repository(ProgramState prgState, String logFilePath) {
        this.prgStates = new ArrayList<>();
        this.prgStates.add(prgState);
        this.logFilePath = logFilePath;

        // clear file before interpreting
        try {
            Files.writeString(Path.of(logFilePath), "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProgramState> getProgramStateList() {
        return prgStates;
    }

    @Override
    public void setProgramStateList(List<ProgramState> prgStates) {
        this.prgStates = prgStates;
    }

    @Override
    public void logProgramStateExec(ProgramState prgState) throws InterpreterException {
        try {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.format("%s\n\n", prgState);
            logFile.flush();
            logFile.close();
        } catch (IOException e) {
            throw new InterpreterException(e.getMessage());
        }
    }
}
