import java.util.ArrayList;
public class Memory{
  private static int[] arr = new int[26];
  private static ArrayList<Integer> declaredVar = new ArrayList<Integer>();
  public static int getVal(char c){
    int val = c-97;
    //check if value exist
    if(declaredVar.contains(val)){
      return arr[val];
    }else{
     throw new IllegalArgumentException("Variable "+c+" Not Declared"); 
    }
  }
  public static void storeVal(char c, int exprVal){
    int val = c-97;
    arr[val]=exprVal;
    declaredVar.add(val);
  }
  
  
  
  
}