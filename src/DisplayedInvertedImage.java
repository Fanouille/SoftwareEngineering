import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;

//import com.sun.prism.paint.Color;
 
public class DisplayedInvertedImage extends JPanel {
	
    private BufferedImage Invimage;
    
    public DisplayedInvertedImage() {
    		try {
    			/*On lira l'image choisie à travers le menu "File"*/
    			File file= new File("img.png");
    			Invimage = ImageIO.read(file);
    			/*Début du traitement de l'image*/
    			int x,y;
    			int color;
    			//int pixel[][]=new int[Invimage.getWidth()][Invimage.getHeight()];

    			
    			for(x=0;x<Invimage.getWidth();x++){
    				for(y=0; y<Invimage.getHeight();y++){
    					
    					color=Invimage.getRGB(x, y);
    					int blue = color & 0xff;
    					int green = (color & 0xff00) >> 8;
    					int red = (color & 0xff0000) >> 16;
    					
    					//pixel[x][y]={red, green, blue};
    					/*System.out.println(red);
    					System.out.println(green);
    					System.out.println(blue);*/
    					
    					Color newclr= new Color(255-red, 255-green, 255-blue);
    					Invimage.setRGB(x, y, newclr.getRGB());
    				}
    			}
    			//On crée un nouveau fichier

    		    File outputfile = new File("saved.png");
    		    ImageIO.write(Invimage, "png", outputfile);
    			
    			
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
    }
    
    public void paintComponent(Graphics g){
    		g.drawImage(Invimage, 0, 0, this.getWidth(), this.getHeight(), this);
    }                   
}