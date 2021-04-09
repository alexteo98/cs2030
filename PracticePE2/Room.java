import java.util.ArrayList;

class Room { 
  private String name = "";
  private ArrayList<Tickable> arr = new ArrayList<>();

  public Room(String s) { 
    this.name = s;
  }

  public Room(String s, ArrayList<Tickable> arr) { 
    this.name = s;
    this.arr = arr;
  }

  public Room add(Tickable t) { 
    ArrayList<Tickable> newArr = new ArrayList<>(this.arr);
    newArr.add(t);
    return new Room(this.name, newArr);
  }

  public Room tick() { 
      ArrayList<Tickable> newArr = new ArrayList<>();;

      for (int i = 0;i<this.arr.size();i++) { 
          newArr.add(this.arr.get(i).tick());
      }

      return new Room(this.name, newArr);

  }

  @Override
  public String toString() { 
    String s = String.format("@%s", this.name);
    for (int i = 0; i < this.arr.size(); i++) { 
        s = s + this.arr.get(i).toString();
    }
    return s;
  }
}
