import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;

class Main  { 
    public static void main(String[] args)  { 
        Producer<Integer> p = () ->  { 
            System.out.println("zero called!");
            return 1;
        };
        InfiniteList<Integer> one =  InfiniteList.generate(p);
        System.out.println(one);
        InfiniteList<Integer> twos = one.map(x -> 2 * x);
        System.out.println(one);
        System.out.println(twos);
    }
}
