package snake;

public enum Direcao {

	CIMA(0),
	ESQUERDA(1),
	BAIXO(2), 
	DIREITA(3);
	
	private final int codigo;
	
	private Direcao(int codigo) {
		
		this.codigo = codigo;		
	}
	
	
}
