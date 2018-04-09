import java.util.ArrayList;
import java.util.Iterator;
public class Block{
  ArrayList<Statement> arr;
  public Block(Statement statement){
    arr = new ArrayList<Statement>();
    arr.add(statement);
  } 
  public void add(Statement statement){
    arr.add(statement);
  }
  public void process(){
   Iterator<Statement> iterate = this.arr.iterator();
   while(iterate.hasNext()){
    iterate.next().evaluate(); 
   }
  }
  public int size(){
   return this.arr.size(); 
  }
}