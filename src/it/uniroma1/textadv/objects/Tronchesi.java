package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe delle tronchesi.
 *
 */
public class Tronchesi extends Attrezzo
{
	/**
	 * Istanzia delle tronchesi con il nome ed un oggetto con cui può interagire.
	 * @param <T>	tipo dell'oggetto con cui può interagire.
	 * @param nome	nome delle tronchesi.
	 * @param interactWith	oggetto con cui può interagire.
	 */
	public <T> Tronchesi(String nome, T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Stampa a video la descrizione delle tronchesi.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Tronchesi]"));
	}
}
