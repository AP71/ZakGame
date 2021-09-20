package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Rappresenta la classe del martello.
 *
 */
public class Martello extends Attrezzo
{
	/**
	 * Istanzia un martello con il nome passato in input.
	 * @param nome	nome del martello.
	 */
	public Martello(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione del martello.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Martello]"));
		
	}
}
