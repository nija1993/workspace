import syntaxtree.*;
import visitor.*;

public class Main {
   public static void main(String [] args) {
      try {
         Node root = new microIRParser(System.in).Goal();
//         System.out.println("Program parsed successfully");
         IRVisitor vis = new IRVisitor(0);
         root.accept(vis); // Your assignment part is invoked here.
         vis.no_of_parse = 1;
//         System.out.println("parse 1 over");
         root.accept(vis);
         vis.no_of_parse = 2;
//         System.out.println("parse 2 over");
         root.accept(vis);
//         System.out.println("parse 3 over");
         vis.no_of_parse = 3;
         root.accept(vis);
//         System.out.println("parse 4 over");
      }
      catch (ParseException e) {
         System.out.println(e.toString());
      }
   }
} 



