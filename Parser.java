import java.util.LinkedList;
import java.util.Iterator;
public class Parser{
  LexicalAnalyzer lexArr;
  LinkedList<token> tokens;
  token tok;
  token prevTok;
  public Parser(String filename){
    assert(!filename.equals(""));
    lexArr = new LexicalAnalyzer(filename);
    tokens = lexArr.getTokenList();
    tok=tokens.getFirst();
//    while(tok.getTokenType()!=tokenType.EOF_TKN){
//      Program prg = this.Parse();
//    }
  }
  
  //TODO: Change type to Program
  public Program Parse(){
    if(this.tok.getTokenType()!=tokenType.FUNCTION_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected FUNCTION on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN){
      throw new IllegalArgumentException("Expected ID on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    Id id = new Id(this.tok.getLexeme().charAt(0));
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ) on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    Block blk = this.block();
    blk.process();
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    Program prg = new Program(blk);
    return prg;
  }
  
  //return block
  private Block block(){
    if(!this.isStatement(tok.getTokenType())){
      throw new IllegalArgumentException("Expected STATEMENT on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    Statement stm = this.statement();//return statement and add it to the block
    Block blk = new Block(stm);
    tokenType nextToken = lookAHead();
    while(this.isStatement(nextToken)){
      this.nextToken();
      Statement stment = this.statement();
      blk.add(stment);
      nextToken = lookAHead();
    }
    return blk;
    
  }
  
  private boolean isStatement(tokenType tokType){
    if(tokType==tokenType.IF_STATEMENT_TKN || tokType==tokenType.IDEN_TKN || tokType==tokenType.WHILE_STATEMENT_TKN || tokType==tokenType.PRINT_STATEMENT_TKN || tokType==tokenType.REPEAT_STATEMENT_TKN)
      return true;
    return false;
    
  }
  
  //return statement
  private Statement statement(){
    if(this.tok.getTokenType()==tokenType.IF_STATEMENT_TKN){
      this.nextToken();
      Statement stm = this.ifStatement(); 
      return stm;
    }else if(this.tok.getTokenType()==tokenType.IDEN_TKN){
      this.nextToken();
      Statement stm = this.assignmentStatement();
      return stm;
    }else if(this.tok.getTokenType()==tokenType.WHILE_STATEMENT_TKN){
      this.nextToken();
      Statement stm = this.whileStatement();
      return stm;
    }else if(this.tok.getTokenType()==tokenType.PRINT_STATEMENT_TKN){
      this.nextToken();
      Statement stm = this.printStatement();
      return stm;
    }else if(this.tok.getTokenType()==tokenType.REPEAT_STATEMENT_TKN){
      this.nextToken();
      Statement stm = this.repeatStatement();
      return stm;
    }else if(!this.checkEndOfFile()){
      throw new IllegalArgumentException("Expected STATEMENT on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    return null;
  }
  
  private Statement ifStatement(){
    
    BooleanExpr result = this.boolean_expression();
    
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.THEN_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected THEN on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    
    this.nextToken();
    
    Block blk1 = this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.ELSE_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected ELSE on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    Block blk2 = this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    Statement stm = new ifStatement(result,blk1,blk2);
    return stm;
  }
  
  private Statement whileStatement(){
    BooleanExpr expr = this.boolean_expression();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.DO_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected DO on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    Block blk = this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    Statement stm = new whileStatement(expr,blk);
    return stm;
  }
  private Assignment assignmentStatement(){
    if(this.tok.getTokenType()!=tokenType.ASSIGNMENT_OP_TKN){
      throw new IllegalArgumentException("Expected = on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    Id id = new Id(this.prevTok.getLexeme().charAt(0));
    this.nextToken();
    arithMeticExpr exp = this.arithmetic_exp();
    return new Assignment(id,exp);
    
  }
  private printStatement printStatement(){
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    arithMeticExpr exp = this.arithmetic_exp();
    printStatement prntStm = new printStatement(exp);
    
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ) on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    return prntStm;
  }
  private Statement repeatStatement(){
    Block blk = this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.UNTIL_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected UNTIL on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    BooleanExpr exp = this.boolean_expression();
    return new repeatStatement(exp,blk);
  }
  
  private BooleanExpr boolean_expression(){
    if(this.tok.getTokenType()!=tokenType.RELATIVE_OP_TKN){
      throw new IllegalArgumentException("Expected RELATIVE OPERATOR on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    String op = this.tok.getLexeme();
    this.nextToken();
    arithMeticExpr exp1 = this.arithmetic_exp();
    this.nextToken();
    arithMeticExpr exp2 = this.arithmetic_exp();
    BooleanExpr bool = new BooleanExpr(op,exp1,exp2);
    return bool;
  }
  private arithMeticExpr arithmetic_exp(){
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN && this.tok.getTokenType()!=tokenType.LITERAL_INTEGAR_TKN && this.tok.getTokenType()!=tokenType.ARITH_OP_TKN){
      throw new IllegalArgumentException("Expected ID or INTEGAR or ARITHMETIC OPERATOR on line:"+this.tok.getRowNumber() +" found "+this.tok.getLexeme()); 
    }
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN && this.tok.getTokenType()!=tokenType.LITERAL_INTEGAR_TKN){
      String op = this.tok.getLexeme();
      this.nextToken();
      arithMeticExpr exp1 = this.arithmetic_exp();
      this.nextToken();
      arithMeticExpr exp2 = this.arithmetic_exp();
      return new arithMeticExpr(op,exp1,exp2);
    }else if(this.tok.getTokenType()==tokenType.IDEN_TKN){
      Id id = new Id(this.tok.getLexeme().charAt(0));
      return new arithMeticExpr(id);
    }else{
      int val = Integer.parseInt(this.tok.getLexeme());
      return new arithMeticExpr(val);
    }
    
    
  }
  
  
  private void nextToken(){
    this.prevTok = this.tok;
    if(!tokens.isEmpty()){
      this.tokens.pop();
      this.tok=tokens.getFirst();
    }
    
  }
  private Boolean checkEndOfFile(){
    if(this.tok.getTokenType()==tokenType.EOF_TKN){
      return true; 
    }
    return false;
  }
  private tokenType lookAHead(){
    if(tokens.size()>=2){
     return tokens.get(1).getTokenType(); 
    }
    return tokenType.EOF_TKN;
  }
  public Boolean checkEOF(){
    return tok.getTokenType()==tokenType.EOF_TKN;
  }
  
  
  
  
  
  
  
  
}