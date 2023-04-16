package etats;

import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'état de type Proie captivée.
 *
 * @author .
 *
 */
public class Captive extends Etat {


  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {}

  @Override
  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.magenta);
    vue.setDimension(new Dimension(6, 6));
  }

  @Override
  public void bilan(Bilan bilan) {}

}
