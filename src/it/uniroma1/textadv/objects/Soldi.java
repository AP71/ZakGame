package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Rappresenta la classe dei soldi.
 *
 */
public class Soldi extends Oggetto
{
	/**
	 * Istanzia i soldi con il nome passato in input.
	 * @param nome	nome dei soldi.
	 */
	public Soldi(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione dei soldi.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Soldi]"));
	}
}
