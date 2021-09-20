package it.uniroma1.textadv.objects;


/**
 * Rappresenta la classe degli attrezi.<p>
 * La classe non pu� essere istanziata.
 */
public abstract class Attrezzo extends Oggetto{

	/**
	 * Istanzia un attrezzo con il nome passato in input.
	 * @param nome	nome dell'attrezzo.
	 */
	public Attrezzo(String nome) {
		super(nome);
	}

	/**
	 * Istanzia un attrezzo con il nome ed un oggetto con cui pu� interagire.
	 * @param <T>	tipo dell'oggetto con cui pu� interagire.
	 * @param nome	nome dell'attrezzo.
	 * @param interactWith	oggetto con cui pu� interagire.
	 */
	public <T> Attrezzo(String nome, T interactWith) {
		super(nome,interactWith);
	}

	
}
