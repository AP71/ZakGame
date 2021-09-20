package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe della chiave.
 *
 */
public class Chiave extends Oggetto
{

	/**
	 * Istanzia una chiave con il nome e con un oggetto con cui può interagire.
	 * @param <T>	tipo dell'oggetto con cui può interagire.
	 * @param nome	nome della chiave.
	 * @param interactWith	oggetto con cui può interagire.
	 */
	public <T> Chiave(String nome,T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Stampa a video la descrizione della chiave.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Chiave]"));
		
	}
}
