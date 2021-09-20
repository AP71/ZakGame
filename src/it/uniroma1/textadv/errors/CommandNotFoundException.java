package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Comando non trovato.
 * @see java.lang.Exception
 *
 */
public class CommandNotFoundException extends Exception{

	/**
	 * Istanzia un errore.
	 */
	public CommandNotFoundException() {}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[CNF]");
	}
}
