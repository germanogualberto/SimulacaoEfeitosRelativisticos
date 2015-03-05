package logic;

import java.util.ArrayList;

public class Viagem {

	private static double SEGUNDOS_A_DIA = 1./(60.*60.*24.);
	private static double DEZANONA = 1000000000.;
	private static EspacoNave NAVE = new EspacoNave(8.6, 3.3,
			"/figuras/orion.png");
//	private static CorpoCeleste TERRA = new CorpoCeleste(0, 29.78*DEZA3,
//			"/figuras/terra.png");

	private static ArrayList<CorpoCeleste> getCorposCelestes() {

		//TODO converter medidas
		// metros e segundos
		ArrayList<CorpoCeleste> corpos = new ArrayList<CorpoCeleste>();
		corpos.add(new CorpoCeleste(77.3, 47.3, "/figuras/mercurio.png"));// Mércurio
		corpos.add(new CorpoCeleste(38.2, 35.02, "/figuras/venus.png"));// Vênus
		corpos.add(new CorpoCeleste(0.378, 1.022, "/figuras/lua.png"));// Lua
		corpos.add(new CorpoCeleste(55.7, 24.07, "/figuras/marte.png"));// Marte
		corpos.add(new CorpoCeleste(588.5, 13.06, "/figuras/jupiter.png"));// Júpiter
		corpos.add(new CorpoCeleste(1195.5, 9.68, "/figuras/saturno.png"));// Saturno
		corpos.add(new CorpoCeleste(2581.9, 6.8, "/figuras/urano.png"));// Urano
		corpos.add(new CorpoCeleste(4305.9, 5.43, "/figuras/netuno.png"));// Netuno
		
		return corpos;
	}

	// 0 < velocidade < 1 representa uma fração de C
	public static ArrayList<String> getDadosViagem(int indiceCorpo,
			double velocidade) {

		ArrayList<String> dados = new ArrayList<String>();
		CorpoCeleste corpo = getCorposCelestes().get(indiceCorpo);
		double tempoNaNave, tempoNaTerra, tempoNoDestino, energiaRel;
		
		tempoNaNave = (corpo.getDistancia()*DEZANONA) / (velocidade * ModuloFisica.C);
		tempoNaTerra = ModuloFisica.dilatacaoDoTempo(tempoNaNave, (velocidade * ModuloFisica.C));
		tempoNoDestino = ModuloFisica.dilatacaoDoTempo(tempoNaTerra, corpo.getVelocidade());
		energiaRel = ModuloFisica.energiaRelativistica(NAVE.getMassa(), (velocidade * ModuloFisica.C));

		dados.add(corpo.getImagem());// imagem
		dados.add(velocidade + "c");// Velocidade
		dados.add(corpo.getDistancia() + "");// distancia
		dados.add(tempoNaNave*SEGUNDOS_A_DIA + "");// Tempo na Nave
		dados.add(tempoNaTerra*SEGUNDOS_A_DIA+ "");//Tempo na Terra
		dados.add(tempoNoDestino*SEGUNDOS_A_DIA + "");//Tempo no Destino
		dados.add(ModuloFisica.contracaoDoComprimento(NAVE.getComprimento(), (velocidade * ModuloFisica.C)) + "");//Comprimento Contraido
		dados.add(ModuloFisica.massaRelativistica(NAVE.getMassa(), (velocidade * ModuloFisica.C)) + "");//Massa Relativistica
		dados.add(energiaRel + "");//Energia relativistica
		dados.add(energiaRel*tempoNaNave + "");//Energia gasta

		return dados;
	}

	public static ArrayList<String> getDadosVeiculo() {
		ArrayList<String> dados = new ArrayList<String>();
		dados.add("" + NAVE.getMassa());
		dados.add("" + NAVE.getComprimento());
		return dados;
	}

}
