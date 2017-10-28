
public class KdTree {
	int P=0;
	KdNode Racine;
	public KdTree(int[] RVB)
	{
		couleur coul= new couleur(RVB);
		Racine= new KdNode(coul,P);
	}
	
	void addpoint(int []RVB)
	{
		//ajoute un point à l'arbre, si il est plus profond, on augemente la profondeur max de l'arbre
		couleur coul=new couleur(RVB);
		int A=Racine.addpointnode(coul);
		if (A>P)
		{
			P=A;
		}
	}
	couleur[] palette(int p)
	{
		int L=(int) Math.pow(2,p);
		couleur[] palette= new couleur[L];
		palette= Racine.palettenode(palette, p);
		return palette;
	}
}
