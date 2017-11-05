import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;

public class Traitement {
	int[][][] imagefinale;
	public Traitement(int[][][] image, int w, int h) 
	{
		
		
		Couleur[] palette;
		//passage d'un tableau w*h à un tableau à une ligne
		int[][] line= linear(image,w,h);
		//création d'un arbre à partir de line
		KdTree Arbre1 = InitFromArray(line);
		palette = Arbre1.palette(5);
		line = CoulToInt(palette);
		KdTree Arbre2 = InitFromArray(line);
		System.out.println("arbre de la palette créé.");
		int[][] image_traitee = quantif(Arbre2, image, w,h, palette);
		System.out.println("Image Traitée!!!");
		imagefinale = ajoutcoul(image_traitee, palette,w,h);		
 }

	public int[][][] returnImage()
	{
		return this.imagefinale;
	}
	public int[][][] ajoutcoul(int[][] image, Couleur[] palette, int w, int h)
	{
		int[][][] imagefinale = new int[w][h][3];
		int i,j;
		for (i=0; i<w; i++)
		{
			for(j=0; j<h; j++)
			{
				imagefinale[i][j]= palette[image[i][j]].getRVB();
			}
		}
		return imagefinale;
	}
	public int[][] quantif(KdTree Arbre, int[][][] image, int w, int h, Couleur[] pal)
	{
		int[][] image_t= new int[w][h];
		Couleur A= new Couleur(0,0,0);
		int i,j;
		for(i=0; i<w; i++)
		{
			for(j=0; j<h; j++)
			{
				A= new Couleur(image[i][j]);
				image_t[i][j]= Arbre.getNearestNeighbors(A, pal);
			}
		}
		
		return image_t;
	}
	
	public int[][] CoulToInt(Couleur[] pal)
	{
		int[][] line = new int[pal.length][3];
		int i;
		for(i=0;i<pal.length;i++)
		{
			int[] A= pal[i].getRVB();
			line[i]=A;
		}
		return line;
	}
	
	public void printpal(Couleur[] pal)
	{
		int i;
		for(i=0;i<pal.length;i++)
		{
			pal[i].printcoul();
		}
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
		System.out.println("premier tri fini: veuillez attendre environ 70sec");
		int L=line.length;
		int mid=(int)(L/2);
		KdTree Arbre= new KdTree(line[mid]);
		int[][] LP=new int[mid][3];
		int[][] LG=new int[L-1-mid][3];
		int i;
		for(i=0;i<mid;i++)
		{
			LP[i]=line[i];
		}
		for(i=mid +1;i<L; i++)
		{
			LG[i-(mid +1)]=line[i];
		}
		i=InitR(Arbre, LP, 1);
		i=InitR(Arbre, LG, 1);
		System.out.println("arbre implanté ");
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
		
		int mid=(int)(L/2), i;
		list=tridim(list, dim);
		int[][] LP=new int[mid][3];
		int[][] LG=new int[L-1-mid][3];

		for(i=0;i<L;i++)
		{
			if(i<mid) {LP[i]=list[i];}
			else if(i==mid) {Arbre.addpoint(list[mid]);}
			else {LG[i-1 -mid]= list[i];}
		}
		
		i=InitR(Arbre, LP, (dim+1)%3);
		i=InitR(Arbre, LG, (dim+1)%3);
		return 0;
		
	}

}
