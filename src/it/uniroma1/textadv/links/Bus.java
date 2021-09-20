package it.uniroma1.textadv.links;


import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.objects.*;
import it.uniroma1.textadv.errors.*;

/**
 * Rappresenta la classe del bus.
 */
public class Bus extends Link 
{
	/**
	 * Contatore EE.
	 */
	private int contatoreEE = 0;
	
	/**
	 * Chiave EE.
	 */
	private Chiave chiaveEE;
	
	/**
	 * Istanzia un bus con il nome passato in input.
	 * @param nome	nome del bus.
	 */
	public Bus(String nome) 
	{
		super(nome);
	}
	
	/**
	 * Istaniza un bus con il nome e la chiave passata in input.
	 * @param nome	nome del bus.
	 * @param chiaveEE	chiave EE.
	 */
	public Bus(String nome,Chiave chiaveEE)
	{
		super(nome);
		this.chiaveEE=chiaveEE;
	}

	/**
	 * Muove il giocatore dalla prima stanza alla seconda(o viceversa).
	 */
	@Override
	public void muovi()
	{
		super.muovi();
		if (chiaveEE!=null)
		{
			contatoreEE++;
			if (contatoreEE==5)	
			{
				try {
					Giocatore.getInstance().aggiungiAdInventario(chiaveEE);
					chiaveEE.setRoom(null);
					chiaveEE = null;
					System.out.println(Traduttore.get("[Regalo autista]"));
				} catch (EntityIsAlreadyInAnotherRoomException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Stampa a video la descrizione del bus.
	 */
	@Override
	public void descrivi() {
		System.out.println(Traduttore.get("[Bus]"));
		
	}
	
}
