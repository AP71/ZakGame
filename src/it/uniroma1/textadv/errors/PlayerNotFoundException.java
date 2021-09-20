package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Giocatore non trovato.
 * @see java.lang.Exception
 *
 */
public class PlayerNotFoundException extends Exception 
{
	/**
	 * Istanzia un errore.
	 */
	public PlayerNotFoundException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[PNF]");
	}
}
