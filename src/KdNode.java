import java.util.*;


public class KdNode {
	//ou classe privée de KdTree comme utilisateur a pas besoin de savoir la structure du KdTree
	//fils x2; couleur, coord x et y; profondeur

	private Couleurs couleur;
	
	private int p;
	
	private KdNode filsG = null;
	private KdNode filsD = null;
	
	public KdNode(int prof, Couleurs col){
		
		this.p = prof;
		
		this.couleur = col ;
		
	}
	
	public int getDirection(){
		return this.p % 3; //0:R;1:V;2:B
	}
	
	public Couleurs getCouleur(){ //avoir couleur du noeud principal 
		return this.couleur;
	}
	
	public int getProfondeur(){
		return this.p;
	}
	
	public int addPointNode(KdNode point){
		int direction = this.getDirection();
		int p = this.getProfondeur();
		switch (this.couleur.comparaison(direction, point.getCouleur())){
		case 1: //fils de gauche
			if (this.filsG==null){
				this.filsG = point;
				return p+1;
			}
			else{
				this.filsG.addPointNode(point);
			}
		
		case -1: //fils de droite
			if (this.filsD==null){
				this.filsD = point;
				return (p +1);
			}
			else{
				this.filsD.addPointNode(point);
			}
		default: //0
			return this.p;//le point est deja dedans
			
		}
		
	}
	
	public int getNombreFils(){
		if(this.filsG != null && this.filsD != null){
			return 2;
		}
		else if (this.filsG != null || this.filsD != null){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public KdNode getFilsG(){
		return this.filsG;
	}
	
	public KdNode getFilsD(){
		return this.filsD;
	}
	
	public void replaceNode(KdNode point){ //remplace this par point
		this.couleur = point.getCouleur();
		this.filsG = point.getFilsG();
		this.filsD = point.getFilsD();
	}
	
	
	public ArrayList<KdNode> getPointFromTree(KdNode point, ArrayList<KdNode> liste){ //donne tous les points en dessous dans l'arbre
		if (point != null){
 			liste.add(point);
			if(point.getFilsG()!=null){
				getPointFromTree(point.getFilsG(),liste);
			}
			else if(point.getFilsD()!=null){
				getPointFromTree(point.getFilsD(),liste);
			}
		}
		return liste;
	}
	
	
	public void removePointNode(KdNode point){ // enleve point et reconstruit arbre
		int direction = this.getDirection();
		switch (this.couleur.comparaison(direction, point.getCouleur())){
		case 1: //fils de gauche
			this.filsG.removePointNode(point);
		
		case -1: //fils de droite
			this.filsD.removePointNode(point);
			
		default: //0 c'est le point à supprimer
			//son fils de droite va prendre sa place, tous les fils de gauche reconstruits
			point.replaceNode(point.getFilsD());//il emmene tous ses fils avec 
			ArrayList<KdNode> liste = new ArrayList<KdNode>();
			getPointFromTree(point.getFilsG(), liste);
			for (int i=0;i<liste.size();i++){
				point.getFilsD().addPointNode(liste.get(i)); // point fils ou point tt court
			}
			
			
		}
	}


}
