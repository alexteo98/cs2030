class BoxIt<T> implements Transformer<T, Box<T>> { 

  /**
   * This class implements an Interface to Box an item.
   *
   * @author Alex Teo (Lab16A)
   * @version CS2030S AY20/21 Semester 2
   */

  public BoxIt() {
  }

  @Override
  public Box<T> transform(T t) { 
    return Box.of(t);
  }
}
