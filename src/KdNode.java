import java.util.*;


public class KdNode {
	//ou classe privée de KdTree comme utilisateur a pas besoin de savoir la structure du KdTree
	//fils x2; couleur, coord x et y; profondeur

	private Couleurs couleur;
	
	private int p;
	
	private KdNode fils1 = null;
	private KdNode fils2 = null;
	
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
			if (this.fils1==null){
				this.fils1 = point;
				return p+1;
			}
			else{
				this.fils1.addPointNode(point);
			}
		
		case -1: //fils de droite
			if (this.fils2==null){
				this.fils2 = point;
				return (p +1);
			}
			else{
				this.fils2.addPointNode(point);
			}
		default: //0
			return this.p;//le point est deja dedans
			
		}
		
	}
	
	public int getNombreFils(){
		if(this.fils1 != null && this.fils2 != null){
			return 2;
		}
		else if (this.fils1 != null || this.fils2 != null){
			return 1;
		}
		else{
			return 0;
		}
	}
	
	public KdNode getFils1(){
		return this.fils1;
	}
	
	public KdNode getFils2(){
		return this.fils2;
	}
	
	public void replaceNode(KdNode point){ //remplace this par point
		this.couleur = point.getCouleur();
		this.fils1 = point.getFils1();
		this.fils2 = point.getFils2();
	}
	
	
	public ArrayList<KdNode> getPointFromTree(KdNode point, ArrayList<KdNode> liste){ //donne tous les points en dessous dans l'arbre
		if (point != null){
 			liste.add(point);
			if(point.getFils1()!=null){
				getPointFromTree(point.getFils1(),liste);
			}
			else if(point.getFils2()!=null){
				getPointFromTree(point.getFils2(),liste);
			}
		}
		return liste;
	}
	
	
	public void removePointNode(KdNode point){ // enleve point et reconstruit arbre
		int direction = this.getDirection();
		switch (this.couleur.comparaison(direction, point.getCouleur())){
		case 1: //fils de gauche
			this.fils1.removePointNode(point);
		
		case -1: //fils de droite
			this.fils2.removePointNode(point);
			
		default: //0 c'est le point à supprimer
			//son fils de droite va prendre sa place, tous les fils de gauche reconstruits
			point.replaceNode(point.getFils2());//il emmene tous ses fils avec 
			ArrayList<KdNode> liste = new ArrayList<KdNode>();
			getPointFromTree(point.getFils1(), liste);
			for (int i=0;i<liste.size();i++){
				point.getFils2().addPointNode(liste.get(i)); // point fils ou point tt court
			}
			
			
		}
	}


}
