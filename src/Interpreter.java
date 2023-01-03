import controller.Controller;
import model.ProgramState;
import model.adt.*;
import model.expression.*;
import model.operators.ArithmeticOperator;
import model.operators.RelationalOperator;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.*;
import repository.IRepository;
import repository.Repository;
import view.TextMenu;
import view.command.ExitCommand;
import view.command.RunExample;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        // Ex 1
        ProgramState ex1_program = new ProgramState(ex1_exeStack, ex1_symTable, ex1_output, ex1_fileTable, ex1_heap, ex1);
        IRepository ex1_repo = new Repository(ex1_program, ex1_log);
        Controller ex1_controller = new Controller(ex1_repo, true);

        // Ex 2
        ProgramState ex2_program = new ProgramState(ex2_exeStack, ex2_symTable, ex2_output, ex2_fileTable, ex2_heap, ex2);
        IRepository ex2_repo = new Repository(ex2_program, ex2_log);
        Controller ex2_controller = new Controller(ex2_repo, true);

        // Ex 3
        ProgramState ex3_program = new ProgramState(ex3_exeStack, ex3_symTable, ex3_output, ex3_fileTable, ex3_heap, ex3);
        IRepository ex3_repo = new Repository(ex3_program, ex3_log);
        Controller ex3_controller = new Controller(ex3_repo, true);

        // Ex 4
        ProgramState ex4_program = new ProgramState(ex4_exeStack, ex4_symTable, ex4_output, ex4_fileTable, ex4_heap, ex4);
        IRepository ex4_repo = new Repository(ex4_program, ex4_log);
        Controller ex4_controller = new Controller(ex4_repo, true);

        // Ex 5
        ProgramState ex5_program = new ProgramState(ex5_exeStack, ex5_symTable, ex5_output, ex5_fileTable, ex5_heap, ex5);
        IRepository ex5_repo = new Repository(ex5_program, ex5_log);
        Controller ex5_controller = new Controller(ex5_repo, true);

        // Ex 6
        ProgramState ex6_program = new ProgramState(ex6_exeStack, ex6_symTable, ex6_output, ex6_fileTable, ex6_heap, ex6);
        IRepository ex6_repo = new Repository(ex6_program, ex6_log);
        Controller ex6_controller = new Controller(ex6_repo, true);

        // Ex 7
        ProgramState ex7_program = new ProgramState(ex7_exeStack, ex7_symTable, ex7_output, ex7_fileTable, ex7_heap, ex7);
        IRepository ex7_repo = new Repository(ex7_program, ex7_log);
        Controller ex7_controller = new Controller(ex7_repo, true);

        // Ex 8
        ProgramState ex8_program = new ProgramState(ex8_exeStack, ex8_symTable, ex8_output, ex8_fileTable, ex8_heap, ex8);
        IRepository ex8_repo = new Repository(ex8_program, ex8_log);
        Controller ex8_controller = new Controller(ex8_repo, true);

        // Ex 9
        ProgramState ex9_program = new ProgramState(ex9_exeStack, ex9_symTable, ex9_output, ex9_fileTable, ex9_heap, ex9);
        IRepository ex9_repo = new Repository(ex9_program, ex9_log);
        Controller ex9_controller = new Controller(ex9_repo, true);

        // Ex 10
        ProgramState ex10_program = new ProgramState(ex10_exeStack, ex10_symTable, ex10_output, ex10_fileTable, ex10_heap, ex10);
        IRepository ex10_repo = new Repository(ex10_program, ex10_log);
        Controller ex10_controller = new Controller(ex10_repo, true);

        // Ex 11
        ProgramState ex11_program = new ProgramState(ex11_exeStack, ex11_symTable, ex11_output, ex11_fileTable, ex11_heap, ex11);
        IRepository ex11_repo = new Repository(ex11_program, ex11_log);
        Controller ex11_controller = new Controller(ex11_repo, true);

        // Ex 11
        ProgramState ex12_program = new ProgramState(ex12_exeStack, ex12_symTable, ex12_output, ex12_fileTable, ex12_heap, ex12);
        IRepository ex12_repo = new Repository(ex12_program, ex12_log);
        Controller ex12_controller = new Controller(ex12_repo, true);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ex1_controller));
        menu.addCommand(new RunExample("2", ex2.toString(), ex2_controller));
        menu.addCommand(new RunExample("3", ex3.toString(), ex3_controller));
        menu.addCommand(new RunExample("4", ex4.toString(), ex4_controller));
        menu.addCommand(new RunExample("5", ex5.toString(), ex5_controller));
        menu.addCommand(new RunExample("6", ex6.toString(), ex6_controller));
        menu.addCommand(new RunExample("7", ex7.toString(), ex7_controller));
        menu.addCommand(new RunExample("8", ex8.toString(), ex8_controller));
        menu.addCommand(new RunExample("9", ex9.toString(), ex9_controller));
        menu.addCommand(new RunExample("10", ex10.toString(), ex10_controller));
        menu.addCommand(new RunExample("11", ex11.toString(), ex11_controller));
        menu.addCommand(new RunExample("12", ex12.toString(), ex12_controller));
        menu.show();
    }


    /*
    EX 1
    int v;
    v=2;
    Print(v)
     */
    private static final String ex1_log = "examples/ex1_log.txt";
    private static final IStatement ex1 = new CompStatement(
            new VarDeclStatement(new IntType(), "v"),
            new CompStatement(
                    new AssignStatement("v",
                            new ValueExpression(
                                    new IntValue(2))),
                    new PrintStatement(new VarExpression("v"))
            )
    );
    private static final IExeStack<IStatement> ex1_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex1_symTable = new SymTable<>();
    private static final IOutput<IValue> ex1_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex1_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex1_heap = new Heap();


    /*
    EX 2
    int a;
    int b;
    a=2+3*5;
    b=a+1;
    Print(b)
    */
    private static final String ex2_log = "examples/ex2_log.txt";
    private static final IStatement ex2 = new CompStatement(
            new VarDeclStatement(new IntType(), "a"),
            new CompStatement(
                    new VarDeclStatement(new IntType(), "b"),
                    new CompStatement(
                            new AssignStatement(
                                    "a",
                                    new ArithmeticExpression(
                                            new ValueExpression(new IntValue(2)),
                                            new ArithmeticExpression(
                                                    new ValueExpression(new IntValue(3)),
                                                    new ValueExpression(new IntValue(5)),
                                                    ArithmeticOperator.MUL
                                            ),
                                            ArithmeticOperator.ADD
                                    )
                            ),
                            new CompStatement(
                                    new AssignStatement(
                                            "b",
                                            new ArithmeticExpression(
                                                    new VarExpression("a"),
                                                    new ValueExpression(new IntValue(1)),
                                                    ArithmeticOperator.ADD
                                            )
                                    ),
                                    new PrintStatement(new VarExpression("b"))
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex2_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex2_symTable = new SymTable<>();
    private static final IOutput<IValue> ex2_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex2_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex2_heap = new Heap();


    /*
    EX 3
    bool a;
    int v;
    a=true;
    If a Then v=2 Else v=3;
    Print(v)
    */
    private static final String ex3_log = "examples/ex3_log.txt";
    private static final IStatement ex3 = new CompStatement(
            new VarDeclStatement(new BoolType(), "a"),
            new CompStatement(
                    new VarDeclStatement(new IntType(), "v"),
                    new CompStatement(
                            new AssignStatement(
                                    "a",
                                    new ValueExpression(new BoolValue(true))
                            ),
                            new CompStatement(
                                    new IfStatement(
                                            new VarExpression("a"),
                                            new AssignStatement(
                                                    "v",
                                                    new ValueExpression(new IntValue(2))
                                            ),
                                            new AssignStatement(
                                                    "v",
                                                    new ValueExpression(new IntValue(3))
                                            )
                                    ),
                                    new PrintStatement(new VarExpression("v"))
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex3_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex3_symTable = new SymTable<>();
    private static final IOutput<IValue> ex3_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex3_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex3_heap = new Heap();


    /*
    EX 4
    string varf;
    varf="test.in";
    openRFile(varf);
    int varc;
    readFile(varf,varc);
    print(varc);
    readFile(varf,varc);
    print(varc);
    closeRFile(varf)
     */
    private static final String ex4_file = "examples/ex4_in.txt";
    private static final String ex4_log = "examples/ex4_log.txt";
    private static final IStatement ex4 = new CompStatement(
            new VarDeclStatement(new StringType(), "varf"),
            new CompStatement(
                    new AssignStatement("varf", new ValueExpression(new StringValue(ex4_file))),
                    new CompStatement(
                            new OpenRFile(new VarExpression("varf")),
                            new CompStatement(
                                    new VarDeclStatement(new IntType(), "varc"),
                                    new CompStatement(
                                            new ReadFile(new VarExpression("varf"), "varc"),
                                            new CompStatement(
                                                    new PrintStatement(new VarExpression("varc")),
                                                    new CompStatement(
                                                            new ReadFile(new VarExpression("varf"), "varc"),
                                                            new CompStatement(
                                                                    new PrintStatement(new VarExpression("varc")),
                                                                    new CloseRFile(new VarExpression("varf"))
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex4_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex4_symTable = new SymTable<>();
    private static final IOutput<IValue> ex4_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex4_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex4_heap = new Heap();


    /*
    EX 5
    print("1 == 1");
    print(1 == 1);
    print("1 == 2");
    print(1 == 2);

    print("1 != 1");
    print(1 != 1);
    print("1 != 2");
    print(1 != 2);

    print("1 > 1");
    print(1 > 1);
    print("1 > 2");
    print(1 > 2);
    print("1 > 0");
    print(1 > 0);

    print("1 < 1");
    print(1 < 1);
    print("1 < 2");
    print(1 < 2);
    print("1 < 0");
    print(1 < 0);

    print("1 >= 1");
    print(1 >= 1);
    print("1 >= 2");
    print(1 >= 2);
    print("1 >= 0");
    print(1 >= 0);

    print("1 <= 1");
    print(1 <= 1);
    print("1 <= 2");
    print(1 <= 2);
    print("1 <= 0");
    print(1 <= 0)
     */
    private static final String ex5_log = "examples/ex5_log.txt";
    private static final IStatement ex5 = new CompStatement(
            new PrintStatement(new ValueExpression(new StringValue("1 == 1"))),
            new CompStatement(
                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.EQ)),
                    new CompStatement(
                            new PrintStatement(new ValueExpression(new StringValue("1 == 2"))),
                            new CompStatement(
                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.EQ)),
                                    new CompStatement(
                                            new PrintStatement(new ValueExpression(new StringValue("1 != 1"))),
                                            new CompStatement(
                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.NE)),
                                                    new CompStatement(
                                                            new PrintStatement(new ValueExpression(new StringValue("1 != 2"))),
                                                            new CompStatement(
                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.NE)),
                                                                    new CompStatement(
                                                                            new PrintStatement(new ValueExpression(new StringValue("1 > 1"))),
                                                                            new CompStatement(
                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.GT)),
                                                                                    new CompStatement(
                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 > 2"))),
                                                                                            new CompStatement(
                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.GT)),
                                                                                                    new CompStatement(
                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 > 0"))),
                                                                                                            new CompStatement(
                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(0)), RelationalOperator.GT)),
                                                                                                                    new CompStatement(
                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 < 1"))),
                                                                                                                            new CompStatement(
                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.LT)),
                                                                                                                                    new CompStatement(
                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 < 2"))),
                                                                                                                                            new CompStatement(
                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.LT)),
                                                                                                                                                    new CompStatement(
                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 < 0"))),
                                                                                                                                                            new CompStatement(
                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(0)), RelationalOperator.LT)),
                                                                                                                                                                    new CompStatement(
                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 >= 1"))),
                                                                                                                                                                            new CompStatement(
                                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.GE)),
                                                                                                                                                                                    new CompStatement(
                                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 >= 2"))),
                                                                                                                                                                                            new CompStatement(
                                                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.GE)),
                                                                                                                                                                                                    new CompStatement(
                                                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 >= 0"))),
                                                                                                                                                                                                            new CompStatement(
                                                                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(0)), RelationalOperator.GE)),
                                                                                                                                                                                                                    new CompStatement(
                                                                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 <= 1"))),
                                                                                                                                                                                                                            new CompStatement(
                                                                                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(1)), RelationalOperator.LE)),
                                                                                                                                                                                                                                    new CompStatement(
                                                                                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 <= 2"))),
                                                                                                                                                                                                                                            new CompStatement(
                                                                                                                                                                                                                                                    new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(2)), RelationalOperator.LE)),
                                                                                                                                                                                                                                                    new CompStatement(
                                                                                                                                                                                                                                                            new PrintStatement(new ValueExpression(new StringValue("1 <= 0"))),
                                                                                                                                                                                                                                                            new PrintStatement(new RelationalExpression(new ValueExpression(new IntValue(1)), new ValueExpression(new IntValue(0)), RelationalOperator.LE))
                                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                                            )
                                                                                                                                                                                                                                    )
                                                                                                                                                                                                                            )
                                                                                                                                                                                                                    )
                                                                                                                                                                                                            )
                                                                                                                                                                                                    )
                                                                                                                                                                                            )
                                                                                                                                                                                    )
                                                                                                                                                                            )
                                                                                                                                                                    )
                                                                                                                                                            )
                                                                                                                                                    )
                                                                                                                                            )
                                                                                                                                    )
                                                                                                                            )
                                                                                                                    )
                                                                                                            )
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex5_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex5_symTable = new SymTable<>();
    private static final IOutput<IValue> ex5_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex5_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex5_heap = new Heap();


    /*
    EX 6
    Ref int v;
    new(v,20);
    Ref Ref int a;
    new(a,v);
    print(v);
    print(a)
    */
    private static final String ex6_file = "examples/ex6_in.txt";
    private static final String ex6_log = "examples/ex6_log.txt";
    private static final IStatement ex6 = new CompStatement(
            new VarDeclStatement(new RefType(new IntType()), "v"),
            new CompStatement(
                    new NewStatement("v", new ValueExpression(new IntValue(20))),
                    new CompStatement(
                            new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                            new CompStatement(
                                    new NewStatement("a", new VarExpression("v")),
                                    new CompStatement(
                                            new PrintStatement(new VarExpression("v")),
                                            new PrintStatement(new VarExpression("a"))
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex6_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex6_symTable = new SymTable<>();
    private static final IOutput<IValue> ex6_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex6_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex6_heap = new Heap();
    
    
    /*
    EX 7
    Ref int v;
    new(v,20);
    Ref Ref int a;
    new(a,v);
    print(rH(v));
    print(rH(rH(a))+5)
    */
    private static final String ex7_file = "examples/ex7_in.txt";
    private static final String ex7_log = "examples/ex7_log.txt";
    private static final IStatement ex7 = new CompStatement(
            new VarDeclStatement(new RefType(new IntType()), "v"),
            new CompStatement(
                    new NewStatement("v", new ValueExpression(new IntValue(20))),
                    new CompStatement(
                            new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                            new CompStatement(
                                    new NewStatement("a", new VarExpression("v")),
                                    new CompStatement(
                                            new PrintStatement(new RHExpression(new VarExpression("v"))),
                                            new PrintStatement(new ArithmeticExpression(
                                                    new RHExpression(new RHExpression(new VarExpression("a"))),
                                                    new ValueExpression(new IntValue(5)),
                                                    ArithmeticOperator.ADD)
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex7_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex7_symTable = new SymTable<>();
    private static final IOutput<IValue> ex7_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex7_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex7_heap = new Heap();
    
    
    /*
    EX 8
    Ref int v;
    new(v,20);
    print(rH(v));
    wH(v,30);
    print(rH(v)+5);
    */
    private static final String ex8_file = "examples/ex8_in.txt";
    private static final String ex8_log = "examples/ex8_log.txt";
    private static final IStatement ex8 = new CompStatement(
            new VarDeclStatement(new RefType(new IntType()), "v"),
            new CompStatement(
                    new NewStatement("v", new ValueExpression(new IntValue(20))),
                    new CompStatement(
                            new PrintStatement(new RHExpression(new VarExpression("v"))),
                            new CompStatement(
                                    new WHStatement("v", new ValueExpression(new IntValue(30))),
                                    new PrintStatement(new ArithmeticExpression(
                                            new RHExpression(new VarExpression("v")),
                                            new ValueExpression(new IntValue(5)),
                                            ArithmeticOperator.ADD)
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex8_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex8_symTable = new SymTable<>();
    private static final IOutput<IValue> ex8_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex8_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex8_heap = new Heap();
    
    
    /*
    EX 9
    Ref int v;
    new(v,20);
    Ref Ref int a;
    new(a,v);
    new(v,30);
    new(a,v);
    print(rH(rH(a)))
    */
    private static final String ex9_file = "examples/ex9_in.txt";
    private static final String ex9_log = "examples/ex9_log.txt";
    private static final IStatement ex9 = new CompStatement(
            new VarDeclStatement(new RefType(new IntType()), "v"),
            new CompStatement(
                    new NewStatement("v", new ValueExpression(new IntValue(20))),
                    new CompStatement(
                            new VarDeclStatement(new RefType(new RefType(new IntType())), "a"),
                            new CompStatement(
                                    new NewStatement("a", new VarExpression("v")),
                                    new CompStatement(
                                            new NewStatement("v", new ValueExpression(new IntValue(30))),
                                            new CompStatement(
                                                    new NewStatement("a", new VarExpression("v")),
                                                    new PrintStatement(new RHExpression(new RHExpression(new VarExpression("a"))))
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex9_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex9_symTable = new SymTable<>();
    private static final IOutput<IValue> ex9_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex9_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex9_heap = new Heap();
    
    
    /*
    EX 10
    int v;
    v=4;
    (while (v>0) print(v);v=v-1);
    print(v)
    */
    private static final String ex10_file = "examples/ex10_in.txt";
    private static final String ex10_log = "examples/ex10_log.txt";
    private static final IStatement ex10 = new CompStatement(
            new VarDeclStatement(new IntType(), "v"),
            new CompStatement(
                    new AssignStatement("v", new ValueExpression(new IntValue(4))),
                    new CompStatement(
                            new WhileStatement(
                                    new RelationalExpression(
                                            new VarExpression("v"), 
                                            new ValueExpression(new IntValue(0)), 
                                            RelationalOperator.GT
                                    ),
                                    new CompStatement(
                                            new PrintStatement(new VarExpression("v")),
                                            new AssignStatement("v", new ArithmeticExpression(
                                                    new VarExpression("v"), 
                                                    new ValueExpression(new IntValue(1)), 
                                                    ArithmeticOperator.SUB)
                                            )
                                    )
                            ),
                            new PrintStatement(new VarExpression("v"))
                    )
            )
    );
    private static final IExeStack<IStatement> ex10_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex10_symTable = new SymTable<>();
    private static final IOutput<IValue> ex10_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex10_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex10_heap = new Heap();

    /*
    EX 11
    int v;
    Ref int a;
    v=10;
    new(a,22);
    fork(wH(a,30);v=32;print(v);print(rH(a)));
    print(v);
    print(rH(a))
    */
    private static final String ex11_file = "examples/ex11_in.txt";
    private static final String ex11_log = "examples/ex11_log.txt";
    private static final IStatement ex11 = new CompStatement(
            new VarDeclStatement(new IntType(), "v"),
            new CompStatement(
                    new VarDeclStatement(new RefType(new IntType()), "a"),
                    new CompStatement(
                            new AssignStatement("v", new ValueExpression(new IntValue(10))),
                            new CompStatement(
                                    new NewStatement("a", new ValueExpression(new IntValue(22))),
                                    new CompStatement(
                                            new ForkStatement(
                                                    new CompStatement(
                                                            new WHStatement("a", new ValueExpression(new IntValue(30))),
                                                            new CompStatement(
                                                                    new AssignStatement("v", new ValueExpression(new IntValue(32))),
                                                                    new CompStatement(
                                                                            new PrintStatement(new VarExpression("v")),
                                                                            new PrintStatement(
                                                                                    new RHExpression(new VarExpression("a"))
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            ),
                                            new CompStatement(
                                                    new PrintStatement(new VarExpression("v")),
                                                    new PrintStatement(
                                                            new RHExpression(new VarExpression("a"))
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex11_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex11_symTable = new SymTable<>();
    private static final IOutput<IValue> ex11_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex11_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex11_heap = new Heap();

    /*
    EX 12
    int v;
    v=10;
    Ref int a;
    new(a, 20);
    fork(Ref int b;new(b, 30),Ref Ref int c;new(c, b),print(rH(b)),print(rH(rH(c))));
    nop;
    nop;
    nop;
    nop;
    print(v);
    print(rH(a))
    */
    private static final String ex12_file = "examples/ex12_in.txt";
    private static final String ex12_log = "examples/ex12_log.txt";
    private static final IStatement ex12 = new CompStatement(
            new VarDeclStatement(new IntType(), "v"),
            new CompStatement(
                    new VarDeclStatement(new RefType(new IntType()), "a"),
                    new CompStatement(
                            new AssignStatement("v", new ValueExpression(new IntValue(10))),
                            new CompStatement(
                                    new NewStatement("a", new ValueExpression(new IntValue(20))),
                                    new CompStatement(
                                            new ForkStatement(
                                                    new CompStatement(
                                                            new VarDeclStatement(new RefType(new IntType()), "b"),
                                                            new CompStatement(
                                                                    new NewStatement("b", new ValueExpression(new IntValue(30))),
                                                                    new CompStatement(
                                                                            new VarDeclStatement(new RefType(new RefType(new IntType())), "c"),
                                                                            new CompStatement(
                                                                                    new NewStatement("c", new VarExpression("b")),
                                                                                    new CompStatement(
                                                                                            new PrintStatement(
                                                                                                    new RHExpression(new VarExpression("b"))
                                                                                            ),
                                                                                            new PrintStatement(
                                                                                                    new RHExpression(
                                                                                                            new RHExpression(new VarExpression("c"))
                                                                                                    )
                                                                                            )
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            ),
                                            new CompStatement(
                                                    new NopStatement(),
                                                    new CompStatement(
                                                            new NopStatement(),
                                                            new CompStatement(
                                                                    new NopStatement(),
                                                                    new CompStatement(
                                                                            new NopStatement(),
                                                                            new CompStatement(
                                                                                    new PrintStatement(new VarExpression("v")),
                                                                                    new PrintStatement(
                                                                                            new RHExpression(new VarExpression("a"))
                                                                                    )
                                                                            )
                                                                    )
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            )
    );
    private static final IExeStack<IStatement> ex12_exeStack = new ExeStack<>();
    private static final ISymTable<String, IValue> ex12_symTable = new SymTable<>();
    private static final IOutput<IValue> ex12_output = new Output<>();
    private static final IFileTable<StringValue, BufferedReader> ex12_fileTable = new FileTable<>();
    private static final IHeap<Integer, IValue> ex12_heap = new Heap();
}
