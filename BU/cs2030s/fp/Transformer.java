package cs2030s.fp;

public interface Transformer<T, U> { 

  /**
   * This class implements a Transformer interface.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  public abstract U transform(T t);
}
