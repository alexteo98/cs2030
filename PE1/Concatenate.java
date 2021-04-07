/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
class Concatenate extends Operation<String>  { 
  private String s1;
  private String s2;

  public Concatenate(Operand<String> s1, Operand<String> s2) { 
    this.s1 = s1.eval();
    this.s1 = s2.eval();
  }

  public String eval() { 
    return this.s1 + this.s2;
  }
}
