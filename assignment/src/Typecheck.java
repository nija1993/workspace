import syntaxtree.*;
import visitor.*;

public class Typecheck {
   public static void main(String [] args) {
      try {
         Node root = new MiniJavaParser(System.in).Goal();
         //System.out.println("Program parsed successfully");
         TypeCheck type = new TypeCheck(0);
         root.accept(type); // Your assignment part is invoked here.
         type.no_of_parse = 1;
         //System.out.println("one parse over");
         root.accept(type);
         type.no_of_parse = 2;
         root.accept(type);
         System.out.println("Program type checked successfully");	
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



