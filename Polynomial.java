import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

public class Polynomial {
	double[] coefficients;
	int[] exponents;

	public Polynomial() {
		// Set the polynomial to zero
		coefficients = new double[1];
		exponents = new int[1];
	}

	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];

		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
	}

	public Polynomial(File file) throws FileNotFoundException, IOException {
		BufferedReader input = new BufferedReader(new FileReader(file));
		String[] terms = input.readLine().replace("-", "+-").split("\\+");

		input.close();

		coefficients = new double[terms.length];
		exponents = new int[terms.length];

		for (int i = 0; i < terms.length; i++) {
			if (terms[i].isEmpty())
				continue;
			
			String[] term = terms[i].split("x");
			if (term.length == 2) {
				coefficients[i] = Double.parseDouble(term[0]);
				exponents[i] = Integer.parseInt(term[1]);
			} else if (term.length == 1) {
				coefficients[i] = Double.parseDouble(term[0]);
				exponents[i] = 0;
			}
		}
	}

	public Polynomial add(Polynomial polynomial) {
		int degree = 0;
		int terms = 0;
		int result_index = 0;
		double[] coefficients;
		Polynomial result;

		// Find degree of new polynomial
		for (int exponent : this.exponents) {
			if (exponent > degree) {
				degree = exponent;
			}
		}
		for (int exponent : polynomial.exponents) {
			if (exponent > degree) {
				degree = exponent;
			}
		}

		// Create array to store coefficients of the new polynomial
		coefficients = new double[degree + 1];

		// Add polynomials
		for (int i = 0; i < this.coefficients.length; i++) {
			coefficients[this.exponents[i]] += this.coefficients[i];
		}
		for (int i = 0; i < polynomial.coefficients.length; i++) {
			coefficients[polynomial.exponents[i]] += polynomial.coefficients[i];
		}

		// Find number of terms
		for (double coefficient : coefficients) {
			if (coefficient != 0) {
				terms++;
			}
		}

		// Create new polynomial
		result = new Polynomial(new double[terms], new int[terms]);
		for (int i = 0; i < coefficients.length; i++) {
			if (coefficients[i] != 0) {
				result.coefficients[result_index] = coefficients[i];
				result.exponents[result_index] = i;
				result_index++;
			}
		}

		return result;
	}

	public double evaluate(double x) {
		double result = 0;

		for (int i = 0; i < coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, exponents[i]);
		}

		return result;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}

	public Polynomial multiply(Polynomial polynomial) {
		Polynomial result = new Polynomial();
		Polynomial temp;

		for (int i = 0; i < this.coefficients.length; i++) {
			for (int j = 0; j < polynomial.coefficients.length; j++) {
				temp = new Polynomial(new double[] { this.coefficients[i] * polynomial.coefficients[j] },
						new int[] { this.exponents[i] + polynomial.exponents[j] });
				result = result.add(temp);
			}
		}

		return result;
	}

	public void saveToFile(String fileName) throws FileNotFoundException {
		PrintStream output = new PrintStream(fileName);

		for (int i = 0; i < coefficients.length; i++) {
			if (i > 0 && coefficients[i] > 0)
				output.print("+");
			if (coefficients[i] != 0 && exponents[i] == 0)
				output.print(coefficients[i]);
			else if (coefficients[i] != 0)
				output.print(coefficients[i] + "x" + exponents[i]);
		}
		output.println();
		output.close();
	}

	public void print() {
		for (int i = 0; i < coefficients.length; i++) {
			if (i > 0 && coefficients[i] > 0)
				System.out.print("+");
			if (coefficients[i] != 0 && exponents[i] == 0)
				System.out.print(coefficients[i]);
			else if (coefficients[i] != 0)
				System.out.print(coefficients[i] + "x" + exponents[i]);
		}
		System.out.println();
	}
}
