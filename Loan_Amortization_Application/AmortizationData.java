import java.util.ArrayList;

/* Class Handling the Loan parameters calculation for data
 * 
 *  consists of fields : Loan Amount which is principal
 *  				     Term which is number of months annually
 *  					 Annual Interest rate 
 *  */

public class AmortizationData 
{
	
	private double loanAmount;
	private int term;
	private double anuualInterestRate ;
	
		
	public AmortizationData(){}
	
	public AmortizationData(double loanAmount,int term, double anuualInterestRate)     // constructor to set the Amortization data fields
	{
		this.loanAmount=loanAmount;
		this.term=term;
		this.anuualInterestRate=anuualInterestRate;		
		
	}
	
	
	public ArrayList<Double> getData()                                          /* Returns all data in ArrayList . Use this data in other */
	{
		ArrayList<Double> d=new ArrayList<Double>();
		d.add(loanAmount);													/* Purpose to use the loan parameters in other classes like Graph, Chart */			
		d.add(Double.parseDouble(Integer.toString(term)));
		d.add(anuualInterestRate);
		return d;
	}	
	

}
