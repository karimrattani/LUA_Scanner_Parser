import java.util.LinkedList;
import java.util.Iterator;
public class Parser{
  LexicalAnalyzer lexArr;
  LinkedList<token> tokens;
  token tok;
  public Parser(String filename){
    assert(!filename.equals(""));
    lexArr = new LexicalAnalyzer(filename);
    tokens = lexArr.getTokenList();
    tok=tokens.getFirst();
    while(tok.getTokenType()!=tokenType.EOF_TKN){
      this.Parse();
    }
    System.out.println("Parse File Correctly");
  }
  
  private void Parse(){
    if(this.tok.getTokenType()!=tokenType.FUNCTION_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected FUNCTION on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN){
      throw new IllegalArgumentException("Expected ID on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ) on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    
    this.block();
    
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
  }
  
  private void block(){
    if(!this.isStatement(tok.getTokenType())){
      throw new IllegalArgumentException("Expected STATEMENT on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.statement();
    
    tokenType nextToken = lookAHead();
    if(this.isStatement(nextToken)){
      this.nextToken();
      this.block();
    }
    
  }
  
  private boolean isStatement(tokenType tokType){
    if(tokType==tokenType.IF_STATEMENT_TKN || tokType==tokenType.IDEN_TKN || tokType==tokenType.WHILE_STATEMENT_TKN || tokType==tokenType.PRINT_STATEMENT_TKN || tokType==tokenType.REPEAT_STATEMENT_TKN)
      return true;
    return false;
    
  }
  
  private void statement(){
    if(this.tok.getTokenType()==tokenType.IF_STATEMENT_TKN){
      this.nextToken();
      this.ifStatement(); 
    }else if(this.tok.getTokenType()==tokenType.IDEN_TKN){
      this.nextToken();
      this.assignmentStatement();
    }else if(this.tok.getTokenType()==tokenType.WHILE_STATEMENT_TKN){
      this.nextToken();
      this.whileStatement();
    }else if(this.tok.getTokenType()==tokenType.PRINT_STATEMENT_TKN){
      this.nextToken();
      this.printStatement();
    }else if(this.tok.getTokenType()==tokenType.REPEAT_STATEMENT_TKN){
      this.nextToken();
      this.repeatStatement();
    }else if(!this.checkEndOfFile()){
      throw new IllegalArgumentException("Expected STATEMENT on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    
  }
  
  private void ifStatement(){
    
    this.boolean_expression();
    
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.THEN_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected THEN on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    
    this.nextToken();
    
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.ELSE_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected ELSE on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    
  }
  private void whileStatement(){
    this.boolean_expression();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.DO_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected DO on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
  }
  private void assignmentStatement(){
    if(this.tok.getTokenType()!=tokenType.ASSIGNMENT_OP_TKN){
      throw new IllegalArgumentException("Expected = on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.arithmetic_exp();
    
  }
  private void printStatement(){
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.arithmetic_exp();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ) on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    
  }
  private void repeatStatement(){
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.UNTIL_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected UNTIL on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.boolean_expression();
  }
  private void boolean_expression(){
    if(this.tok.getTokenType()!=tokenType.RELATIVE_OP_TKN){
      throw new IllegalArgumentException("Expected RELATIVE OPERATOR on line:"+this.tok.getRowNumber()+" found "+this.tok.getLexeme()); 
    }
    this.nextToken();
    this.arithmetic_exp();
    this.nextToken();
    this.arithmetic_exp();
  }
  private void arithmetic_exp(){
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN && this.tok.getTokenType()!=tokenType.LITERAL_INTEGAR_TKN && this.tok.getTokenType()!=tokenType.ARITH_OP_TKN){
      throw new IllegalArgumentException("Expected ID or INTEGAR or ARITHMETIC OPERATOR on line:"+this.tok.getRowNumber() +" found "+this.tok.getLexeme()); 
    }
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN && this.tok.getTokenType()!=tokenType.LITERAL_INTEGAR_TKN){
      this.nextToken();
      this.arithmetic_exp();
      this.nextToken();
      this.arithmetic_exp();
    }
    
    
  }
  
  
  private void nextToken(){
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
  
  
  
  
  
  
  
  
}