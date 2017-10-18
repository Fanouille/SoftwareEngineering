import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart_AWT extends ApplicationFrame {
   
   public BarChart_AWT( String applicationTitle , String chartTitle, int[] histoRGB ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "Category",            
         "Nombre de pixels",            
         createDataset(histoRGB),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset(int[] histoRGB ) {
      final String R = "Rouge";        
      final String V = "Vert";        
      final String B = "Bleu";        
      final String pixels = "Pixels";        
       
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  

      dataset.addValue( histoRGB[0] , R , pixels );                   
      dataset.addValue( histoRGB[2] , B , pixels );        
      dataset.addValue( histoRGB[1] , V , pixels );        
            

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
	   int[] histoRGB= {12,15,16};
      BarChart_AWT chart = new BarChart_AWT("Histogramme des pixels", 
         "Histogramme couleurs",histoRGB);
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
   
   
   
}