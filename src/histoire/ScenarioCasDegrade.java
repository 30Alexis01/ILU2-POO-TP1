package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;


public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		Gaulois asterix = new Gaulois("Astérix", 8);
		Gaulois obelix = new Gaulois("Obélix", 25);
		try {
			etal.libererEtal(); 
		}
		catch (IllegalStateException e) {
			System.err.println("Erreur lors de la libération de l'étal : " + e.getMessage());
		}
		try {
			etal.occuperEtal(asterix, "potion", 10);
			etal.acheterProduit(5, obelix);
		}
		catch (IllegalStateException | IllegalArgumentException e) {
			System.err.println("Erreur lors de l'achat du produit : " + e.getMessage());
		}
		finally {
			System.out.println("Fin du test");
		}
		
	}

	
}
