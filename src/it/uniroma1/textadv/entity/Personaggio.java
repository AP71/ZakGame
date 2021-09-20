package it.uniroma1.textadv.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import it.uniroma1.textadv.links.*;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.gameEngine.Movimento.Direzione;
import it.uniroma1.textadv.objects.*;
import it.uniroma1.textadv.luoghi.Room.*;
/**
 * Rappresenta la classe di un personaggio.
 * @param <T>	tipo degli elementi presenti nell'inventario.
 */
public abstract class Personaggio<T> extends Entita{
	
	/**
	 * Inventario del personaggio.
	 */
	private final List<T> inventario = new ArrayList<>();
	
	/**
	 * Istanzia un oggetto di tipo Personaggio.
	 * @param nome	nome del personaggio.
	 * @param oggetti	inventario.
	 */
	public Personaggio(String nome, List<T> oggetti)
	{
		super(nome);
		oggetti.stream().forEach(inventario::add);
	}
	
	/**
	 * Istanzia un oggetto di tipo Personaggio.
	 * @param nome	nome del personaggio.
	 */
	public Personaggio(String nome)
	{
		super(nome);
	}
	
	/**
	 * Restituisce l'inventario del giocatore.
	 * @return	List.class .
	 */
	public List<T> getInventario() {	return inventario; }
	
	/**
	 * Aggiunge un elemento all'inventario.
	 * @param t	elemento da aggiungere all'inventario.
	 */
	public void aggiungiAdInventario(T t)
	{	
		try
		{
			if (t.getClass().getPackageName().equals("it.uniroma1.textadv.objects"))
			{
				inventario.add(t);	
				((Oggetto) t).setRoom(null);
				getRoom().deleteFromRoom(t);
				if (!t.getClass().equals(Incantesimo.class)) System.out.println(t.toString() + Traduttore.get("[aggiungiAdInventario1]"));
				
			}
			else if (t.getClass().getSuperclass().equals(Animale.class))
			{
				inventario.add(t);	
				((Entita) t).setRoom(null);
				getRoom().deleteFromRoom(t);
				System.out.println(t.toString() + Traduttore.get("[aggiungiAdInventario1]"));
			}
			else System.out.println(t.toString() + Traduttore.get("[aggiungiAdInventario2]"));
		}
		catch (Exception e) {
			
		}
	}
	
	/**
	 * Rimuove un elemento dall'inventario.
	 * @param t	elemento da rimuovere dall'inventario.
	 */
	public void rimuoviDaInventario(T t)	{	inventario.remove(t);	}

	/**
	 * Riceve un oggetto.
	 * @param oggetto	oggetto da ricevere.
	 */
	public void ricevi(T oggetto) {
		this.inventario.add(oggetto);
		
	}

	
	
}
