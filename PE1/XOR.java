/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
class XOR extends Operation<Boolean> implements Evaluatable<Boolean> { 
  private Evaluatable b1;
  private Evaluatable b2;

  public XOR(Evaluatable e1, Evaluatable e2) { 
    super(e1, e2);
    this.b1 = e1;
    this.b2 = e2;
  }

  public Boolean eval() throws InvalidOperandException { 
    Object o1 = b1.eval();
    Object o2 = b2.eval();
    if (!(o1 instanceof Boolean) || !(o2 instanceof Boolean)) { 
        throw new InvalidOperandException('^');
    } else { 
        Boolean i1 = (Boolean) o1;
        Boolean i2 = (Boolean) o2;
        return i1 ^ i2;
    }
  }
}
