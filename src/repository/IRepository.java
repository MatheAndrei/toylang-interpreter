package repository;

import exception.InterpreterException;
import model.ProgramState;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> prgStates);
    void logProgramStateExec(ProgramState prgState) throws InterpreterException;
}
