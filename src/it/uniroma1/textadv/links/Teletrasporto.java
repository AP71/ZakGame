package it.uniroma1.textadv.links;


import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.objects.*;


/**
 * Rappresenta la classe del teletrasporto.
 * 
 */
public class Teletrasporto extends Link{

	/**
	 * Istanzia un teletrasporto con il nome passato in input.
	 * @param nome	nome del teletrasporto.
	 */
	public Teletrasporto(String nome)
	{
		super(nome);
	}

	/**
	 * Muove il giocatore dalla prima stanza alla seconda(o viceversa) se il link è aperto altrimenti apre il link e muove il giocatore.
	 * @param <T>	tipo dell'oggetto per aprire il link.
	 * @param oggetto	oggetto per aprire il link.
	 */
	public <T> void usa(T oggetto) {
		if (oggetto==null && getStato()==true)	muovi();
		else if (oggetto.getClass().equals(Chiave.class))
		{
			apriLinkCon((Oggetto) oggetto);
			muovi();
		}
	}
	
	/**
	 * Stampa a video la descrizione del teletrasporto.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Teletrasporto]"));
	}
}
