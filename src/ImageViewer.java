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

		JPanel action = new JPanel();
		action.setLayout(new BoxLayout(action, BoxLayout.PAGE_AXIS));
		action.add(buttonAction);
		// Defines action associated to buttons
		buttonAction.addActionListener(new ButtonListener());
		
		JPanel charger = new JPanel();
		charger.setLayout(new BoxLayout(charger, BoxLayout.PAGE_AXIS));
		charger.add(itemCharge);
		// Defines action associated to buttons
		itemCharge.addActionListener(new ButtonCharger());

		JPanel output = new JPanel();
		output.setLayout(new BoxLayout(output, BoxLayout.PAGE_AXIS));
		output.add(ouputImage); 

		JPanel global = new JPanel();
		global.setLayout(new BoxLayout(global, BoxLayout.LINE_AXIS));
		global.add(input);
		global.add(action);
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
	class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) 
		{
			System.out.println("Action Performed");
		}
	}
	
	class ButtonCharger implements ActionListener{
		public JTextField status = new JTextField("pas de fichier chargé");
	    public JFileChooser choose = new JFileChooser();
	    private BufferedImage image;
	    
	    public void actionPerformed(ActionEvent evt){ //ouvrir une boite de dialogue pour choisir un fichier
	    	choose.setApproveButtonText("Charger un fichier");
	    	choose.showOpenDialog(null);
	    	if (choose.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
	    		status.setText(choose.getSelectedFile().getAbsolutePath());
	    		System.out.println(status.getText());
	    	}
	    	String str = status.getText(); // charger image 
	    	try {
    			DisplayedImage.setImage(image,str); 
    			repaint();
           		} 
    		catch (IOException e) {
        		e.printStackTrace();
        	} 
	    }
	    
	    
	}
	

}