package etats;

import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'état d'une fourmi de type Nymphe.
 *
 * @author .
 *
 */
public class Nymphe extends Etat {

  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {}

  @Override
  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.pink);
    vue.setDimension(new Dimension(5, 5));
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Nymphe");
  }


}
