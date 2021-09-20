package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Linea non trovata.
 * @see java.lang.Exception
 *
 */
public class LineNotFoundException extends Exception
{
	/**
	 * Istanzia un errore.
	 */
	public LineNotFoundException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("LNF");
	}
}
