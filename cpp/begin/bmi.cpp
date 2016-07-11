#include <iostream>
using namespace std;

int main()
{
  cout << "Zadej svou vysku v metrech: ";
  float vyska;
  cin >> vyska;

  cout << "Zadej svou vahu v kilogramech: ";
  float vaha;
  cin >> vaha;

  float BMI = vaha / (vyska * vyska);

  cout << "Tvoje BMI je " << BMI << endl;

  return 0;
}
