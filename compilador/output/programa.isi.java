import java.util.Scanner;
public class Program {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		double a, b, c, d;		System.out.println("Programa Teste");
		System.out.println("Digite A");
		a = scanner.nextDouble();
		System.out.println("Digite B");
		b = scanner.nextDouble();
		if (a<b) {
			c = a+b;
		} else {
			c = a-b;
		}
		System.out.println("C e igual a ");
		System.out.println(c);
		d = c*a+b;
		System.out.println("D e igual a ");
		System.out.println(d);
	}}