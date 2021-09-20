package it.uniroma1.textadv.entity;

import java.util.List;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.objects.*;


/**
 * Rappresenta la classe del venditore.
 * @param <T>	tipo degli elementi presenti nell'inventario.
 */
public class Venditore<T> extends Personaggio{
	
	/**
	 * Istanzia un oggetto di tipo Venditore.
	 * @param nome	nome del venditore.
	 * @param vende	oggetti che vende.
	 */
	public Venditore(String nome, List<T> vende) {
		super(nome,vende);
	}
	
	/**
	 * Se il venditore riceve soldi, restituisce al giocatore principale il proprio inventario.
	 */
	public void acquisto()
	{
		for(Object t:getInventario())
		{
			if (t.getClass().equals(Soldi.class))
			{
				daiInventario();
				return;
			}
		}
	}

	/**
	 * Da un oggetto al giocatore principale.
	 * @param o	oggetto da vendere.
	 */
	private void vendi(Object o) {
		Giocatore.getInstance().ricevi(o);
		System.out.println("\t"+o.toString());
		
		try {
			((Oggetto) o).setRoom(null);
		} catch (EntityIsAlreadyInAnotherRoomException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce il proprio inventario al giocatore principale.
	 */
	private void daiInventario()
	{
		System.out.println(this.nome +  Traduttore.get("[daiInventario]"));
		getInventario().stream().filter(s -> !s.getClass().equals(Soldi.class)).forEach(o -> vendi(o));
		Giocatore.getInstance().getInventario().stream().forEach(o -> this.rimuoviDaInventario(o));
	}
}
