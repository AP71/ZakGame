package it.uniroma1.textadv.luoghi;

import java.util.ArrayList;
import java.lang.reflect.InvocationTargetException;
import it.uniroma1.textadv.links.*;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.gameEngine.Movimento.Direzione;
import it.uniroma1.textadv.objects.*;
import it.uniroma1.textadv.luoghi.Room;
/**
 * Rappresenta la classe della stanza.
 * 
 * @param <T>	tipo dei link/stanze comunicanti.
 */
public class Room<T> {

	/**
	 * Nome della stanza.
	 */
	private final String nome;
	
	/**
	 * Descrizione della stanza.
	 */
	private final String descrizione;
	
	/**
	 * Oggetti presenti nella stanza.
	 */
	private final ArrayList<Oggetto> oggetti = new ArrayList<>();
	
	/**
	 * Entità presenti nella stanza.
	 */
	private final ArrayList<Entita> entita = new ArrayList<>();
	
	/**
	 * Riga di testo con i nomi delle istanze comunicanti.
	 */
	private String link;
	
	/**
	 * Istanza comunicante a nord.
	 */
	private T nord = null;
	
	/**
	 * Istanza comunicante a sud.
	 */
	private T sud = null;
	
	/**
	 * Istanza comunicante ad est.
	 */
	private T est = null;
	
	/**
	 * Istanza comunicante ad ovest.
	 */
	private T ovest = null;
	
	/**
	 * Istanzia una stanza con il nome e la descrizione passati in input.
	 * @param nome	nome della stanza.
	 * @param descrizione	descrizione della stanza.
	 */
	public Room(String nome, String descrizione) { this.nome = nome; this.descrizione = descrizione; }
	
	/**
	 * Aggiunge un'istanza alla stanza.
	 * @param t	istanza da aggiungere.
	 */
	public void add(T t)
	{
		if (t.getClass().getPackageName().equals("it.uniroma1.textadv.objects")) oggetti.add((Oggetto) t);
		else if (t.getClass().getPackageName().equals("it.uniroma1.textadv.entity")) entita.add((Entita) t);
	}
	
	/**
	 * Memorizza la riga del file contenente i nomi delle istanze comunicanti.
	 * @param link riga di testo.
	 */
	public void setLink(String link) { this.link = link; }
	
	/**
	 * Restituisce la riga di testo con i nomi delle istanze comunicanti.
	 * @return riga di testo.
	 */
	public String getLink() { return link; }
	
	/**
	 * Imposta la comunicazione con il nord.
	 * @param l	Link/Room.
	 */
	public void setNord(T l) { nord = l; }
	
	/**
	 * Imposta la comunicazione con il sud.
	 * @param l	Link/Room.
	 */
	public void setSud(T l) { sud = l; }
	
	/**
	 * Imposta la comunicazione con l'est.
	 * @param l	Link/Room.
	 */
	public void setEst(T l) { est = l; }
	
	/**
	 * Imposta la comunicazione con l'ovest.
	 * @param l	Link/Room.
	 */
	public void setOvest(T l) { ovest = l; }
	
	/**
	 * Restituisce la comunicazione con il nord.
	 * @return	Link/Room.
	 */
	public T getNord() { return nord; }
	
	/**
	 * Restituisce la comunicazione con il sud.
	 * @return	Link/Room.
	 */
	public T getSud() { return sud; }
	
	/**
	 * Restituisce la comunicazione con l'est.
	 * @return	Link/Room.
	 */
	public T getEst() { return est; }
	
	/**
	 * Restituisce la comunicazione con l'ovest.
	 * @return	Link/Room.
	 */
	public T getOvest() { return ovest; }
	
	/**
	 * Rimuove un'istanza dalla stanza se presente, altrimenti lancia un'eccezione.
	 * @param instance	istanza da rimuovere.
	 * @throws EntityNotFoundException	eccezione istanza non trovata.
	 */
	public void deleteFromRoom(T instance) throws EntityNotFoundException
	{
		if (oggetti.contains(instance)) oggetti.remove(instance);
		else if (entita.contains(instance)) entita.remove(instance);
		else throw new EntityNotFoundException();
	}
	
	/**
	 * Stampa a video la descrizione della stanza.
	 */
	public void descrivi()
	{
		System.out.println(Traduttore.get("[descrivi1]") + nome);
		System.out.println(Traduttore.get("[descrivi2]") + descrizione);
		System.out.println(Traduttore.get("[descrivi3]"));
		oggetti.stream().forEach(s -> System.out.println("\t"+s));
		System.out.println(Traduttore.get("[descrivi4]"));
		entita.stream().forEach(s -> System.out.println("\t"+s));
		System.out.println(Traduttore.get("[descrivi5]"));
		if (nord!=null) System.out.println("\t"+Traduttore.get("[direzione1]")+"\t"+nord.toString());
		if (sud!=null) System.out.println("\t"+Traduttore.get("[direzione2]")+"\t"+sud.toString());
		if (est!=null) System.out.println("\t"+Traduttore.get("[direzione3]")+"\t"+est.toString());
		if (ovest!=null) System.out.println("\t"+Traduttore.get("[direzione4]")+"\t"+ovest.toString());
	}
	
	/**
	 * Restituisce true se l'istanza è presente nella stanza come oggetto/entita/link/stanza.
	 * @param t	istanza da cercare.
	 * @return	true o false.
	 */
	public boolean contains(T t)
	{
		if (t==null)	return false;
		if (t.getClass().getPackageName().equals("it.uniroma1.textadv.objects"))
			for(Oggetto oggetto:oggetti) 
				if (oggetto.equals(t)) return true;
		if (t.getClass().getPackageName().equals("it.uniroma1.textadv.entity"))
			for(Entita entita:entita) 
				if (entita.equals(t)) return true;
		if (nord!=null && checkCollegamento(nord, t))	return true;
		if (sud!=null && checkCollegamento(sud, t))		return true;
		if (est!=null && checkCollegamento(est, t))		return true;
		if (ovest!=null && checkCollegamento(ovest, t))	return true;

		return false;
	}
	
	/**
	 * Prende in input l'istanza corrispondente alla direzione e un'istanza. Restituisce true se i valori corrispondono.
	 * @param direzione	direzione(nord/sud/est/ovest).
	 * @param instance	istanza da cercare.
	 * @return	true o false.
	 */
	private boolean checkCollegamento(T direzione,T instance)
	{
		return direzione.equals(instance);
	}
	
	/**
	 * Restituisce true se la stanza contiene un'istanza del tipo passato in input.
	 * @param c	tipo da cercare.
	 * @return	true o false.
	 */
	public boolean containsClass(Class c)
	{
		for(Entita obj:entita) if (obj.getClass().equals(c)) return true;
		for(Oggetto obj:oggetti) if (obj.getClass().equals(c)) return true;
		return false;
	}
	
	/**
	 * Prende in input un'altra stanza, rimuove il giocatore dall'istanza e lo aggiunge alla nuova stanza.
	 * @param nuovaStanza	stanza in cui spostare il giocatore.
	 */
	public void muovi(Room nuovaStanza)
	{
		this.entita.remove(Giocatore.getInstance());
		nuovaStanza.add(Giocatore.getInstance());
		Giocatore.getInstance().muoviEntita(nuovaStanza);
		System.out.println(Traduttore.get("[muovi]")+Giocatore.getInstance().getRoom().toString());
	}
	
	/**
	 * Restituisce l'istanza corrispondente al tipo della classe passato in input.
	 * @param classe	tipo della classe da cercare.
	 * @return	istanza corrispondente alla classe.
	 */
	public Entita getEntityFromClass(Class classe)
	{
		for(Entita e:entita)
			if (e.getClass().equals(classe))	
				return e;
		return null;
	}
	
	/**
	 * Restituisce il nome della stanza.
	 */
	@Override
	public String toString()
	{
		return nome;
	}

	/**
	 * Restituisce il valore hashCode dell'istanza.
	 * @return hashCode dell'oggetto.
	 */
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + nome.hashCode();
		result = 31 * result + descrizione.hashCode();
		result = 31 * result + link.hashCode();
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
