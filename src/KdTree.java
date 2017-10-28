
public class KdTree {
	// un pixel --> coordonnées, RVB, fils1, fils2, choix sous R ou V ou B 
	// + grand à dte, + petit à gauche
	
	private int profondeur; //profondeur globale
	private Couleurs couleur; // couleur racine
	private KdNode Racine;
	
	
		public KdTree(int profondeur, int RVB[]){//tableau avec profondeur tot;x,y de la racine et sa couleur
			this.profondeur = profondeur;
			this.couleur = new Couleurs(RVB);
			this.Racine = new KdNode(0,couleur);
	}
	
	public void addPoint(int RVB[]){ // remettre la profondeur bien
		Couleurs couleur = new Couleurs(RVB);
		KdNode point = new KdNode(0,couleur);
		int p = this.Racine.addPointNode(point);
		
		if (p>this.profondeur){
			this.profondeur = p;
		}
	}
	
	public void removePoint(int RVB[],int XY[]){
		Couleurs couleur = new Couleurs(RVB);
		KdNode point = new KdNode(0,couleur);
		this.Racine.removePointNode(point);
				
	}

}
