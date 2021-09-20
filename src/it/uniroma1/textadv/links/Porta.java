package it.uniroma1.textadv.links;


import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.objects.*;
/**
 * Rappresenta la classe della porta.
 * 
 */
public class Porta extends Link{
	
	/**
	 * Istanzia una porta con il nome passato in input.
	 * @param nome	nome della porta.
	 */
	public Porta(String nome) {
		super(nome);
	}
	
	/**
	 * Stampa a video la descrizione della porta.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Porta]"));
	}
}
