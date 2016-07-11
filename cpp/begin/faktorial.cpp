#include <iostream>
using namespace std;

int main()
{
  cout << "Zadejte cislo: ";
  int n;
  cin >> n;

  int res = 1;
  for (int i = 1; i <= n; i++)
  {
    res *= i;
  }
  cout << "Faktorial " << n << " je " << res << endl;
}

Scanner sc = new Scanner(System.in);
System.out.println("Zadejte cele cislo: ");
int n = sc.nextInt();
int res = 1;
for (int i = 1; i <= n; i++) {
  res *= i;
}
System.out.println("Faktorial " + n + " je " + res);
