import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;


/*   Main Class which is the Loan EMI Calculator or Loan Amortization Calculator . Its GUI includes
 *    Has TextFields : Principal - Loan amount 
 *                     Term  - number of months 
 *                     Rate  - Annual Interest rate 
 *                     Monthly Payment - EMI
 *                     Total Payment
 *     Has two buttons   Graph - Generates graph for the decline in the remaining balance over the term of the loan   
 *     					Chart - chart to be displayed showing the payment number, the amount of that payment that is principal, 
 *     							the amount that is interest, and the remaining balance.     
 *     
 *        Execute LoanParameters.java for starting the program
 *            
 *  */

public class LoanParameters extends JFrame  
{

	private JPanel contentPane;
	private JTextField txtPrincipal;
	private JTextField txtTerm;
	private JTextField txtRate;
	private JLabel hlblPrincipal;
	private JLabel hlblTerm;
	private JLabel hlblRate;
	private JLabel lblMonthlyPayment;
	private JTextField txtMnthlyPayment;
	private JLabel lblTotalPayment;
	private JTextField txtTotPayment;
	private JButton btnGraph;
	private JButton btnChart;
	private ArrayList<Double> s;
	private Object[][] data;
	private int flag;					// flag to enable or disable buttons
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)      // Execution of the application begins 
	{
		//EventQueue.invokeLater(new Runnable() {
			//public void run() {
				try {
					LoanParameters frame = new LoanParameters();    // create object for the LoanParameters class
					frame.setVisible(true);
					frame.setTitle("Loan Parameters Main Program");
									
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		//});
	}
	
	
	/**
	 * Create the frame.
	 */
	
	public LoanParameters()                                  // LoanParameters class constructor
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 511, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Font ft= new Font("Arial", Font.BOLD, 20);
		JLabel lblTitle = new JLabel("LOAN EMI CALCULATOR ");    // Heading 
		lblTitle.setFont(ft);
		lblTitle.setBounds(103, 11, 313, 14);
		contentPane.add(lblTitle);
		
		JLabel lblTerm = new JLabel("Term");
		lblTerm.setBounds(103, 107, 78, 14);                       /*   three Labels :  lblTerm,lblPrincipal,lblRate for term,principal,rate */
		contentPane.add(lblTerm);
																  
		JLabel lblPrincipal = new JLabel("Principal");
		lblPrincipal.setBounds(103, 63, 60, 14);
		contentPane.add(lblPrincipal);
		
		txtPrincipal = new JTextField();                       /* five textfields : txtPrincipal,txtTerm,txtRate,txtMnthlyPayment,txtTotPayment  */
		txtPrincipal.setBounds(201, 60, 86, 20);
		contentPane.add(txtPrincipal);
		txtPrincipal.setColumns(10);						/*      for    Principal, term, rate monthly payment and total payment*/
		
		txtTerm = new JTextField();
		txtTerm.setBounds(201, 104, 86, 20);
		contentPane.add(txtTerm);
		txtTerm.setColumns(10);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setBounds(103, 149, 46, 14);
		contentPane.add(lblRate);
		
		txtRate = new JTextField();
		txtRate.setBounds(201, 146, 86, 20);
		contentPane.add(txtRate);
		txtRate.setColumns(10);
		
		btnGraph = new JButton("Graph");      // buttons       /* Two buttons : btnGraph,btnChart for Generating graphs and chart */
		btnGraph.setBounds(88, 215, 121, 23);
		contentPane.add(btnGraph);
		
		btnChart = new JButton("Chart");
		btnChart.setBounds(247, 215, 121, 23);
		contentPane.add(btnChart);
		
		btnGraph.setEnabled(false);      // disable buttons intially 
		btnChart.setEnabled(false);
		
		// hidden labels for validating 
		
		hlblPrincipal = new JLabel("");
		hlblPrincipal.setBounds(308, 63, 162, 14);  		/* Three hidden labels : hlblPrincipal,hlblTerm,hlblRate for */
		contentPane.add(hlblPrincipal);
																/* displaying error messages while validating textfields principal,term,rate */
		hlblTerm = new JLabel("");
		hlblTerm.setBounds(308, 107, 162, 14);
		contentPane.add(hlblTerm);
		
		hlblRate = new JLabel("");
		hlblRate.setBounds(308, 149, 162, 14);
		contentPane.add(hlblRate);
		
		lblMonthlyPayment = new JLabel("Monthly Payment");
		lblMonthlyPayment.setBounds(21, 269, 101, 14);
		contentPane.add(lblMonthlyPayment);
		
		txtMnthlyPayment = new JTextField();
		txtMnthlyPayment.setBounds(132, 266, 86, 20);
		contentPane.add(txtMnthlyPayment);
		txtMnthlyPayment.setColumns(10);
		
		lblTotalPayment = new JLabel("Total Payment");
		lblTotalPayment.setBounds(233, 269, 87, 14);
		contentPane.add(lblTotalPayment);
		
		txtTotPayment = new JTextField();
		txtTotPayment.setBounds(330, 266, 86, 20);
		contentPane.add(txtTotPayment);
		txtTotPayment.setColumns(10);
		
			
		
		 txtPrincipal.addFocusListener(new FocusAdapter() {				// handling Focuslost event when the  focus is lost while entering text in principal field
					
			@Override
			public void focusLost(FocusEvent e)
			{
				if (!e.isTemporary()) 
				{
					try
					{
						double value = Double.parseDouble(txtPrincipal.getText());  // check for principal is numeric . if the entered text is  not numeric then  
																						//  number Format exception is thrown and catched and text is set empty
																							// flag decremented
						if (value<0)                                // check if principal is positive
						{
							txtPrincipal.setText("");
							hlblPrincipal.setText("Enter positive number");
							flag--;											 // flag decremented 
						}
						else {
							flag++;                                         // if everything okay  then increment flag
						}
											
					} catch (NumberFormatException ne) {
						txtPrincipal.setText("");
						hlblPrincipal.setText("Enter number");
						flag--;
					}						
				}				
			}
			
			@Override
			public void focusGained(FocusEvent e) {                  // when focus is gained set the cursor
				txtPrincipal.setBackground(Color.white);
				
			}
		});   
		 
		 
		 txtTerm.addFocusListener(new FocusAdapter() {										// same as above logic for term field
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (!e.isTemporary()) 
					{
						try
						{
							double value = Integer.parseInt(txtTerm.getText());
							
							if (value<0) 
							{
								txtTerm.setText("");
								hlblTerm.setText("Enter positive number");
								flag--;
							}
							else {
								flag++;
							}
												
						} catch (NumberFormatException ne) {
							txtTerm.setText("");
							hlblTerm.setText("Enter number");
							flag--;
						}						
					}						
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					txtTerm.setBackground(Color.white);
					
				}
			});
		 
		 
		 txtRate.addFocusListener(new FocusAdapter() {              						// same as above logic for rate field
				
				@Override
				public void focusLost(FocusEvent e)
				{
					if (!e.isTemporary()) 
					{
						try
						{
							double value = Double.parseDouble(txtRate.getText());
							
							if (value<0) 
							{
								txtRate.setText("");
								hlblRate.setText("Enter positive number");
								flag--;
							}
							else {
								flag++;
							}
												
						} catch (NumberFormatException ne) {
							txtRate.setText("");
							hlblRate.setText("Enter number");
							flag--;
						}	
						ButtonEnableDisable();										// check the flags and flag=3 enable two buttons 
						LoanParametersCalculation();							// to set the monthlypayment and totalpayment textfields
					}						
				}
				
				@Override
				public void focusGained(FocusEvent e) {
					txtRate.setBackground(Color.white);
					
				}				
				
			});
		 
		 
		
		btnGraph.addActionListener(new ActionListener()  			// event handling for button Graph
		{															// when button is clicked then Graph frame is displayed for 
																	// for the decline in the remaining balance over the term of the loan 
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
								int no_of_payments=Integer.parseInt(txtTerm.getText())*12;            
								AmortizationData ad = new AmortizationData(Double.parseDouble(txtPrincipal.getText()),   // create Amortization data object
										Integer.parseInt(txtTerm.getText()),Double.parseDouble(txtRate.getText()));		// by inserting data from textfields principal,term,rate into the object
								LoanParametersCalculation();				// Calculation of loan amortization parameters 
								Graph frame = new Graph(ad,data);			// send the object and data- 2D array which has all data of loan parametrs.
								frame.setVisible(true);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				// TODO Auto-generated method stub
				
			}
		});
		
		btnChart.addActionListener(new ActionListener() 				// event handling for button Chart
		{																// when button is clicked then chart frame is displayed for 
																		//chart to be displayed showing the payment number, the amount of that payment that is principal, 
			 															// the amount that is interest, and the remaining balance.			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
								AmortizationData dm = new AmortizationData(Double.parseDouble(txtPrincipal.getText()), Integer.parseInt(txtTerm.getText()),Double.parseDouble(txtRate.getText()));
								Chart frame = new Chart(dm);             // send Amortization data object 
								frame.setVisible(true);
								frame.setTitle("Chart");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
				
			
	}	
		
	private void ButtonEnableDisable()                // method for enabling or disabling button 
	{
		if (flag==3)
		 {
			btnGraph.setEnabled(true);          // buttons enabled
			btnChart.setEnabled(true);			
		 }
		 else 
		 {
			 btnGraph.setEnabled(false);         // buttons disabled 
			 btnChart.setEnabled(false);
		 }
	}
	
	private void LoanParametersCalculation()               // method to calculate loan amortization parameters 
	{
		double loanAmount=Double.parseDouble(txtPrincipal.getText());
		int noOfMonths= Integer.parseInt(txtTerm.getText())*12;								// no of months = term *12
		double monthlyinterestRate=Double.parseDouble(txtRate.getText())/12;				// monthly interest rate = annual interest rate /12
		double monthlyPayment= (loanAmount * monthlyinterestRate)/(1-Math.pow(1+monthlyinterestRate,-noOfMonths));  // monthly payment or EMI  
		double totalPayment=monthlyPayment*noOfMonths;																// total payment = EMI * no of months	
		DecimalFormat df= new DecimalFormat("#.####");									// create decimal format for setting prescision of the double vales
		txtMnthlyPayment.setText(df.format(monthlyPayment)); 					// set the monthlypayment and total payment fields
		txtTotPayment.setText(df.format(totalPayment));
		
		double balance=loanAmount;
		double principal;
		double interest;
		
		 data =new Object[1000][1000];								// create 2 D array to store the loan parameters
		
		 for (int paymentPeriod = 0; paymentPeriod <=noOfMonths; paymentPeriod++)
		 {
				interest=balance*monthlyinterestRate;                     			// interest paid after first period = balance * monthly interest rate        
				principal=monthlyPayment-interest;										// principal after first period = monthly payment - interest paid after first period
				balance=balance-principal;											// balance 
				data[paymentPeriod][0]=paymentPeriod;
				data[paymentPeriod][1]=df.format(principal);
				data[paymentPeriod][2]=df.format(interest);
				data[paymentPeriod][3]=df.format(balance);
				
		 }		
		
	}

	
}
				

