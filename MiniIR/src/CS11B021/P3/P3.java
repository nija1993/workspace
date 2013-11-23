import syntaxtree.*;
import visitor.*;

public class P3 {
   public static void main(String [] args) {
      try {
         Node root = new MiniJavaParser(System.in).Goal();
//         System.out.println("Program parsed successfully");
         MiniIRVisitor ir = new MiniIRVisitor(0);
         root.accept(ir); // Your assignment part is invoked here.
         ir.no_of_parse = 1;
         root.accept(ir);
         ir.no_of_parse = 2;
         root.accept(ir);
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



