package vue;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;


import fourmiliere.Fourmiliere;

/**
 * classe qui cree une vue de fourmiliere.
 *
 * @author .
 *
 */
public class VueFourmiliere extends VueElement {
  private static final long serialVersionUID = -1082764165537478273L;
  Fourmiliere fourmiliere;

  public Color defaultBorderColor() {
    return Color.red;
  }

  public Fourmiliere getFourmiliere() {
    return fourmiliere;
  }

  /**
   * Constructeur de la vue fourmiliere.
   *
   * @param fourmiliere Fourmiliere à afficher dans la vue.
   */
  public VueFourmiliere(Fourmiliere fourmiliere) {
    this.fourmiliere = fourmiliere;
    Point pos = this.fourmiliere.getPos();
    Dimension dim = this.fourmiliere.getDimension();
    this.setBounds(pos.x, pos.y, dim.width, dim.height);
    this.setBackground((Color.red));
  }

  @Override
  public void redessine() {}
}
