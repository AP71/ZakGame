package it.uniroma1.textadv.entity;

import java.lang.reflect.InvocationTargetException;
import it.uniroma1.textadv.links.*;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.gameEngine.Movimento.Direzione;
import it.uniroma1.textadv.objects.*;
import it.uniroma1.textadv.luoghi.Room;

/**
 * Rappresenta la classe del giocatore principale
 * @param <T>	tipo degli elementi presenti nell'inventario.
 */
public class Giocatore<T> extends Personaggio{
	
	/**
	 * Istanza del giocatore.
	 */
	private static Giocatore instance;
	
	/**
	 * Istanzia un oggetto di tipo Giocatore.
	 * @param nome	nome del giocatore.
	 */
	private Giocatore(String nome)
	{
		super(nome);
	}
	
	/**
	 * Restituisce l'istanza della classe Giocatore.
	 * @return	istanza del giocatore.
	 */
	public static Giocatore getInstance()
	{
		return instance;
	}
	
	/**
	 * Crea un istanza di tipo Giocatore e la salva all'interno della classe.
	 * @param nome	nome del giocatore.
	 * @throws PlayerAlreadyExistException	giocatore già esistente.
	 */
	public static void createInstance(String nome) throws PlayerAlreadyExistException
	{
		if (instance==null)	instance = new Giocatore(nome);
		else throw new PlayerAlreadyExistException();
	}
	
	/**
	 * Prende in input un oggetto e verifica se è presente all'interno dell'inventario.
	 * @param t	oggetto da controllare
	 * @return	true o false.
	 */
	public boolean hasKey(T t)
	{
		return getInventario().contains(t);
	}
	
	/**
	 * Stampa a video la descrizione della stanza in cui si trova.
	 */
	public void guarda() {	getRoom().descrivi(); }
	
	/**
	 * Restituisce true se l'oggetto è presente nell'inventario, false e un messaggio altrimenti.
	 * @param oggettoDaInventario	oggetto da controllare.
	 * @return	true o false+messaggio.
	 */
	private boolean checkInventario(T oggettoDaInventario)
	{
		if (hasKey(oggettoDaInventario)) return true;
		else System.out.println(oggettoDaInventario + Traduttore.get("[checkInventario]"));
		return false;
	}
	
	/**
	 * Restituisce true se l'oggetto è presente nella stanza in cui si trova il personaggio, false e un messaggio altrimenti.
	 * @param oggettoDaInventario	oggetto da controllare.
	 * @return	true o false+messaggio.
	 */
	private boolean checkRoom(T oggettoNellaStanza)
	{
		if (getRoom().contains(oggettoNellaStanza))	return true;
		else System.out.println(oggettoNellaStanza + Traduttore.get("[checkRoom]"));
		return false;
	}
	
	/**
	 * Restituisce true se l'oggetto è presente nell'inventario e il secondo oggetto è presente nella stanza, false e un messaggio altrimenti.
	 * @param oggettoDaInventario	oggetto da controllare all'interno dell'inventario.
	 * @param oggettoNellaStanza	oggetto da controllare all'interno della stanza.
	 * @return	true o false+messaggio.
	 */
 	private boolean checkStatus(T oggettoDaInventario, T oggettoNellaStanza)
	{
		if (hasKey(oggettoDaInventario) && getRoom().contains(oggettoNellaStanza)) return true;
		else System.out.println(oggettoDaInventario + Traduttore.get("[checkInventario]").substring(0, Traduttore.get("[checkInventario]").length()-1) + " o " + oggettoNellaStanza + Traduttore.get("[checkRoom]"));
		return false;
	}
	
 	/**
 	 * Stampa a video la descrizione dell'oggetto passato in input.
 	 * @param t	oggetto da cui prendere la descrizione.
 	 */
	public void guarda(T t)
	{
		if (checkRoom(t))
		{
			try {
				t.getClass().getMethod("descrivi").invoke(t);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
					| NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Apre un link o un oggetto apribile senza l'uso di una chiave.
	 * @param t	oggetto da aprire.
	 * @see it.uniroma1.textadv.links.Link#apriLink()
	 * @see it.uniroma1.textadv.objects.OggettoApribile#apri()
	 */
	public void apri(T t)
	{
		if (checkRoom(t))
		{
			if (t.getClass().getSuperclass().equals(Link.class))
			{
				((Link) t).apriLink();
			}
			else if (t.getClass().getSuperclass().equals(OggettoApribile.class))
			{
				((OggettoApribile) t).apri();
				System.out.println(t.toString() + Traduttore.get("[apri1]"));
			}
			else	System.out.println(t.toString() + Traduttore.get("[apri2]"));
		}
	}
	
	/**
	 * Apre un link o un oggetto apribile con una chiave/oggetto.
	 * @param t1	link/oggetto da aprire.
	 * @param t2	chiave/oggetto.
	 * @see it.uniroma1.textadv.links.Link#apriLinkCon(Oggetto)
	 * @see it.uniroma1.textadv.objects.OggettoApribile#apriCon(Oggetto)
	 */
	public void apri(T t1, T t2)
	{ 
		if (checkStatus(t2,t1))
		{
			if (t1.getClass().getSuperclass().equals(Link.class))
			{
				((Link) t1).apriLinkCon((Oggetto) t2);
			}
			else if (t1.getClass().getSuperclass().equals(OggettoApribile.class))
			{
				((OggettoApribile) t1).apriCon((Oggetto) t2);
			}
			else	System.out.println(t1.toString() + Traduttore.get("[apri2]"));
		}
	}

	/**
	 * Prende un oggetto da un oggetto apribile.
	 * @param oggetto	oggetto da prendere
	 * @param contenitore	oggetto apribile  da cui prelevare l'oggetto.
	 */
	public void prendi(T oggetto, T contenitore)
	{
		if (checkRoom(contenitore))
		{
			if (contenitore.getClass().getSuperclass().equals(OggettoApribile.class))
			{
				if (((OggettoApribile) contenitore).getStato()==true) ((OggettoApribile) contenitore).dai(oggetto);
				else System.out.println(contenitore+ Traduttore.get("[prendi]"));
			}
			else System.out.println(new CommandNotFoundException().toString());
		}
	}

	/**
	 * Prende un oggetto/link se presente nella stanza.
	 * @param oggetto	oggetto da prendere.
	 */
	public void prendi(T oggetto)
	{
		if (checkRoom(oggetto))
		{
			if (oggetto.getClass().equals(Bus.class))	((Bus) oggetto).muovi();
			else if (oggetto.getClass().equals(Vite.class) && getRoom().contains(oggetto))	System.out.println(Traduttore.get("[prendi1]"));
			else if (oggetto.getClass().getSuperclass().equals(OggettoApribile.class) && !oggetto.getClass().equals(Secchio.class))	System.out.println(Traduttore.get("[prendi2]"));
			else
			{
				if (oggetto.getClass().getSuperclass().equals(Personaggio.class))	System.out.println(Traduttore.get("[prendi3]"));
				else if (getRoom().containsClass(Venditore.class))	System.out.println(Traduttore.get("[prendi4]"));
				else if (getRoom().containsClass(Guardiano.class) && oggetto.getClass().equals(Tesoro.class)) 
				{
					Guardiano guardiano = (Guardiano) getRoom().getEntityFromClass(Guardiano.class);
					guardiano.daiTesoro();
				}
				else
				{
					aggiungiAdInventario(oggetto);
				}
			}
		}

	}
	
	/**
	 * Stampa a video il contenuto dell'inventario.
	 */
	public void inventario()
	{
		System.out.println(Traduttore.get("[inventario]"));
		this.getInventario().stream().forEach(s -> System.out.println("\t"+s));
	}
	
	/**
	 * Muove il giocatore nella direzione passata in input se possibile.
	 * @param dir	direzione.
	 * @see it.uniroma1.textadv.gameEngine.Movimento.Direzione
	 * @see it.uniroma1.textadv.gameEngine.Movimento#vai(Direzione)
	 */
	public void vai(Direzione dir)
	{
		Movimento.vai((Direzione) dir);
	}
	
	/**
	 * Accarezza l'animale passato in input se presente nella stanza.
	 * @param animale	animale da accarezzare.
	 * @see it.uniroma1.textadv.entity.Animale#reagisci()
	 */
	public void accarezza(T animale)
	{
		if (checkRoom(animale))
			if (animale.getClass().getSuperclass().equals(Animale.class))	((Animale)animale).reagisci();
			else System.out.println(animale.toString() + Traduttore.get("[accarezza]"));
	}
	
	/**
	 * Se possibile, rompe l'oggetto passato in input se presente nella stanza.
	 * @param oggetto	oggetto da rompere.
	 */
	public void rompi(T oggetto)
	{
		if (checkRoom(oggetto))
		{
			if (oggetto.getClass().equals(Salvadanaio.class)) System.out.println(Traduttore.get("[rompi1]"));
			else System.out.println(oggetto.toString() + Traduttore.get("[rompi2]"));
		}
	}
	
	/**
	 * Usa il primo oggetto passato in input sul secondo.
	 * @param oggetto1	oggetto da usare.
	 * @param oggetto2	oggetto che subisce l'azione.
	 */
	public void usa(T oggetto1, T oggetto2)
	{
		if (checkStatus(oggetto1, oggetto2))
		{
			String nomeClasse = oggetto2.getClass().getSimpleName();
			switch(nomeClasse)
			{
				case "Salvadanaio"	:	
					rompi(oggetto2,oggetto1);
					return;
				case "Vite"			:	
					((Vite) oggetto2).smonta(oggetto1);
					return;
				case "Pozzo"		:	
					((Pozzo) oggetto2).riempi(oggetto1);
					return;
				case "Teletrasporto":	
					((Teletrasporto) oggetto2).usa(oggetto1);
					return;
				case "Camino"		:	
					((Camino) oggetto2).spegni(oggetto1);
					return;
				case "Guardiano":
					((Guardiano) oggetto2).muori(oggetto1);
					return;
				default				:	
					System.out.println(new CommandNotFoundException().toString());
			}
		}
	}
	
	/**
	 * Usa l'oggetto passato in input se presente nella stanza o nell'inventario.
	 * @param oggetto	oggetto da usare.
	 */
	public void usa(T oggetto)
	{
		if(checkRoom(oggetto) || checkInventario(oggetto))
		{
			if (oggetto.getClass().equals(Teletrasporto.class)) ((Teletrasporto) oggetto).usa(null);
			else if (oggetto.getClass().equals(Bus.class))	((Bus) oggetto).muovi();
			else System.out.println(new CommandNotFoundException().toString());
		}
	}
	
	/**
	 * Se possibile, rompe il primo oggetto, se presente nella stanza, con il secondo, se presente nell'inventario.
	 * @param oggetto1	oggetto da rompere.
	 * @param oggetto2	oggetto da usare.
	 */
 	public void rompi(T oggetto1, T oggetto2)
	{
		if (checkStatus(oggetto2, oggetto1)) ((Salvadanaio) oggetto1).rompi(oggetto2);	
		else System.out.println(new CommandNotFoundException().toString());
	}
	
 	/**
 	 * Parla con un personaggio se presente nella stanza.
 	 * @param entita	personaggio con cui parlare.
 	 */
	public void parla(T entita)
	{
		if (checkRoom(entita))
			if (entita.getClass().getSuperclass().equals(Animale.class)) ((Animale) entita).reagisci();
			else System.out.println(new CommandNotFoundException().toString());
	}
	
	/**
	 * Entra in un link/stanza se collegata a quella in cui si trova il giocatore.
	 * @param collegamento	link/stanza.
	 */
	public void entra(T collegamento)
	{
		if (checkRoom(collegamento))
		{
			if (collegamento.getClass().equals(Room.class))
				getRoom().muovi((Room) collegamento);
			else if (collegamento.getClass().getSuperclass().equals(Link.class))
				((Link) collegamento).muovi();
			else System.out.println(new CommandNotFoundException().toString());
		}
	}

	/**
	 * Da un oggetto, se presente nell'inventario, ad un personaggio, se presente nella stanza.
	 * @param oggetto	oggetto da dare.
	 * @param personaggio	personaggio a cui dare l'oggetto.
	 */
	public void dai(T oggetto, T personaggio)
	{
		if (checkStatus(oggetto, personaggio))
		{
			T merce = oggetto;
			this.rimuoviDaInventario(oggetto);
			if (personaggio.getClass().equals(Venditore.class))	
			{
				((Venditore) personaggio).ricevi(oggetto);
				((Venditore) personaggio).acquisto();
			}
			else ((Personaggio) personaggio).ricevi(oggetto);
		}
	}

	/**
	 * Apprende un incantesimo presente nella stanza.
	 * @param oggetto	incantesimo.
	 */
	public void apprendi(T oggetto)
	{
		if (checkRoom(oggetto))
		{
			if (oggetto.getClass().equals(Incantesimo.class))
			{
				aggiungiAdInventario(oggetto);
				System.out.println(Traduttore.get("[Apprendi]") + oggetto.toString() + ".");
			}
			else System.out.println(oggetto.toString() + Traduttore.get("[Errore apprendi]"));
		}
	}
	
	/**
	 * Restituisce true se il giocatore possiede il tesoro, false altrimenti.
	 * @return	true o false.
	 */
	public boolean hasTreasure()	
	{
		for(Object x:getInventario())
		{
			if (x.getClass().equals(Tesoro.class))
			{
				System.out.println(Traduttore.get("[hasTreasure]"));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Restituisce il nome del giocatore.
	 */
	@Override
	public String toString()
	{
		return nome;
	}
}
