import java.util.Scanner;

public class BMI {
  public static void main(String... args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Zadej svou vysku v metrech: ");
    double vyska = sc.nextDouble();
    System.out.print("Zadej svou vahu v kilogramech: ");
    double vaha = sc.nextDouble();

    double BMI = vaha / (vyska * vyska);
    System.out.println("Tvoje BMI je " + BMI);
  }
}

