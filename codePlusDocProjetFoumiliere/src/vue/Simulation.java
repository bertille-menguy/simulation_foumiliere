package vue;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import etresVivants.Individu;
import fourmiliere.Fourmiliere;
import nicellipse.component.NiSpace;
import terrain.Terrain;

/**
 * Classe de simulation de la fourmiliere dans un espace.
 *
 * @author bertillemenguy
 *
 */
public class Simulation {
  NiSpace space = new NiSpace("Simulation Fourmis", new Dimension(800, 800));
  Terrain terrain = new Terrain(new Point(10, 10), new Dimension(700, 700));
 
  final int niveau_fourmiliere = 1;
  final int niveau_individu = 2;
  final int niveau_individu1 = 3;
  final int niveau_proie = 4;
  final int niveau_pheromone = 2;



  /**
   * Constructeur d'une Simulation.
   */
  public Simulation() {
    space.setDoubleBuffered(true);
    space.openInWindow();
    this.nouveauTerrain(terrain);
  }


  public Terrain getTerrain() {
    return this.terrain;
  }

  /**
   * Creation d'une Vue d'un terrain.
   *
   * @param terrain Terrain à afficher.
   */
  public void nouveauTerrain(Terrain terrain) {
    VueTerrain v = new VueTerrain(terrain);
    this.space.add(v);
    this.space.repaint();
  }


  /**
   * Creation d'une Vue d'une pheromone.
   *
   * @param point Point ou se situe la pheromone à afficher.
   */
  public void nouvellePheromone(Point point) {
    VuePheromone v = new VuePheromone(point);
    this.space.add(v, this.niveau_pheromone, 0);
    this.space.repaint();
  }

  /**
   * Creation d'une Vue d'une fourmiliere.
   *
   * @param fourmiliere Fourmiliere à afficher.
   */
  public void nouvelleFourmiliere(Fourmiliere fourmiliere) {
    VueFourmiliere v = new VueFourmiliere(fourmiliere);
    // Ajoute l'individu au dessus du terrain
    this.space.add(v, this.niveau_fourmiliere, 0);
    this.space.repaint();
  }

  /**
   * Creation d'une vue d'un individu.
   *
   * @param individu Individu à afficher.
   */
  public void nouvelIndividu(Individu individu) {
    VueIndividu v = new VueIndividu(individu);
    // Ajoute l'individu au dessus de la fourmiliere
    this.space.add(v, this.niveau_individu, 0);
    this.space.repaint();
  }

  /**
   * Fonction qui sert à démarer l'animation.
   *
   */
  public void startGraphicAnimation() {
    GraphicAnimation animation = new GraphicAnimation();
    animation.start();
  }

  /**
   * Creation d'un classe enfuie qui creer une animation graphique.
   *
   * @author .
   *
   */
  class GraphicAnimation implements ActionListener {

    final int graphicAnimationDelay1 = 100;


    // delais
    final int graphicAnimationDelay = 10;


    /**
     * Fonction qui met à jour les vues.
     */
    public void actionPerformed(ActionEvent e) {
      Component[] views = Simulation.this.space.getComponents();
      for (int i = 0; i < views.length; i++) {
        Component c = views[i];
        if (c instanceof VueElement) {
          VueElement next = (VueElement) c;
          next.mettreAJourVue();
        }
      }
      terrain.etapeDeSimulation(new ContexteDeSimulation(Simulation.this));

    }

    /**
     * Fonction récursive qui affiche l'animation de la fourmiliere.
     *
     */
    public void start() {
      Timer animation = new Timer(0, this);
      animation.setDelay(this.graphicAnimationDelay1);
      animation.start();
    }
  }

  /**
   * Programme principal.
   *
   * @param args tableau de caractères.
   */
  public static void main(String[] args) {
    Simulation simulation = new Simulation();
    simulation.startGraphicAnimation();
  }
}
