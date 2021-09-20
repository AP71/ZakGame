package it.uniroma1.textadv.links;


import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.objects.*;

/**
 * Rappresenta la classe della botola.
 */
public class Botola extends Link 
{
	/**
	 * Istanzia una botola con il nome passato in input.
	 * @param nome	nome della botola.
	 */
	public Botola(String nome) 
	{
		super(nome);
	}
	
	/**
	 * Apre il link se possibile.
	 */
	@Override
	public void apriLink()
	{
		if (chiave!=null && getStato()==false)
		{
			if (Giocatore.getInstance().hasKey(chiave))
			{
				setStato(true);
				System.out.println(Traduttore.get("[Link aperto]"));
				
			}
			else System.out.println(Traduttore.get("[no chiave/bloccato]"));
		}
		else System.out.println(Traduttore.get("[Link aperto]"));
	}
	
	/**
	 * Stampa a video la descrizione della botola.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Botola]"));
		
	}
}
