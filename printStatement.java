public class printStatement extends Statement{
  arithMeticExpr expr;
  public printStatement(arithMeticExpr expr){
    this.expr = expr;
    
  }
  public void evaluate(){
   System.out.println(expr.evaluate()); 
  }
  
  
  
  
  
  
  
  
}