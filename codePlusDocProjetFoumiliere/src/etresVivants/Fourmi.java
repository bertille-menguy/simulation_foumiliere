package etresVivants;

import java.awt.Point;
import etats.Adulte;
import etats.Etat;
import etats.Larve;
import etats.Mort;
import etats.Nymphe;
import etats.Oeuf;
import vue.ContexteDeSimulation;
import vue.VueIndividu;


/**
 * Classe d'individu type fourmi.
 *
 * @author .
 *
 */
public class Fourmi extends Individu {

  private int dureeDeVie;
  private Etat etat;
  private int age;
  private int energie;

  /**
   * Constructeur de la fourmi.
   *
   * @param point Point ou se situe la fourmis.
   */
  public Fourmi(Point point) {
    this.setAge(0);
    this.setEtat(new Oeuf());
    this.setPos(point);
    this.energie = 720;
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

  public int getEnergie() {
    return this.energie;
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
   * Fonction qui sert à faire évoluer la fourmi en fonction du temps.
   *
   * @param contexte Contexte de la fourmilière.
   */
  public void evolution(ContexteDeSimulation contexte) {
    if (!(this.etat instanceof Mort)) {
      this.age++;
      switch (this.age) {
        case 3:
          this.etat = new Larve();
          this.setPoids(6);
          break;
        case 10:
          this.etat = new Nymphe();
          this.setPoids(0);
          break;
        case 20:
          this.etat = new Adulte();
          this.setPoids(2);
          break;
      }
      if (this.age == this.dureeDeVie) {
        if (this.getPoids() > 0.0) {
          contexte.getFourmiliere().getStock().ajouterAuStock(this);
        }

        this.etat = new Mort();

      }
    }
  }

  /**
   * Fonction qui fait perdre de l'énergie à fourmis.
   *
   * @param contexte Contexte de la fourmiliere.
   */
  public void perteEnergie(ContexteDeSimulation contexte) {
    this.energie--;
    if (!(this.etat instanceof Mort)) {
      if (this.energie < 13) {
        if (contexte.getFourmiliere().getStock().size() != 0) {
          contexte.getFourmiliere().getStock().supprimerDuStock(this.getPoids());
          this.energie = 72;
          // nettoyage();
        } else {
          if (this.energie == 0) {
            this.age = this.dureeDeVie - 1;
          }
        }
      }
    }



  }

  public void initialise(VueIndividu vue) {
    this.etat.initialise(vue);
  }

  /**
   * Fonction qui fait évoluer la fourmi en fonction du temps.
   *
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {
    super.etapeDeSimulation(contexte);
    this.evolution(contexte);
    this.perteEnergie(contexte);
    this.etat.etapeDeSimulation(contexte);

  }


}
