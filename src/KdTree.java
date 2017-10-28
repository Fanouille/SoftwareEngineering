
public class KdTree{
	private int profondeur=0;

	private couleur couleurR;
	private KdNode Racine;

	public KdTree (int RVB[]){
		this.couleurR=new couleur(RVB[0], RVB[1], RVB[2]);
		this.Racine= new KdNode(couleurR,0);
	}
	


	public void addpoint(int RVB[]){
		couleur C=new couleur(RVB);
		int p= Racine.addpointnode(C);
		if(p>profondeur)
		{
			profondeur=p;
		}
		
	}
	public couleur[] palette(int pow)
	{
		//renvoie une palette de 2**pow couleurs
		couleur[] palette= new couleur[(int) Math.pow(2, pow-1)];
		palette= Racine.palnode(palette);
		return palette;
	}


}