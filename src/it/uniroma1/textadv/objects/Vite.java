package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.entity.Giocatore;

/**
 * Rappresenta la classe della vite.
 *
 */
public class Vite extends Oggetto
{
	
	/**
	 * Istanzia una vite con il nome ed un oggetto con cui può interagire.
	 * @param <T>	tipo dell'oggetto con cui può interagire.
	 * @param nome	nome della vite.
	 * @param interactWith	oggetto con cui può interagire.
	 */
	public <T> Vite(String nome, T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Se l'oggetto passato in input è un Cacciavite, smonta la vite.
	 * @param <T>	tipo dell'oggetto passato in input.
	 * @param attrezzo	ogetto passato in input.
	 */
	public <T> void smonta(T attrezzo) {
		if (attrezzo.getClass().equals(Cacciavite.class))
			Giocatore.getInstance().aggiungiAdInventario(this);
	}
	
	/**
	 * Stampa a video la descrizione della vite.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Vite]"));
	}

	

}
