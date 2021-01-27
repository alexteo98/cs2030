class Point {

  private double x;
  private double y;
  
  Point(double x, double y) {
    this.x = x;
    this.y = y;
  }
  
  double distance(Point otherpoint) {
    double dispX = this.x - otherpoint.x;
    double dispY = this.y - otherpoint.y;
    return Math.sqrt(dispX * dispX + dispY * dispY);
  }
  
  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
