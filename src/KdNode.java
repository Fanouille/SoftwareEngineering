
public class KdNode {
	int P;
	couleur coul;
	KdNode filsG;
	KdNode filsD;
	boolean[] fils= {false,false};
	public KdNode(couleur C, int p)
	{
		//Constructeur : P=profondeur du noeud, coul= couleur
		P=p;
		coul=C;
	}
	
	public int addpointnode(couleur RVB)
	{
		//ajoute un point par récursivité et renvoie la profondeur à laquelle il a été ajouté
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
	public couleur[] palettenode(couleur[] palette,int p)
	{
		
	}
}
