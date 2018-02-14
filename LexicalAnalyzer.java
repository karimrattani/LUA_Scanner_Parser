import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
  public class LexicalAnalyzer{
  ArrayList<token> tokenList;
  ArrayList<String> keywords;
  public LexicalAnalyzer(String filename){
    assert(filename!=null); //check if empty filename
    
    //add all special keywords into array, keywords are define in text file with name keywords
    keywords = new ArrayList<String>();
    try{
      Scanner keywordFile = new Scanner(new File("keywords.txt"));
      while(keywordFile.hasNext()){
       keywords.add(keywordFile.nextLine()); 
      }
      keywordFile.close();
    }catch(Exception e){
     System.out.println(e);
     System.exit(0);
    }
    
    //get all the tokens, compute it by line, and put it in token array.
    tokenList = new ArrayList<token>();
    int LineNumber = 0;
    
    try{
      Scanner scan = new Scanner(new File(filename));
      while(scan.hasNext()){
        String line = scan.nextLine();
        LineNumber++;
        //processLine        
      }
      scan.close();
    }catch(Exception e){
      System.out.println(e);
      System.exit(0);
    }
   
   
  }
  
  private void computeLine(int LineNumber, String line){
    assert(LineNumber>=0);
    assert(line!=null);
    int index=0;
    index=removeWhiteSpaces(line,index);
    while(index<line.length()){
      String lexeme = getLexeme(line,index);
      lexeme=lexeme.toLowerCase();
      tokenType tokt = getTokenType(lexeme,LineNumber);
      index+=lexeme.length();
      index+=removeWhiteSpaces(line,index);
    }
  }
  
  private String getLexeme(String line, int index){
    assert(line!=null && index>=0);
    int end=index;
    while(end<line.length() && !isWhiteSpace(line.charAt(index))){
      end++;
    }
    String lex=line.substring(index,end);
    
    return lex;
    
    
  }
  
  private tokenType getTokenType(String lexeme, int LineNumber){
    assert(lexeme!=null && LineNumber>=0);
    tokenType tok = null;
    if(is_alphabet(lexeme.charAt(0))){
      if(lexeme.length()==1){
       //identifier 
      }else if(true){
        
      }
      //either special keyword or identifier
      
    }else if(is_digit(lexeme.charAt(0))){
      //integer
      //compare if all lexeme are digit, if not error
      
    }else if(lexeme.charAt(0)=='+'){
      //arithmetic operator
      
    }else if(lexeme.charAt(0)=='-'){
      //arithmetic operator
      
    }else if(lexeme.charAt(0)=='*'){
      //arithmetic operator
      
    }else if(lexeme.charAt(0)=='/'){
      //arithmetic operator
      
    }else if(lexeme.charAt(0)=='<'){
      //relative op
      
    //}else if(lexeme.charAt(0)=='<='){
      //relative op
      
    }else if(lexeme.charAt(0)=='>'){
      //relative op
      
  //  }else if(lexeme.charAt(0)=='>='){
      //relative op
      
    }else if(lexeme.charAt(0)=='='){
      //assignment op
      
  //  }else if(lexeme.charAt(0)=='=='){
      //relative op
      
   // }else if(lexeme.charAt(0)=='~='){
      //relative op
      
    }else{
     //invalid 
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
    if(c==' '){
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
    if(c>=0 && c<=9){
     return true; 
    }
    return false;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
}