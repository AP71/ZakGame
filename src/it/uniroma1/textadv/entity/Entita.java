package it.uniroma1.textadv.entity;

import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.luoghi.*;

/**
 * Rappresenta l'oggetto Entita.<p>
 * La classe non pu� essere istanziata.
 *
 */
public abstract class Entita{
	
	/**
	 * Nome dell'entit�.
	 */
	protected final String nome;
	
	/**
	 * Stanza in cui � presente l'entit�
	 */
	private Room stanza = null;
	
	/**
	 * Istanzia un oggetto di tipo Entita.
	 * @param nome	nome dell'entit�.
	 */
	public Entita(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Imposta la stanza in cui � presente l'entit�.
	 * @param stanza	stanza in cui � presente l'entit�.
	 * @throws EntityIsAlreadyInAnotherRoomException entit� gi� presente in un'altra stanza.
	 * @see #muoviEntita(Room)
	 */
	public void setRoom(Room stanza) throws EntityIsAlreadyInAnotherRoomException
	{
		if (this.stanza==null || stanza==null) muoviEntita(stanza);
		else throw new EntityIsAlreadyInAnotherRoomException();
	}	
	
	/**
	 * Restituisce la stanza in cui � presente l'entit�.
	 * @return	stanza in cui � presente l'entit�.
	 */
	public Room getRoom() {	return stanza; }
	
	/**
	 * Sposta l'entit� in un'altra stanza.
	 * @param stanza nuova stanza.
	 */
	public void muoviEntita(Room stanza) {	this.stanza = stanza; }
	
	/**
	 * Restituisce il nome dell'entit�.
	 * @return nome dell'entit�.
	 */
	@Override
	public String toString() { return nome; }

	
	/**
	 * Restituisce il valore hashCode dell'istanza.
	 * @return hashCode dell'oggetto.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + nome.hashCode();
		if (stanza!=null)	result = 31 * result + stanza.hashCode();
		return result;
	}
	
	/**
	 * Restituisce true se l'oggetto passato in input � uguale all'istanza che ha invocato il metodo.
	 * @param	obj	oggetto da controllare.
	 * @return	true se l'istanza e l'oggetto sono uguale, false altrimenti.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)	return true;
		if (obj == null)	return false;
		if (this.hashCode() == obj.hashCode())	return true;
		if (!getClass().equals(obj.getClass()))	return false;
		if (!this.toString().equals(obj.toString()))	return false;
		return false;
	}
}
