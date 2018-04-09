public class BooleanExpr{
 String op;
 arithMeticExpr expr1;
 arithMeticExpr expr2;
 public BooleanExpr(String op, arithMeticExpr expr1, arithMeticExpr expr2){
   this.op = op;
   this.expr1=expr1;
   this.expr2=expr2;
 }
 
 public Boolean getExprResult(){
   if(op.equals("<")){
     return expr1.evaluate() < expr2.evaluate();
   }else if(op.equals(">")){
     return expr1.evaluate() > expr2.evaluate();
   }else if(op.equals("==")){
     return expr1.evaluate() == expr2.evaluate();
   }else if(op.equals("~=")){
     return expr1.evaluate() != expr2.evaluate();
   }else if(op.equals("<=")){
     return expr1.evaluate() <= expr2.evaluate();
   }else if(op.equals(">=")){
     return expr1.evaluate() >= expr2.evaluate();
   }else{
     throw new IllegalArgumentException("Expected Relative Operator, Found "+this.op);  
   }
 }
  
  
  
  
}