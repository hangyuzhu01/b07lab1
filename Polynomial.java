public class Polynomial {
	double[] coefficients;

	public Polynomial() {
		coefficients = new double[1];
	}

	public Polynomial(double[] array) {
		coefficients = new double[array.length];

		for (int i = 0; i < array.length; i++) {
			coefficients[i] = array[i];
		}
	}

	public Polynomial add(Polynomial polynomial) {
		Polynomial result;

		if (coefficients.length > polynomial.coefficients.length) {
			result = new Polynomial(coefficients);

			for (int i = 0; i < polynomial.coefficients.length; i++) {
				result.coefficients[i] += polynomial.coefficients[i];
			}
		} else {
			result = new Polynomial(polynomial.coefficients);

			for (int i = 0; i < coefficients.length; i++) {
				result.coefficients[i] += coefficients[i];
			}
		}

		return result;
	}

	public double evaluate(double x) {
		double result = 0;

		for (int i = 0; i < coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, i);
		}

		return result;
	}

	public boolean hasRoot(double x) {
		return this.evaluate(x) == 0;
	}
}
