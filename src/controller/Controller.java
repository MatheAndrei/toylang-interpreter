package controller;

import exception.InterpreterException;
import model.ProgramState;
import model.adt.Heap;
import model.adt.IExeStack;
import model.adt.IHeap;
import model.adt.ISymTable;
import model.statement.IStatement;
import model.value.IValue;
import model.value.RefValue;
import repository.IRepository;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Controller {
    private final IRepository repo;
    private final boolean displayFlag;
    private ExecutorService executor;
    private final int numThreads = 8;

    public Controller(IRepository repo, boolean displayFlag) {
        this.repo = repo;
        this.displayFlag = displayFlag;
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(numThreads);
        List<ProgramState> prgStates = removeCompletedPrograms(repo.getProgramStateList());
        try {
            printAllProgramStates(prgStates);
        } catch (InterpreterException e) {
            System.out.println(e.getMessage());
        }
        while (prgStates.size() > 0) {
            oneStepForAllPrograms(prgStates);
            prgStates = removeCompletedPrograms(repo.getProgramStateList());

            IHeap<Integer, IValue> newHeap = conservativeGarbageCollector(prgStates);
            prgStates.forEach(prgState -> {
                prgState.setHeap(newHeap);
            });
        }
        executor.shutdownNow();
        repo.setProgramStateList(prgStates);
    }

    private void oneStepForAllPrograms(List<ProgramState> prgStates) {
        List<Callable<ProgramState>> callList = prgStates.stream()
                .map((ProgramState prgState) -> (Callable<ProgramState>)(prgState::oneStep))
                .toList();

        try {
            List<ProgramState> newPrgStates = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .toList();

            prgStates.addAll(newPrgStates);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            printAllProgramStates(prgStates);
        } catch (InterpreterException e) {
            System.out.println(e.getMessage());
        }

        repo.setProgramStateList(prgStates);
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> prgStates) {
        return prgStates.stream().filter(prgState -> !prgState.isCompleted()).collect(Collectors.toList());
    }

    private IHeap<Integer, IValue> conservativeGarbageCollector(List<ProgramState> prgStates) {
        Map<Integer, IValue> newMap = new HashMap<>();
        IHeap<Integer, IValue> newHeap = new Heap();

        for (ProgramState prgState : prgStates) {
            IHeap<Integer, IValue> heap = garbageCollector(prgState);
            heap.getContent().forEach((key, value) -> {
                if (!newMap.containsKey(key))
                    newMap.put(key, value);
            });
        }

        newHeap.setContent(newMap);

        return newHeap;
    }

    private IHeap<Integer, IValue> garbageCollector(ProgramState prgState) {
        ISymTable<String, IValue> symTable = prgState.getSymTable();
        IHeap<Integer, IValue> heap = prgState.getHeap();

        ConcurrentLinkedDeque<Integer> symTableAddresses = getAddressesFromSymTable(symTable, heap);
        Set<Map.Entry<Integer, IValue>> items = heap.getContent().entrySet();

        Map<Integer, IValue> newMap;
        IHeap<Integer, IValue> newHeap;

        newMap = items.stream()
                .filter(item -> symTableAddresses.contains(item.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        newHeap = new Heap();
        newHeap.setContent(newMap);

        return newHeap;
    }

    private ConcurrentLinkedDeque<Integer> getAddressesFromSymTable(ISymTable<String, IValue> symTable, IHeap<Integer, IValue> heap) {
        Collection<IValue> values = symTable.getContent().values();
        ConcurrentLinkedDeque<Integer> addresses;

        addresses = values.stream()
                .filter(value -> value instanceof RefValue)
                .map(value -> ((RefValue)value).getAddress())
                .collect(Collectors.toCollection(ConcurrentLinkedDeque::new));

        addresses.forEach(address -> {
            IValue value = heap.lookUp(address);
            if (value instanceof RefValue) {
                Integer forwordAddress = ((RefValue)value).getAddress();
                if (!addresses.contains(forwordAddress))
                    addresses.add(forwordAddress);
            }
        });

        return addresses;
    }

    private void printAllProgramStates(List<ProgramState> prgStates) throws InterpreterException {
        for (ProgramState prgState : prgStates)
            printProgramState(prgState);
    }

    private void printProgramState(ProgramState prgState) throws InterpreterException {
        if (!displayFlag)
            return;
        System.out.println(prgState + "\n\n");
        repo.logProgramStateExec(prgState);
    }
}
