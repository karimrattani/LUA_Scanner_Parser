public class Assignment extends Statement{
 Id var;
 arithMeticExpr expr;
 public Assignment(Id var, arithMeticExpr expr){
   this.var=var;
   this.expr=expr;
 }
 public void evaluate(){
   Memory.storeVal(var.getID(),expr.evaluate());
 }
  
  
  
  
  
  
  
  
}