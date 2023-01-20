import model.expression.*;
import model.operators.ArithmeticOperator;
import model.operators.RelationalOperator;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.RefType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;

import java.util.ArrayList;
import java.util.List;

public class Programs {
    public static List<IStatement> getAllPrograms() {
        List<IStatement> programs = new ArrayList<>();
        programs.add(ex1);
        programs.add(ex2);
        programs.add(ex3);
        programs.add(ex4);
        programs.add(ex5);
        programs.add(ex6);
        programs.add(ex7);
        programs.add(ex8);
        programs.add(ex9);
        programs.add(ex10);
        programs.add(ex11);
        programs.add(ex12);
        return programs;
    }

    /*
    EX 1
    int v;
    v=2;
    Print(v)
     */
    public static final IStatement ex1 = new CompStatement(
            new VarDeclStatement(new IntType(), "v"),
            new CompStatement(
                    new AssignStatement("v",
                            new ValueExpression(new IntValue(2))),
                    new PrintStatement(new VarExpression("v"))
            )
    );


    /*
    EX 2
    int a;
    int b;
    a=2+3*5;
    b=a+1;
    Print(b)
    */
    public static final IStatement ex2 = new CompStatement(
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


    /*
    EX 3
    bool a;
    int v;
    a=true;
    If a Then v=2 Else v=3;
    Print(v)
    */
    public static final IStatement ex3 = new CompStatement(
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
    private static final String ex4_file = "examples/test.in";
    public static final IStatement ex4 = new CompStatement(
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
    public static final IStatement ex5 = new CompStatement(
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


    /*
    EX 6
    Ref int v;
    new(v,20);
    Ref Ref int a;
    new(a,v);
    print(v);
    print(a)
    */
    public static final IStatement ex6 = new CompStatement(
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


    /*
    EX 7
    Ref int v;
    new(v,20);
    Ref Ref int a;
    new(a,v);
    print(rH(v));
    print(rH(rH(a))+5)
    */
    public static final IStatement ex7 = new CompStatement(
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


    /*
    EX 8
    Ref int v;
    new(v,20);
    print(rH(v));
    wH(v,30);
    print(rH(v)+5);
    */
    public static final IStatement ex8 = new CompStatement(
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
    public static final IStatement ex9 = new CompStatement(
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


    /*
    EX 10
    int v;
    v=4;
    (while (v>0) print(v);v=v-1);
    print(v)
    */
    public static final IStatement ex10 = new CompStatement(
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
    public static final IStatement ex11 = new CompStatement(
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
    public static final IStatement ex12 = new CompStatement(
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
}
