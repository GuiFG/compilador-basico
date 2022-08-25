import java.util.Scanner;
public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double opcao;		double a, b, c;		boolean l1;		boolean l2;		double i, j;		double decimal;		String t1;		String t2;		a = scanner.nextDouble();
		b = scanner.nextDouble();
		a = 1+2*3/b;
		t1 = "ola tudo bom"+"42";
		opcao = 3;
		decimal = 3.565;
		System.out.println(a);
		System.out.println(b);
		if (a>b) {
			System.out.println(a);
		} else {
			System.out.println(b);
		}
		if (t1==t2) {
			System.out.println(t1);
		}
		if (a==b) {
			if (t1==t2) {
				if (b==c) {
					System.out.println(a);
				} else {
					System.out.println(b);
				}
			}			System.out.println(t1);
		}
		while (a==c) {
			System.out.println(b);			if (t1!=t2) {
				System.out.println(t1);				break;
			} else {
				a = scanner.nextDouble();
			}			if (a==c) {
				System.out.println(b);				continue;
			} else {
				System.out.println(c);
			}
		}
		if (l2!=l1) {
			l1 = scanner.next();
		}
		switch (opcao) {			case 1:				System.out.println(b);				break;			case 5:				t1 = scanner.nextLine();			default:				System.out.println(a);		}
		for (i = 3; i <= 27; i += 5) {
			System.out.println(c);			if (a==c) {
				System.out.println(b);				continue;
			} else {
				System.out.println(c);
			}			for (j = 3; j <= 27; j += 5) {
				System.out.println(a);				if (a==b) {
					continue;
				} else {
					System.out.println(b);					break;
				}				c = scanner.nextDouble();
			}
		}
	}}