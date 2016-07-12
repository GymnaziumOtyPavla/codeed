public class Soucet {
  public static void main(String... args) {
    double[] prvky = { 2, 3, 5, 7, 11, 13, 17, 19 };
    double res = 0;
    for (int i = 0; i < prvky.length; i++) {
      res += prvky[i];
    }
    System.out.println(res);
  }
}
