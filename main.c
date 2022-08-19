#include <stdio.h>
int main(){
	double opcao;
	double a;
	double b;
	double c;
	bool l1;
	bool l2;
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
		}
		printf("%s", t1);
	}

	while (a==c) {
		printf("%lf", b);		if (t1!=t2) {
			printf("%s", t1);
		} else {
			scanf("%lf", a);
		}
	}
	if (l2!=l1) {
		scanf("%s", l1);
	}

	switch (opcao) {		case 1:			printf("%lf", b);		case 5:			scanf("%s", t1);		case outrocaso:			printf("%lf", a);	}
}