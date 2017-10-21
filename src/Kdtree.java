
public class Kdtree{

	private Couleur couleurR;
	private KdNode Racine;

	public Kdtree (int RVB[], int XY[]){
		this.couleurR=new Couleur(RVB[0], RVB[1], RVB[2]);
		this.Racine= new KdNode(couleurR, XY,0);
	}
	
	public void removePoint(int XY[]){
		
	}
	//Retire un noeud de l’arbe
	public void addpoint(int XY[], int RVB[]){
		
	}
	/*Ajoute un point à la racine de l’arbre, en créant une couleur associée à RVB
	Met à jour profondeur
	>> la méthode addpoint de KdNode devra renvoyer un entier la profondeur du nouveau noeud créé*/
	
	/*public int addpoint(Couleur RVB, int[] XY){ 
	 	if(RVB.compare(couleur, P%3)){
			if (fils[0]){
				filsG.addpoint(RVB, XY);
			}
			else{ 
				filsG= new KdNode(RVB,XY, P+1);
				fils[0]=true; return P+1;
			}
		}
		else{
			if (fils[1]){
				filsD.addpoint(RVB, XY);
			}
				else{ 
					filsD= new KdNode(RVB,XY,P+1);
					fils[1]=true; return P+1;
				}
		}*/
}
/*couleurR= couleur de la racine (class Couleur)
Profondeur=profondeur de l’arbre
Racine = Premier noeud de l’arbe (class KdNode)*/