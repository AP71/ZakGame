package it.uniroma1.textadv.elaborazione;

import it.uniroma1.textadv.elaborazione.*;
import it.uniroma1.textadv.entity.*;
import it.uniroma1.textadv.errors.*;
import it.uniroma1.textadv.luoghi.*;
import it.uniroma1.textadv.links.*;
import it.uniroma1.textadv.objects.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Classe per la creazione degli elementi presenti nel file di gioco.
 * @param <T>	tipo degli elementi presenti nella classe.
 */
public class ElaboraFile<T>{
	
	/**
	 * Contiene gli elementi di gioco istanziati.
	 */
	private ArrayList<T> instances = new ArrayList<>();
	
	/**
	 * Oggetto per la lettura del file di gioco.
	 */
	private final BufferedFile file;
	
	/**
	 * Istanzia un oggetto di tipo ElaboraFile mediante l'uso di un Path.<p>
	 * Il Path deve contenente l'indirizzo al file di gioco.
	 * @param path indirizzo al file di gioco.
	 * @see it.uniroma1.textadv.elaborazione.BufferedFile
	 */
	public ElaboraFile(Path path)
	{
		file = new BufferedFile(path);
		createPlayer();
	}
	
	/**
	 * Istanzia il giocatore principale.
	 */
	private void createPlayer()
	{
		try 
		{
			if (Giocatore.getInstance()==null)
			{
				file.cerca("[player]",1);
				
				String name = file.getRiga();
				Giocatore.createInstance(name.substring(0, name.indexOf("\t")));
				return;
			}
		} 
		catch (LineNotFoundException | PlayerAlreadyExistException e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Prende in ingresso una categoria e istanzia tutti gli oggetti apparatenenti a quella categoria.
	 * @param categoria	inizio categoria.
	 * @param fineCategoria	fine categoria.
	 */
	private void get(String categoria, String fineCategoria)
	{
		try 
		{
			file.cerca(categoria,1);
			T classe;
			String riga = file.getRiga();
			while (!riga.contains(fineCategoria))
			{
				if(!riga.isEmpty())
				{
						String[] res = riga.split("\t");
						classe = generaClasse(res);
						if (exist(res[0])==null && classe!=null) instances.add(classe);
				}
				riga = file.next();
			}	
		} 
		catch (LineNotFoundException  e) 
		{
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Istanzia i personaggi presenti all'interno del file di gioco.
	 */
	private void getCharacters()
	{
		get("[characters]","[player]");
	}
	
	/**
	 * Istanzia gli oggetti presenti all'interno del file di gioco.
	 */
	private void getObjectcs()
	{
		get("[objects]","[characters]");		
	}
	
	/**
	 * Istanzia i link presenti all'interno del file di gioco che non hanno una chiave per aprirli.
	 */
	private void generateLinkWithoutKey()
	{
		get("[links]","[objects]");
	}
	
	/**
	 * Prende in ingresso una riga di testo contenente le informazioni sull'oggetto da creare.
	 * Successivamente istanzia l'oggetto.
	 * Se l'istanza interagisce con uno o più istanze, entra in ricorsione e aggiunge tutti, incluso se stesso, alla lista delle istanze contenute nel file di gioco.
	 * @param	res	riga di testo divisa.
	 * @return	istanza dell'oggetto creato.
	 */
	private T generaClasse(String[] res)
	{
		String cartella = RicercaClasse.getPackage(res[1]);
		Class<?> c = null ;
		try {
			c = Class.forName(cartella+"."+res[1]);
			if (cartella.equals("it.uniroma1.textadv.links") || res.length<3)
			{
				if (res.length==5 && c.equals(Bus.class))
				{
					T chiave = generaAggiunte(res[4]);
					Bus bus = Bus.class.getConstructor(String.class,Chiave.class).newInstance(res[0],chiave);
					return ((T) bus);
				}
				return    exist(res[0])!=null ?  
												exist(res[0]) : 
												(T) c.getConstructor(String.class).newInstance(res[0]);
			}
			else if (cartella.equals("it.uniroma1.textadv.entity") && res.length==4)
			{
				int puntatore = file.getPuntatore();
				List<T> classi = List.of(res).stream().skip(2).map(r -> {
																		try {
																			String[] res2 = file.cerca(r).getRiga().split("\t");
																			return exist(res2[0])!=null ? exist(res2[0]) : generaClasse(res2);
																		} catch (LineNotFoundException e) {
																			System.out.println(e.toString());
																			return null;
																		}
																		}).collect(Collectors.toList());
				classi.stream().filter(r -> r!=null && exist(r.toString())==null).forEach(instances::add);
				file.setPuntatore(puntatore);
				return (T) c.getConstructor(String.class,List.class).newInstance(res[0],classi);
			}
			else
			{
				T classe = generaAggiunte(res[2]);
				if (c.equals(Venditore.class))
				{
					List<T> classi = List.of(classe);
					return (T) c.getConstructor(String.class,List.class).newInstance(res[0],classi);
				}
				else return (T) c.getConstructor(String.class,Object.class).newInstance(res[0],classe);
			}
			
		}
		catch (ClassNotFoundException | InstantiationException | 
				IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException | NoSuchMethodException | SecurityException e) {
			System.out.println(e.toString());
		} catch (LineNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.toString());
			try 
			{
				T classe = (T) c.getConstructor(String.class,Object.class).newInstance(res[0],null);
				if (classe!=null) instances.add(classe);
				return classe;
			} 
			catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e1) 
			{
				System.out.println(e.toString());
			}
			
		}
		return null;

	}
	
	/**
	 * Prende in ingresso il nome di un oggetto da istanziare, lo cerca all'interno del file, se già è presente nella lista delle istanze ritorna il riferimento, altrimenti lo istanzia.
	 * @param res	oggetto da generare.
	 * @return	oggetto istanziato.
	 * @throws LineNotFoundException	Eccezione linea non trovata all'interno del file.
	 */
	private T generaAggiunte(String res) throws LineNotFoundException
	{
		int puntatore = file.getPuntatore();
		T classe;
		if (exist(res)!=null)	classe = exist(res);
		else 
		{
			classe = generaClasse(file.cerca(res).getRiga().split("\t"));
			if (exist(res)==null && classe!=null) instances.add(classe);
		}
		file.setPuntatore(puntatore);
		return classe;
	}
	
	/**
	 * Restituisce l'istanza di un Oggetto/Entita/Room/Link se già esistente.
	 */
	private T exist(String s)
	{
		for(T obj:instances)
		{
			if (s.equals(obj.toString())) return obj;
		}
		return null;
	}

	/**
	 * Imposta alle istanze di tipo Link le stanze con cui è in comunicazione.
	 */
	private void completeLink() {
		for(T link:instances)
		{
			if(link.getClass().getSuperclass().equals(Link.class))
			{
				try {
					file.cerca(link.toString());
					String[] res = file.getRiga().split("\t");
					((Link) link).setStanze(((Room)exist(res[2])), ((Room)exist(res[3])));
				} catch (LineNotFoundException e) {
					System.out.println(e.toString());
				}
			}
		}
	}
	
	/**
	 * Imposta alle istanze di tipo Link/OggettoApribile le chiavi con cui possono essere aperti.
	 */
	private void setKey()
	{
		for(T key:instances)
		{
		    if(key.getClass().equals(Chiave.class) || key.getClass().equals(Vite.class))
			{
				Link l = (Link) exist(((Oggetto) key).getInteractWith().toString());
				if ( l!=null)
				{
					l.setChiave( key);
				}
			}
			else if(key.getClass().getSuperclass().equals(OggettoApribile.class))
			{
				Oggetto oggetto = null;
				for(T t:instances) 
					if ((t.getClass().getSuperclass().equals(Oggetto.class) || t.getClass().getSuperclass().equals(Attrezzo.class)) && ((Oggetto)t).getInteractWith()!=null && ((Oggetto)t).getInteractWith().equals(key)) 
					{
						oggetto = (Oggetto) t;
						break;
					}
				if (oggetto!=null) ((OggettoApribile)key).setApribileCon(oggetto);
			}
		}
	}

	/**
	 * Aggiunge le istanze presenti nella riga presa in input alla stanza.
	 */
	private void addInstances(Room s,String riga)
	{
		for(String o:riga.substring(riga.indexOf("\t")+1).split(",")) 
		{
			if (o.startsWith(" ")) o = o.substring(1);
			T obj = exist(o);
			if (obj!=null)
			{
				try 
				{
					s.add(obj);
					obj.getClass().getMethod("setRoom", Room.class).invoke(obj, s);
				} 
				catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) 
				{
					System.out.println(e.toString());
				}
			}
		}
	}
	
	/**
	 * Istanzia i luoghi presenti nel file di gioco.
	 */
	private void getLuoghi()
	{
		String nome;
		String descrizione;
		String oggetti;
		String[] res;
		try 
		{
			file.cerca("[room");
		} 
		catch (LineNotFoundException e) 
		{
			System.out.println(e.toString());
		}
		String riga = file.getRiga();
		while(!riga.contains("[links]"))
		{
			if (!riga.isEmpty())
			{
				nome = riga.substring(riga.indexOf(":")+1, riga.indexOf("]"));
				riga = file.next();
				descrizione =  riga.substring(riga.indexOf("\t")+1);
				Room s = new Room(nome,descrizione);
				
				riga = file.next();
				while(!riga.startsWith("links"))
				{
					addInstances(s,riga);
					riga = file.next();
				}					
				s.setLink(riga.substring(riga.indexOf("\t")+1));
				instances.add((T) s);
			}
			riga = file.next();
		}
	}
	
	/**
	 * Istanzia tutti gli oggetti di gioco e restituisce la stanza in cui inizia il gioco.
	 * @return	stanza in cui inizia il gioco.
	 * @throws LinkNotFoundException	link non trovato.
	 */
	public Room getStart() throws LinkNotFoundException
	{
		final String NORD = "N";
		final String SUD = "S";
		final String EST = "E";
		final String OVEST = "O";
		final String WEST = "W";
		
		this.getCharacters();
		this.generateLinkWithoutKey();
		this.getObjectcs();
		this.setKey();
		this.getLuoghi();
		this.completeLink();
		
		String riga ;
		String[] res;
		for(T luogo:instances)
		{
			if (luogo.getClass().equals(Room.class))
			{
				riga = ((Room) luogo).getLink();
				for(String dir:riga.split(","))
				{
					res = dir.split(":");
					switch(res[0])
					{
						case NORD:
							((Room) luogo).setNord(exist(res[1]));
							break;
						case SUD:
							((Room) luogo).setSud(exist(res[1]));
							break;
						case OVEST:								
							((Room) luogo).setOvest(exist(res[1]));
							break;
						case WEST:
							((Room) luogo).setOvest(exist(res[1]));
							break;
						case EST:
							((Room) luogo).setEst(exist(res[1]));
							break;
					}
				}
			}
		}
		file.setPuntatore(2);
		String[] res2 = file.getRiga().split("\t");
		Room start = (Room) exist(res2[1]);
		instances.sort((s1,s2) -> s1.toString().length()<s2.toString().length() ? 1 : -1);
		return start;
	}
	
	/**
	 * Restituisce un oggetto di tipo List con tutte le istanze di gioco.
	 * @return lista con le istanze di gioco.
	 */
	public List<T> getInstances()	{	return instances; }

	/**
	 * Restituisce il nome del mondo.
	 * @return	nome del mondo.
	 */
	public String getNomeMondo() {
		String riga = file.getRiga(0);
		return riga.substring(riga.indexOf(":")+1, riga.indexOf("]"));
	}

	/**
	 * Restituisce la descrizione del mondo.
	 * @return	descrizione del mondo.
	 */
	public String getDescrizioneMondo() {
		String riga = file.getRiga(1);
		return riga.substring(riga.indexOf("\t")+1);
	}
	
	
	
	
}
