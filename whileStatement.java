public class whileStatement extends Statement{
  BooleanExpr expr;
  Block blk; 
  public whileStatement(BooleanExpr expr,Block blk){
    this.expr=expr;
    this.blk=blk;
  }
  public void evaluate(){
    while(expr.getExprResult()){
     this.blk.process(); 
    }
  }
  
}