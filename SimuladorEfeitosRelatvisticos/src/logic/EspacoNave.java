package logic;

public class EspacoNave {
	private double massa;
	private double comprimento;
	private String imagem;

	public EspacoNave(double massa, double comprimento, String imagem) {
		this.massa = massa;
		this.comprimento = comprimento;
		this.imagem = imagem;
	}

	public double getMassa() {
		return massa;
	}

	public double getComprimento() {
		return comprimento;
	}
	
	public String getImagem(){
		return imagem;
	}
}
