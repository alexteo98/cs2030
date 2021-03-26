import cs2030s.fp.BooleanCondition;
import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;

class Main  { 
    public static void main(String[] args)  { 
        Producer<Integer> p = () ->  { 
            System.out.println("zero called!");
            return 0;
        };
BooleanCondition<Integer> isEven = x -> {
      System.out.println("    filter x % 2 == 0: " + x);
      return x % 2 == 0;
    };

Transformer<Integer, Integer> incr = x -> {
     System.out.println("    iterate: " + x);
     return x + 1;
   };
Transformer<Integer, Integer> doubler = x -> {
     System.out.println("    map x * 2: " + x);
     return x * 2;
  };

BooleanCondition<Integer> moreThan5 = x -> {
      System.out.println("    filter x > 5: " + x);
      return x > 5;
    };

//InfiniteList l = InfiniteList.iterate(1,  x -> x + 1).filter(x -> x % 2 == 0);

//System.out.println(l.head());

InfiniteList l = InfiniteList.iterate(1, incr).map(doubler).filter(moreThan5).filter(isEven);

l.tail().head();


//System.out.println(InfiniteList.iterate(1,  x -> x + 1).filter(x -> x % 2 == 0));
       // InfiniteList l = InfiniteList.iterate(1, incr).filter(isEven);
       // l.tail().head();
       // System.out.println(l);



/*        InfiniteList<Integer> zero =  InfiniteList.generate(p);
        System.out.println(zero);

        zero.head();
        System.out.println(zero);

        zero.tail().head();
        System.out.println(zero);
       // InfiniteList<Integer> twos = one.map(x -> 2 * x);
       // System.out.println(one);
       // System.out.println(twos);
       // */
    }
}
