
public class KdTree{
	private int profondeur=0;

	private Couleur CouleurR;
	private KdNode Racine;

	public KdTree (int RVB[]){
		this.CouleurR=new Couleur(RVB[0], RVB[1], RVB[2]);
		this.Racine= new KdNode(CouleurR,0);
	}
	


	public void addpoint(int RVB[]){
		Couleur C=new Couleur(RVB);
		int p= Racine.addpointnode(C);
		if(p>profondeur)
		{
			profondeur=p;
		}
		
	}
	public Couleur[] palette(int pow)
	{
		//renvoie une palette de 2**pow Couleurs
		Couleur[] palette= new Couleur[(int) Math.pow(2, pow-1)];
		palette= Racine.palnode(palette);
		return palette;
	}
	
	public int getNearestNeighbors(Couleur A, Couleur[] pal)
	{
		Couleur Nearest= Racine.getNN(A);
		int i;
		for(i=0;i<pal.length;i++)
		{
			if (Nearest.egal(pal[i]))
			{
				return (i);
			}
		}
		return 0;
	}


}