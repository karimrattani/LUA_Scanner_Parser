public class Block{
  Block blk;
  Statement statement;
  
  
  public Block(Block blk,Statement statement){
    this.blk=blk;
    this.statement=statement;
  } 
  
  public Block(Statement statement){
    this.statement=statement;
  } 
  
}