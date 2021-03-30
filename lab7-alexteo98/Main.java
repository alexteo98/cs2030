import cs2030s.fp.BooleanCondition;
import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;
import cs2030s.fp.Transformer;
import java.util.Random;

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

BooleanCondition<Integer> lessThan10 = x -> {
      System.out.println("    filter x < 10: " + x);
      return x < 10;
    };

//InfiniteList l = InfiniteList.iterate(1,  x -> x + 1).filter(x -> x % 2 == 0);

//System.out.println(l.head());

//InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 0).isEmpty()

//InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 2).isEmpty()

InfiniteList<Integer> l = InfiniteList.iterate(0, incr);
InfiniteList l1= l.takeWhile(lessThan10);
InfiniteList l2= l1.filter(isEven);

//l2.head();

//System.out.println(l3.head());
System.out.println(l);
System.out.println(l1);
System.out.println(l2);
//System.out.println("l2 head");
//System.out.println(l2.tail().head());
System.out.println(l2.count());
//System.out.println(l1.toList());
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
