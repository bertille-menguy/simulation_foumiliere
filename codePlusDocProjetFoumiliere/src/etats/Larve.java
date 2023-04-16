package etats;

import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

/**
 * Classe d'état d'un fourmi de type larve.
 *
 * @author .
 *
 */
public class Larve extends Etat {

  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {}

  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.yellow);
    vue.setDimension(new Dimension(5, 5));
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Larve");
  }


}
