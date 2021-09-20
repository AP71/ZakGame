package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Rappresent la classe del pozzo.
 *
 */
public class Pozzo extends Oggetto
{
	/**
	 * Istanzia un pozzo con il nome passato in input.
	 * @param nome	nome del pozzo.
	 */
	public Pozzo(String nome) {
		super(nome);
	}
	
	/**
	 * Se l'oggetto passato in input è un secchio, lo riempie.
	 * @param <T>	tipo dell'oggetto passato in input.
	 * @param oggetto1	oggetto da riempire.
	 */
	public <T> void riempi(T oggetto1) {
		if (oggetto1.getClass().equals(Secchio.class))
		{
			((Secchio) oggetto1).riempi();
		}
		else
		{
			System.out.println(oggetto1 + Traduttore.get("[Pozzo errore]"));
		}
		
	}
	
	/**
	 * Stampa a video la descrizione del pozzo.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Pozzo]"));
		
	}

	
}
