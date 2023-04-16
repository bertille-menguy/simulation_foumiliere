package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import terrain.Terrain;

/**
 * Classe qui permet de creer une vue de terrain.
 *
 * @author .
 *
 */
class VueTerrain extends VueElement {
  private static final long serialVersionUID = -1082764165537478273L;
  Terrain terrain;

  public Terrain getTerrain() {
    return terrain;
  }

  /**
   * Constructeur d'une vue terrain.
   *
   * @param terrain le terrain à faire afficher dans le vue.
   */
  public VueTerrain(Terrain terrain) {
    this.terrain = terrain;
    Point pos = terrain.getPos();
    Dimension dim = terrain.getDimension();
    this.setBounds(pos.x, pos.y, dim.width, dim.height);
    Color source = Color.green;
    int alpha = 20;
    Color tc = new Color(source.getRed(), source.getGreen(), source.getBlue(), alpha);
    this.setBackground(tc);
  }

  @Override
  public void redessine() {}
}
