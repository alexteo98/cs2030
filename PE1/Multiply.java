/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
class Multiply extends Operation<Integer> implements Evaluatable<Integer> { 
  private Evaluatable op1;
  private Evaluatable op2;

  public Multiply(Evaluatable e1, Evaluatable e2) { 
    super(e1, e2);
    this.op1 = e1;
    this.op2 = e2;
  }

  @Override
  public Integer eval() throws InvalidOperandException { 
    Object o1 = this.op1.eval();
    Object o2 = this.op2.eval();
    if (!(o1 instanceof Integer) || !(o2 instanceof Integer)) { 
        throw new InvalidOperandException('*');
    } else { 
        Integer i1 = (Integer) o1;
        Integer i2 = (Integer) o2;
        return i1 * i2;
    }
  }
}
