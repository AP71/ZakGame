package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe del cassetto.
 *
 */
public class Cassetto extends OggettoApribile
{
	
	/**
	 * Istanzia un cassetto con il nome ed un oggetto che può contenere.
	 * @param <T>	tipo dell'oggetto che può contenere.
	 * @param nome	nome del camino.
	 * @param interactWith	oggetto che può contenere.
	 */
	public <T> Cassetto(String nome, T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Stampa a video la descrizione del cassetto.
	 */
	@Override
	public void descrivi() {
		if (super.getStato()) System.out.println(nome + Traduttore.get("[contiene]")+getInteractWith().toString()+".");
		else System.out.println(Traduttore.get("[Cassetto]"));
		
	}
}
