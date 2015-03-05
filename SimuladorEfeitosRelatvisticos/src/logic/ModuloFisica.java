package logic;

import java.lang.Math;

public class ModuloFisica {

	// m/s
	public static double C = 299792458;

	private static double fatorDeLorentz(double velocidade) {
		return 1 / Math.sqrt(1 - ((velocidade * velocidade) / (C * C)));
	}

	public static double dilatacaoDoTempo(double tempo, double velocidade) {
		return tempo * fatorDeLorentz(velocidade);
	}

	public static double contracaoDoComprimento(double comprimento, double velocidade) {
		return comprimento / fatorDeLorentz(velocidade);
	}

	public static double massaRelativistica(double massa, double velocidade) {
		return massa * fatorDeLorentz(velocidade);
	}

	public static double energiaRelativistica(double massa, double velocidade) {
		return massa * fatorDeLorentz(velocidade) * C * C;
	}
}