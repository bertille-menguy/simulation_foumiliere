package roles;

import java.awt.Point;
import java.util.Random;


import etresVivants.Fourmi;
import fourmiliere.Bilan;
import terrain.Chemin;
import vue.ContexteDeSimulation;


// Ne servent qu'à defendre le nid
/**
 * Classe qui définit une fourmi au type soldat.
 *
 * @author .
 *
 */
public class Soldat extends Role {

  @Override
  public void etapeDeSimulation(ContexteDeSimulation contexte) {


    Fourmi fourmi = (Fourmi) contexte.getIndividu();
    Point point = contexte.getTerrain().getChemin().cheminAleatoire(fourmi);
    fourmi.setPos(point);
  }

  public void bilan(Bilan bilan) {
    bilan.incr("Soldat");
  }


}
