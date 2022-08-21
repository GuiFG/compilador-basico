#include <stdio.h>
int main(){
	double opcao;
	double a;
	double b;
	double c;
	bool l1;
	bool l2;
	double i;
	double j;
	char t1[];
	char t2[];
	scanf("%lf", a);
	scanf("%lf", b);
	a = 1+2*3/b;
	t1 = "ola tudo bom"+"42";
	opcao = 3;
	printf("%lf", a);
	printf("%lf", b);
	if (a>b) {
		printf("%lf", a);
	} else {
		printf("%lf", b);
	}
	if (t1==t2) {
		printf("%s", t1);
	}
	if (a==b) {
		if (t1==t2) {
			if (b==c) {
				printf("%lf", a);
			} else {
				printf("%lf", b);
			}
		}		printf("%s", t1);
	}
	while (a==c) {
		printf("%lf", b);		if (t1!=t2) {
			printf("%s", t1);			break;
		} else {
			scanf("%lf", a);
		}		if (a==c) {
			printf("%lf", b);			continue;
		} else {
			printf("%lf", c);
		}
	}
	if (l2!=l1) {
		scanf("%s", l1);
	}
	switch (opcao) {		case 1:			printf("%lf", b);			break;		case 5:			scanf("%s", t1);		default:			printf("%lf", a);	}
	for (int i = 3; i <= 27; i += 5) {
		printf("%lf", c);		if (a==c) {
			printf("%lf", b);			continue;
		} else {
			printf("%lf", c);
		}		for (int j = 3; j <= 27; j += 5) {
			printf("%lf", a);			if (a==b) {
				continue;
			} else {
				printf("%lf", b);				break;
			}			scanf("%lf", c);
		}
	}
}