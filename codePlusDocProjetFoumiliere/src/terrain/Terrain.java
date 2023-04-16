package terrain;

import java.awt.Point;
import java.util.Random;

import java.awt.Dimension;

import etats.Adulte;
import etresVivants.Fourmi;
import etresVivants.Proie;
import fourmiliere.Fourmiliere;
import proies.Proies;
import roles.Reine;
import vue.ContexteDeSimulation;

/**
 * Classe Terrain ou se situe la fourmiliere.
 *
 * @author .
 *
 */
public class Terrain {

  protected Point pos;
  protected Dimension dim;
  Fourmiliere fourmiliere;
  Proies proies;
  Chemin chemin;

  public Chemin getChemin() {
    return this.chemin;
  }

  public Point getPos() {
    return this.pos;
  }

  public Dimension getDimension() {
    return this.dim;
  }


  /**
   * Constructeur du terrain.
   *
   * @param pos Point ou se situe le terain.
   * @param dim Dimension du terrain.
   */
  public Terrain(Point pos, Dimension dim) {
    this.pos = pos;
    this.dim = dim;
    this.chemin = new Chemin();
  }

  /**
   * Fonction qui fait évoluer dans le temps la fourmiliere.
   *
   * @param contexte Contexte du terrain.
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {


    if (fourmiliere == null) {
      Point p =
          new Point(this.pos.x + this.dim.width / 2 - 30, this.pos.y + this.dim.height / 2 - 30);
      fourmiliere = new Fourmiliere(p);
      Point posReine = new Point(p.x + 15, p.y + 15);

      // création de la reine
      Fourmi laReine = new Fourmi(posReine);

      laReine.setAge(30);
      laReine.setDureeDeVie(500);
      laReine.setPoids(2);
      laReine.setEtat(new Adulte(new Reine()));
      contexte.getSimulation().nouvelIndividu(laReine);

      fourmiliere.setReine(laReine);

      // création du nouvel affichage de la fourmilière mise à jour
      contexte.getSimulation().nouvelleFourmiliere(fourmiliere);

    }
    if (proies == null) {

      // ----- par défault création de 10 proies
      proies = new Proies();

      for (int i = 0; i < 100; i++) {

        Random rand = new Random();
        int posx = rand.nextInt(600);
        int posy = rand.nextInt(600);

        int duree_vie = 100 + rand.nextInt(10000 - 100);

        // création proie
        Point posProie = new Point(posx, posy);
        Proie proie = new Proie(posProie);
        proie.setAge(3);
        proie.setDureeDeVie(duree_vie);
        proie.setPoids(20);

        proies.ajouterProie(proie);

        contexte.getSimulation().nouvelIndividu(proie);
      }

    }
    chemin.etapeDeSimulation(contexte);
    fourmiliere.etapeDeSimulation(contexte);
    proies.etapeDeSimulation(contexte);


  }

}
