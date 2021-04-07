/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */

class Operand<T> implements Evaluatable<T>{ 

  T operand;

  public Operand(T operand) { 
    this.operand = operand;
  }

  public T eval() { 
    return this.operand; 
  }
}
