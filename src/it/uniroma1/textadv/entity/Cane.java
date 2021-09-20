package it.uniroma1.textadv.entity;

/**
 * 
 * Rappresenta l'oggetto Cane.
 *
 */
public class Cane extends Animale{

	/**
	 * Istanzia un oggetto di tipo Cane.
	 * @param nome	nome del cane.
	 */
	public Cane(String nome)
	{
		super(nome);
	}
	
	/**
	 * Verso del cane.
	 */
	@Override
	public void reagisci() {
		System.out.println(this.nome + ": woof, woof!");	
	}
}
