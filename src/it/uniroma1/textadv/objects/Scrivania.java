package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe della scrivania.
 *
 */
public class Scrivania extends OggettoApribile
{
	/**
	 * Istanzia una scrivania con il nome ed un oggetto che può contenere.
	 * @param <T>	tipo dell'oggetto che la scrivania può contenere.
	 * @param nome	nome della scrivania.
	 * @param interactWith	oggetto che la scrivania può contenere.
	 */
	public <T> Scrivania(String nome, T interactWith) {
		super(nome,interactWith);
	}

	/**
	 * Stampa a video la descrizione della scrivania.
	 */
	@Override
	public void descrivi() {
		if (super.getStato() && getRoom().contains(interactsWith)) System.out.println(nome + Traduttore.get("[contiene]")+getInteractWith().toString()+".");
		else System.out.println(Traduttore.get("[Scrivania]"));
	}
}
