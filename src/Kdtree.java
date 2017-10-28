
import java.lang.reflect.Array;
import java.util.*;

public class Kdtree {
	Couleur couleurR; 
	int Profondeur=0; 
	KdNode Racine=null; 
	
 	public Kdtree (int[] RVB) 
 	{ 
 	 	this.couleurR=new Couleur(RVB[0], RVB[1], RVB[2]);  	 	
 	 	this.Racine= new KdNode(couleurR, 0); 
 	}

 	public void addpoint(Couleur col) {
 		if (Racine==null) {
 			Racine= new KdNode(col,0);
 		}
 		else {
 			int P=Racine.addpointnode(col);
 			if (P>Profondeur) {
 				Profondeur=P;
 			}
 		}
 	}
 	
 	Couleur[] fd=null;
 	Couleur[] fg=null;
 	Couleur point_median;
 	
 	public void median(Couleur[] matrice,int p){
 		int direction = p%3;
 		Arrays.sort(matrice, (a,b)->a.compared(b, direction));
 		int len = matrice.length;
 		point_median = matrice[len/2]; 
 		for (int i=0;i<len/2;i++) {
 			fg[i]=matrice[i];
 			if (i != 0) {
 				fd[i]=matrice[len/2+i];
 			}
 		}		
 	}
 	
 	public int initFromArray(Couleur[] matrice) {
 		if (matrice == null) {
 			return 0;
 		}
 		else {
 			fd=null;
 			fg=null;
 			point_median=null;
 			median(matrice,Profondeur);
 			addpoint(point_median);
 			initFromArray(fg);
 			initFromArray(fd);
 		}
 		return 0;	
 	}

}
