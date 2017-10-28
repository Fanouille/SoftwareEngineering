

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
		//ajoute un point par recursivite et renvoie la profondeur a laquelle il a �t� ajout�
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
	public couleur getcoul()
	{
		return coul;
	}
	public couleur[] palnode(couleur[] pal)
	{
		int L= pal.length;
		if(L==2)
		{
			pal[0]=filsD.Moy();
			pal[1]=filsG.Moy();
			return pal;
		}
		else
		{
			int i;
			couleur[] A=new couleur[L/2];
			couleur[] B=new couleur[L/2];
			if(fils[0]) {A=filsG.palnode(A);}
			else {
				for(i=0;i<L/2;i++)
				{
					A[i]=new couleur(0,0,0);
				}
			}
			if(fils[1]) {B=filsD.palnode(B);}
			else
			{
				for(i=0;i<L/2;i++)
				{
					B[i]=new couleur(0,0,0);
				}
			}
			for(i=0;i<L;i++)
			{
				if(i<L/2) {pal[i]=A[i];}
				else {pal[i]=B[i-L/2];}
			}
			return pal;
			
		}
	}
	public couleur Moy()
	{
		if(fils[0] && fils[1])
		{
			couleur A = (filsD.Moy()).moyenne(coul);
			A=(filsG.Moy()).moyenne(A);
			return A;
		}
		else if(fils[1])
		{
			couleur A = (filsD.Moy()).moyenne(coul);
			return A;
		}
		else if(fils[0])
		{
			couleur A = (filsG.Moy()).moyenne(coul);
			return A;
		}
		else
		{
			return coul;
		}
	}

	public couleur getNN(couleur A)
	{
		if ((A.getRVB())[P%3] > (coul.getRVB())[P%3])
		{
			if (fils[1])
			{
				couleur CurrentBest = filsD.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && fils[0])
				{
					couleur Alter = filsG.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
		else
		{
			if (fils[0])
			{
				couleur CurrentBest = filsG.getNN(A);
				if (CurrentBest.distance(A) > coul.distance(A))
				{
					CurrentBest=coul;
				}
				if(CurrentBest.distance(A) > Math.abs((coul.getRVB())[P%3] - (A.getRVB())[P%3])  && fils[1])
				{
					couleur Alter = filsD.getNN(A);
					if(CurrentBest.distance(A) > Alter.distance(A))
					{
						CurrentBest=Alter;
					}
				}
				return CurrentBest;
			}
			else
			{
				return coul;
			}
		}
	}
}
