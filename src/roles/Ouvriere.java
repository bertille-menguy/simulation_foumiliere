package roles;

import java.awt.Point;
import java.util.Random;

import Pheromones.Phero;
import etresVivants.Fourmi;
import fourmiliere.Bilan;
import modes.Chasse;
import modes.Mode;
import terrain.Chemin;
import vue.ContexteDeSimulation;

/**
 * Classe difinit une fourmi au role d'ouvriere.
 *
 * @author .
 *
 */
public class Ouvriere extends Role {

  Mode mode;

  public Ouvriere() {
    mode = new Chasse();
  }

  public void setMode(Mode mode) {
    this.mode = mode;
  }

  public Mode getMode(Mode mode) {
    return this.mode;
  }


  // changer de place
  
  /**
   * Fonction qui permet de faire évoluer la fourmi en fonction de son role.
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {

    Point point;
    Fourmi fourmi = (Fourmi) contexte.getIndividu();

    // trouver le noouveau point
    point =
        contexte.getTerrain().getChemin().getChemin(fourmi, Phero.PheroProie, Phero.PheroChasse);

    // dire qu'on change de place
    contexte.getTerrain().getChemin().changerEmplacement(fourmi.getPos(), point, fourmi);

    fourmi.setPos(point);

    // déposer phéromone en fonction de si elle a trouvé
    contexte.getTerrain().getChemin().ajouterPheromone(point, Phero.PheroChasse);

  }

  public void bilan(Bilan bilan) {
    bilan.incr("Ouvriere");
  }


}
