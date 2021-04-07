/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
public abstract class Operation<T> implements Evaluatable<T> { 
  private Evaluatable op1;
  private Evaluatable op2;

  public static Operation of(char c, Evaluatable op1, Evaluatable op2) { 
    if (c == '*') { 
        return new Multiply(op1, op2);
    } else if (c == '+') { 
        return new Concatenate(op1, op2);
    } else if (c == '^') { 
        return new XOR(op1, op2);
    } else { 
        return null;
    }
  }

  public Operation(Evaluatable op1, Evaluatable op2) { 
      this.op1 = op1;
      this.op2 = op2;
  }

  public T eval() { 
   return null;   
  }
}
