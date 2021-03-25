import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;

class Main  { 
    public static void main(String[] args)  { 
        Producer<Integer> p = () ->  { 
            System.out.println("zero called!");
            return 0;
        };
        InfiniteList<Integer> zero =  InfiniteList.generate(p);
        System.out.println(zero);

        zero.head();
        System.out.println(zero);

        zero.tail().head();
        System.out.println(zero);
       // InfiniteList<Integer> twos = one.map(x -> 2 * x);
       // System.out.println(one);
       // System.out.println(twos);
    }
}
