package roles;

import java.awt.Point;
import java.util.Random;

import Pheromones.Phero;
import etresVivants.Fourmi;
import etresVivants.Sexe;
import fourmiliere.Bilan;
import terrain.Chemin;
import vue.ContexteDeSimulation;

/**
 * Classe qui difinit un Individu au role d'indivu sexuel.
 *
 * @author .
 *
 */

public class IndividuSexue extends Role {
  private Sexe sexe;


  /**
   * Contructeur d'un individu sexuel.
   */
  public IndividuSexue() {
    super();
    Random rand = new Random();
    int proba = rand.nextInt(100);

    // 50 % de males et 50 % de femelles
    if (proba < 50) {
      this.setSexe(Sexe.femelle);
    } else {
      this.setSexe(Sexe.male);
    }
  }


  public Sexe getSexe() {
    return sexe;
  }


  public void setSexe(Sexe sexe) {
    this.sexe = sexe;
  }

  @Override
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
    bilan.incr("IndividuSexue");
  }



}
