package etresVivants;

import java.awt.Point;

import etats.Etat;
import etats.Libre;
import etats.Mort;
import fourmiliere.Stock;
import vue.ContexteDeSimulation;
import vue.VueIndividu;

/**
 * Classe d'individu de type proie.
 *
 * @author .
 *
 */
public class Proie extends Individu {

  private int dureeDeVie;
  private Etat etat;
  private int age;

  /**
   * Constructeur d'un proie.
   *
   * @param pos Point de la proie.
   */
  public Proie(Point pos) {
    this.setAge(0);
    this.setPos(pos);
    this.setEtat(new Libre());
  }

  public int getDureeDeVie() {
    return dureeDeVie;
  }

  public void setDureeDeVie(int dureeDeVie) {
    this.dureeDeVie = dureeDeVie;
  }

  public Etat getEtat() {
    return etat;
  }

  public void setEtat(Etat etat) {
    this.etat = etat;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  /**
   * Fonction qui sert à faire évoluer la proie.
   *
   * @param contexte Contexte de la fourmi.
   */
  public void evolution(ContexteDeSimulation contexte) {
    if (!(this.etat instanceof Mort)) {
      this.age++;
      if (this.age == this.dureeDeVie) {
        this.etat = new Mort();
        contexte.getFourmiliere().getStock().ajouterAuStock(this);
      }
    }
  }

  public void initialise(VueIndividu vue) {
    this.etat.initialise(vue);
  }

  /**
   * Fonction qui sert à faire évoluer la proie.
   *
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {
    super.etapeDeSimulation(contexte);
    this.evolution(contexte);
    this.etat.etapeDeSimulation(contexte);

  }

}
