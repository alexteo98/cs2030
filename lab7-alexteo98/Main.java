import cs2030s.fp.InfiniteList;
import cs2030s.fp.Producer;

class Main  { 
    public static void main(String[] args)  { 
        Producer<Integer> p = () ->  { 
            System.out.println("zero called!");
            return 0;
        };
        InfiniteList<Integer> zeros =  InfiniteList.generate(p);
        System.out.println(zeros);
        System.out.println(zeros.head());
        System.out.println(zeros);
        System.out.println(zeros.tail().head());
        System.out.println(zeros);
    }
}
