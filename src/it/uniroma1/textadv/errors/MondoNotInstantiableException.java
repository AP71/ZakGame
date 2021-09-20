package it.uniroma1.textadv.errors;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Mondo non istanziabile.
 * @see java.lang.Exception
 *
 */
public class MondoNotInstantiableException extends Exception
{

	/**
	 * Istanzia un errore.
	 */
	public MondoNotInstantiableException() {	}
	
	/**
	 * Restituisce il testo dell'errore.
	 * @return	testo sotto forma di stringa
	 */
	@Override
	public String toString() {
		return super.toString() + Traduttore.get("[MNI]");
	}
}
