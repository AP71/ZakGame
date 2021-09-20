package it.uniroma1.textadv.gameEngine;

import java.util.HashMap;
import it.uniroma1.textadv.entity.Giocatore;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.links.Link;

/**
 * Classe che consente il movimento del giocatore principale.
 * 
 */
public class Movimento {
	
	/**
	 * Rappresenta i movimenti possibili.
	 */
	public static enum Direzione
	{
		/**
		 * Direzione nord.
		 */
		NORD,
		
		/**
		 * Direzione sud.
		 */
		SUD,
		
		/**
		 * Direzione est.
		 */
		EST,
		
		/**
		 * Direzione ovest.
		 */
		OVEST
	}
	
	/**
	 * Istanza della classe.
	 */
	private static Movimento instance;
	
	/**
	 * Mappa da Stringa a Direzione
	 * @see Movimento.Direzione .
	 */
	private final HashMap<String,Direzione> DIREZIONI = new HashMap<>();
	
	/**
	 * Crea un un'istanza di tipo Movimento.
	 */
	private Movimento()
	{
		DIREZIONI.put("nord", Direzione.NORD);
		DIREZIONI.put("est", Direzione.EST);
		DIREZIONI.put("sud", Direzione.SUD);
		DIREZIONI.put("ovest", Direzione.OVEST);
	}
	
	/**
	 * Restituisce l'istanza della classe o la crea, se vuota.
	 * @return	istanza della classe.
	 */
	private static Movimento getInstance()
	{
		if (instance==null) instance = new Movimento();
		return instance;
	}
	
	/**
	 * Restituisce la direzione in base alla riga di testo passata in input.
	 * @param line	riga di testo.
	 * @return	direzione.
	 * @see Movimento.Direzione .
	 */
	public static Direzione getDirection(String line)
	{
		return getInstance().DIREZIONI.get(line);
	}

	/**
	 * Muove il giocatore nella direzione passata in input
	 * @param <T>	Link/Room presente nella direzione indicata.
	 * @param dir	direzione.
	 * @see	Movimento.Direzione .
	 */
	public static <T> void vai(Direzione dir) {
		T t;
		switch(dir) 
		{
			case NORD:
				t = (T) Giocatore.getInstance().getRoom().getNord();
				if (t!=null) elaboraMovimento(t);
				else System.out.println(Traduttore.get("[vai1]"));
				break;
			case SUD:
				t = (T) Giocatore.getInstance().getRoom().getSud();
				if (t!=null) elaboraMovimento(t);
				else System.out.println(Traduttore.get("[vai2]"));
				break;
			case OVEST:
				t = (T) Giocatore.getInstance().getRoom().getOvest();
				if (t!=null) elaboraMovimento(t);
				else System.out.println(Traduttore.get("[vai3]"));
				break;
			case EST:
				t = (T) Giocatore.getInstance().getRoom().getEst();
				if (t!=null) elaboraMovimento(t);
				else System.out.println(Traduttore.get("[vai4]"));
				break;
			
		}
	}
	
	/**
	 * Effettua il trasferimento da una stanza all'altra mediante la stanza stessa o mediante un link.
	 * @param <T>	Link/Room presente nella direzione indicata.
	 * @param t	Link/Room che deve elaborare il movimento.
	 */
	private static <T> void elaboraMovimento(T t)
	{
		if (t.getClass().equals(Room.class)) Giocatore.getInstance().getRoom().muovi(((Room) t));
		else ((Link) t).muovi();
		
	}
	
}
