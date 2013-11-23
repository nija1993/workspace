//
// Generated by JTB 1.3.2
//

package visitor;
import syntaxtree.*;
import java.util.*;

/**
 * Provides default methods which visit each node in the tree in depth-first
 * order.  Your visitors may extend this class.
 */
public class MiniIRVisitor<R> implements GJNoArguVisitor<R> {
   //
   // Auto class visitors--probably don't need to be overridden.
   //
	
	HashMap<String, String> extending = new HashMap<String, String>();
	HashMap<String, ClassDetails> classdet =  new HashMap<String, ClassDetails>();
	public class VarDecl{
		String name;
		String temp_name;
		int index;
		String type;
		VarDecl(String name,String temp_name, int index){
			this.name = name;
			this.temp_name = temp_name;
			this.index = index;
		}
	}
	public class MethodDetails
	{
		String name;
		int index;
		String IRMethName;
		int no_of_parameters;
		String return_type;
		HashMap<String,VarDecl> vardecl = new HashMap<String,VarDecl>();
	}
	public class ClassDetails{
		HashMap<String,VarDecl> vardecl = new HashMap<String,VarDecl>();
		LinkedHashMap<String,MethodDetails> methoddet = new LinkedHashMap<String,MethodDetails>();
	}
	private String class_name = "";
	private String meth_name = "";
	private String class_extend;
	private int temp_value = 20;
	private int temp_parameters;
	private int label = 0;
	private int index = 4;
	private int method_index;
	private String explist = "";
	private String new_class = "";
	public int no_of_parse = 0;
	public MiniIRVisitor(int no){
		no_of_parse = no;
	}
	
   public R visit(NodeList n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeListOptional n) {
      if ( n.present() ) {
         R _ret=null;
         int _count=0;
         for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
            e.nextElement().accept(this);
            _count++;
         }
         return _ret;
      }
      else
         return null;
   }

   public R visit(NodeOptional n) {
      if ( n.present() )
         return n.node.accept(this);
      else
         return null;
   }

   public R visit(NodeSequence n) {
      R _ret=null;
      int _count=0;
      for ( Enumeration<Node> e = n.elements(); e.hasMoreElements(); ) {
         e.nextElement().accept(this);
         _count++;
      }
      return _ret;
   }

   public R visit(NodeToken n) { return null; }

   //
   // User-generated visitor methods below
   //

   /**
    * f0 -> MainClass()
    * f1 -> ( TypeDeclaration() )*
    * f2 -> <EOF>
    */
   public R visit(Goal n) {
      R _ret=null;
      n.f0.accept(this);
      n.f1.accept(this);
      n.f2.accept(this);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> "public"
    * f4 -> "static"
    * f5 -> "void"
    * f6 -> "main"
    * f7 -> "("
    * f8 -> "String"
    * f9 -> "["
    * f10 -> "]"
    * f11 -> Identifier()
    * f12 -> ")"
    * f13 -> "{"
    * f14 -> PrintStatement()
    * f15 -> "}"
    * f16 -> "}"
    */
   public R visit(MainClass n) {
      R _ret=null;
      if(no_of_parse == 2)
      {
	      System.out.println("MAIN");
	      n.f14.accept(this);
	      System.out.println("END");
      }
      return _ret;
   }

   /**
    * f0 -> ClassDeclaration()
    *       | ClassExtendsDeclaration()
    */
   public R visit(TypeDeclaration n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "{"
    * f3 -> ( VarDeclaration() )*
    * f4 -> ( MethodDeclaration() )*
    * f5 -> "}"
    */
   public R visit(ClassDeclaration n) {
      R _ret=null;
      class_name = (String) n.f1.accept(this);
      index = 4;
      method_index = 0;
      meth_name = "";
      if(no_of_parse == 0)
      {
    	  ClassDetails cls = new ClassDetails();
    	  classdet.put(class_name, cls);
      }
      n.f3.accept(this);
      n.f4.accept(this);
      return _ret;
   }

   /**
    * f0 -> "class"
    * f1 -> Identifier()
    * f2 -> "extends"
    * f3 -> Identifier()
    * f4 -> "{"
    * f5 -> ( VarDeclaration() )*
    * f6 -> ( MethodDeclaration() )*
    * f7 -> "}"
    */
   public R visit(ClassExtendsDeclaration n) {
      R _ret=null;
      class_name = n.f1.f0.toString();
      class_extend = n.f3.f0.toString();
      meth_name = "";
      index = 4;
      method_index = 0;
      if(no_of_parse == 0)
      {
    	  ClassDetails cls = new ClassDetails();
    	  classdet.put(class_name, cls);
    	  extending.put(class_name, class_extend);
      }
      else if(no_of_parse == 1)
      {
    	  int parent_size = classdet.get(class_extend).vardecl.size()*4;
    	  for(String v:classdet.get(class_name).vardecl.keySet())
    	  {
    		  classdet.get(class_name).vardecl.get(v).index += parent_size;
    	  }
    	  for(String v : classdet.get(class_extend).vardecl.keySet())
    	  {
    		  classdet.get(class_name).vardecl.put(v, classdet.get(class_extend).vardecl.get(v));
    	  }
    	  int parent_func = classdet.get(class_extend).methoddet.size()*4;
//     	  for(String v : classdet.get(class_name).methoddet.keySet())
//     	  {
//     		  if(classdet.get(class_extend).methoddet.containsKey(v))
//     		  {
//     			  parent_func = parent_func-4;
//     		  }
//     	  }
    	  for(String v : classdet.get(class_name).methoddet.keySet())
    	  {
    		  if(!classdet.get(class_extend).methoddet.containsKey(v))
    		  {
    			  classdet.get(class_name).methoddet.get(v).index += parent_func;
    		  }
    		  else
    		  {
    			  classdet.get(class_name).methoddet.get(v).index = classdet.get(class_extend).methoddet.get(v).index;
    			  parent_func = parent_func-4;
			  }
    	  }
    	  for(String v : classdet.get(class_extend).methoddet.keySet())
    	  {
    		  if(!classdet.get(class_name).methoddet.containsKey(v))
    		  {
    			  classdet.get(class_name).methoddet.put(v, classdet.get(class_extend).methoddet.get(v));
    			  classdet.get(class_name).methoddet.get(v).IRMethName = class_extend+"_"+v;
    		  }
    	  }
      }
      n.f5.accept(this);
      n.f6.accept(this);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    * f2 -> ";"
    */
   public R visit(VarDeclaration n) {
      R _ret=null;
      String id = (String)n.f1.accept(this);
      String type = (String)n.f0.accept(this);
//      System.out.println(no_of_parse);
      if(no_of_parse == 0)
      {
    	  if(meth_name.equals(""))
    	  {
//    		  System.out.println("varrrr");
    		  VarDecl var = new VarDecl(id,null,index);
    		  var.type = type;
    		  index = index+4;
    		  classdet.get(class_name).vardecl.put(id, var);
    	  }
    	  else
    	  {
//    		  System.out.println("varrrr with meth");
//    		  System.out.println(class_name);
//    	      System.out.println(meth_name);
//    	      System.out.println(id);
    		  VarDecl var = new VarDecl(id,"TEMP "+Integer.toString(temp_value),0);
    		  var.type = type;
    		  temp_value++;
    		  classdet.get(class_name).methoddet.get(meth_name).vardecl.put(id, var);
    	  }
      }
      return _ret;
   }

   /**
    * f0 -> "public"
    * f1 -> Type()
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( FormalParameterList() )?
    * f5 -> ")"
    * f6 -> "{"
    * f7 -> ( VarDeclaration() )*
    * f8 -> ( Statement() )*
    * f9 -> "return"
    * f10 -> Expression()
    * f11 -> ";"
    * f12 -> "}"
    */
   public R visit(MethodDeclaration n) {
      R _ret=null;
      String ret = (String)n.f1.accept(this);
      meth_name = (String)n.f2.accept(this);
      if(no_of_parse == 0)
      {
	      MethodDetails meth = new MethodDetails();
	      meth.name = meth_name;
	      meth.index = method_index;
	      meth.return_type = ret;
	      meth.IRMethName = class_name+"_"+meth_name;
	      method_index += 4;
	      classdet.get(class_name).methoddet.put(meth_name, meth);
	      n.f4.accept(this);
	      n.f7.accept(this);
      }
      if(no_of_parse == 2)
      {
    	  n.f4.accept(this);
    	  if(n.f4.present())
    		  classdet.get(class_name).methoddet.get(meth_name).no_of_parameters = temp_parameters;
    	  else
    		  classdet.get(class_name).methoddet.get(meth_name).no_of_parameters = 0;
    	  System.out.println(class_name+"_"+meth_name+" ["+(classdet.get(class_name).methoddet.get(meth_name).no_of_parameters+1)+"]");
    	  System.out.println("BEGIN");
          n.f7.accept(this);
          n.f8.accept(this);
          System.out.println("RETURN");
          System.out.println(n.f10.accept(this));
          System.out.println("END");
      }
      
      return _ret;
   }

   /**
    * f0 -> FormalParameter()
    * f1 -> ( FormalParameterRest() )*
    */
   public R visit(FormalParameterList n) {
      R _ret=null;
      temp_parameters = 1;
//      System.out.println(temp_parameters);
      n.f0.accept(this);
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> Type()
    * f1 -> Identifier()
    */
   public R visit(FormalParameter n) {
      R _ret=null;
      String id = (String)n.f1.accept(this);
      String type = (String)n.f0.accept(this);
//      System.out.println(temp_parameters);
      if(no_of_parse == 0)
      {
//    	  System.out.println("class name : "+class_name);
//    	  System.out.println("method name : "+meth_name);
    	  VarDecl v = new VarDecl(id,"TEMP "+Integer.toString(temp_parameters),0);
    	  v.type = type;
    	  temp_parameters++;
    	  classdet.get(class_name).methoddet.get(meth_name).vardecl.put(id, v);
      }
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> FormalParameter()
    */
   public R visit(FormalParameterRest n) {
      R _ret=null;
      n.f1.accept(this);
      return _ret;
   }

   /**
    * f0 -> ArrayType()
    *       | BooleanType()
    *       | IntegerType()
    *       | Identifier()
    */
   public R visit(Type n) {
      R _ret=null;
      return n.f0.accept(this);
   }

   /**
    * f0 -> "int"
    * f1 -> "["
    * f2 -> "]"
    */
   public R visit(ArrayType n) {
      R _ret=null;
      return (R)"int[]";
   }

   /**
    * f0 -> "boolean"
    */
   public R visit(BooleanType n) {
      R _ret=null;
      return (R)"boolean";
   }

   /**
    * f0 -> "int"
    */
   public R visit(IntegerType n) {
      R _ret=null;
      return (R)"int";
   }

   /**
    * f0 -> Block()
    *       | AssignmentStatement()
    *       | ArrayAssignmentStatement()
    *       | IfStatement()
    *       | WhileStatement()
    *       | PrintStatement()
    */
   public R visit(Statement n) {
      R _ret=null;
      n.f0.accept(this);
      return _ret;
   }

   /**
    * f0 -> "{"
    * f1 -> ( Statement() )*
    * f2 -> "}"
    */
   public R visit(Block n) {
      R _ret=null;
      if(no_of_parse == 2)
      {
	      n.f1.accept(this);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "="
    * f2 -> Expression()
    * f3 -> ";"
    */
   public R visit(AssignmentStatement n) {
      R _ret=null;
      String id = n.f0.f0.toString();
      String new_id = (String)n.f0.accept(this);
//      System.out.println(class_name);
//      System.out.println(meth_name);
//      System.out.println(id);
      if(no_of_parse == 2)
      {
    	  if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(id))
    	  {
    		  System.out.print("MOVE "+new_id+" ");
    	  }
    	  else
    	  {
    		  System.out.print("HSTORE TEMP 0 "+classdet.get(class_name).vardecl.get(id).index+" ");
    	  }
    	  String st = (String)n.f2.accept(this);
		  System.out.println(st);
      }
      return _ret;
   }

   /**
    * f0 -> Identifier()
    * f1 -> "["
    * f2 -> Expression()
    * f3 -> "]"
    * f4 -> "="
    * f5 -> Expression()
    * f6 -> ";"
    */
   public R visit(ArrayAssignmentStatement n) {
      R _ret=null;
      String id = n.f0.f0.toString();
      String new_id = (String)n.f0.accept(this);
      if(no_of_parse == 2)
      {
    	  String str = (String)n.f2.accept(this);
    	  if(classdet.get(class_name).vardecl.get(id) == null)
    	  {
    		  System.out.print("MOVE PLUS "+new_id+" TIMES PLUS "+str+" 1 4 ");
    	  }
    	  else
    	  {
    		  System.out.print("HSTORE PLUS "+new_id+" TIMES PLUS "+str+" 1 4 0");
    	  }
    	  String st = (String)n.f5.accept(this);
    	  System.out.println(st);
      }
      return _ret;
   }

   /**
    * f0 -> "if"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    * f5 -> "else"
    * f6 -> Statement()
    */
   public R visit(IfStatement n) {
      R _ret=null;
      String exp = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  String label1 = "L"+Integer.toString(label++);
    	  String label2 = "L"+Integer.toString(label++);
    	  System.out.println("CJUMP "+exp+" "+label1);
    	  n.f4.accept(this);
    	  System.out.println("JUMP "+label2);
    	  System.out.print(label1+" ");
    	  n.f6.accept(this);
    	  System.out.println(label2+" NOOP");
      }
      return _ret;
   }

   /**
    * f0 -> "while"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> Statement()
    */
   public R visit(WhileStatement n) {
      R _ret=null;
      String st = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  String label1 = "L"+Integer.toString(label++);
    	  String label2 = "L"+Integer.toString(label++);
    	  System.out.println(label1+" CJUMP "+st+" "+label2);
    	  n.f4.accept(this);
    	  System.out.println("JUMP "+label1);
    	  System.out.println(label2+" NOOP ");
      }
      return _ret;
   }

   /**
    * f0 -> "System.out.println"
    * f1 -> "("
    * f2 -> Expression()
    * f3 -> ")"
    * f4 -> ";"
    */
   public R visit(PrintStatement n) {
      R _ret=null;
      if(no_of_parse == 2)
      {
    	  String st = (String)n.f2.accept(this);
    	  System.out.println("PRINT "+st);
      }
      return _ret;
   }

   /**
    * f0 -> AndExpression()
    *       | CompareExpression()
    *       | PlusExpression()
    *       | MinusExpression()
    *       | TimesExpression()
    *       | ArrayLookup()
    *       | ArrayLength()
    *       | MessageSend()
    *       | PrimaryExpression()
    */
   public R visit(Expression n) {
      R _ret=null;
      String st = (String)n.f0.accept(this);
//      System.out.println(st);
      return (R)st;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "&"
    * f2 -> PrimaryExpression()
    */
   public R visit(AndExpression n) {
      R _ret=null;
      String exp1 = (String)n.f0.accept(this);
      String exp2 = (String)n.f2.accept(this);
      String st;
      if(no_of_parse == 2)
      {
    	  String tmp = "TEMP "+Integer.toString(temp_value++);
    	  String label1 = "L"+Integer.toString(label++);
    	  st = "\nBEGIN\n";
    	  st = st+"MOVE "+tmp+" 0\n";
    	  st = st+"CJUMP "+exp1+" "+label1+"\n";
    	  st = st+"CJUMP "+exp2+" "+label1+"\n";
    	  st = st+"MOVE "+tmp+" 1\n";
    	  st = st+label1+" NOOP\nRETURN "+tmp+"\nEND\n";
    	  return (R)st;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "<"
    * f2 -> PrimaryExpression()
    */
   public R visit(CompareExpression n) {
      R _ret=null;
      String p1 = (String)n.f0.accept(this);
      String p2 = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  if(classdet.get(class_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("if");
    		  p1 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p1).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("else if");
    		  p1 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p1).temp_name;
    	  }
    	  if(classdet.get(class_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("if");
    		  p2 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p2).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("else if");
    		  p2 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p2).temp_name;
    	  }
    	  String tmp = "LT "+p1+" "+p2;
    	  return (R)tmp;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "+"
    * f2 -> PrimaryExpression()
    */
   public R visit(PlusExpression n) {
      R _ret=null;
      String p1 = (String)n.f0.accept(this);
      String p2 = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  if(classdet.get(class_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("if");
    		  p1 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p1).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("else if");
    		  p1 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p1).temp_name;
    	  }
    	  if(classdet.get(class_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("if");
    		  p2 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p2).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("else if");
    		  p2 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p2).temp_name;
    	  }
    	  String tmp = "PLUS "+p1+" "+p2;
    	  return (R)tmp;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "-"
    * f2 -> PrimaryExpression()
    */
   public R visit(MinusExpression n) {
      R _ret=null;
      String p1 = (String)n.f0.accept(this);
      String p2 = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  if(classdet.get(class_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("if");
    		  p1 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p1).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("else if");
    		  p1 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p1).temp_name;
    	  }
    	  if(classdet.get(class_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("if");
    		  p2 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p2).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("else if");
    		  p2 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p2).temp_name;
    	  }
    	  String tmp = "MINUS "+p1+" "+p2;
    	  return (R)tmp;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "*"
    * f2 -> PrimaryExpression()
    */
   public R visit(TimesExpression n) {
      R _ret=null;
      String p1 = (String)n.f0.accept(this);
      String p2 = (String)n.f2.accept(this);
      if(no_of_parse == 2)
      {
    	  if(classdet.get(class_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("if");
    		  p1 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p1).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p1))
    	  {
//    		  System.out.println("else if");
    		  p1 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p1).temp_name;
    	  }
    	  if(classdet.get(class_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("if");
    		  p2 = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(p2).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(p2))
    	  {
//    		  System.out.println("else if");
    		  p2 = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(p2).temp_name;
    	  }
    	  String tmp = "TIMES "+p1+" "+p2;
    	  return (R)tmp;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "["
    * f2 -> PrimaryExpression()
    * f3 -> "]"
    */
   public R visit(ArrayLookup n) {
      R _ret=null;
      String p1 = (String)n.f0.accept(this);
      String p2 = (String)n.f2.accept(this);
      String st;
      if(no_of_parse == 2)
      {
    	  String tmp = "TEMP "+Integer.toString(temp_value++);
    	  String tmp1 = "TEMP "+Integer.toString(temp_value++);
    	  st = "\nBEGIN\nHLOAD "+tmp+" PLUS "+p1+"\nPLUS\nBEGIN\nMOVE "+tmp1+" TIMES "+p2+" 4\nRETURN "+tmp1+"\nEND\n4 0\nRETURN "+tmp+"\nEND\n";
    	  return (R)st;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> "length"
    */
   public R visit(ArrayLength n) {
      R _ret=null;
      String p = (String)n.f0.accept(this);
      String st;
      if(no_of_parse == 2)
      {
    	  String tmp = "TEMP "+Integer.toString(temp_value++);
    	  String tmp1 = "TEMP "+Integer.toString(temp_value++);
    	  String tmp2 = "TEMP "+Integer.toString(temp_value++);
    	  String tmp3 = "TEMP "+Integer.toString(temp_value++);
    	  String label1 = "L"+Integer.toString(label++);
    	  String label2 = "L"+Integer.toString(label++);
    	  st = "\nBEGIN\nMOVE "+tmp+" 0\nMOVE "+tmp3+" 0\nMOVE "+tmp1+"\nBEGIN\nHLOAD "+tmp2+" PLUS "+p+" 0 0\nRETURN "+tmp2+"\nEND\n";
    	  st = st+label1+" CJUMP LT "+tmp+" "+tmp1+" "+label2+"\n";
    	  st = st+"MOVE "+tmp+" PLUS "+tmp+" 4\n";
    	  st = st+"MOVE "+tmp3+" PLUS "+tmp3+" 1\n";
    	  st = st+"JUMP "+label1+"\n";
    	  st = st+label2+" NOOP\n";
    	  st = st+" RETURN "+tmp3+"\nEND\n";
    	  return (R)st;
      }
      return _ret;
   }

   /**
    * f0 -> PrimaryExpression()
    * f1 -> "."
    * f2 -> Identifier()
    * f3 -> "("
    * f4 -> ( ExpressionList() )?
    * f5 -> ")"
    */
   public R visit(MessageSend n) {
      R _ret=null;
      new_class = "";
      String id = n.f2.f0.toString();
      String p = (String)n.f0.accept(this);
//      System.out.println(p);
      String s;
      explist = "";
      if(no_of_parse == 2)
      {
    	  String tmp1 = "TEMP "+Integer.toString(temp_value++);
    	  String tmp2 = "TEMP "+Integer.toString(temp_value++);
    	  String tmp3 = "TEMP "+Integer.toString(temp_value++);
    	  s = "\nCALL\nBEGIN\nMOVE "+tmp1+" "+p+"\n";
    	  s = s+"HLOAD "+tmp2+" "+tmp1+" 0\n";
//    	  System.out.println(new_class);
    	  s = s+"HLOAD "+tmp3+" "+tmp2+" "+Integer.toString(classdet.get(new_class).methoddet.get(id).index)+"\n";
    	  s = s+"RETURN "+tmp3+"\nEND\n";
    	  s = s+"("+tmp1+" ";
    	  new_class = classdet.get(new_class).methoddet.get(id).return_type;
	      n.f4.accept(this);
//	      System.out.println("explist inside : "+explist);
	      s = s+explist+")\n";
//	      System.out.println("class "+new_class);
//	      System.out.println("method "+id);
	      return (R)s;
      }
      return _ret;
   }

   /**
    * f0 -> Expression()
    * f1 -> ( ExpressionRest() )*
    */
   public R visit(ExpressionList n) {
      R _ret=null;
      String exp = (String)n.f0.accept(this);
      if(no_of_parse == 2)
      {
    	  explist = explist+exp+" ";
    	  n.f1.accept(this);
//          System.out.println("explist exp "+explist);
      }
      return _ret;
   }

   /**
    * f0 -> ","
    * f1 -> Expression()
    */
   public R visit(ExpressionRest n) {
      R _ret=null;
      String exp = (String)n.f1.accept(this);
//      System.out.println("value of exp "+exp);
      if(no_of_parse == 2)
      {
    	  explist = explist+exp+" ";
//          System.out.println("explist exp "+explist);
      }
      return _ret;
   }

   /**
    * f0 -> IntegerLiteral()
    *       | TrueLiteral()
    *       | FalseLiteral()
    *       | Identifier()
    *       | ThisExpression()
    *       | ArrayAllocationExpression()
    *       | AllocationExpression()
    *       | NotExpression()
    *       | BracketExpression()
    */
   public R visit(PrimaryExpression n) {
      R _ret=null;
      String st = (String)n.f0.accept(this);
//      System.out.println(st);
      return (R)st;
   }

   /**
    * f0 -> <INTEGER_LITERAL>
    */
   public R visit(IntegerLiteral n) {
  R _ret=null;	
      n.f0.accept(this);
      return (R)n.f0.toString();
   }

   /**
    * f0 -> "true"
    */
   public R visit(TrueLiteral n) {
      R _ret=null;
      n.f0.accept(this);
      return (R)Integer.toString(1);
   }

   /**
    * f0 -> "false"
    */
   public R visit(FalseLiteral n) {
      R _ret=null;
      n.f0.accept(this);
      return (R)Integer.toString(0);
   }

   /**
    * f0 -> <IDENTIFIER>
    */
   public R visit(Identifier n) {
      R _ret=null;
      String st = n.f0.toString();
//      System.out.println(no_of_parse);
//      System.out.println(st);
//      System.out.println(meth_name);
      if(no_of_parse == 0)
    	  return (R)n.f0.toString();
      if(!class_name.equals(""))
      {
	      if(!meth_name.equals("") && classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(st))
	      {
	    	  new_class = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(st).type;
	    	  return (R)classdet.get(class_name).methoddet.get(meth_name).vardecl.get(st).temp_name;
	      }
	      else if(classdet.get(class_name).vardecl.containsKey(st))
	      {
	    	  new_class = classdet.get(class_name).vardecl.get(st).type;
//	    	  System.out.println("new class "+new_class);
	    	  int tmp = temp_value++;
	    	  return (R)("\nBEGIN\nHLOAD TEMP "+Integer.toString(tmp)+" TEMP 0 "+classdet.get(class_name).vardecl.get(st).index+
	    			  "\nRETURN TEMP "+Integer.toString(tmp)+"\nEND\n");
	      }
      }
      return (R)n.f0.toString();
   }

   /**
    * f0 -> "this"
    */
   public R visit(ThisExpression n) {
      R _ret=null;
      n.f0.accept(this);
      new_class = class_name;
      return (R)"TEMP 0";
   }

   /**
    * f0 -> "new"
    * f1 -> "int"
    * f2 -> "["
    * f3 -> Expression()
    * f4 -> "]"
    */
public R visit(ArrayAllocationExpression n) {
      R _ret=null;
      String exp,ex;
      String st;
      if(no_of_parse == 2)
      {
    	  String obj = "TEMP "+temp_value;
    	  temp_value++;
    	  String counter = "TEMP "+temp_value;
    	  temp_value++;
    	  String label1 = "L"+Integer.toString(label++);
    	  String label2 = "L"+Integer.toString(label++);
    	  ex = (String)n.f3.accept(this);
//    	  System.out.println(ex+" next ");
//    	  for(String s: classdet.get(class_name).methoddet.get(meth_name).vardecl.keySet())
//    		  System.out.println(s);
    	  if(classdet.get(class_name).vardecl.containsKey(ex))
    	  {
//    		  System.out.println("if");
    		  exp = "TEMP 0 "+Integer.toString(classdet.get(class_name).vardecl.get(ex).index);
    	  }
    	  else if(classdet.get(class_name).methoddet.get(meth_name).vardecl.containsKey(ex))
    	  {
//    		  System.out.println("else if");
    		  exp = classdet.get(class_name).methoddet.get(meth_name).vardecl.get(ex).temp_name;
    	  }
    	  else
    		  exp = ex;
    	  st = "\nBEGIN\n"+"MOVE "+obj+" HALLOCATE PLUS TIMES "+exp+" 4 4\n"+
    	  "MOVE "+counter+" 4\n"+
    	  label1+" CJUMP LT "+counter+" TIMES PLUS "+exp+" 1 4 "+label2+"\n"+
    	  "HSTORE PLUS "+obj+" "+counter+" 0 0\n"+
    	  "MOVE "+counter+" PLUS "+counter+" 4\n"+
    	  "JUMP "+label1+"\n"+
    	  label2+" HSTORE "+obj+" 0 TIMES "+exp+" 4\n"+
    	  "RETURN "+obj+"\nEND";
    	  return (R)st;
      }
      return _ret;
   }

   /**
    * f0 -> "new"
    * f1 -> Identifier()
    * f2 -> "("
    * f3 -> ")"
    */
   public R visit(AllocationExpression n) {
      R _ret=null;
      String st = (String)n.f1.accept(this);
      new_class = n.f1.f0.toString();
      String pass;
      if(no_of_parse == 2)
      {
    	  String obj_vtable = "TEMP "+temp_value;
    	  temp_value++;
    	  String obj_class = "TEMP "+temp_value;
    	  temp_value++;
    	  String counter = "TEMP "+temp_value;
    	  temp_value++;
    	  String label1 = "L"+Integer.toString(label++);
    	  String label2 = "L"+Integer.toString(label++);
    	  int no_of_functions = classdet.get(st).methoddet.size();
    	  int no_of_variables = classdet.get(st).vardecl.size();
//    	  System.out.println("class to be printed : "+st);
    	  String store = "";
    	  for(String fun : classdet.get(st).methoddet.keySet())
    	  {
//        	  System.out.println("method to be printed : "+fun);
    		  store = store + "\nHSTORE "+obj_vtable+" "+classdet.get(st).methoddet.get(fun).index+" "+classdet.get(st).methoddet.get(fun).IRMethName;
    	  }
    	  pass = "\nBEGIN\nMOVE "+obj_vtable+" HALLOCATE "+Integer.toString(no_of_functions*4)+
    			  "\nMOVE "+obj_class+" HALLOCATE "+Integer.toString((no_of_variables+1)*4)+
    			  "\n"+store+"\nMOVE "+counter+" 4\n"+
    			  label1+" CJUMP LT "+counter+" "+(no_of_variables+1)*4+" "+label2+
    			  "\nHSTORE PLUS "+obj_class+" "+counter+" 0 0"+
    			  "\nMOVE "+counter+" PLUS "+counter+" 4"+
    			  "\nJUMP "+label1+
    			  "\n"+label2+" HSTORE "+obj_class+" 0 "+obj_vtable+
    			  "\nRETURN "+obj_class+
    			  "\nEND\n";
    	  return (R)pass;
      }
      return _ret;
   }

   /**
    * f0 -> "!"
    * f1 -> Expression()
    */
   public R visit(NotExpression n) {
      R _ret=null;
      String st = (String)n.f1.accept(this);
      if(no_of_parse == 2)
      {
    	  st = "MINUS 1 "+st;
    	  return (R)st;
      }
      return _ret;
   }

   /**
    * f0 -> "("
    * f1 -> Expression()
    * f2 -> ")"
    */
   public R visit(BracketExpression n) {
      R _ret=null;
      return n.f1.accept(this);
   }

}
