package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Player già esistente.
 * @see java.lang.Exception
 *
 */
public class PlayerAlreadyExistException extends Exception
{
	
	/**
	 * Istanzia un errore.
	 */
	public PlayerAlreadyExistException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[PAE]");
	}
}
