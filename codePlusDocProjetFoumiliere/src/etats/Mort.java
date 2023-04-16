package etats;

import fourmiliere.Bilan;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'état d'un individu de type mort.
 *
 * @author .
 *
 */
public class Mort extends Etat {


  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {

  }

  @Override
  public void initialise(VueIndividu vue) {
    vue.setBackground(Color.green);
    vue.setDimension(new Dimension(3, 3));
    vue.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Mort");
  }



}
