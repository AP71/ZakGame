package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Rappresenta la classe dell'incantesimo.
 *
 */
public class Incantesimo extends Oggetto{

	/**
	 * Istanzia un incantesimo con il nome passato in input.
	 * @param nome	nome dell'incantesimo.
	 */
	public Incantesimo(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione dell'incantesimo.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Incantesimo]"));
		
	}
}
