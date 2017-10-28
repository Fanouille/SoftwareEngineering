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
		buttonQuant.addActionListener(new Quant()
				{
			public void actionPerformed(ActionEvent arg0) 
			{
				couleur[] palette;
				//importation de l'image
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
				
				//passage d'un tableau w*h à un tableau à une ligne
				int[][] line= linear(image,w,h);
				//création d'un arbre à partir de line
				KdTree Arbre1 = InitFromArray(line);
				palette = Arbre1.palette(5);
				line = CoulToInt(palette);
				KdTree Arbre2 = InitFromArray(line);
				System.out.println("arbre de la palette créé.");
				int[][] image_traitee = quantif(Arbre2, image, w,h, palette);
				System.out.println("Image Traitée!!!");
				affichage(image_traitee, palette);

				
				
		 }
			public void affichage(int[][] image, couleur[] palette)
			{
				BufferedImage Invimage;
				Invimage = ouputImage.getimage();
				
				/*DÃ©but du traitement de l'image*/
				int x,y;
				int color;
				
				for(x=0;x<Invimage.getWidth();x++){
					for(y=0; y<Invimage.getHeight();y++){
						
						color=Invimage.getRGB(x, y);
						int[] RVB = (palette[image[x][y]]).getRVB();
						Color newclr= new Color(RVB[0], RVB[1], RVB[2]);
						Invimage.setRGB(x, y, newclr.getRGB());
						ouputImage.RefreshImage(Invimage);
					}
				}
			}
			
			public int[][] quantif(KdTree Arbre, int[][][] image, int w, int h, couleur[] pal)
			{
				int[][] image_t= new int[w][h];
				couleur A= new couleur(0,0,0);
				int i,j;
				for(i=0; i<w; i++)
				{
					for(j=0; j<h; j++)
					{
						A= new couleur(image[i][j]);
						image_t[i][j]= Arbre.getNearestNeighbors(A, pal);
					}
				}
				
				return image_t;
			}
			
			public int[][] CoulToInt(couleur[] pal)
			{
				int[][] line = new int[pal.length][3];
				int i;
				for(i=0;i<pal.length;i++)
				{
					int[] A= pal[i].getRVB();
					line[i]=A;
				}
				return line;
			}
			
			public void printpal(couleur[] pal)
			{
				int i;
				for(i=0;i<pal.length;i++)
				{
					pal[i].printcoul();
				}
			}
			
			public void printtab(int[][] tableau, int dim)
			{
				int i;
				for (i=0; i<tableau.length; i++)
				{
					System.out.println(tableau[i][dim]);
				}
			}
			public int[][] linear(int[][][] matrice, int w, int h)
			//passage d'un tableau w*h à un tableau à une ligne

			{
				int j,i;
				int[][] line = new int[w*h][3];
				for( j=0 ; j<h;  j+=1)
				{
					for(i=0; i<w ; i+=1)
						{

				        	line[i+ w*j][0]=matrice[i][j][0];
				        	line[i+ w*j][1]=matrice[i][j][1];
				        	line[i+ w*j][2]=matrice[i][j][2];

						}   
				}
				return line;
			}
			public int[][] tridim(int[][] liste, int dim)
			{
				int L=liste.length;
				int[][][] Tri = new int[256][L][3];
				int i,j,k=0;
				int[] Compte= new int[256];
				for(i=0;i<256;i++)
				{
					Compte[i]=0;
				}
				for(i=0;i<L;i++)
				{
					j=(int)liste[i][dim];
					Tri[j][Compte[j]]=liste[i];
					Compte[j]++;
				}
				int[][] Retour = new int[L][3];
				for(i=0;i<256;i++)
				{
					if(Compte[i]!=0)
					{
						for(j=0;j<Compte[i];j++)
						{
							Retour[k]=Tri[i][j];
							k++;
						}
					}
				}
				return Retour;

			}
			
			KdTree InitFromArray(int[][] line)
			{
				line = tridim(line, 0);
				System.out.println("premier tri fini: veuillez attendre environ 70sec");
				int L=line.length;
				int mid=(int)(L/2);
				KdTree Arbre= new KdTree(line[mid]);
				int[][] LP=new int[mid][3];
				int[][] LG=new int[L-1-mid][3];
				int i;
				for(i=0;i<mid;i++)
				{
					LP[i]=line[i];
				}
				for(i=mid +1;i<L; i++)
				{
					LG[i-(mid +1)]=line[i];
				}
				i=InitR(Arbre, LP, 1);
				i=InitR(Arbre, LG, 1);
				System.out.println("arbre implanté ");
				return Arbre;
			}
			int InitR(KdTree Arbre, int[][] list, int dim)
			{
				int L=list.length;
				switch(L) {
				case 0:
					return 0;
				case 1:
					Arbre.addpoint(list[0]);
					return 0;
				case 2:
					Arbre.addpoint(list[0]);
					Arbre.addpoint(list[1]);
					return 0;
				}
				
				int mid=(int)(L/2), i;
				list=tridim(list, dim);
				int[][] LP=new int[mid][3];
				int[][] LG=new int[L-1-mid][3];

				for(i=0;i<L;i++)
				{
					if(i<mid) {LP[i]=list[i];}
					else if(i==mid) {Arbre.addpoint(list[mid]);}
					else {LG[i-1 -mid]= list[i];}
				}
				
				i=InitR(Arbre, LP, (dim+1)%3);
				i=InitR(Arbre, LG, (dim+1)%3);
				return 0;
				
			}

				});

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
			System.out.println("Histogramme crÃ©Ã©");
		}

	}

	}
}