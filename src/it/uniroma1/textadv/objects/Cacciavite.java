package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe del cacciavite.
 *
 */
public class Cacciavite extends Attrezzo
{
	/**
	 * Istanzia un cacciavite con il nome passato in input.
	 * @param nome	nome del cacciavite.
	 */
	public Cacciavite(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione del cacciavite.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Cacciavite]"));
		
	}
}
