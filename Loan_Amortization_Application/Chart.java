import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTable;
import javax.swing.JList;

/*   Chart class - chart to be displayed showing the payment number, the amount of that payment that is principal, 
 *     							the amount that is interest, and the remaining balance. 
 *    in a seperate frame
 *  */

/**
 * Create the frame.
 */
public class Chart extends JFrame {

	private JPanel contentPane;
	private JTable table;

		
	public Chart(AmortizationData dm) {              		// Chart class constructor amortization object is passed 
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);       
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
			
		ArrayList<Double> s= dm.getData();					// get the loan parameters from Amortization class
		
		double loanAmount=s.get(0);						
		int noOfMonths= s.get(1).intValue()*12;
		double monthlyinterestRate=s.get(2)/12;
		double monthlyPayment= (loanAmount * monthlyinterestRate)/(1-Math.pow(1+monthlyinterestRate,-noOfMonths));
		//double new_rate= rate/100/12;
		
		double balance=loanAmount;
		double principal;
		double interest;
		
		String columnNames[]={"PaymentNo.","Principal","Interest","Balance"};
		Object[][] data =new Object[1000][1000];
		DecimalFormat df=new DecimalFormat("#.###"); 			// create decimal format for setting prescision of the double vales
							
		for (int paymentPeriod = 0; paymentPeriod <=noOfMonths; paymentPeriod++)
		{
			interest=balance*monthlyinterestRate;
			principal=monthlyPayment-interest;
			balance=balance-principal;
			data[paymentPeriod][0]=paymentPeriod;				// store the loan parameters calculation in data array
			data[paymentPeriod][1]="$"+df.format(principal);
			data[paymentPeriod][2]="$"+df.format(interest);
			data[paymentPeriod][3]="$"+df.format(balance);
			
		}
				
		DefaultTableModel md= new DefaultTableModel(data, columnNames);
		table = new JTable(md); 											// create JTable 
		md.setColumnIdentifiers(columnNames);							// set the column names 
		contentPane.add(table, BorderLayout.CENTER);
		JScrollPane p= new JScrollPane(table);	                			// add scroll bar 
		contentPane.add(p);
		
	}

}
