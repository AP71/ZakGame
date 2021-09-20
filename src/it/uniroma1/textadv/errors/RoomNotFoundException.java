package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Stanza non trovata.
 * @see java.lang.Exception
 *
 */
public class RoomNotFoundException extends Exception 
{
	/**
	 * Istanzia un errore.
	 */
	public RoomNotFoundException() {}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[RNF]");
	}
}
