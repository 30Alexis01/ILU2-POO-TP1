package villagegaulois;

import personnages.Gaulois;

public class Etal {
	private Gaulois vendeur;
	private String produit;
	private int quantiteDebutMarche;
	private int quantite;
	private boolean etalOccupe = false;

	public boolean isEtalOccupe() {
		return etalOccupe;
	}

	public Gaulois getVendeur() {
		return vendeur;
	}
	
	public String getProduit() {
        return this.produit;
    }

    public int getQuantite() {
        return this.quantite;
    }

	public void occuperEtal(Gaulois vendeur, String produit, int quantite) {
		this.vendeur = vendeur;
		this.produit = produit;
		this.quantite = quantite;
		quantiteDebutMarche = quantite;
		etalOccupe = true;
	}

	public String libererEtal() {
		if (!etalOccupe || vendeur == null) {
	        throw new IllegalStateException("L'étal n'a pas été occupé.");
	    }
	    etalOccupe = false;
	    StringBuilder chaine = new StringBuilder("Le vendeur " + vendeur.getNom() + " quitte son étal, ");
	    int produitVendu = quantiteDebutMarche - quantite;
	    if (produitVendu > 0) {
	        chaine.append("il a vendu " + produitVendu + " parmi les " + quantiteDebutMarche + " " + produit + " qu'il voulait vendre.\n");
	    } else {
	        chaine.append("il n'a malheureusement rien vendu.\n");
	    }
	    /
	    vendeur = null;
	    produit = null;
	    quantite = 0;
	    quantiteDebutMarche = 0;
	    return chaine.toString();
	}

	public String afficherEtal() {
		if (etalOccupe) {
			return "L'étal de " + vendeur.getNom() + " est garni de " + quantite
					+ " " + produit + "\n";
		}
		return "L'étal est libre";
	}

	public String acheterProduit(int quantiteAcheter, Gaulois acheteur) {
		if (etalOccupe) {
			StringBuilder chaine = new StringBuilder();
			chaine.append(acheteur.getNom() + " veut acheter " + quantiteAcheter
					+ " " + produit + " à " + vendeur.getNom());
			if (quantite == 0) {
				chaine.append(", malheureusement il n'y en a plus !");
				quantiteAcheter = 0;
			}
			if (quantiteAcheter > quantite) {
				chaine.append(", comme il n'y en a plus que " + quantite + ", "
						+ acheteur.getNom() + " vide l'étal de "
						+ vendeur.getNom() + ".\n");
				quantiteAcheter = quantite;
				quantite = 0;
			}
			if (quantite != 0) {
				quantite -= quantiteAcheter;
				chaine.append(". " + acheteur.getNom()
						+ ", est ravi de tout trouver sur l'étal de "
						+ vendeur.getNom() + "\n");
			}
			return chaine.toString();
		}
		return null;
	}

	public boolean contientProduit(String produit) {
		return this.produit.equals(produit);
	}

}
