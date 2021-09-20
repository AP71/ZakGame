package it.uniroma1.textadv.entity;
/**
 * Rappresenta l'oggetto Animale.<p>
 * La classe non può essere istanziata.
 */
public abstract class Animale extends Entita{

	/**
	 * Istanzia un oggetto di tipo Animale.
	 * @param nome	nome da dare all'animale.
	 */
	public Animale(String nome) {
		super(nome);
	}

	/**
	 * Verso dell'animale.
	 */
	public abstract void reagisci();

	
}
