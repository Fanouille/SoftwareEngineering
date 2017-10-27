import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DisplayedImage extends JPanel {
	
    private BufferedImage image;
    
    
    public DisplayedImage() {
    		try {
    			image = ImageIO.read(new File("img.png"));
           		} 
    		catch (IOException e) {
        		e.printStackTrace();
        	}                
    }
    
    static void setImage(BufferedImage img, String str){
    	try {
			img = ImageIO.read(new File(str));
			//repaint();
       		} 
		catch (IOException e) {
    		e.printStackTrace();
		}
    }
    
    public BufferedImage getimage(){
    	return image;
    }

    public void RefreshImage(BufferedImage newimg){
    	image=newimg;
    	repaint();
    }
    
    
    public void paintComponent(Graphics g){
    		//g.drawImage(image, 0, 0, this); // draw as much as possible
    		g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this); // draw full image
    }                   
}


