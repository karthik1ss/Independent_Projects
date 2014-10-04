import java.awt.Component;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;
import javax.xml.crypto.dsig.spec.HMACParameterSpec;


public class MiniMaxOpeningBlack
{
	
	private String board;
	private int depth;
	//HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	private static long numpositions=0l;
	//private String bestmove=null;
	private static long max_estimate=0l;
	//private int min_estimate=0;
	
	public MiniMaxOpeningBlack(String board, int depth) 
	{
		this.board = board;
		this.depth = depth;
	}
	
	
	private ArrayList<String> bList;
	
	public ArrayList<String> generateWhiteMoves(String brd) 
	{
		bList = new ArrayList<String>();
		
		char[] b = brd.toCharArray();
		
		char[] cpybrd;
		
		for(int i=0;i<b.length;i++)
		{
			if(b[i]=='x')
			{
				cpybrd = b.clone();
				cpybrd[i]='W';
				if(closeMill(i,cpybrd))
				{
					bList=generateRemove(cpybrd, bList); 
				}
				else 
				{
					bList.add(new String(cpybrd));
				}
			}
		}
		return bList;
	}
	
	public ArrayList<String> generateBlackMoves(String brd)
	{
		char[] tempb = brd.toCharArray();
		//char temp;
		
		for (int i = 0; i < tempb.length; i++) 
		{
			if(tempb[i]=='W')
			{
				tempb[i]='B';
				continue;
			}
								
			if(tempb[i]=='B')
				tempb[i] ='W';			
		}
		
		ArrayList<String> a = generateWhiteMoves(new String(tempb));
		ArrayList<String> b =new ArrayList<String>();
		for (String s : a) 
		{
			char[] tn = s.toCharArray(); 
			for (int i = 0; i < tn.length; i++) 
			{
				if(tn[i]=='B')
				{
					tn[i]='W';
					continue;
				}
									
				if(tn[i]=='W')
					tn[i] ='B';			
			}
			b.add(new String(tn));
		}
		return b;
		
	}
	
					
	
	public String MaxMin(String board,int depth) 
	{
		if(depth >0)
		{
			ArrayList<String> t = generateWhiteMoves(board);
			
			if(t==null)
				return board;
			
			depth--;
			
			String minMove=null;
			String move = null;
			
			long max = -1000000; 
			for(String s : t)
			{
				//System.out.println("calling maxmin for this:" + s);
						
				minMove = MinMax(s,depth);
				//System.out.println(minMove);
				//System.out.println("esti"+ staticEstimation(minMove));
				if(max < staticEstimation(minMove))
				{
					max = staticEstimation(minMove);
					
					move = s;
					//hmap.put(s, max);
					this.max_estimate = max;
				}
				
			}
			return move;
		}
		else
		{
			if(depth == 0)
				numpositions++;	
		}
		return board;
		
	}

	public String MinMax(String board,int depth) 
	{
		if(depth >0)
		{
			ArrayList<String> t = generateBlackMoves(board);
			
			if(t==null)
				return board;
			
			depth--;
			
			String maxMove=null;
			String move = null;
			
			long min = 1000000; 
			for(String s : t)
			{
				
				//System.out.println("calling minmax for this:" + s);
					
				maxMove = MaxMin(s,depth);
				//System.out.println(maxMove);
				
				if( min > staticEstimation(maxMove))
				{
					min = staticEstimation(maxMove);
					
					move = s;
					//hmap.put(s, min);
				}
			}
						
			return move;
		}
		else
		{
			if(depth == 0)
				numpositions++;	
		}
		return board;
		
	}
	
	
	
	public boolean closeMill(int loc, char[] cpybrd)
	{
		char c = cpybrd[loc];
		if(c=='W' || c=='B'){
			switch(loc) {
			case 0 : if((cpybrd[6]==c && cpybrd[18]==c)||(cpybrd[2]==c && cpybrd[4]==c))
						return true;
					else
						return false;//a0
			case 6: if((cpybrd[7]==c && cpybrd[8]==c)||(cpybrd[0]==c && cpybrd[18]==c))
						return true;
					else
						return false;//a3
			case 18: if((cpybrd[0]==c && cpybrd[6]==c)||(cpybrd[19]==c && cpybrd[20]==c))
						return true;
					else
						return false;//a6
			case 2: if((cpybrd[0]==c && cpybrd[4]==c)||(cpybrd[7]==c && cpybrd[15]==c))
						return true;
					else
						return false;//b1
			case 7: if((cpybrd[6]==c && cpybrd[8]==c)||(cpybrd[2]==c && cpybrd[15]==c))
						return true;
					else
						return false;//b3
			case 15: if((cpybrd[7]==c && cpybrd[2]==c)||(cpybrd[16]==c && cpybrd[17]==c))
						return true;
					else
						return false;//b5
			case 4: if((cpybrd[0]==c && cpybrd[2]==c)||(cpybrd[8]==c && cpybrd[12]==c))
						return true;
					else
						return false;//c2
			case 8: if((cpybrd[6]==c && cpybrd[7]==c)||(cpybrd[4]==c && cpybrd[12]==c))
						return true;
					else
						return false;//c3
			case 12: if((cpybrd[4]==c && cpybrd[8]==c)||(cpybrd[13]==c && cpybrd[14]==c))
						return true;
					else
						return false;//c4
			case 13: if((cpybrd[12]==c && cpybrd[14]==c)||(cpybrd[16]==c && cpybrd[19]==c))
						return true;
					else
						return false;//d4
			case 16: if((cpybrd[13]==c && cpybrd[19]==c)||(cpybrd[15]==c && cpybrd[17]==c))
						return true;
					else
						return false;//d5
			case 19: if((cpybrd[13]==c && cpybrd[16]==c)||(cpybrd[18]==c && cpybrd[20]==c))
						return true;
					else
						return false;//d6
			case 5: if(cpybrd[9]==c && cpybrd[14]==c)
						return true;
					else
						return false;//e2
			case 9: if((cpybrd[5]==c && cpybrd[14]==c)||(cpybrd[10]==c && cpybrd[11]==c))
						return true;
					else
						return false;//e3
			case 14: if((cpybrd[5]==c && cpybrd[9]==c)||(cpybrd[12]==c && cpybrd[13]==c))
						return true;
					else
						return false;//e4
			case 3: if(cpybrd[10]==c && cpybrd[17]==c)
						return true;
					else
						return false;//f1
			case 10: if((cpybrd[3]==c && cpybrd[17]==c)||(cpybrd[9]==c && cpybrd[11]==c))
						return true;
					else
						return false;//f3
			case 17: if((cpybrd[3]==c && cpybrd[10]==c)||(cpybrd[15]==c && cpybrd[16]==c))
						return true;
					else
						return false;//f5
			case 1: if(cpybrd[11]==c && cpybrd[20]==c)
						return true;
					else
						return false;//g0
			case 11: if((cpybrd[1]==c && cpybrd[20]==c)||(cpybrd[9]==c && cpybrd[10]==c))
						return true;
					else
						return false;//g3
			case 20: if((cpybrd[1]==c && cpybrd[11]==c)||(cpybrd[18]==c && cpybrd[19]==c))
						return true;
					else
						return false;//g6			
			}
		}
		return false;		
	}
	
	public ArrayList<String> generateRemove(char[] b, ArrayList<String> list) 
	{
		bList = list;
		char[] cb;
		
		for(int i=0;i<b.length;i++) 
		{
			if(b[i]=='B') 
			{
				if(!(closeMill(i,b))) 
				{
					cb=b.clone();
					cb[i] = 'x';
					bList.add(new String(cb));
				}
				else 
				{
					cb=b.clone();
					bList.add(new String(cb));
					
				}
			}
		}
		return bList;
	}
	
	public static int staticEstimation(String board) 
	{
		int numwhites = 0;
		int numblack = 0;
		char[] b = board.toCharArray();
		for(int i=0;i<b.length;i++) 
		{
			if(b[i]=='W') 
			{
				numwhites++;
			}
			else if(b[i]=='B') 
			{
				numblack++;
			}
		}
		return (numwhites-numblack);
	}
	
	 
		
	public static void main(String[] args) 
	{
		
		int depth = Integer.parseInt(args[2]);
		String brd = null;
		
		String bestmove=null;
		long minimax_estimate=0l;
		long num_positions=0l;
		
		try 
		{
			BufferedReader in = new BufferedReader(new FileReader(args[0]));
			String str="";
		    while ((str = in.readLine()) != null)
		    {
		    	brd=str;
		    	char[] tempb = brd.toCharArray();
				
				for (int i = 0; i < tempb.length; i++) 
				{
					if(tempb[i]=='W')
					{
						tempb[i]='B';
						continue;
					}
										
					if(tempb[i]=='B')
						tempb[i] ='W';			
				}
		    	
		    	MiniMaxOpeningBlack obj = new MiniMaxOpeningBlack(new String(tempb),depth);
		    	bestmove = obj.MaxMin(brd, depth);
		    	
		    	char[] tn = bestmove.toCharArray(); 
				for (int i = 0; i < tn.length; i++) 
				{
					if(tn[i]=='B')
					{
						tn[i]='W';
						continue;
					}
										
					if(tn[i]=='W')
						tn[i] ='B';			
				}
				bestmove=new String(tn);
		    	num_positions=obj.numpositions;	
		    	minimax_estimate= obj.max_estimate;	
		    	
		    }
		   		    
		    FileWriter fstream = new FileWriter(args[1]);
	        PrintWriter out = new PrintWriter(fstream);
	        System.out.println("Best move "+ bestmove + " "+ "Minimax estimate" + minimax_estimate + " "+ "positions evaluated" + num_positions );
	        
	        out.println("Board Position "+ bestmove + " "+ "Minimax estimate" + minimax_estimate + " "+ "positions evaluated" + num_positions );
			out.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
