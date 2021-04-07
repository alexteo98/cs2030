/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
abstract class Operation<T> { 
  private Operand op1;
  private Operand op2;

  public static <S> Operation<S> of(char c, Operand<S> op1, Operand<S> op2) {  
    if (c == '*') { 
      if (op1 instanceof Operand<Integer> && op2 instanceof Operand<Integer>) { 
        return new Multiply(op1, op2);
      } else { 
        throw new InvalidOperandException('*'); 
      }
    } else if (c == '+') {  
      if (op1 instanceof Operand<String> && op2 instanceof Operand<String>) { 
        return new Concatenate(op1, op2);
      } else { 
        throw new InvalidOperandException('+');   
      }
    } else if (c == '^') { 
      if (op1 instanceof Operand<Boolean> && op2 instanceof Operand<Boolean>) { 
        return new XOR(op1, op2);
      } else { 
        throw new InvalidOperandException('^');   
      }  
    } else { 
      return null;
    }
  }
}
