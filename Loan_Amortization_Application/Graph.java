import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


/*  Graph class which implements graph for  for the decline in the remaining balance over the term of the loan  
 * 
 *  in a seperate frame 
 * */

	/**
	 * Create the frame.
	 */

public class Graph extends JPanel 
{

	private int noOfMonths;
	private double remaining_balance;
	
	double[] data;
    final int PAD = 100;
	
   
	public Graph(AmortizationData ad,Object[][] dm) 		// Graph class constructor - amortization object and data 2D array are passed 
	{
		ArrayList<Double> s= ad.getData();							// get the loan parameters data
		noOfMonths= s.get(1).intValue()*12;					
		data = new double[noOfMonths+1];                        // create new array for drawing the graph 
		for(int i =1 ;i < data.length;i++){
    		this.data[i] = Double.parseDouble((dm[i][3].toString()));             // store remaining balance in the new array 
			
		}
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setBounds(10, 101, 742, 276);
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        f.add(this);
        f.setSize(500,500);
        f.setLocation(200,200);
        f.setVisible(true);
	}
	
	
	protected void paintComponent(Graphics g) { 			// paint method to draw the graph 
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));          // Draw Y Axis.        
        
        g2.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));		// Draw X Axis.
        
        Font font = g2.getFont();									// Draw labels.
        FontRenderContext frc = g2.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        
        String s = "Remaining Balance";									// Y Axis  label. 
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            g2.drawString(letter, sx, sy);
            sy += sh;
        }
        
        s = "No of Months";														// X Axis label.
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        g2.drawString(s, sx, sy);
        
        double xInc = (double)(w - 2*PAD)/(data.length-1);							// Draw lines.
        double scale = (double)(h - 2*PAD)/getMax();
        g2.setPaint(Color.white.darker());
        for(int i = 0; i < data.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*data[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*data[i+1];
            g2.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        g2.setPaint(Color.black);											// Mark data points.
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            g2.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
        }
    }
	
	private double getMax() 											 // get maximum value 
	{
        double max = -Double.MAX_VALUE;
        for(int i = 0; i < data.length; i++) 
        {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
 
   
 }	
	
	
	


