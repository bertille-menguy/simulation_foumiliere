package fourmiliere;

import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import etats.Adulte;
import etresVivants.Fourmi;
import terrain.Chemin;
import vue.ContexteDeSimulation;


/**
 * Classe qui permet de creer une fourmiliere.
 *
 * @author .
 *
 */
public class Fourmiliere {

  private List<Fourmi> population;
  private Point pos;
  private Dimension dim;
  private Bilan bilan;
  private Stock stock;

  public Point getPos() {
    return pos;
  }

  public Dimension getDimension() {
    return dim;
  }

  /**
   * Constructeur de la fourmiliere.
   *
   * @param pos Point ou se situe la fourmiliere.
   */
  public Fourmiliere(Point pos) {
    this.population = new ArrayList<>();
    this.pos = pos;
    this.dim = new Dimension(80, 80);
    this.bilan = new Bilan();
    this.stock = new Stock();
  }

  public Stock getStock() {
    return this.stock;
  }

  public void ponte(Fourmi oeuf) {
    this.population.add(oeuf);
  }

  public void setReine(Fourmi reine) {
    population.add(reine);
  }

  /**
   * Fonction qui fait évoluer la fourmiliere.
   *
   * @param contexte Contexte de la fourmiliere.
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {

    Fourmi[] mesFourmis = this.population.toArray(new Fourmi[this.population.size()]);
    contexte.setFourmiliere(this);
    for (Fourmi fourmi : mesFourmis) {
      fourmi.etapeDeSimulation(contexte);

      // faire le bilan des états
      fourmi.getEtat().bilan(bilan);

      // faire le bilan des rôles quand c'est un adulte
      if (fourmi.getEtat() instanceof Adulte) {
        ((Adulte) fourmi.getEtat()).getRole().bilan(bilan);
      }
    }


    contexte.getFourmiliere().getStock().perimer();
    contexte.getFourmiliere().getStock().gestionDepot();


    // ------------------- affichage --------------- //

    // bilan
    System.out.println(this.bilan.asString());

    // stock
    // System.out.println(Stock.asString());
    // System.out.println(Stock.listeNourriture);
    System.out.println(
        "Nb élements dans réserve foumilière : " + contexte.getFourmiliere().getStock().size());
    System.out.println("Liste déchets à enlever de la fourmiliere : "
        + contexte.getFourmiliere().getStock().listeDepot);


    // remettre les compteurs à zéro pour le tour d'après
    bilan.clear();



  }

}
