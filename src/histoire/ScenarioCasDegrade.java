package histoire;

import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ScenarioCasDegrade {

	public static void main(String[] args) {
		Etal etal = new Etal();
		etal.libererEtal();
		etal.occuperEtal(new Gaulois("VendeurTest", 5), "Potion", 10); 
        String resultat = etal.acheterProduit(5, null); 
        System.out.println(resultat);
		System.out.println("Fin du test");
		}

}
