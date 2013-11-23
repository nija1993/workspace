//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * All GJ void visitors must implement this interface.
 */

public interface GJVoidVisitor<A> {

   //
   // GJ void Auto class visitors
   //

   public void visit(NodeList n, A argu);
   public void visit(NodeListOptional n, A argu);
   public void visit(NodeOptional n, A argu);
   public void visit(NodeSequence n, A argu);
   public void visit(NodeToken n, A argu);

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> "MAIN"
    * f1 -> StmtList()
    * f2 -> "END"
    * f3 -> ( Procedure() )*
    * f4 -> <EOF>
    */
   public void visit(Goal n, A argu);

   /**
    * f0 -> ( ( Label() )? Stmt() )*
    */
   public void visit(StmtList n, A argu);

   /**
    * f0 -> Label()
    * f1 -> "["
    * f2 -> IntegerLiteral()
    * f3 -> "]"
    * f4 -> StmtExp()
    */
   public void visit(Procedure n, A argu);

   /**
    * f0 -> NoOpStmt()
    *       | ErrorStmt()
    *       | CJumpStmt()
    *       | JumpStmt()
    *       | HStoreStmt()
    *       | HLoadStmt()
    *       | MoveStmt()
    *       | PrintStmt()
    */
   public void visit(Stmt n, A argu);

   /**
    * f0 -> "NOOP"
    */
   public void visit(NoOpStmt n, A argu);

   /**
    * f0 -> "ERROR"
    */
   public void visit(ErrorStmt n, A argu);

   /**
    * f0 -> "CJUMP"
    * f1 -> Exp()
    * f2 -> Label()
    */
   public void visit(CJumpStmt n, A argu);

   /**
    * f0 -> "JUMP"
    * f1 -> Label()
    */
   public void visit(JumpStmt n, A argu);

   /**
    * f0 -> "HSTORE"
    * f1 -> Exp()
    * f2 -> IntegerLiteral()
    * f3 -> Exp()
    */
   public void visit(HStoreStmt n, A argu);

   /**
    * f0 -> "HLOAD"
    * f1 -> Temp()
    * f2 -> Exp()
    * f3 -> IntegerLiteral()
    */
   public void visit(HLoadStmt n, A argu);

   /**
    * f0 -> "MOVE"
    * f1 -> Temp()
    * f2 -> Exp()
    */
   public void visit(MoveStmt n, A argu);

   /**
    * f0 -> "PRINT"
    * f1 -> Exp()
    */
   public void visit(PrintStmt n, A argu);

   /**
    * f0 -> StmtExp()
    *       | Call()
    *       | HAllocate()
    *       | BinOp()
    *       | Temp()
    *       | IntegerLiteral()
    *       | Label()
    */
   public void visit(Exp n, A argu);

   /**
    * f0 -> "BEGIN"
    * f1 -> StmtList()
    * f2 -> "RETURN"
    * f3 -> Exp()
    * f4 -> "END"
    */
   public void visit(StmtExp n, A argu);

   /**
    * f0 -> "CALL"
    * f1 -> Exp()
    * f2 -> "("
    * f3 -> ( Exp() )*
    * f4 -> ")"
    */
   public void visit(Call n, A argu);

   /**
    * f0 -> "HALLOCATE"
    * f1 -> Exp()
    */
   public void visit(HAllocate n, A argu);

   /**
    * f0 -> Operator()
    * f1 -> Exp()
    * f2 -> Exp()
    */
   public void visit(BinOp n, A argu);

   /**
    * f0 -> "LT"
    *       | "PLUS"
    *       | "MINUS"
    *       | "TIMES"
    */
   public void visit(Operator n, A argu);

   /**
    * f0 -> "TEMP"
    * f1 -> IntegerLiteral()
    */
   public void visit(Temp n, A argu);

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public void visit(IntegerLiteral n, A argu);

   /**
    * f0 -> <IDENTIFIER>
    */
   public void visit(Label n, A argu);

}
