public class arithMeticExpr{
  Id id;
  int val;
  String op;
  arithMeticExpr exp1;
  arithMeticExpr exp2;
  public arithMeticExpr(Id id){
    this.id=id;
    op="";
  }
  public arithMeticExpr(int val){
    this.val=val;
    op="";
  }
  public arithMeticExpr(String op, arithMeticExpr exp1, arithMeticExpr exp2){
    this.op=op;
    this.exp1=exp1;
    this.exp2=exp2;
  }
  
  public int evaluate(){
    if(id==null && op.equals("")){
     return this.val; 
    }else if(op.equals("")){
     return Memory.getVal(id.getID());
    }else{
     return this.calcArithMetic();
    }
  }
  private int calcArithMetic(){
    if(op.equals("+")){
      return exp1.evaluate() + exp2.evaluate();
    }else if(op.equals("-")){
      return exp1.evaluate() - exp2.evaluate();
    }else if(op.equals("*")){
      return exp1.evaluate() * exp2.evaluate();
    }else if(op.equals("/")){
      return exp1.evaluate() / exp2.evaluate();
    }else{
     throw new IllegalArgumentException("Invalid Operator, Found "+op); 
    }
  }
  
  
}