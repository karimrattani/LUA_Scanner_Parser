import java.util.ArrayList;
import java.util.Iterator;
public class test{
  public static void main(String[] args){
    String filename = "test_list.txt";
   LexicalAnalyzer lexicalA = new LexicalAnalyzer(filename);
   ArrayList<token> tknList = lexicalA.getTokenList();
   Iterator iterate = tknList.iterator();
   while(iterate.hasNext()){
    token tkn = (token)iterate.next();
    System.out.println(tkn);
   }
   
  }
}