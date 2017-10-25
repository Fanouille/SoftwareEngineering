import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.jfree.ui.RefineryUtilities;
//import BarChart_AWT;

public class HistoListener implements ActionListener {
	public void actionPerformed(ActionEvent arg0) 
	{
		BufferedImage ima = null;
		try {
			ima = ImageIO.read(new File("img.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		System.out.println("Histogramme Affiche");
		int h;
		int w;
		int total=0;
		w= ima.getWidth();
		h= ima.getHeight();
		System.out.println("L x H :"+w +"  "+ h);
		int[] histoRGB= new int[3];
		int R=0;
		int B=0;
		int V=0;
		int[][] pixels = new int[w][h];
		int i;
		int j;
		int A;
		for( j=0 ; j<h;  j+=1)
			for(i=0; i<w ; i+=1){
				{
			
		        A = ima.getRGB( i, j );
		        R=(byte)(A >>> 16)&0xFF;
				V=(byte)(A >>> 8)&0xFF;
				B=(byte)(A >>> 0)&0xFF;
				if(B>V) {
					if(B>R) {histoRGB[2]++;}
					else {histoRGB[0]++;}
				}
				else {
					if(V>R) {histoRGB[1]++;}
					else {histoRGB[0]++;}
				}
				total++;
				}   
		}
		System.out.println(total);
		
		
	      BarChart_AWT chart = new BarChart_AWT("Histogramme des pixels", 
	    	         "Histogramme couleurs",histoRGB);
	      chart.pack( );        
	      RefineryUtilities.centerFrameOnScreen( chart );        
	      chart.setVisible( true ); 



	}
	


}
