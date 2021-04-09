/*
 * CS2030S PE1 Question 2
 * AY20/21 Semester 2
 *
 * @author A0221444R
 */
public class EmptyList<T> implements SourceList<T> {

  public EmptyList() {  }

  public T getFirst() {
    return null;
  }

  public SourceList<T> getRest() {
    return new EmptyList<T>();
  }

  @Override
  public String toString() {
    return "EmptyList";
  }

  // Write your code here
  public int length() { 
    return 0;
  }

  public SourceList<T> filter(BooleanCondition<? super T> predicate) { 
      return new EmptyList<>();
  }
  public <R> SourceList<R> map(Transformer<? super T, ? extends R> tr) { 
      return new EmptyList<>();
  }

  public boolean equals(Object s) { 
    if (s instanceof EmptyList) { 
      return true; 
    } else { 
      return false;
    }
  }
}
