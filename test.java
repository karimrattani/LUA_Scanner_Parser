import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
public class test{
  public static void main(String[] args){
    String filename = "interpreter.txt";
//    String filename = "interpreter.txt";
//    LexicalAnalyzer lexicalA = new LexicalAnalyzer(filename);
//    LinkedList<token> tknList = lexicalA.getTokenList();
////    Iterator iterate = tknList.iterator();
////    while(iterate.hasNext()){
////      token tkn = (token)iterate.next();
////      //System.out.println(tkn);
////    }
//   Parser parse = new Parser(filename);
//   Program prg = parse.Parse();
    
    interpreter interp = new interpreter(filename);
    interp.execute();
  }
}