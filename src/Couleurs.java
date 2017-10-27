
public class Couleurs {
	private int couleurR; //couleur du pixel
	private int couleurV;
	private int couleurB;
	
	public Couleurs(int arg[]){
		this.couleurR = arg[0];
		this.couleurV = arg[1];
		this.couleurB = arg[2];
	}
	
	public int getR(){
		return this.couleurR;
	}
	
	public int getV(){
		return this.couleurV;
	}
	
	public int getB(){
		return this.couleurB;
	}
	
	public int comparaison(int direction, Couleurs col){
		switch (direction){
		default: //case 0, par defaut la racine
			if (this.getR()>col.getR()){
				return 1;
			}
			else if(this.getR()==col.getR()){
				return 0;
			}
			else{
				return -1;
			}
		case 1:
			if (this.getV()>col.getV()){
				return 1;
			}
			else if(this.getV()==col.getV()){
				return 0;
			}
			else{
				return -1;
			}
		case 2:
			if (this.getB()>col.getB()){
				return 1;
			}
			else if(this.getB()==col.getB()){
				return 0;
			}
			else{
				return -1;
			}
		}
	}

}
