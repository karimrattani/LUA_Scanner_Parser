public class token{
  
  private int rowNumber;
  private int colNumber;
  private tokenType tok;
  private String lexeme;
  
  
  public token(int colNumber, int rowNumber, String lexeme, tokenType tokT){
    if(rowNumber<=0 || colNumber<=0 || lexeme==null || tokT == null || lexeme.equals("")){
      throw new IllegalArgumentException("Invalid inputs for token");
    }
    this.rowNumber=rowNumber;
    this.colNumber=colNumber;
    this.tok=tokT;
    this.lexeme=lexeme;
  }
  
  public String getLexeme(){
   return this.lexeme; 
  }
  public int getrowNumber(){
    return this.rowNumber; 
  }
  public int getColNumber(){
    return this.colNumber; 
  }
  public tokenType getTokenType(){
    return this.tok; 
  }
  
  
  
  
  
  
  
  
}