import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Polynomial p = new Polynomial();
		System.out.print("p = ");
		p.print();
		System.out.println("p(3) = " + p.evaluate(3));

		Polynomial q = new Polynomial(new double[1], new int[1]);
		System.out.print("q = ");
		q.print();
		System.out.println("q(3) = " + q.evaluate(3));

		Polynomial p1 = new Polynomial(new double[] { 6, 5 }, new int[] { 0, 3 });
		System.out.print("p1 = ");
		p1.print();

		Polynomial p2 = new Polynomial(new double[] { -2, -9, 5, 3, 2, 1 }, new int[] { 1, 4, 0, 2, 9, 4 });
		System.out.print("p2 = ");
		p2.print();

		/*
		 * Polynomial file = new Polynomial(new File("file.txt"));
		 * System.out.print("file = "); file.print();
		 */

		Polynomial s = p1.add(p2);
		System.out.print("s = p1 + p2 = ");
		s.print();
		System.out.println("s(0.32) = " + s.evaluate(0.32));
		System.out.println("s(-0.32) = " + s.evaluate(-0.32));
		if (s.hasRoot(1))
			System.out.println("1 is a root of s");
		else
			System.out.println("1 is not a root of s");

		Polynomial s1 = p1.add(new Polynomial(new double[] { -5, -6 }, new int[] { 3, 0 }));
		System.out.print("s1 = ");
		s1.print();
		System.out.println("s1(453) = " + s1.evaluate(453));

		Polynomial m = p1.multiply(p2);
		System.out.print("m = p1 * p2 = ");
		m.print();
		System.out.println("m(0.1) = " + m.evaluate(0.1));
		System.out.println("m(-0.1) = " + m.evaluate(-0.1));
		System.out.println("m(0.1345) = " + m.evaluate(0.1345));
		System.out.println("m(0.9876541) = " + m.evaluate(0.9876541));
		System.out.println("m(-1.5436634) = " + m.evaluate(-1.5436634));
		if (m.hasRoot(1))
			System.out.println("1 is a root of m");
		else
			System.out.println("1 is not a root of m");
		if (m.hasRoot(-1))
			System.out.println("-1 is a root of m");
		else
			System.out.println("-1 is not a root of m");
		m.saveToFile("output.txt");

		Polynomial m1 = p1.multiply(q);
		System.out.print("m1 = p1 * q = ");
		m1.print();
		System.out.println("m1(0.1) = " + m1.evaluate(0.1));
		System.out.println("m1(-0.1) = " + m1.evaluate(-0.1));
		System.out.println("m1(0.1345) = " + m1.evaluate(0.1345));
		System.out.println("m1(0.9876541) = " + m1.evaluate(0.9876541));
		System.out.println("m1(-1.5436634) = " + m1.evaluate(-1.5436634));
	}
}