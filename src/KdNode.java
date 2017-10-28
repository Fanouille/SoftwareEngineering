public class KdNode {  	
	private Couleur couleur;  	 	
	private int P;  	
	private boolean[] fils= {false,false}; 
	private KdNode filsD;  	
	private KdNode filsG; 
 	
	public KdNode (Couleur RVB, int Prof) 
 	{ 
 	 	/* CONSTRUCTEUR 
   * Argument: un objet couleur, un tableau avec 2 nombres X,Y, et la profondeur du noeud. 
 	 	 */ 
			this.couleur=RVB;   	 	
			this.P=Prof; 
 	} 

	public int addpointnode(Couleur RVB) 
 	{ 
 	 	if(RVB.compare(couleur, P%3)) 
 	 	{ 
 	 	 	if (fils[0]) 
 	 	 	{ 
 	 	 	 	filsG.addpointnode(RVB); 
 	 	 	} 
 	 	 	else 
 	 	 	{ 
 	 	 	 	filsG= new KdNode(RVB, P+1); 
 	 	 	 	fils[0]=true;  	 	 	 	return P+1; 
 	 	 	} 
 	 	} 
 	 	else 
 	 	{ 
 	 	 	if (fils[1]) 
 	 	 	{ 
 	 	 	 	filsD.addpointnode(RVB); 
 	 	 	} 
 	 	 	else 
 	 	 	{ 
 	 	 	 	filsD= new KdNode(RVB,P+1); 
 	 	 	 	fils[1]=true; 
 	 	 	 	return P+1; 
 	 	 	} 
 	 	} 
 	 	return P; 
 	} 
}
