import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewer extends JFrame /*implements ActionListener*/
{
	private DisplayedImage inputImage = new DisplayedImage(); 
	private DisplayedImage ouputImage = new DisplayedImage();

	private JButton buttonAction = new JButton("Action");
	private JButton buttonHisto = new JButton("Histogramme");
	private JButton buttonQuant = new JButton("Quantification");

	private JButton buttonInverse = new JButton("Inverse");

	private JMenuBar menuBar = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	
	private JMenuItem itemCharge = new JMenuItem("Charger une image");


	private JMenuItem itemClose = new JMenuItem("Close");

	public ImageViewer () {
		this.setTitle("Image Viewer");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1000, 400);

		JPanel input = new JPanel();
		input.setLayout(new BoxLayout(input, BoxLayout.PAGE_AXIS));
		input.add(inputImage);

		JPanel inverse = new JPanel();
		inverse.setLayout(new BoxLayout(inverse, BoxLayout.PAGE_AXIS));
		inverse.add(buttonInverse);
		
		JPanel charger = new JPanel();
		charger.setLayout(new BoxLayout(charger, BoxLayout.PAGE_AXIS));
		charger.add(itemCharge);
		// Defines action associated to buttons
		itemCharge.addActionListener(new ButtonCharger());
		// Defines action associated to buttons

		buttonAction.addActionListener(new ButtonListener());
		
		JPanel histo = new JPanel();
		histo.setLayout(new BoxLayout(histo, BoxLayout.PAGE_AXIS));
		histo.add(buttonHisto);
		// Defines action associated to buttons
		buttonHisto.addActionListener(new HistoListener());
		
		JPanel quant = new JPanel();
		quant.setLayout(new BoxLayout(quant, BoxLayout.PAGE_AXIS));
		quant.add(buttonQuant);
		// Defines action associated to buttons
		
		// ##########  CLASS QUANT  ##########
		buttonQuant.addActionListener(new Quanti());
// ############## FIN CLASS QUANT #############
		buttonInverse.addActionListener(new ButtonListener(){
			public void actionPerformed(ActionEvent arg0) {						
	    		BufferedImage Invimage;
				Invimage = ouputImage.getimage();
				
				/*DÃ©but du traitement de l'image*/
				int x,y;
				int color;
				
				for(x=0;x<Invimage.getWidth();x++){
					for(y=0; y<Invimage.getHeight();y++){
						
						color=Invimage.getRGB(x, y);
						int blue = color & 0xff;
						int green = (color & 0xff00) >> 8;
						int red = (color & 0xff0000) >> 16;
						
						Color newclr= new Color(255-red, 255-green, 255-blue);
						Invimage.setRGB(x, y, newclr.getRGB());
						ouputImage.RefreshImage(Invimage);
					}
				}
	    			
	    		}});


		JPanel output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.PAGE_AXIS));
		output.add(ouputImage); 

		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(input);

		global.add(quant);
		global.add(histo);

		global.add(inverse);

		global.add(output);


		this.getContentPane().add(global);

		this.fileMenu.addSeparator();
		itemClose.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}        
		});
		this.fileMenu.add(itemClose);
		this.fileMenu.add(itemCharge);

		this.menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

		this.setVisible(true);
		
		
	}

	/**
	 * Class listening to a given button
	 */
	class ButtonCharger implements ActionListener{
		public JTextField status = new JTextField("pas de fichier chargé");
	    public JFileChooser choose = new JFileChooser();
	    private BufferedImage image = inputImage.getimage();
	    
	    public void actionPerformed(ActionEvent evt){ //ouvrir une boite de dialogue pour choisir un fichier
	    	choose.setApproveButtonText("Charger un fichier");
	    	choose.showOpenDialog(null);
	    	if (choose.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
	    		status.setText(choose.getSelectedFile().getAbsolutePath());
	    		System.out.println(status.getText());
	    		
	    		try{
	    			image = ImageIO.read(new File(status.getText()));
	    		}
	    		catch (IOException e) {
	        		e.printStackTrace();
	    		}
	    		

	    	}
	    	inputImage.RefreshImage(image);
	    	ouputImage.RefreshImage(image);
	    	
	    }
	    
	    
	}
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
		}
	}	
	class Quanti implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
				BufferedImage ima = null;
				try {
					ima = ImageIO.read(new File("img.png"));
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				System.out.println("Matrice importée");
				int h;
				int w;
				w= ima.getWidth();
				h= ima.getHeight();
				int[][][] image = new int[w][h][3];
				System.out.println("L x H :"+w +"  "+ h);
				//récupération des couleurs, mises dans le tableau image
				int i,j,A;
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
				Traitement PAL = new Traitement(image, w,h);
				int[][][] image_finale= PAL.returnImage();
				BufferedImage Invimage;
				Invimage = ouputImage.getimage();
				
				int x,y;
				for(x=0;x<w;x++){
					for(y=0; y<h;y++){
						Color newclr= new Color(image_finale[x][y][0],image_finale[x][y][1], image_finale[x][y][2]);
						Invimage.setRGB(x, y, newclr.getRGB());
						ouputImage.RefreshImage(Invimage);
					}
				}
		}


	}
}

