package it.uniroma1.textadv;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.luoghi.Room;
import it.uniroma1.textadv.entity.Giocatore;
import it.uniroma1.textadv.elaborazione.*;
import it.uniroma1.textadv.gameEngine.*;

/**
 * Rappresenta la classe che contiene il mondo di gioco.
 * @param <T>	tipo delle istanze presenti nel file di gioco.
 */
public class Mondo<T> {

	/**
	 * Nome del mondo.
	 */
	private final String nome;
	
	/**
	 * Descrizione del mondo.
	 */
	private final String descrizione;
	
	/**
	 * Stanza iniziale del mondo.
	 */
	private final Room start;

	/**
	 * Lista delle istanze del file di gioco.
	 */
	private final List<T> instances;
	
	/**
	 * Istanzia un mondo.
	 * @param nome	nome del mondo.
	 * @param descrizione	descrizione del mondo.
	 * @param start	stanza iniziale.
	 * @param entity	istanze del file di gioco.
	 */
	private Mondo(String nome, String descrizione, Room start, List<T> entity) 
	{ 
		this.nome = nome;
		this.descrizione = descrizione;
		this.start = start;
		this.instances = entity;
		try {
			Giocatore.getInstance().setRoom(start);
			this.start.add(Giocatore.getInstance());
		} catch (EntityIsAlreadyInAnotherRoomException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Istanzia un mondo prendendo in input il nome del file del mondo di gioco.
	 * @param s	nome del file di gioco.
	 * @return	mondo di gioco
	 */
	public static Mondo fromFile(String s)
	{
		ElaboraFile ef = new ElaboraFile(Paths.get(s));
		try 
		{
			return new Builder().nomeMondo(ef.getNomeMondo())
								.descrizioneMondo(ef.getDescrizioneMondo())
								.stanzaIniziale(ef.getStart())
								.istanzeMondo(ef.getInstances()).build();
		} catch (MondoNotInstantiableException | LinkNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Istanzia un mondo prendendo in input un Path.
	 * Il Path contiene l'indirizzo al file del mondo di gioco.
	 * @param path	Path del file di gioco.
	 * @return	mondo di gioco
	 */
	public static Mondo fromFile(Path path)	{	return fromFile(path.toString());	}	
	
	/**
	 * Restituisce le istanze del mondo di gioco.
	 * @return	lista delle istanze di gioco.
	 */
	public List<T> getInstances()	{	return instances;	}
	
	/**
	 * Restituisce una descrizione generale del mondo di gioco.
	 * @return descrizione generale del mondo.
	 */
	@Override
	public String toString()
	{
		return Traduttore.get("[Nome mondo]") +nome+ "\n"+Traduttore.get("[Descrizione mondo]")+descrizione+"\n"+Traduttore.get("[Start]")+start.toString();
	}
	
	/**
	 * Classe per la creazione del Mondo.
	 * 
	 */
	static class Builder<T>
	{
		/**
		 * Nome del mondo.
		 */
		private String nomeMondo;
		
		/**
		 * Descrizione del mondo.
		 */
		private String descrizioneMondo;
		
		/**
		 * Stanza iniziale del mondo.
		 */
		private Room startMondo;

		/**
		 * Lista delle istanze del file di gioco.
		 */
		private List<T> instancesMondo;
		
		/**
		 * Istanzia un builder del Mondo.
		 */
		public Builder() {}
		
		/**
		 * Imposta il nome del mondo.
		 * @param nome	nome del mondo.
		 * @return	istanza della classe Builder.
		 */
		public Builder nomeMondo(String nome)
		{
			this.nomeMondo = nome;
			return this;
		}
		
		/**
		 * Imposta la descrizione del mondo.
		 * @param descrizione	descrizione del mondo.
		 * @return	istanza della classe Builder.
		 */
		public Builder descrizioneMondo(String descrizione)
		{
			this.descrizioneMondo = descrizione;
			return this;
		}
		
		/**
		 * Imposta la stanza iniziale.
		 * @param start	stanza iniziale.
		 * @return	istanza della classe Builder.
		 */
		public Builder stanzaIniziale(Room start)
		{
			this.startMondo = start;
			return this;
		}
		
		/**
		 * Imposta le istanze del file di gioco.
		 * @param instaces	istanze del file di gioco.
		 * @return	istanza della classe Builder.
		 */
		public Builder istanzeMondo(List<T>	instaces)
		{
			this.instancesMondo = instaces;
			return this;
		}
		
		/**
		 * Istanzia un Mondo.
		 * @return	Istanza del Mondo.
		 * @throws MondoNotInstantiableException	Eccezione mondo non istanziabile, elementi mancanti.
		 */
		public Mondo build() throws MondoNotInstantiableException
		{
			if (this.nomeMondo!=null && this.descrizioneMondo!=null && this.startMondo!=null && this.instancesMondo!=null)	return new Mondo(nomeMondo,descrizioneMondo,startMondo,instancesMondo);
			else throw new MondoNotInstantiableException();
		}
	}
}
