
public class KdTree {
	// un pixel --> coordonn�es, RVB, fils1, fils2, choix sous R ou V ou B 
	// + grand � dte, + petit � gauche
	
	private int profondeur; //profondeur globale
	private Couleurs couleur; // couleur racine
	private KdNode racine;
	
	
		public KdTree(int profondeur, int RVB[]){//tableau avec profondeur tot;x,y de la racine et sa couleur
			this.profondeur = profondeur;
			this.couleur = new Couleurs(RVB);
			this.racine = new KdNode(0,couleur);
	}
	
	public void addPoint(int RVB[]){ // remettre la profondeur bien
		Couleurs couleur = new Couleurs(RVB);
		KdNode point = new KdNode(0,couleur);
		int p = this.racine.addPointNode(point);
		
		if (p>this.profondeur){
			this.profondeur = p;
		}
	}
	
	public void removePoint(int RVB[],int XY[]){
		Couleurs couleur = new Couleurs(RVB);
		KdNode point = new KdNode(0,couleur);
		this.racine.removePointNode(point);
				
	}

}
