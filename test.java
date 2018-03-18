import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
public class test{
  public static void main(String[] args){
    //String filename = "test_list.txt";
    String filename = "parser_list_test.txt";
    LexicalAnalyzer lexicalA = new LexicalAnalyzer(filename);
    LinkedList<token> tknList = lexicalA.getTokenList();
    Iterator iterate = tknList.iterator();
    while(iterate.hasNext()){
      token tkn = (token)iterate.next();
      //System.out.println(tkn);
    }
   Parser parse = new Parser(filename);
  }
}