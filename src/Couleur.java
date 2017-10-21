public class Couleur {
	private int[] RVB= {0,0,0};
	public Couleur(int R, int V, int B)
	{
		this.RVB[0]=R;
		this.RVB[1]=V;
		this.RVB[2]=B;
	}
	public int getR()
	{
		return this.RVB[0];
	}
	public int getV()
	{
		return this.RVB[1];
	}
	public int getB()
	{
		return this.RVB[2];
	}
}
