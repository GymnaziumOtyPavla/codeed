import java.util.Scanner;

public class Faktorial {
  public static void main(String... args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Zadejte cele cislo: ");
    int n = sc.nextInt();
    int res = 1;
    for (int i = 1; i <= n; i++) {
      res *= i;
    }
    System.out.println("Faktorial " + n + " je " + res);
  }
}
