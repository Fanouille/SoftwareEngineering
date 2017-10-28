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
 
 	public boolean compare(Couleur C, int pos ) 
 	{ 
 	 	int A=0;
 	 	switch(pos) { 
 	 			case 0: 
 	 	 	 		A=C.getR(); 	 	 	
 	 	 	 	case 1: 
 	 	 	 		A=C.getV();  	 	 	
 	 	 	 	case 2: 
 	 	 	 		A=C.getB();	 
 	 	} 
 	 	return (A>this.RVB[pos]);
 	} 
 	
 	public int compared(Couleur C, int pos ) 
 	{ 
 	 	int A=0;
 	 	switch(pos) { 
 	 			case 0: 
 	 	 	 		A=C.getR(); 	 	 	
 	 	 	 	case 1: 
 	 	 	 		A=C.getV();  	 	 	
 	 	 	 	case 2: 
 	 	 	 		A=C.getB();	 
 	 	} 
 	 	return (A-this.RVB[pos]);
 	} 
 	
 	public int getColor(int pos ) 
 	{ 
 	 	int A=0;
 	 	switch(pos) { 
 	 			case 0: 
 	 	 	 		A=this.getR(); 	 	 	
 	 	 	 	case 1: 
 	 	 	 		A=this.getV();  	 	 	
 	 	 	 	case 2: 
 	 	 	 		A=this.getB();	 
 	 	} 
 	 	return (A);
 	} 
}


