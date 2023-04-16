package fourmiliere;

import etresVivants.Individu;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de gestion des stock d'une fourmilière.
 *
 * @author .
 *
 */
public class Stock {

  List<Individu> listeNourriture = new ArrayList<Individu>();
  List<Individu> listeDepot = new ArrayList<Individu>();

  private int dureeDePeremption = 200;

  /**
   * Fonction qui ajoute dans le stock un element. 
   *
   * @param individu Individu à ajouter dans le stock
   */
  public void ajouterAuStock(Individu individu) {
    individu.setDureeDeVie(dureeDePeremption);
    listeNourriture.add(individu);
    System.out.println(listeNourriture);
  }

  /**
   * Fonction qui nourris une foumi.
   *
   * @param poids Poids manger par un fourmi
   */
  public void supprimerDuStock(double poids) {
    if (size() != 0) {
      Individu individu = listeNourriture.get(0);
      if (poids != 0) {
        if (individu.getPoids() == poids) {
          listeDepot.add(individu);
          listeNourriture.remove(individu);
        } else if (individu.getPoids() < poids) {
          poids = poids - individu.getPoids();
          listeDepot.add(individu);
          listeNourriture.remove(individu);
          supprimerDuStock(poids);
        } else if (individu.getPoids() > poids) {
          listeNourriture.get(0).setPoids(listeNourriture.get(0).getPoids() - poids);
        }
      }
    }

  }

  /**
   * Fonction qui sert à regarder les déchets de la fourmilière.
   *
   */
  public void perimer() {
    for (int i = 0; i < listeNourriture.size(); i++) {
      listeNourriture.get(i).setDureeDeVie(listeNourriture.get(i).getDureeDeVie() - 1);
      if (listeNourriture.get(i).getDureeDeVie() == 0) {
        listeDepot.add(listeNourriture.get(i));
        listeNourriture.remove(i);
      }
    }
  }

  /**
   * Fonction qui gere les déchets.
   */
  public void gestionDepot() {
    for (int i = 0; i < listeDepot.size(); i++) {
      listeDepot.get(i).setDureeDeVie(listeDepot.get(i).getDureeDeVie() - 1);
      if (listeDepot.get(i).getDureeDeVie() == 0) {
        listeDepot.remove(i);
      }

    }
  }

  /**
   * Fonction qui affiche les stocks des fourmis. 
   *
   * @return une chaine de caractères.
   */
  public String asString() {
    double res = 0;
    for (Individu ind : listeNourriture) {
      res = res + ind.getPoids();
    }
    return res + "";
  }

  public int size() {
    return listeNourriture.size();
  }
}
