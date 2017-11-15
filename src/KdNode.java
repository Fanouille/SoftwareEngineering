

public class KdNode {
	int P;
	Couleur coul;
	KdNode filsG;
	KdNode filsD;
	boolean[] fils= {false,false};
	public KdNode(Couleur C, int p)
	{
		//Constructeur : P=profondeur du noeud, coul= Couleur
		P=p;
		coul=C;
	}
	
	public int addpointnode(Couleur RVB)
	{
		//ajoute un point par recursivite et renvoie la profondeur a laquelle il a �t� ajout�
		if (RVB.getRVB()[P%3]>coul.getRVB()[P%3])
		{
			if(fils[1])
			{
				return(filsD.addpointnode(RVB));
			}
			else
			{
				fils[1]=true;
				filsD= new KdNode(RVB, P+1);
				return P+1;
			}
		}
		else
		{
			if(fils[0])
			{
				return(filsG.addpointnode(RVB));
			}
			else
			{
				fils[0]=true;
				filsG= new KdNode(RVB, P+1);
				return P+1;
			}
		}
	}
	public Couleur getcoul()
	{
		return coul;
	}
	public Couleur[] palnode(Couleur[] pal)
	{
		int L= pal.length;
		if(L==2)
		{
			pal[0]=filsD.Moy();
			pal[1]=filsG.Moy();
			return pal;
		}
		else
		{
			int i;
			Couleur[] A=new Couleur[L/2];
			Couleur[] B=new Couleur[L/2];
			if(fils[0]) {A=filsG.palnode(A);}
			else {
				for(i=0;i<L/2;i++)
				{
					A[i]=new Couleur(0,0,0);
				}
			}
			if(fils[1]) {B=filsD.palnode(B);}
			else
			{
				for(i=0;i<L/2;i++)
				{
					B[i]=new Couleur(0,0,0);
				}
			}
			for(i=0;i<L;i++)
			{
				if(i<L/2) {pal[i]=A[i];}
				else {pal[i]=B[i-L/2];}
			}
			return pal;
			
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
		this.coul = point.getcoul();
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
	
	public int getDirection(){
		return this.P % 3; //0:R;1:V;2:B
	}
	
	public int removePointNode(KdNode point){ // enleve point et reconstruit arbre, renvoie profondeur 
		int direction = this.getDirection();
		int new_p = 0;
		switch (this.coul.comparaison(direction, point.getcoul())){
		case 1: //fils de gauche
			return (this.filsG.removePointNode(point));
		
		case -1: //fils de droite
			return (this.filsD.removePointNode(point));
			
		default: //0 
			if (this.coul.egal(point.getcoul())){//c'est le point à supprimer
				//son fils de droite va prendre sa place, tous les fils de gauche reconstruits
				point.replaceNode(point.getFilsD());//il emmene tous ses fils avec 
				
				ArrayList<KdNode> liste = new ArrayList<KdNode>();
				getPointFromTree(point.getFilsG(), liste);
				for (int i=0;i<liste.size();i++){
					new_p = point.getFilsD().addpointnode(liste.get(i).getcoul()); // point fils ou point tt court
				}
				//calcul de la nouvelle profondeur
				if (new_p<KdTree.getProf()){
					return new_p;
				}
				else{
					return KdTree.getProf();
				}
			}
			
			else{// c'est un autre point on va à gauche
				return (this.filsG.removePointNode(point));
			}
			
			
			
		}
	}
	
	
	public Couleur Moy()
	{
		if(fils[0] && fils[1])
		{
			Couleur A = (filsD.Moy()).moyenne(coul);
			A=(filsG.Moy()).moyenne(A);
			return A;
		}
		else if(fils[1])
		{
			Couleur A = (filsD.Moy()).moyenne(coul);
			return A;
		}
		else if(fils[0])
		{
			Couleur A = (filsG.Moy()).moyenne(coul);
			return A;
		}
		else
		{
			return coul;
		}
	}

	public Couleur getNN(Couleur A)
	{
		if ((A.getRVB())[P%3] > (coul.getRVB())[P%3])
		{
			if (fils[1])
			{
				Couleur CurrentBest = filsD.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && fils[0])
				{
					Couleur Alter = filsG.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
		else
		{
			if (fils[0])
			{
				Couleur CurrentBest = filsG.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && fils[1])
				{
					Couleur Alter = filsD.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
	}
}
