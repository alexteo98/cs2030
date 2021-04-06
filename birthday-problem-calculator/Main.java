class Main { 
  public static void main(String[] args) { 
    System.out.println(iter(1, 1, 0.994));
  }
  static double iter(int n, double p, double target) { 
    return (p*(365-n+1)/365)>=(1-target) ? iter(n+1, p*(365-n+1)/365, target) : n ;
  }
}
