package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.errors.*;

/**
 * Rappresenta la classe dell'oggetto.<p>
 * La classe non può essere istanziata.

 * @param <T>	tipo dell'oggetto con cui può interagire.
 */
public abstract class Oggetto<T>
{
	/**
	 * Nome dell'oggetto.
	 */
	protected final String nome;
	
	/**
	 * Oggetto con cui può interagire.
	 */
	protected T interactsWith;	
	
	/**
	 * Stanza in cui è presente l'oggetto.
	 */
	protected Room stanza = null; 
	
	/**
	 * Istanzia un oggetto con il nome passato in input.
	 * @param nome	nome dell'oggetto.
	 */
	public Oggetto(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Istanzia un oggetto con il nome e con un oggetto con cui può interagire.
	 * @param nome	nome dell'oggetto.
	 * @param interactsWith	oggetto con cui può interagire.
	 */
	public Oggetto(String nome, T interactsWith)
	{
		this.nome = nome;
		this.interactsWith = interactsWith;
	}
	
	/**
	 * Restituisce l'oggetto con cui può interagire.
	 * @return	oggetto con cui può interagire.
	 */
	public T getInteractWith() {	return interactsWith; }
	
	/**
	 * Imposta l'oggetto con cui può interagire.
	 * @param interactsWith	oggetto con cui può interagire.
	 */
	public void setInteractWith(T interactsWith)  { this.interactsWith = interactsWith;  }
	
	/**
	 * Imposta la stanza in cui è presente l'oggetto.
	 * @param stanza	stanza in cui è presente l'oggetto.
	 * @throws EntityIsAlreadyInAnotherRoomException	Eccezione : l'oggetto si trova già in un'altra stanza.
	 */
	public void setRoom(Room stanza) throws EntityIsAlreadyInAnotherRoomException
	{
		if (this.stanza==null || stanza==null) 
		{
			this.stanza=stanza;
		}
		else throw new EntityIsAlreadyInAnotherRoomException();
	}
	
	/**
	 * Restituisce la stanza in cui si trova l'oggetto.
	 * @return	stanza in cui si trova l'oggetto.
	 */
	public Room getRoom() {	return stanza; }
	
	/**
	 * Restituisce il nome dell'oggetto.
	 */
	@Override
	public String toString() { return nome; }

	/**
	 * Stampa a video la descrizione dell'oggetto.
	 */
	protected abstract void descrivi();
	
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
	 * Restituisce true se l'oggetto passato in input è uguale all'istanza che ha invocato il metodo.
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
