import java.util.LinkedList;
public class interpreter{
  Parser parse;
  public interpreter(String filename){
    parse = new Parser(filename);
  }
  public void execute(){
    while(!this.parse.checkEOF()){
      parse.Parse();
    }
  }
 
  
  
  
  
  
  
  
  
  
}