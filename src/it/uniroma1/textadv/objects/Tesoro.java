package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe del tesoro.
 *
 */
public class Tesoro extends Oggetto
{
	/**
	 * Istanzia un tesoro con il nome passato in input.
	 * @param nome	nome del tesoro.
	 */
	public Tesoro(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione del tesoro. 
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Tesoro]"));
	}
}
