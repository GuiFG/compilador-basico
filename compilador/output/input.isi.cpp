#include <iostream>
using namespace std;
int main(){
	double opcao;	double a, b, c;	bool l1;	bool l2;	double i, j;	double decimal;	string t1;	string t2;	cin >> a;
	cin >> b;
	a = 1+2*3/b;
	t1 = "ola tudo bom"+"42";
	opcao = 3;
	decimal = 3.565;
	cout << a;
	cout << b;
	if (a>b) {
		cout << a;
	} else {
		cout << b;
	}
	if (t1==t2) {
		cout << t1;
	}
	if (a==b) {
		if (t1==t2) {
			if (b==c) {
				cout << a;
			} else {
				cout << b;
			}
		}		cout << t1;
	}
	while (a==c) {
		cout << b;		if (t1!=t2) {
			cout << t1;			break;
		} else {
			cin >> a;
		}		if (a==c) {
			cout << b;			continue;
		} else {
			cout << c;
		}
	}
	if (l2!=l1) {
		cin >> l1;
	}
	switch (opcao) {		case 1:			cout << b;			break;		case 5:			cin >> t1;		default:			cout << a;	}
	for (int i = 3; i <= 27; i += 5) {
		cout << c;		if (a==c) {
			cout << b;			continue;
		} else {
			cout << c;
		}		for (int j = 3; j <= 27; j += 5) {
			cout << a;			if (a==b) {
				continue;
			} else {
				cout << b;				break;
			}			cin >> c;
		}
	}
}