package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.errors.*;

/**
 * Rappresenta la classe del salvadanaio.
 *
 */
public class Salvadanaio extends OggettoApribile
{
	/**
	 * Istanzia un salvadanaio con il nome ed un oggetto che può contenere.
	 * @param <T>	tipo dell'oggetto che può contenere.
	 * @param nome	nome del salvadanaio.
	 * @param interactWith	oggetto che può contenere.
	 */
	public <T> Salvadanaio(String nome, T interactWith) {
		super(nome,interactWith);
	}
	
	/**
	 * Se l'oggetto passato in input è un attrezzo rompe il salvadanaio, altrimenti stampa a video un messaggio.
	 * @param <T>	tipo dell'oggetto passato in input.
	 * @param attrezzo	oggetto con cui rompere il salvadanaio.
	 */
	public <T> void rompi(T attrezzo)
	{
		if (attrezzo.getClass().getSuperclass().equals(Attrezzo.class))
		{
			super.apri();
			setInteractWith(null);
			System.out.println(Traduttore.get("[rottura salvadanaio]"));
			try {
				getRoom().deleteFromRoom(this);
				this.setRoom(null);
			} catch (EntityNotFoundException | EntityIsAlreadyInAnotherRoomException e) {
				e.printStackTrace();
			}
		}
		else System.out.println(Traduttore.get("[errore salvadanaio]") + attrezzo.toString());
		
	}
	
	/**
	 * Stampa a video la descrizione del salvadanaio.
	 */
	@Override
	public void descrivi() {
		if (super.getStato()) System.out.println(Traduttore.get("[Salvadanaio rotto]"));
		System.out.println(Traduttore.get("[Salvadanaio]"));
	}
}
