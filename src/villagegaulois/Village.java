package villagegaulois;

import java.nio.file.attribute.AclEntry;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;
	int nombreEtal;

	public Village(String nom, int nbVillageoisMaximum, int nombreEtal) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nombreEtal = nombreEtal;
		this.marche = new Marche(nombreEtal);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef " + chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom() + " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}

	// a
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int indiceEtalLibre = marche.trouverEtalLibre();
		if (indiceEtalLibre != -1) {
			marche.utiliserEtal(indiceEtalLibre, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur + " vend des " + produit + " à l'étal n°"+ indiceEtalLibre);
		}
		chaine.append(vendeur + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		return chaine.toString();
	}
	
	//b
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		Etal[] lstEtalProduit = marche.trouverEtals(produit);
		if(lstEtalProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des fleurs au marché. \n");
		}else if(lstEtalProduit.length == 1) {
			Gaulois vendeur= lstEtalProduit[0].getVendeur();
			chaine.append("Seul le vendeur " + vendeur + " propose des " + produit + " au marché");
		}else {
			Gaulois vendeur;
			chaine.append("Les vendeurs qui proposent des fleurs sont : \n");
			for (int i = 0 ; i < lstEtalProduit.length ; i++) {
				vendeur = lstEtalProduit[i].getVendeur();
				chaine.append("- "+ vendeur +"\n");
			}
		}
;		return chaine.toString();
	}

	// classe interne Marche
	public class Marche {
		private Etal[] etals;

		// 2
		public Marche(int nombreEtal) {
			etals = new Etal[nombreEtal];
			for (int i = 0; i < etals.length; i++) {
				etals[i] = new Etal();
			}
		}

		// 3
		// permettant à un gaulois de s’installer à un étal.
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
			if (indiceEtal >= 0 && indiceEtal < etals.length) {
				Etal etal = etals[indiceEtal];
				etal.occuperEtal(vendeur, produit, nbProduit);
			}
		}

		// 4
		//permettant de trouver un étal non occupé dans le tableau etals
		public int trouverEtalLibre() {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe() == false) {
					return i;
				}
			}
			return -1;
		}

		// 5
		//qui retourne un tableau contenant tous les étals où l’on vend un produit.
		public Etal[] trouverEtals(String produit) {
			int nombreEtalProduit = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].contientProduit(produit)) {
					nombreEtalProduit += 1;
				}
			}
			Etal[] etalProduit = new Etal[nombreEtalProduit];
			int i = 0;
			for (int y = 0; y < etals.length; y++) {
				
				if (etals[y].contientProduit(produit)) {
					etalProduit[i] = etals[y];
					i += 1;
				}
			}
			return etalProduit;
		}

		// 6
		//qui retourne l’étal sur lequel s’est installé le vendeur passé en paramètre d’entrée ou null s’il n’y en a pas.
		public Etal trouverVendeur(Gaulois gaulois) {
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		// 7
		public String afficherMarche() {
			StringBuilder chaine = new StringBuilder();
			int nbEtalVide = 0;
			for (int i = 0; i < etals.length; i++) {
				if (etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				} else {
					nbEtalVide += 1;
				}
			}
			chaine.append("Il reste " + nbEtalVide + " étals non utilis�s dans le marché.\n");
			return chaine.toString();
		}
	}
}