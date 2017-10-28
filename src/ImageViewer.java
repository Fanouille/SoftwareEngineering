import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;

import java.awt.Image;
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
		buttonQuant.addActionListener(new Quant());

		buttonInverse.addActionListener(new ButtonListener(){
			public void actionPerformed(ActionEvent arg0) {						
	    		BufferedImage Invimage;
				Invimage = ouputImage.getimage();
				
				/*Début du traitement de l'image*/
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

		global.add(action);
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

		this.menuBar.add(fileMenu);
		this.setJMenuBar(menuBar);

		this.setVisible(true);
		
		
	}

	/**
	 * Class listening to a given button
	 */
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
		}

	class HistoListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			System.out.println("Histogramme créé");
		}

	}

	}
}