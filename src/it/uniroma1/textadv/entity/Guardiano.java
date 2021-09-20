package it.uniroma1.textadv.entity;

import java.util.List;

import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.objects.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.errors.*;

/**
 * Rappresenta la classe del guardiano.
 * @param <T>	tipo degli elementi presenti nell'inventario.
 */
public class Guardiano<T> extends Personaggio{
	
	/**
	 * Istanzia un oggetto di tipo Guardiano.
	 * @param nome	nome del guardiano.
	 * @param guardia	oggetti custoditi dal guardiano.
	 */
	public Guardiano(String nome,List<T> guardia) {
		super(nome,guardia);
	}
	
	/**
	 * Restituisce true se il guardiano possiede il gatto, false altrimenti.
	 * @return	true o false.
	 */
	private boolean hasGatto()
	{
		Room stanzaGatto = ((Gatto)getInventario().get(1)).getRoom();
		return stanzaGatto!=null && stanzaGatto.equals(getRoom());
	}
	
	/**
	 * Da il tesoro al giocatore principale.
	 */
	public void daiTesoro()
	{
		if (hasGatto())
		{
			Tesoro tesoro = (Tesoro) this.getInventario().get(0);
			rimuoviDaInventario(tesoro);
			Giocatore.getInstance().aggiungiAdInventario(tesoro);
		}
		else
		{
			System.out.println(Traduttore.get("[daiTesoro]"));
		}
	}
	
	/**
	 * Il personaggio subisce un incantesimo, muore e rilascia il tesoro.
	 * @param oggetto1	incantesimo
	 */
	public void muori(T oggetto1) {
		if(oggetto1.getClass().equals(Incantesimo.class))
		{
			try {
				getRoom().deleteFromRoom(this);
				System.out.println(Traduttore.get("[Morte guardiano]"));
			} catch (EntityNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Riceve un oggetto.
	 * @param	oggetto	oggetto da ricevere.
	 */
	@Override
	public void ricevi(Object oggetto) {
		if (oggetto.getClass().equals(Gatto.class) && oggetto.equals(getInventario().get(1)))
		{
			try {
				((Gatto) oggetto).setRoom(getRoom());
				getRoom().add(oggetto);
				System.out.println(Traduttore.get("[ricevi]") + oggetto.toString());
			} catch (EntityIsAlreadyInAnotherRoomException e) {
				e.printStackTrace();
			}
		}
		else
		{
			getInventario().add(oggetto);
			try {
				((Oggetto) oggetto).setRoom(getRoom());
			} catch (EntityIsAlreadyInAnotherRoomException e) {
				e.printStackTrace();
			}
		}
	}

	
}
