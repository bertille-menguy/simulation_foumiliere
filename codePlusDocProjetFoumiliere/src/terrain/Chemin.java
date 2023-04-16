package terrain;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import Pheromones.Phero;
import etats.Captive;
import etresVivants.Individu;
import etresVivants.Proie;
import vue.ContexteDeSimulation;


/**
 * Classe qui permet de garder en mémoire les traces des individus.
 *
 * @author .
 *
 */
public class Chemin {

  static Map<String, Case> emplacements = new HashMap<String, Case>();

  
  /**
   * Fonction qui permet de fournir un chemin aléatoire à un individu.
   *
   * @param individu Individu qui doit changer de place.
   * @return le nouveau point/place de l'individu.
   */
  public Point cheminAleatoire(Individu individu) {
    // aléatoire si rien
    int x = individu.getPos().x;
    int y = individu.getPos().y;
    int pos;
    Random rand = new Random();
    pos = rand.nextInt(4);
    switch (pos) {
      case 0: {
        y = y + 1;
        break;
      }
      case 1: {
        x = x + 1;
        break;
      }
      case 2: {
        y = y - 1;
        break;
      }
      case 3: {
        x = x - 1;
        break;
      }
    }
    Point point = new Point(x, y);
    return point;
  }

  /**
   * Fonction qui retource la Case en fonction du point de l'individu.
   *
   * @param p Point de l'individu.
   * @return la case ou se ytrouve l'individu.
   */
  public Case getEmplacement(Point p) {
    if (emplacements.get(p.toString()) == null) {
      emplacements.put(p.toString(), new Case());
    }
    return emplacements.get(p.toString());
  }

  /**
   * Fonction qui ajoute une phéromone à son .
   *
   * @param point Point ou ajouter la phéromone.
   * @param pheromone Type de phéromone à ajouter.
   */
  public void ajouterPheromone(Point point, Phero pheromone) {
    if (emplacements.get(point.toString()) == null) {
      emplacements.put(point.toString(), new Case());
    }
    emplacements.get(point.toString()).ajouterIntensite(pheromone);
  }

  /**
   * Fonction pour changer d'emplacement à l'individu.
   *
   * @param ancienPoint Ancien Point de l'individu.
   * @param nouveauPoint Nouveau Point de l'individu.
   * @param individu Individu qui doit changer de place.
   */
  public void changerEmplacement(Point ancienPoint, Point nouveauPoint, Individu individu) {
    if (emplacements.get(nouveauPoint.toString()) == null) {
      emplacements.put(nouveauPoint.toString(), new Case());
    }
    if (emplacements.get(ancienPoint.toString()) != null) {
      emplacements.get(ancienPoint.toString()).enleverIndividu(individu);
    }
    emplacements.get(nouveauPoint.toString()).ajouterIndividu(individu);
  }

  /**
   * Fonction qui renvoie tous les points ou il y à le moins de phéromones à éviter. 
   * Vérifie si la case n'est pas occupée.
   *
   * @param individu Individu à placer.
   * @param pheromoneAEviter Pheromone a éviter.
   * @return Liste des points où aller.
   */
  public ArrayList<Point> evitePhero(Individu individu, Phero pheromoneAEviter) {
    int x = individu.getPos().x;
    int y = individu.getPos().y;

    Point pointDroit = new Point(x + 1, y);
    Point pointGauche = new Point(x - 1, y);
    Point pointHaut = new Point(x, y + 1);
    Point pointBas = new Point(x, y - 1);

    Map<Point, Integer> liste = new HashMap<Point, Integer>();

    liste.put(pointDroit, getEmplacement(pointDroit).getIntensite(pheromoneAEviter));
    liste.put(pointGauche, getEmplacement(pointGauche).getIntensite(pheromoneAEviter));
    liste.put(pointHaut, getEmplacement(pointHaut).getIntensite(pheromoneAEviter));
    liste.put(pointBas, getEmplacement(pointBas).getIntensite(pheromoneAEviter));


    ArrayList<Point> listePoint = new ArrayList<>();
    Map<Point, Integer> listePointLibre = new HashMap<Point, Integer>();

    // --- 1 Regarder si la voisine est une Proie ---- //

    Iterator it1 = liste.entrySet().iterator();
    Map.Entry val;
    while (it1.hasNext()) {
      val = (Map.Entry) it1.next();
      if (getEmplacement((Point) val.getKey()).isProie()) {
        Proie p = (Proie) getEmplacement((Point) val.getKey()).getIndividu();
        p.setEtat(new Captive());
        listePoint.add(individu.getPos());
        // pas bouger
        return listePoint;
      }
    }


    // --- 2 Recuper les places vides

    Iterator it2 = liste.entrySet().iterator();
    while (it2.hasNext()) {
      val = (Map.Entry) it2.next();
      if (getEmplacement((Point) val.getKey()).placeLibre()) {
        listePointLibre.put((Point) val.getKey(), (Integer) val.getValue());
      }
    }

    // --- 3 recup celles ou il y a le moins de phéro

    if (!listePointLibre.isEmpty()) {
      Iterator it3 = listePointLibre.entrySet().iterator();
      val = (Map.Entry) it3.next();
      Integer min = (Integer) val.getValue();

      while (it3.hasNext()) {
        val = (Map.Entry) it3.next();
        if (min > (Integer) val.getValue()) {
          min = (Integer) val.getValue();
        }
      }

      // --- recup les point
      Iterator it4 = listePointLibre.entrySet().iterator();
      while (it4.hasNext()) {
        val = (Map.Entry) it4.next();
        if (min == val.getValue()) {
          listePoint.add((Point) val.getKey());
        }
      }
    }



    return listePoint;
  }

  /**
   * Fonction qui renvoie la liste des points ou il y a le plus de phéromones rechercher.
   * Vérifié si les case sont disponibles.
   * Remarque : ici meme principe que la fonction evitePhero < et > qui diffère, à améliorer.
   *
   * @param individu Individu à placer.
   * @param pheromoneRecherche Pheromone à rechercher.
   * @return Liste des points où aller.
   */
  public ArrayList<Point> allerPhero(Individu individu, Phero pheromoneRecherche) {
    int x = individu.getPos().x;
    int y = individu.getPos().y;

    Point pointDroit = new Point(x + 1, y);
    Point pointGauche = new Point(x - 1, y);
    Point pointHaut = new Point(x, y + 1);
    Point pointBas = new Point(x, y - 1);

    Map<Point, Integer> liste = new HashMap<Point, Integer>();

    liste.put(pointHaut, getEmplacement(pointHaut).getIntensite(pheromoneRecherche));
    liste.put(pointBas, getEmplacement(pointBas).getIntensite(pheromoneRecherche));
    liste.put(pointDroit, getEmplacement(pointDroit).getIntensite(pheromoneRecherche));
    liste.put(pointGauche, getEmplacement(pointGauche).getIntensite(pheromoneRecherche));


    ArrayList<Point> listePoint = new ArrayList<>();
    Map<Point, Integer> listePointLibre = new HashMap<Point, Integer>();

    // --- 1 Regarder si la voisine est une Proie ---- //

    Iterator it1 = liste.entrySet().iterator();
    Map.Entry val;
    while (it1.hasNext()) {
      val = (Map.Entry) it1.next();
      if (getEmplacement((Point) val.getKey()).isProie()) {
        Proie p = (Proie) getEmplacement((Point) val.getKey()).getIndividu();
        p.setEtat(new Captive());
        listePoint.add(individu.getPos());
        // pas bouger
        return listePoint;
      }
    }


    // --- 2 Recuper les places vides

    Iterator it2 = liste.entrySet().iterator();
    while (it2.hasNext()) {
      val = (Map.Entry) it2.next();
      if (getEmplacement((Point) val.getKey()).placeLibre()) {
        listePointLibre.put((Point) val.getKey(), (Integer) val.getValue());
      }
    }

    // --- 3 recup celles ou il y a le plus de phéro

    if (!listePointLibre.isEmpty()) {
      Iterator it3 = listePointLibre.entrySet().iterator();
      val = (Map.Entry) it3.next();
      Integer max = (Integer) val.getValue();
      while (it3.hasNext()) {
        val = (Map.Entry) it3.next();
        if (max < (Integer) val.getValue()) {
          max = (Integer) val.getValue();

        }

      }

      // --- recup les point
      Iterator it4 = listePointLibre.entrySet().iterator();
      while (it4.hasNext()) {
        val = (Map.Entry) it4.next();
        if (max == val.getValue()) {
          listePoint.add((Point) val.getKey());
        }
      }
    }
    return listePoint;
  }

  /**
   * Fonction qui renvoie la meilleure position ou aller de l'individu.
   *
   * @param individu Indivi.
   * @param pheromoneRecherche Type de phéromone à chercher.
   * @param pheromoneAEviter Type de phéromone à éviter.
   * @return le nouveau point ou aller.
   */
  public Point getChemin(Individu individu, Phero pheromoneRecherche, Phero pheromoneAEviter) {

    ArrayList<Point> listAller = allerPhero(individu, pheromoneRecherche);
    ArrayList<Point> listFuire = evitePhero(individu, pheromoneAEviter);

    listAller.addAll(listFuire);

    // si case pas de case dispo --> pas bouger
    if (listAller.isEmpty()) {
      return individu.getPos();
    }

    // choisir au hasard
    Random rand = new Random();
    int pos = rand.nextInt(listAller.size());
    return listAller.get(pos);

  }


  /**
   * Fonction qui sert à mettre à jour les phéromones et les décrémenter.
   *
   * @param contexte Contexte de la Case contenant les phéromones.
   */
  public void etapeDeSimulation(ContexteDeSimulation contexte) {
    for (Map.Entry mapentry : emplacements.entrySet()) {
      Case c = (Case) mapentry.getValue();
      c.etapeDeSimulation(contexte);
    }
  }

}
