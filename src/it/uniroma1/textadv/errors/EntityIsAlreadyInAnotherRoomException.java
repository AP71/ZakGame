package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Entita è già in un'altra stanza.
 * @see java.lang.Exception
 *
 */
public class EntityIsAlreadyInAnotherRoomException extends Exception
{
	/**
	 * Istanzia un errore.
	 */
	public EntityIsAlreadyInAnotherRoomException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[EIAIAR]");
	}
}
