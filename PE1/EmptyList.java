/**
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
public class EmptyList<T> implements SourceList<T> {

  @Override
  public T getFirst() {
    return null;
  }

  @Override
  public SourceList<T> getSecond() {
    return null;
  }

  @Override
  public String toString() {
    return "EmptyList";
  }

  // Write your code here
  public int length() { 
    return 0;
  }

  public boolean equals(Object s) { 
    if (s instanceof EmptyList) { 
      return true; 
    } else { 
      return false;
    }
  }

  public SourceList<T> filter(BooleanCondition<? super T> bc) { 
    return new EmptyList();
  }
}
