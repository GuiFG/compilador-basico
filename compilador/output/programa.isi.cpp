#include <iostream>
using namespace std;
int main(){
	double a;
	double b;
	double c;
	double d;
	cout << "Programa Teste";
	cout << "Digite A";
	cin >> a;
	cout << "Digite B";
	cin >> b;
	if (a<b) {
		c = a+b;
	} else {
		c = a-b;
	}
	cout << "C e igual a ";
	cout << c;
	d = c*a+b;
	cout << "D e igual a ";
	cout << d;
}