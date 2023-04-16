package etats;

import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'état d'un individu de type oeuf.
 *
 * @author .
 *
 */
public class Oeuf extends Etat {

  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {}

  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.white);
    vue.setDimension(new Dimension(3, 3));
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Oeuf");
  }


}
