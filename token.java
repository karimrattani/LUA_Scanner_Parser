public class token{
  
  private int rowNumber;
  private int colNumber;
  private tokenType tok;
  private String lexeme;
  
  
  public token(int rowNumber, String lexeme, tokenType tokT){
    if(rowNumber<0 || lexeme==null || tokT == null || lexeme.equals("")){
      throw new IllegalArgumentException("Invalid inputs for token Lexeme:"+lexeme+" rowNumber:"+rowNumber+" TokenType:"+tokT);
    }
    this.rowNumber=rowNumber;
    this.tok=tokT;
    this.lexeme=lexeme;
  }
  
  public String getLexeme(){
   return this.lexeme; 
  }
  public int getRowNumber(){
    return this.rowNumber; 
  }
  public tokenType getTokenType(){
    return this.tok; 
  }
  
  public String toString(){
    return "Line Number:"+this.getRowNumber()+" Lexeme Value:"+this.getLexeme()+" Token Type:"+this.getTokenType();
    
    
  }
  
  
  
  
  
  
  
  
}