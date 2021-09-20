package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Link non trovato.
 * @see java.lang.Exception
 *
 */
public class LinkNotFoundException extends Exception 
{

	/**
	 * Istanzia un errore.
	 */
	public LinkNotFoundException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[LINKNF]");
	}
}
