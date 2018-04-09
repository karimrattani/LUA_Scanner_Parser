public class ifStatement extends Statement{
  BooleanExpr expr;
  Block blk;
  Block elseBlk;
  
  public ifStatement( BooleanExpr expr, Block blk, Block elseBlk){
    this.expr=expr;
    this.blk=blk;
    this.elseBlk=elseBlk;
  }
  
  public void evaluate(){
    if(expr.getExprResult()){
      this.blk.process();
    }else{
      this.elseBlk.process();
    }
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}