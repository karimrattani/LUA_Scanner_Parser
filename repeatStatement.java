public class repeatStatement extends Statement{
  BooleanExpr expr;
  Block blk; 
  public repeatStatement(BooleanExpr expr,Block blk){
    this.expr=expr;
    this.blk=blk;
  }
  public void evaluate(){
      while(expr.getExprResult()){
     this.blk.process(); 
    }
  } 
  
  
  
  
  
  
  
  
  
}