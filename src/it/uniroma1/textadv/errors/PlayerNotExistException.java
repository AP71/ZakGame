package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Player inesistente.
 * @see java.lang.Exception
 *
 */
public class PlayerNotExistException extends Exception
{
	/**
	 * Istanzia un errore.
	 */
	public PlayerNotExistException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[PNE]");
	}
}
