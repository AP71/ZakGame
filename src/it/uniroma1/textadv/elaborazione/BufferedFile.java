package it.uniroma1.textadv.elaborazione;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;
import it.uniroma1.textadv.errors.*;

/**
 * Classe che consente la lettura di un file mediante l'uso di un ArrayList e un puntatore.
 *
 */
public class BufferedFile {
	
	/**
	 * Contiene il file di testo, riga per riga, sotto forma di ArrayList.
	 */
	private ArrayList<String> file ;
	
	/*
	 * Contiene il puntatore alla riga di testo sulla quale si sta lavorando.
	 */
	private int puntatore = 0;

	/**
	 * Istanzia un BufferedFile mediante un Path.<p>
	 * Il Path deve corrispondere all'indirizzo del file di gioco.
	 * @param	path	indirizzo del file da leggere.
	 */
	public BufferedFile(Path path) {
		Function<String,String> correggi = s -> s.contains("//") ? s.substring(0,s.indexOf("/")-1) : s ;
		
		try(BufferedReader f = Files.newBufferedReader(path))
		{
			file = new ArrayList<>(f.lines().map(correggi::apply).collect(Collectors.toList()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Restituisce il puntatore alla linea di testo del file.
	 * @return	puntatore(intero).
	 */
	public int getPuntatore() { return puntatore; }
	
	/**
	 * Prende in ingresso un intero e imposta il puntatore al valore passato in input.
	 * @param puntatore	valore da impostare come puntatore.
	 */
	public void setPuntatore(int puntatore) { this.puntatore = puntatore; }
	
	/**
	 * Memorizza all'interno all'interno della variabile puntatore il numero di riga che inizia per la parola data.
	 * Restituisce un oggetto di tipo BufferedFile.
	 * @param parola	parola da cercare all'interno del file.
	 * @return	istanza della classe BufferedFile.
	 * @throws LineNotFoundException	linea di testo non trovata.
	 */
	public BufferedFile cerca(String parola) throws LineNotFoundException
	{
		for(String riga:file) 
			if (riga.startsWith(parola)) 
			{
				puntatore = file.indexOf(riga);
				return this;
			}
		if (true) throw new LineNotFoundException();
		return this;
	}
	
	/**
	 * Memorizza all'interno della variabile puntatore il numero di riga che contiene come prima parola quella passata in input.
	 * Restituisce un oggetto di tipo BufferdFile.
	 * @param parola	parola da cercare all'interno del file.
	 * @return	istanza della classe BufferdFile.
	 * @throws LineNotFoundException	linea di testo non trovata.
	 */
	public BufferedFile cercaComando(String parola) throws LineNotFoundException
	{
		for(String riga:file) 
		{
			String[] res = riga.split("\t");
			if (res[0].equals(parola))
			{
				puntatore = file.indexOf(riga);
				return this;
			}
		}
		if (true) throw new LineNotFoundException();
		return this;
	}
	
	/**
	 * Attraverso il metodo <i>cerca(String parola)</i> imposta il valore del puntatore e successivamente lo incrementa con il valore passato in input.
	 * @param parola	parola da cercare all'interno del file.
	 * @param incremento	valore da aggiungere al valore puntatore.
	 * @throws LineNotFoundException	linea di testo non trovata.
	 */	
	public void cerca(String parola, int incremento) throws LineNotFoundException 
	{ 
		cerca(parola); 
		puntatore = puntatore + incremento; 
	}
	
	/**
	 * Restituisce la riga del file puntata dal valore puntatore.
	 * @return	riga di testo del file passato in input al costruttore.
	 * @see #getRiga(int)
	 */
	public String getRiga()
	{
		return getRiga(puntatore);
	}
	
	/**
	 * Incrementa il puntatore di uno e restituisce la riga di testo puntata dal puntatore.
	 * @return	riga di testo del file passato in input al costruttore.
	 * @see #getRiga()
	 */
	public String next()
	{
		++puntatore;
		return getRiga();
	}
	
	/**
	 * Prende in input un valore e restituisce la riga di testo corrispondente al valore passato in input.
	 * @param k numero della riga di testo da leggere.
	 * @return	riga di testo del file passato in input al costruttore.
	 */
	public String getRiga(int k) 
	{	
		return file.get(k);
	}

	
	/**
	 * Restitusice le righe del file di testo.
	 * @return	ArrayList contenente le righe del file.
	 */
	public ArrayList<String> getLines()	{	return file;	}
	
	

}
