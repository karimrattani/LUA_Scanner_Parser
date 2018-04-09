public class repeatStatement extends Statement{
  BooleanExpr expr;
  Block blk; 
  public repeatStatement(BooleanExpr expr,Block blk){
    this.expr=expr;
    this.blk=blk;
  }
  public void evaluate(){
    do{
      this.blk.process();
    }while(this.expr.getExprResult());
  } 
  
  
  
  
  
  
  
  
  
}