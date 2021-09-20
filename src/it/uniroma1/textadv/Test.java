package it.uniroma1.textadv;

import java.nio.file.Paths;

/**
 * Classe per il test del gioco.
 *
 */
public class Test 
{
	/**
	 * Corpo principale della classe test.
	 * @param <T>	tipo degli oggetti passati in input.
	 * @param args	oggetti passati in input.
	 */
	public static <T> void main(String[] args)
	{
		Gioco g = new Gioco();
		Mondo m = Mondo.fromFile(Paths.get("minizak.game"));
		g.play(m,Paths.get("minizak.ff"));		
	}

}
