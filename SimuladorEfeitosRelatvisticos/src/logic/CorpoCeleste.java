package logic;

public class CorpoCeleste {

	private double distanciaATerra;
	private double velocidadeOrbital;
	private String imagem;

	public CorpoCeleste(double distancia, double velocidade, String imagem) {
		distanciaATerra = distancia;
		velocidadeOrbital = velocidade;
		this.imagem = imagem;
	}

	public double getDistancia() {
		return distanciaATerra;
	}

	public double getVelocidade() {
		return velocidadeOrbital;
	}

	public String getImagem() {
		return imagem;
	}

}
