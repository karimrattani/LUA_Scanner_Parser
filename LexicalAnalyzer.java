import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
public class LexicalAnalyzer{
  LinkedList<token> tokenList;
  ArrayList<String> keywords;
  ArrayList<String> errors;
  HashMap<String, tokenType> hashMap= new HashMap<String, tokenType>();
  public LexicalAnalyzer(String filename){
    assert(filename!=null); //check if empty filename
    
    //add all special keywords into array, keywords are define in text file with name keywords
    keywords = new ArrayList<String>();
    errors = new ArrayList<String>();
    try{
      Scanner keywordFile = new Scanner(new File("keywords.txt"));
      while(keywordFile.hasNext()){//add keyword and tokentype associated with it.
        String line=keywordFile.nextLine();
        int index=removeWhiteSpaces(line,0);
        String key = separateKeyword(line, index);
        key.toLowerCase();
        index+=key.length()+1;//get keyword length and add one space
        String tokTypStr=line.substring(index,line.length());
        tokenType tokTyp= tokenType.valueOf(tokTypStr.toUpperCase());
        hashMap.put(key,tokTyp);
      }
      keywordFile.close();
    }catch(Exception e){
      System.out.println(e);
      System.exit(0);
    }
    
    //get all the tokens, compute it by line, and put it in token array.
    tokenList = new LinkedList<token>();
    int LineNumber = 0;
    
    try{
      Scanner scan = new Scanner(new File(filename));
      while(scan.hasNext()){
        String line = scan.nextLine();
        computeLine(LineNumber, line);  
        LineNumber++;
      }
      scan.close();
    }catch(Exception e){
      System.out.println(e);
      System.exit(0);
    }
    
    
  }
  
  private void computeLine(int LineNumber, String line){//check each line and get lexeme, and token using helper method
    assert(LineNumber>=0);
    assert(line!=null);
    int index=0;
    index=removeWhiteSpaces(line,index);
    while(index<line.length()){
      String lexeme = getLexeme(line,index);
      lexeme=lexeme.toLowerCase();
      tokenType tokt = getTokenType(lexeme,LineNumber);
      index+=lexeme.length();
      index=removeWhiteSpaces(line,index);
      tokenList.add(new token(LineNumber,lexeme,tokt));
    }
  }
  
  private String getLexeme(String line, int index){
    assert(line!=null && index>=0);
    int end=index;
    while(end<line.length() && !isWhiteSpace(line.charAt(end))){
      end++;
    }
    String lex=line.substring(index,end);
    
    return lex;
    
    
  }
  private String separateKeyword(String line, int index){
    assert(line!=null && index>=0);
    int end=index;
    while(end<line.length() && !isWhiteSpace(line.charAt(end))){
      end++;
    }
    String keyword=line.substring(index,end);
    return keyword;
    
    
  }
  
  private tokenType getTokenType(String lexeme, int LineNumber){
    assert(lexeme!=null && LineNumber>=0);
    tokenType tok = null;
    
    if(is_alphabet(lexeme.charAt(0))){
      if(lexeme.length()==1){
        //identifier 
        tok=tokenType.IDEN_TKN;
      }else if(hashMap.containsKey(lexeme)){
        tok=hashMap.get(lexeme);
      }else{
        errors.add("Invalid lexeme:"+lexeme+" on line "+LineNumber); 
      }
      //either special keyword or identifier
      
    }else if(is_digit(lexeme.charAt(0))){//check all the digits
      int index=1;
      boolean checkDigit = true;
      while(index<lexeme.length()){
        if(!(is_digit(lexeme.charAt(index)))){
          checkDigit=false;
          break;
        }
        index++;
      }
      if(checkDigit){
        tok=tokenType.LITERAL_INTEGAR_TKN; 
      }else{
        errors.add("Invalid lexeme:"+lexeme+" on line "+LineNumber); 
      }
      //integer
      //compare if all lexeme are digit, if not error
      
    }
    if(tok==null && lexeme.length()==1){//check for one length operators if token is not detected yet
      if((lexeme.charAt(0)=='+' || lexeme.charAt(0)=='-' || lexeme.charAt(0)=='*' || lexeme.charAt(0)=='/')){//check arithmetic operator
        
        tok=tokenType.ARITH_OP_TKN;
        
      }else if((lexeme.charAt(0)=='<' || lexeme.charAt(0)=='>')){//check relatives 1 char
        //relative op
        tok=tokenType.RELATIVE_OP_TKN;
        
      }else if(lexeme.charAt(0)=='='){
        //assignment op
        tok=tokenType.ASSIGNMENT_OP_TKN;
      }else if(lexeme.charAt(0)=='('){
        
        //left parenthesis
        tok=tokenType.LEFT_PAREN_TKN;
      }else if(lexeme.charAt(0)==')'){
        
        //right parenthesis
        tok=tokenType.RIGHT_PAREN_TKN;
      }}
    if(tok==null && lexeme.length()==2){
      if(((lexeme.charAt(0)=='<' && lexeme.charAt(1)=='=') || (lexeme.charAt(0)=='>' && lexeme.charAt(1)=='=') || (lexeme.charAt(0)=='=' && lexeme.charAt(1)=='=') || (lexeme.charAt(0)=='~' && lexeme.charAt(1)=='='))){//check relatives 2 char
        //relative op
        
        tok=tokenType.RELATIVE_OP_TKN;
      }
    }
    if(tok==null){
      tok=tokenType.INVALID_TKN; 
    }
    return tok;
    
  }
  
  
  private int removeWhiteSpaces(String line,int index){
    assert(line!=null && index>=0);
    while(index<line.length() && isWhiteSpace(line.charAt(index))){
      index++;
    }
    return index;
    
    
  }
  
  private boolean isWhiteSpace(char c){
    if(c==' ' || c=='\t'){
      return true; 
    }
    return false;
  }
  
  private boolean is_alphabet(char c){
    if(c>='a' && c<='z'){
      return true; 
    }
    return false;
  }
  
  
  
  private boolean is_digit(char c){
    if(c>='0' && c<='9'){
      return true; 
    }
    return false;
  }
  
  public LinkedList<token> getTokenList(){
    if(!errors.isEmpty()){
      this.printErrors();
      throw new IllegalArgumentException("Invalid Lexeme");
    }
    return this.tokenList; 
  }
  
  public void printErrors(){
   Iterator iterate = errors.iterator();
   while(iterate.hasNext()){
    String err = (String)iterate.next();
    System.err.println(err);
   }
   
  }

  
}