#include <stdio.h>
int main(){
	double a;
	double b;
	char t1[];
	scanf("%lf", a);
	scanf("%lf", b);
	a = 1+2*3/b;
	printf("%lf", a);
	printf("%lf", b);
	if (a>b) {
		printf("%lf", a);
	} else {
		printf("%lf", b);
	}
	if (t1==a) {
		printf("%s", t1);
	}

	if (a==b) {
		if (t1==a) {
			if (b==t1) {
				printf("%lf", a);
			} else {
				printf("%lf", b);
			}
		}
		printf("%s", t1);
	}

	while (a==c) {
		printf("%lf", b);		if (t1==a) {
			printf("%s", t1);
		} else {
			scanf("%lf", a);
		}
	}
}