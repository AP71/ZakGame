package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe dell'armadio.
 *
 */
public class Armadio extends OggettoApribile
{
	
	/**
	 * Istanzia un armadio con il nome ed un oggetto che può contenere.
	 * @param <T>	tipo dell'oggetto che può contenere.
	 * @param nome	nome dell'armadio.
	 * @param interactWith	oggetto che può contenere.
	 */
	public <T> Armadio(String nome, T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Stampa a video la descrizione dell'armadio.
	 */
	@Override
	public void descrivi() {
		if (super.getStato()) System.out.println(nome + Traduttore.get("[contiene]")+getInteractWith().toString()+".");
		else System.out.println(Traduttore.get("[Armadio]"));
		
	}
}
