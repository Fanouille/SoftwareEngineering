public class KdNode {
	
	private Couleur couleur;
	private int coordX,coordY;
	private int P;
	private boolean[] fils= {false,false};
	private KdNode filsD; private KdNode filsG;
	
	public KdNode (Couleur RVB, int[] XY, int Prof)
	{
	/* CONSTRUCTEUR
	* Argument: un objet couleur, un tableau avec 2 nombres X,Y, et la profondeur du noeud.
	*/
		this.couleur=RVB;
		this.coordX=XY[0];
		this.coordY=XY[1];
		this.P=Prof;
	}
}
