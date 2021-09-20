package it.uniroma1.textadv.links;


import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.objects.*;

/**
 * Rappresenta la classe Link(collegamento tra 2 stanze).<p>
 * La classe non può essere istanziata.
 * @param <T>	tipo della chiave.
 */
public abstract class Link<T> {
	
	/**
	 * Nome del link.
	 */
	private final String nome;
	
	/**
	 * Prima stanza del link.
	 */
	private Room s1;
	
	/**
	 * Seconda stanza del link.
	 */
	private Room s2;
	
	/**
	 * Stato del link(true=aperto, false=chiuso).
	 */
	private boolean stato = true;
	
	/**
	 * Chiave del link.
	 */
	protected T chiave = null;
	
	/**
	 * Istanzia un Link con il nome passato in input.
	 * @param nome	nome link.
	 */
	public Link(String nome)
	{
		this.nome = nome;
	}
	
	/**
	 * Memorizza le stanze prese in input all'interno dell'istanza.
	 * @param s1	prima stanza.
	 * @param s2	seconda stanza.
	 */
	public void setStanze(Room s1, Room s2 )
	{
		this.s1 = s1;
		this.s2 = s2;
	}
	
	/**
	 * Imposta la chiave del link e chiude il link.
	 * @param chiave	chiave del link.
	 */
	public void setChiave(T chiave)
	{
		this.chiave = chiave ;
		stato = false;
	}
	
	/**
	 * Apre il link se la chiave è null o se è stato già sbloccato.
	 */
	public void apriLink()
	{
		if (chiave!=null && stato==false)
		{
			System.out.println(Traduttore.get("[no chiave/bloccato]"));
		}
		else if (chiave==null && stato==false)
		{
			System.out.println(Traduttore.get("[Link aperto]"));
			stato = true;
		}
		else System.out.println(Traduttore.get("[Link aperto]"));

	}
	
	/**
	 * Apre il link, se chiuso, con l'oggetto passato in input.
	 * @param oggetto	oggetto usato per aprire il link.
	 */
	public void apriLinkCon(Oggetto oggetto)
	{
		if (Giocatore.getInstance().hasKey(oggetto) && oggetto.equals(chiave))
		{
			System.out.println(Traduttore.get("[Link aperto]"));
			stato = true;
		}
		else System.out.println(Traduttore.get("[no oggetto]"));
	}
	
	/**
	 * Muove il giocatore dalla prima stanza alla seconda(o viceversa) se il link è aperto.
	 */
	public void muovi()
	{
		if (Giocatore.getInstance().getRoom().equals(s1) || Giocatore.getInstance().getRoom().equals(s2))
		{
			if(stato==true)
			{
				Room stanza = Giocatore.getInstance().getRoom().equals(s1) ? s2 : s1;
				stanza.muovi(stanza);
			}
			else
			{
				System.out.println(Traduttore.get("[ingresso chiuso]"));
			}
		}
		else System.out.println(Traduttore.get("[link non presente]"));
	}
	
	/**
	 * Restituisce lo stato del link(true=aperto, false=chiuso).
	 * @return	true(aperto) o false(chiuso).
	 */
	public boolean getStato()	{	return stato;	}
	
	/**
	 * Imposta lo stato del link con il valore passato in input.
	 * @param stato	true(aperto) o false(chiuso).
	 */
	protected void setStato(boolean stato)	{	this.stato = stato;	}

	/**
	 * Stampa a video la descrizione del link.
	 */
	protected abstract void descrivi();
	
	/**
	 * Restituisce il nome del link.
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
