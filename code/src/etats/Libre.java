package etats;

import Pheromones.Phero;
import etresVivants.Proie;
import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'état d'une proie de type libre.
 *
 * @author .
 *
 */
public class Libre extends Etat {

  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {

    Point point;
    Proie proie = (Proie) contexte.getIndividu();

    point = contexte.getTerrain().getChemin().cheminAleatoire(proie);

    // dire qu'on change de place
    contexte.getTerrain().getChemin().changerEmplacement(proie.getPos(), point, proie);

    proie.setPos(point);

    // déposer phéromone en fonction de si elle a trouvé
    contexte.getTerrain().getChemin().ajouterPheromone(proie.getPos(), Phero.PheroProie);
  }

  @Override
  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.green);
    vue.setDimension(new Dimension(5, 5));
  }

  public void bilan(Bilan bilan) {

  }

}
