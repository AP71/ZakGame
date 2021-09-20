package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;
/**
 * Entità non trovata.
 * @see java.lang.Exception
 *
 */
public class EntityNotFoundException extends Exception
{
	/**
	 * Istanzia un errore.
	 */
	public EntityNotFoundException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[ENF]");
	}
}
