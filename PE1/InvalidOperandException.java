/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */

class InvalidOperandException extends java.lang.RuntimeException { 

  String errorMsg;

  public InvalidOperandException(char c) { 
    super(String.format("ERROR: Invalid operand for operator %c", c)); 
    this.errorMsg = String.format("ERROR: Invalid operand for operator %c", c);
  }

  public String getMessage() { 
    return this.errorMsg;
  }

}
