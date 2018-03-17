import java.util.LinkedList;
import java.util.Iterator;
public class parser{
  LexicalAnalyzer lexArr;
  LinkedList<token> tokens;
  token tok;
  public parser(String filename){
    assert(filename.equals(""));
    lexArr = new LexicalAnalyzer(filename);
    tokens = lexArr.getTokenList();
    tok=tokens.getFirst();
  }
  
  private Program Parse(){
    if(this.tok.getTokenType()!=tokenType.FUNCTION_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected Function on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.IDEN_TKN){
      throw new IllegalArgumentException("Expected Id on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    
    this.block();//for <block>
    
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected End on line "+this.tok.getRowNumber()); 
    }
    return null;
  }

  private void block(){
    if(!this.isStatement(tok.getTokenType())){
       throw new IllegalArgumentException("Expected Statement on line "+this.tok.getRowNumber()); 
    }
    this.statement();
    
    token nextTok = this.lookAHead();
    if(this.isStatement(nextTok.getTokenType())){
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
        //assignment
      }else if(this.tok.getTokenType()==tokenType.WHILE_STATEMENT_TKN){
        //while
      }else if(this.tok.getTokenType()==tokenType.PRINT_STATEMENT_TKN){
        //print
        
      }else if(this.tok.getTokenType()==tokenType.REPEAT_STATEMENT_TKN){
        //repeat
        
      }else{
        throw new IllegalArgumentException("Expected Statement on line "+this.tok.getRowNumber()); 
      }
    
  }
  
  private void ifStatement(){
    
    this.boolean_expression();
    
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.THEN_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected THEN on line "+this.tok.getRowNumber()); 
    }
    
    this.nextToken();
    
    this.block();
    this.nextToken();
    
    if(this.tok.getTokenType()!=tokenType.ELSE_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected ELSE on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    this.block();
    this.nextToken();
     if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line "+this.tok.getRowNumber()); 
    }
    
  }
  private void whileStatement(){
    this.boolean_expression();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.DO_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected DO on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.END_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected END on line "+this.tok.getRowNumber()); 
    }
  }
  private void assignmentStatement(){
    
  }
  private void printStatement(){
    if(this.tok.getTokenType()!=tokenType.LEFT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ( on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    this.arithmetic_exp();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.RIGHT_PAREN_TKN){
      throw new IllegalArgumentException("Expected ) on line "+this.tok.getRowNumber()); 
    }
    
  }
  private void repeatStatement(){
    this.block();
    this.nextToken();
    if(this.tok.getTokenType()!=tokenType.UNTIL_STATEMENT_TKN){
      throw new IllegalArgumentException("Expected UNTIL on line "+this.tok.getRowNumber()); 
    }
    this.nextToken();
    this.boolean_expression();
    
  }
  private void boolean_expression(){
    
  }
  private void arithmetic_exp(){
    
  }
  
  
  private void nextToken(){
    if(!tokens.isEmpty()){
     this.tokens.pop();
     this.tok=tokens.getFirst();
    }
    
  }
  
  private token lookAHead(){
    return lexArr.getTokenList().get(1);
  }
  
  
  
  
  
  
  
  
  
}