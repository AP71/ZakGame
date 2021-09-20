package it.uniroma1.textadv.gameEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.Movimento.Direzione;;

/**
 * Classe che consente lo svolgimento del gioco grazie alla traduzione dei comandi.
 * @param <T>	tipo delle istanze presenti nel mondo.
 */
public class MotoreTestuale<T> {
	
	/**
	 * Lista delle istanze del mondo.
	 */
	private final List<T> instances;
	
	/**
	 * Istanzia un MotoreTestuale prendendo in ingresso le istanze del mondo di gioco.
	 * @param instances	istanze del mondo di gioco.
	 */
 	public MotoreTestuale(List<T> instances)
	{
		this.instances = instances;
	}
	
 	/**
 	 * Elabora il comando passando in input modificando il mondo di gioco.
 	 * @param line	comando.
 	 */
	public void elabora(String line)
	{
		String methodName = Traduttore.get(line.contains(" ") ? 
																					line.substring(0, line.indexOf(" ")) 
																					: line);
		List<T> objects = new ArrayList<>();
		Direzione dir = Movimento.getDirection(Traduttore.get(line.toLowerCase()));
		try
		{
			if (dir!=null)
			{
				objects.add((T) dir);
				Giocatore.class.getMethod("vai", Direzione.class).invoke(Giocatore.getInstance(), objects.get(0));
			}
			else
			{
				objects.add((T) ricerca(line));
				String newLine = objects.get(0)!=null ? 
						line.substring(0, line.indexOf(objects.get(0).toString())-1)+line.substring(line.indexOf(objects.get(0).toString())+objects.get(0).toString().length()) 
						: line;
				objects.add((T) ricerca(newLine));
				objects = objects.stream().filter(s -> s!=null).collect(Collectors.toList());
				if (objects.size()>1) scambia(objects, line);
				switch(objects.size())
				{
					case 0 -> Giocatore.class.getMethod(methodName).invoke(Giocatore.getInstance());
					case 1 -> Giocatore.class.getMethod(methodName, Object.class).invoke(Giocatore.getInstance(), objects.get(0));
					case 2 -> Giocatore.class.getMethod(methodName, Object.class, Object.class).invoke(Giocatore.getInstance(), objects.get(0),objects.get(1));
				}
			}
		}
		catch (NoSuchMethodException e)
		{
			System.out.println(new CommandNotFoundException().toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	/**
	 * Ordina le istanze nella lista passata in input secondo la line di testo.
	 * @param istanze	lista delle istanze.
	 * @param line	linea di testo
	 */
	private void scambia(List<T> istanze, String line)
	{
		int indice1 = line.indexOf(istanze.get(0).toString());
		int indice2 = line.indexOf(istanze.get(1).toString());
		if (indice2<indice1)
		{
			T oggetto = istanze.get(1);
			istanze.remove(1);
			istanze.add(0, oggetto);
		}
	}
	
	/**
	 * Restituisce l'istanza che compare nella riga di testo passata in input.
	 * @param line	linea di testo.
	 * @return	istanza.
	 */
	private T ricerca(String line)
	{
		for(T object:instances)
		{
			if (line.contains(object.toString()))	
			{
				return object;
			}
		}
		return null;
	}

	
	

}
