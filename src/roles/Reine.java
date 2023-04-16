package roles;


import java.util.Random;

import etats.Oeuf;
import etresVivants.Fourmi;
import etresVivants.Individu;
import fourmiliere.Bilan;
import vue.ContexteDeSimulation;

/**
 * Classe qui définit une fourmi au role de Reine.
 *
 * @author .
 *
 */
public class Reine extends Role {

  
  /**
   * Fonction qui fait évoluer la fourmi en fonction de son role.
   *
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {
    Individu reine = contexte.getIndividu();
    Random rand = new Random();
    int nb = rand.nextInt(5);
    for (int i = 0; i < nb; i++) {
      Fourmi oeuf = new Fourmi(reine.getPos());
      int duree = rand.nextInt(5000);
      oeuf.setEtat(new Oeuf());
      oeuf.setDureeDeVie(duree);
      contexte.getFourmiliere().ponte(oeuf);
      contexte.getSimulation().nouvelIndividu(oeuf);
    }
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Reine");
  }


}
