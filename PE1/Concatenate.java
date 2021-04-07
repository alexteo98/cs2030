/**
 * CS2030S PE1 Question 1
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
class Concatenate extends Operation<String> implements Evaluatable<String> { 
  public Evaluatable s1;
  public Evaluatable s2;

  public Concatenate(Evaluatable e1, Evaluatable e2) { 
    super(e1, e2);
    this.s1 = e1;
    this.s2 = e2;
  }

  @Override
  public String eval() throws InvalidOperandException { 
    Object o1 = this.s1.eval();
    Object o2 = this.s2.eval();
    if (!(o1 instanceof String) || !(o2 instanceof String)) { 
      throw new InvalidOperandException('+');
    } else { 
      String i1 = (String) o1;
      String i2 = (String) o2;
      return i1 + i2;
    }
  }

}
