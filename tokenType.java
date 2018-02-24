public enum tokenType{
    PROGRAM_TKN,BLOCK_TKN,IDEN_TKN,STATEMENT_TKN,
    IF_STATEMENT_TKN, THEN_STATEMENT_TKN, ELSE_STATEMENT_TKN, WHILE_STATEMENT_TKN, DO_STATEMENT_TKN, FOR_STATEMENT_TKN, FUNCTION_STATEMENT_TKN, END_STATEMENT_TKN, UNTIL_STATEMENT_TKN,PRINT_STATEMENT_TKN,REPEAT_STATEMENT_TKN,//keywords
    ASSIGNMENT_STATEMENT_TKN,//id op assignment
    BOOLEAN_EXPR_TKN,//relative op arithmatic exp arithmatic exp
    ASSIGNMENT_OP_TKN,//=
    ARITH_EXPR_TKN,//expression
    RELATIVE_OP_TKN,//greater than, less than, equal, not equal, less than or equal, greater than or equal
    ARITH_OP_TKN,//+,-,*,/
    LITERAL_INTEGAR_TKN,//digits
    LEFT_PAREN_TKN,//(
    RIGHT_PAREN_TKN,//)
    INVALID_TKN,  
    
    
      //all grammar from the doc
    
}