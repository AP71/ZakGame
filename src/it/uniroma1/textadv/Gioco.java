package it.uniroma1.textadv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import it.uniroma1.textadv.gameEngine.*;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.gameEngine.Traduttore.Language;;
/**
 * Rappresenta la classe per lo svolgimento del gioco.
 * 
 */
public class Gioco {

	/**
	 * Istanzia il gioco.
	 */
	public Gioco() { }
	
	/**
	 * Gioca al mondo con uno script passato in input.<p>
	 * Lo script deve essere una stringa contenente l'indirizzo del file.
	 * @param m	mondo di gioco.
	 * @param script	script di gioco.
	 * @see #play(Mondo, Path)
	 */
	public void play(Mondo m, String script)
	{
		this.play(m,Paths.get(script));
	}
	
	/**
	 * Gioca al mondo con uno script passato in input.<p>
	 * Lo script deve essere un Path contenente l'indirizzo del file.
	 * @param m	mondo di gioco.
	 * @param script	script di gioco.
	 */
	public void play(Mondo m, Path script) 
	{
		MotoreTestuale it = new MotoreTestuale(m.getInstances());
		String line;
		try(BufferedReader f = Files.newBufferedReader(script))
		{
			
			System.out.println(m.toString());
			System.out.println("\n\n" + Traduttore.get("[Inizio gioco]")+"\n");
			
			while (!Giocatore.getInstance().hasTreasure() && f.ready())
			{
				line = check(f.readLine());
				System.out.println(Traduttore.get("[Comando]")+line);
				it.elabora(check(line));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Gioca al mondo passato in input inserendo i comandi manualmente.
	 * @param m	mondo di gioco.
	 */
	public void play(Mondo m)
	{
		String line = "";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{			
			impostaLingua(br);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		prendiComandi(br,m);
	}
	
	/**
	 * Avvia una partita facendo scegliere al giocatore la lingua e il mondo di gioco.
	 */
	public void play()
	{
		String line = "";
		Mondo m = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{		
			impostaLingua(br);
			m = scegliMondo(br);
		}
		catch (Exception e) {
			e.printStackTrace();
			return;
		}
		prendiComandi(br,m);
	}
	
	/**
	 * Stampa a video del testo e permette di scegliere il mondo di gioco tra quelli predefiniti.
	 * @param br	BufferedReader, buffer per la lettura dei comandi da tastiera.
	 * @return	mondo di gioco selezionato dall'utente.
	 */
	private Mondo scegliMondo(BufferedReader br)
	{
		System.out.println(Traduttore.get("[MondiPredefiniti]"));
		List<File> files = List.of(new File("src/it/uniroma1/textadv/worlds").listFiles()).stream().filter(f -> f.getName().endsWith(".game")).collect(Collectors.toList());
		int i = 1;
		
		for(File f : files)
		{
			System.out.println("\t"+ i++ +": "+f.getName());
		}
		
		System.out.print(Traduttore.get("[SceltaMondo]"));
		try {
			i = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
			i = 1;
		}
		
		System.out.println("\n"+Traduttore.get("[MondoSelezionato]"));
		return Mondo.fromFile(Paths.get(files.get(i-1).toString()));
	}
	
	/**
	 * Imposta lingua in base al valore inserito da tastiera.
	 * @param br	BufferedReader.
	 */
	private void impostaLingua(BufferedReader br)
	{
		System.out.print("Seleziona la lingua : (Italiano 1,Inglese 2)\nSelezione: ");
		int selezione = 0;
		try {
			selezione = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		switch(selezione)
		{
			case 1:
				localizza(Language.IT);
				break;
			case 2:
				localizza(Language.EN);
				break;
			default:
				localizza(Language.IT);
		}
		Traduttore.stampaLinguaSelezionata();
		System.out.println("\n");
	}
	
	/**
	 * Imposta la lingua in base al valore passato in input.
	 * @param lingua	lingua.
	 * @see gameEngine.Traduttore.Language
	 */
	private void localizza(Language lingua)
	{
		Traduttore.impostaLingua(lingua);
	}
	
	/**
	 * Rimuove tutto ciò che è dopo "//" se presenti nella riga passata in input.
	 * @param s	riga.	
	 * @return	riga formattata.
	 */
 	private String check(String s)
	{
		return s.contains("//") ? s.substring(0, s.indexOf("//")-1) : s ;
	}
	
 	/**
 	 * Stampa a video e prende in input i comandi. Successivamente li elabora.
 	 * @param br	BufferedReader, per la lettura dei comandi di gioco.
 	 * @param m	mondo di gioco.
 	 */
 	private void prendiComandi(BufferedReader br, Mondo m)
 	{
 		String line = "";
 		MotoreTestuale it = new MotoreTestuale(m.getInstances());
		System.out.println(m.toString()+"\n\n");
		System.out.println(Traduttore.get("[Inizio gioco]"));

		while(!Giocatore.getInstance().hasTreasure())
		{
			System.out.print(Traduttore.get("[Comando]"));
			
			try 
			{
				line = br.readLine();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
				line = "";
			}
			if (!line.isEmpty()) it.elabora(check(line));
			
		}
 	}
}
