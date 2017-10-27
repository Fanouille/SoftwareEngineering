
public class couleur {
	int R,G,B;
	public couleur(int Red, int Green, int Blue)
	{
		R=Red;
		G=Green;
		B=Blue;
	}
	public couleur(int[] RVB)
	{
		// deuxième constructeur pour faciliter la vie
		R=RVB[0];
		G=RVB[1];
		B=RVB[2];
	}
	
	public int getR()
	{
		return R;
	}
	public int getG()
	{
		return G;
	}
	public int getB()
	{
		return B;
	}
	public int[] getRVB()
	{
		int[] a= {R,G,B};
		return a;
	}
	
	public int distance(couleur C)
	{
		//calcule la distance au carré entre 2 couleurs, sur les 3 axes
		int A=(R-C.getR())*(R-C.getR());
		A+=(G-C.getG())*(G-C.getG());
		A+=(B-C.getB())*(B-C.getB());
		return A;
	}
	
}
