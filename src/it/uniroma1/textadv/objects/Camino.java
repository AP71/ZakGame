package it.uniroma1.textadv.objects;

import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe del camino.
 *
 */
public class Camino extends OggettoApribile
{	
	/**
	 * Istanzia un camino con il nome passato in input.
	 * @param nome	nome del camino.
	 */
	public Camino(String nome) {
		super(nome);
	}
	
	/**
	 * Istanzia un camino con il nome ed un oggetto che può nascondere all'interno.
	 * @param <T>	tipo dell'oggetto che può nascondere.
	 * @param nome	nome del camino.
	 * @param interactsWith	oggetto che può nascondere.
	 */
	public <T> Camino(String nome, T interactsWith)
	{
		super(nome,interactsWith);
	}
	
	/**
	 * Spegne il camino con l'oggetto passato in input, se possibile.
	 * @param <T>	tipo dell'oggetto con cui spegenre il camino.
	 * @param oggetto	oggetto con cui spegenere il camino.
	 */
	public <T> void spegni(T oggetto) {
		
		if (getStato()==false)
		{
			if (oggetto.getClass().equals(Secchio.class))
			{
				if (((Secchio) oggetto).getStato()==true)
				{
					super.apri();
					setInteractWith(null);
					System.out.println(Traduttore.get("[Camino spento1]"));
				}
				else System.out.println(Traduttore.get("[Camino secchio]"));
			}
			else System.out.println(Traduttore.get("[Camino errore]"));
		}
		else System.out.println(Traduttore.get("[Camino spento2]"));
		
		
	}
	
	/**
	 * Stampa a video la descrizione del camino.
	 */
	@Override
	public void descrivi() {
		if (super.getStato()) System.out.println(nome + Traduttore.get("[contiene]")+getInteractWith().toString()+".");
		System.out.println(Traduttore.get("[Camino]"));
		
	}

	
}
