# CS 4308 – Concepts of Programming Languages
# LUA_Scanner_Parser

## Course Project
The project for this course is the development of an interpreter of a language implemented in any of
the following programming languages: C , C + + , Java, Python, or Ada.

## Interpreter Project
This project consists of developing an interpreter for a minimal form (subset) of the Lua
language. This minimal form of Lua has only 1 data type, integer, and the only identifiers are
single letters. Lua is not case sensitive. The specification of the grammar is given below.

The interpreter will process a Lua program and build some intermediate data structures. These data
structures will then be interpreted to execute the program. All tokens in this language are separated by
white space. The parsing algorithm should detect any syntactical or semantic error. The first such
error discovered should cause an appropriate error message to be printed, and then the interpreter
should terminate. Run-time errors should also be detected with appropriate error messages being
printed.

## Deliverables (see course schedule for due dates):
1. Module_3 – 1 st Deliverable
Develop a complete scanner. Write a short report describing the work performed. Include the
source program, input and output. You must show the execution of this program by using
several relevant source lines as input, the program must show a list of the tokens scanned.

2. Module_5 – 2 nd Deliverable
Develop a complete parser that executes with the scanner. You must show the execution of
this program by using several relevant source lines as input, the program must show the
corresponding statement recognized. Write a short report describing the work performed.
Include the source program, input and output.

3. Module_7 – 3 rd Deliverable
Develop a complete interpreter that includes the scanner and parser. You must show the
execution of this program by using a relevant source line as input, the program must show the
results after executing the statement recognize by the parser. Write a short report describing
the work performed. Include the source program, input and output.


## Grammar for the (subset of Lua) language

![alt text](https://image.ibb.co/naT1K7/Screenshot_from_2018_04_14_12_19_33.png)

id → letter

literal_integer → digit literal_integer | digit

assignment_operator → =

le_operator → <=

lt_operator → <

ge_operator → >=

gt_operator → >

eq_operator → = =

ne_operator → ~=

add_operator → +

sub_operator → -

mul_operator → *

div_operator → /

Download Lua http://www.lua.org/download.html
Reference Manual http://www.lua.org/manual/5.1/manual.html
