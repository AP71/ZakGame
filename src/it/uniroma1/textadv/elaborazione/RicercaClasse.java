package it.uniroma1.textadv.elaborazione;

import java.util.HashMap;

/**
 * Classe per la ricerca del packageName di una classe non ancora istanziata.
 */
public class RicercaClasse {

	/**
	 * Dizionario contenente le coppie(NomeClasse,PackageName).
	 */
	private final HashMap<String,String> CLASSI = new HashMap<>();
	
	/**
	 * Stringa utile al costruttore.
	 */
	private static final String OBJECTS = "it.uniroma1.textadv.objects";
	
	/**
	 * Stringa utile al costruttore.
	 */
	private static final String LINK = "it.uniroma1.textadv.links";
	
	/**
	 * Stringa utile al costruttore.
	 */
	private static final String ENTITY = "it.uniroma1.textadv.entity";
	
	/**
	 * Istanza della classe.
	 */
	private static RicercaClasse instance ;
	
	/**
	 * Istanzia la classe e riempie la mappa con i valori chiave=Classe e valore=Package.
	 */
	private RicercaClasse() 
	{
		CLASSI.put("Armadio",OBJECTS);
		CLASSI.put("Cacciavite",OBJECTS);
		CLASSI.put("Camino",OBJECTS);
		CLASSI.put("Cassetto",OBJECTS);
		CLASSI.put("Chiave",OBJECTS);
		CLASSI.put("Martello",OBJECTS);
		CLASSI.put("Pesce",OBJECTS);
		CLASSI.put("Pozzo",OBJECTS);
		CLASSI.put("Salvadanaio",OBJECTS);
		CLASSI.put("Scrivania",OBJECTS);
		CLASSI.put("Secchio", OBJECTS);
		CLASSI.put("Soldi",OBJECTS);
		CLASSI.put("Tesoro",OBJECTS);
		CLASSI.put("Tronchesi",OBJECTS);
		CLASSI.put("Incantesimo",OBJECTS);
		CLASSI.put("Vite",OBJECTS);
		CLASSI.put("Botola",LINK);
		CLASSI.put("Bus",LINK);
		CLASSI.put("Finestra",LINK);
		CLASSI.put("Porta",LINK);
		CLASSI.put("Teletrasporto", LINK);
		CLASSI.put("Cane",ENTITY);
		CLASSI.put("Gatto",ENTITY);
		CLASSI.put("Personaggio",ENTITY);
		CLASSI.put("Giocatore",ENTITY);
		CLASSI.put("Venditore",ENTITY);
		CLASSI.put("Guardiano",ENTITY);
	}
	
	/**
	 * Restituisce l'istanza della classe.
	 * @return istanza della classe.
	 */
	private static RicercaClasse getInstance()
	{
		if (instance==null) instance = new RicercaClasse();
		return instance;
	}
	
	/**
	 * Restituisce il packageName della classe passata in input se trovata, null altrimenti.
	 * @param s	nome della classe
	 * @return	nome del package della classe passata in input.
	 */
	public static String getPackage(String s) 
	{	
		return getInstance().CLASSI.get(s); 
	}
	
}
