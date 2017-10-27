import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;

public class Quant implements ActionListener {
	public void actionPerformed(ActionEvent arg0) 
	{
		couleur[] palette;
		int[][] image_traitee;
		//importation de l'image
		BufferedImage ima = null;
		try {
			ima = ImageIO.read(new File("img.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Matrice importée");
		int h;
		int w;
		int total=0;
		w= ima.getWidth();
		h= ima.getHeight();
		int[][][] image = new int[w][h][3];
		System.out.println("L x H :"+w +"  "+ h);
		//récupération des couleurs, mises dans le tableau image
		int R,V,B,i,j,A;
		for( j=0 ; j<h;  j+=1)
		{
			for(i=0; i<w ; i+=1)
				{
		        	A = ima.getRGB( i, j );
		        	image[i][j][0]=(byte)(A >>> 16)&0xFF;
		        	image[i][j][1]=(byte)(A >>> 8)&0xFF;
		        	image[i][j][2]=(byte)(A >>> 0)&0xFF;
				}   
		}
		
		//passage d'un tableau w*h à un tableau à une ligne
		int[][] line= linear(image,w,h);
		//création d'un arbre à partir de line
		KdTree Arbre1 = InitFromArray(line);
		
 }
	public void printtab(int[][] tableau, int dim)
	{
		int i;
		for (i=0; i<tableau.length; i++)
		{
			System.out.println(tableau[i][dim]);
		}
	}
	public int[][] linear(int[][][] matrice, int w, int h)
	//passage d'un tableau w*h à un tableau à une ligne

	{
		int j,i;
		int[][] line = new int[w*h][3];
		for( j=0 ; j<h;  j+=1)
		{
			for(i=0; i<w ; i+=1)
				{

		        	line[i+ w*j][0]=matrice[i][j][0];
		        	line[i+ w*j][1]=matrice[i][j][1];
		        	line[i+ w*j][2]=matrice[i][j][2];

				}   
		}
		return line;
	}
	public int[][] tridim(int[][] liste, int dim)
	{
		int L=liste.length;
		int[][][] Tri = new int[256][L][3];
		int i,j,k=0;
		int[] Compte= new int[256];
		for(i=0;i<256;i++)
		{
			Compte[i]=0;
		}
		for(i=0;i<L;i++)
		{
			j=(int)liste[i][dim];
			Tri[j][Compte[j]]=liste[i];
			Compte[j]++;
		}
		int[][] Retour = new int[L][3];
		for(i=0;i<256;i++)
		{
			if(Compte[i]!=0)
			{
				for(j=0;j<Compte[i];j++)
				{
					Retour[k]=Tri[i][j];
					k++;
				}
			}
		}
		return Retour;

	}
	
	KdTree InitFromArray(int[][] line)
	{
		line = tridim(line, 0);
		System.out.println("premier tri fini");
		int L=line.length;
		KdTree Arbre= new KdTree(line[(int)(L/2)]);
		int[][] LP=new int[(int)(L/2)][3];
		int[][] LG=new int[L-1-(int)(L/2)][3];
		int i;
		for(i=0;i<(int)(L/2);i++)
		{
			LP[i]=line[i];
		}
		for(i=(int)(L/2) +1;i<L; i++)
		{
			LG[i-((int)(L/2) +1)]=line[i];
		}
		i=InitR(Arbre, LG, 1);
		i=InitR(Arbre, LP, 1);
		System.out.println("arbre implanté");
		return Arbre;
	}
	int InitR(KdTree Arbre, int[][] list, int dim)
	{
		int L=list.length;
		switch(L) {
		case 0:
			return 0;
		case 1:
			Arbre.addpoint(list[0]);
			return 0;
		case 2:
			Arbre.addpoint(list[0]);
			Arbre.addpoint(list[1]);
			return 0;
		}
		list=tridim(list, dim);
		Arbre.addpoint(list[(int)(L/2)]);
		int[][] LP=new int[(int)(L/2)][3];
		int[][] LG=new int[L-1-(int)(L/2)][3];
		int i;
		for(i=0;i<(int)(L/2);i++)
		{
			LP[i]=list[i];
		}
		for(i=(int)(L/2) +1;i<L; i++)
		{
			LG[i-((int)(L/2) +1)]=list[i];
		}
		System.out.println("dimension: "+ dim);
		InitR(Arbre, LG, (dim+1)%3);
		InitR(Arbre, LP, (dim+1)%3);
		return 0;
		
	}

}
