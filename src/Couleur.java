
public class Couleur {
	int R,G,B;
	int N=1;
	// N représente de combien de Couleurs est issue celle ci, 
	//pour pouvoir faire des moyennes sans consommer trop de mémoire 
	public Couleur(int Red, int Green, int Blue)
	{
		R=Red;
		G=Green;
		B=Blue;
	}
	public Couleur(int[] RVB)
	{
		// deuxième constructeur pour faciliter la vie
		R=RVB[0];
		G=RVB[1];
		B=RVB[2];
	}
	
	public void initN(int n)
	{
		N=n;
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
	public int getN()
	{
		return N;
	}
	public int[] getRVB()
	{
		int[] a= {R,G,B};
		return a;
	}
	
	public int distance(Couleur C)
	{
		//calcule la distance au carré entre 2 Couleurs, sur les 3 axes
		int A=(R-C.getR())*(R-C.getR());
		A+=(G-C.getG())*(G-C.getG());
		A+=(B-C.getB())*(B-C.getB());
		return A;
	}
	
	public void printcoul()
	{
		System.out.printf(" %d , %d , %d \n",R, G, B);
	}
	
	public Couleur moyenne(Couleur C)
	{
		int[] A=new int[3];
		A[0]=(R*N + C.getR()*C.getN())/(N+C.getN());
		A[1]=(G*N + C.getG()*C.getN())/(N+C.getN());
		A[2]=(B*N + C.getB()*C.getN())/(N+C.getN());
		Couleur Retour=new Couleur(A);
		Retour.initN(N+C.getN());
		return Retour;
	}
	public boolean egal(Couleur C)
	{
		return(R==C.getR() && G==C.getG() && B==C.getB());
	}
	
}
