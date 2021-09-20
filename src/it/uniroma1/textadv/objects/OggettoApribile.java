package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.entity.Giocatore;

/**
 * Rappresenta la classe di un oggetto apribile.<p>
 * La classe non può essere istanziata.
 *
 */
public abstract class OggettoApribile extends Oggetto{

	/**
	 * Stato dell'oggetto(true=aperto, false=chiuso).
	 */
	private boolean stato = false;
	
	/**
	 * Oggetto necessario per aprire l'oggetto stesso.
	 */
	private Oggetto apribileCon = null;
	
	/**
	 * Istanzia un oggetto apribile con il nome ed un oggetto che può contenere.
	 * @param <T>	tipo dell'oggetto che può contenere.
	 * @param nome	nome dell'oggetto apribile.
	 * @param interactWith	oggetto che può contenere.
	 */
	public <T> OggettoApribile(String nome, T interactWith) {
		super(nome,interactWith);
	}

	/**
	 * Istanzia un oggetto con il nome passato in input.
	 * @param nome	nome dell'oggetto apribile.
	 */
	public OggettoApribile(String nome) {
		super(nome);
	}
	
	/**
	 * Se non è necessario nulla per aprire l'oggetto lo apre, altrimenti stampa a video un messaggio.
	 */
	public void apri()
	{
		if (apribileCon!=null)
		{
			System.out.println(Traduttore.get("[apriOggetto1]"));
		}
		else
		{
			stato = true;
			if (interactsWith!=null)	rilascia();
		}
	}
	
	/**
	 * Se l'oggetto passato in input corrisponde con l'oggetto necessario apre l'oggetto apribile, altrimenti stampa a video un messaggio.
	 * @param apribileCon	oggetto con cui aprire l'oggetto apribile.
	 */
	public void apriCon(Oggetto apribileCon)
	{
		if (apribileCon.equals(this.apribileCon))
		{
			stato = true;
			if (interactsWith!=null)	rilascia();
			System.out.println(toString() + Traduttore.get("[apriOggetto3]"));
			
		}
		else
		{
			System.out.println(Traduttore.get("[apriOggetto2]"));
		}
	}
	
	/**
	 * Rilascia all'interno della stanza l'oggetto contenuto all'interno dell'oggetto apribile.
	 */
	private void rilascia()
	{
		getRoom().add(interactsWith);
		try {
			((Oggetto) interactsWith).setRoom(getRoom());
		} catch (EntityIsAlreadyInAnotherRoomException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restituisce al giocatore principale l'oggetto passato in input se uguale all'oggetto contenuto all'interno dell'istanza.
	 * @param <T>	tipo dell'oggetto da restiuire.
	 * @param oggetto	oggetto da restituire.
	 */	
	public <T> void dai(T oggetto) {
		if (interactsWith.equals(oggetto))
		{
			Giocatore.getInstance().aggiungiAdInventario(oggetto);
			interactsWith = null;
		}
	}
	
	
	/**
	 * Prende in in input un oggetto e lo imposta come oggetto necessario per aprire l'oggetto apribile.
	 * @param apribileCon	oggetto necessario per aprire l'oggetto apribile.
	 */
	public void setApribileCon(Oggetto apribileCon)	{ this.apribileCon=apribileCon; }
			
	/**
	 * Stampa a video lo stato dell'oggetto apribile(true=aperto, false=chiuso).
	 * @return	true se l'oggetto è aperto, false altrimenti.
	 */
	public boolean getStato()	{	return stato;	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		if (stato==true)	result = 31 * result + 1;
		if (apribileCon!=null)	result = 31 * result + apribileCon.hashCode();
		return result;
	}

			
}
