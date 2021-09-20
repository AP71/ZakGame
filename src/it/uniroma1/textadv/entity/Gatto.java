package it.uniroma1.textadv.entity;

/**
 * 
 * Rappresenta l'oggetto Gatto.
 *
 */
public class Gatto extends Animale {

	/**
	 * Istanzia un oggetto di tipo Gatto.
	 * @param nome	nome del gatto.
	 */
	public Gatto(String nome) 
	{
		super(nome);
	}
	
	/**
	 * Verso del gatto.
	 */
	@Override
	public void reagisci() {
		System.out.println(this.nome + ": miao? frrrrr?");
	}
	
}
