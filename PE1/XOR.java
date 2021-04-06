/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
class XOR extends Operation<Boolean>  { 
  private Boolean b1;
  private Boolean b2;

  public XOR(Operand<Boolean> b1, Operand<Boolean> b2) { 
    this.b1 = b1.eval();
    this.b2 = b2.eval();
  }

  public Boolean eval() { 
    return this.b1 ^ this.b2;
  }
}
