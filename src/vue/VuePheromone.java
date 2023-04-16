package vue;

import java.awt.Color;
import java.awt.Point;

/**
 * Classe qui cree une vue de pheromone.
 *
 * @author .
 *
 */
public class VuePheromone extends VueElement {

  private static final long serialVersionUID = -4841180339460220613L;
  Point point;

  /**
   * Constructeur d'une vue de pheromone. 
   *
   * @param point Point ou se situe la pheromone à faire afficher dans la vue.
   */
  public VuePheromone(Point point) {
    this.point = point;
    this.setBackground(Color.PINK);
    this.setBounds(point.x, point.y, 2, 2);
  }

  @Override
  public void redessine() {

  }


}
