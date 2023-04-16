package terrain;

import java.util.HashMap;
import java.util.Map;
import Pheromones.Phero;
import etresVivants.Individu;
import etresVivants.Proie;
import vue.ContexteDeSimulation;

/**
 * Classe qui creer une case ou se situe un individu et des pheromone.
 *
 * @author .
 *
 */
public class Case {

  static int duree = 100000;
  static int compteur = 0;
  Map<Phero, Integer> intensites = new HashMap<>();
  Individu individu = null;

  /**
   * Fonction qui sert à placer l'individu de la case.
   *
   * @param individu Individu à placer.
   */
  public void ajouterIndividu(Individu individu) {
    this.individu = individu;
  }

  /**
   * Fonction qui sert à enlever l'individu de la case. 
   *
   * @param individu Individu à enlever.
   */
  public void enleverIndividu(Individu individu) {
    this.individu = null;
  }

  /**
   * Fonction qui sert à savoir si la place est libre. 
   *
   * @return un booléen.
   */
  public boolean placeLibre() {
    return this.individu == null;
  }

  public Individu getIndividu() {
    return this.individu;
  }

  /**
   * Si une proie est sur la case.
   *
   * @return un booléen.
   */
  public boolean isProie() {
    return this.individu instanceof Proie;
  }

  /**
   * Fonction qui ajoute de l'intensité .
   *
   * @param pheromone Type de phéromone.
   */
  public void ajouterIntensite(Phero pheromone) {
    Integer n = this.intensites.get(pheromone);
    this.intensites.put(pheromone, (n == null) ? 1 : n + 1);
  }

  /**
   * Fonction qui sert à savoir connaitre l'intensite de la pheromone sur la case.
   *
   * @param pheromoneRecherche type de Pheromone à recherche 
   * @return en entier
   */
  public int getIntensite(Phero pheromoneRecherche) {
    return this.intensites.get(pheromoneRecherche) == null ? 0
        : this.intensites.get(pheromoneRecherche);
  }

  // décrémenté les phéromones
  /**
   * Fonction qui permet de faire évoluer la case.
   *
   * @param contexte Contexte de la fourmiliere.
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {
    /*
     * if(this.individu != null) {
     * contexte.getSimulation().nouvellePheromone(this.individu.getPos()); }
     */
    compteur++;
    if (compteur == duree) {
      for (Map.Entry mapentry : intensites.entrySet()) {
        int val = (Integer) mapentry.getValue();
        if (val > 0) {
          mapentry.setValue(val - 1);
        }
      }
      compteur = 0;
    }

  }

}
