package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe del secchio.
 *
 */
public class Secchio extends OggettoApribile
{
	
	/**
	 * Istanzia un secchio con il nome passato in input.
	 * @param nome	nome del secchio.
	 */
	public Secchio(String nome) {
		super(nome);
	}
	
	/**
	 * Riempie il secchio.
	 */
	public void riempi()
	{
		apri();
		System.out.println(Traduttore.get("[riempi]"));
	}
	
	/**
	 * Stampa a video la descrizione del secchio.
	 */
	@Override
	public void descrivi() {
		if (super.getStato()) System.out.println(Traduttore.get("[Secchio pieno]"));
		System.out.println(Traduttore.get("[Secchio]"));
	}
}
