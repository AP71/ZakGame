package it.uniroma1.textadv.gameEngine;

import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import it.uniroma1.textadv.elaborazione.*;
/**
 * Classe che si occupa della traduzione in base alla lingua scelta.
 * 
 */
public class Traduttore {

	/**
	 * Rappresenta le lingue disponibili.
	 */
	public static enum Language
	{
		/**
		 * Lingua italiana.
		 */
		IT	{ @Override public BufferedFile getFile() {return new BufferedFile(Paths.get("src/it/uniroma1/textadv/language/IT.language"));}},	
		
		/**
		 * Lingua inglese.
		 */
		EN	{ @Override public BufferedFile getFile() {return new BufferedFile(Paths.get("src/it/uniroma1/textadv/language/EN.language"));}};
		
		/**
		 * Restituisce il file di testo, in base alla lingua selezionata, con i comandi e le traduzioni.
		 * @return	file di testo contenete i comandi e le rispettive traduzioni.
		 */
		public abstract BufferedFile getFile();
	}
	
	/**
	 * Lingua di default.
	 */
	private static Language lingua = Language.IT;
	
	/**
	 * Istanza della classe.
	 */
	private static Traduttore instance;
	
	/**
	 * Mappa da comando a risultato.
	 */
	private Map<String,String> commands;

	/**
	 * Istanzia un Traduttore in base alla lingua scelta.
	 */
	private Traduttore() 
	{
		Function<String[],String> key = s -> s[0];
		Function<String[],String> value = s -> s[1];
		commands = lingua.getFile().getLines().stream().map(s -> s.split("\t")).collect(Collectors.toMap(key::apply, value::apply));
	}
	
	/**
	 * Imposta la lingua in base al valore passato in input.
	 * @param l	lingua scelta.
	 */
	public static void impostaLingua(Language l)
	{
		lingua = l;
	}

	/**
	 * Restituisce l'istanza della classe o la crea, se vuota.
	 * @return	istanza della classe.
	 */
	private static Traduttore getInstance() 
	{
		if (instance==null)	instance = new Traduttore();
		return instance;
	}
	
	/**
	 * Stampa a video la lingua scelta.
	 */
	public static void stampaLinguaSelezionata()
	{
		System.out.println(getInstance().get("[Impostazione lingua]"));
	}
	
	/**
	 * Restituisce la stringa di testo corrispondente al comando passato in input.
	 * @param command	comando.
	 * @return	linea di testo corrispondente al comando passato in input.
	 */
	public static String get(String command)
	{
		return getInstance().commands.get(command);
	}
}
