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


public class MiniMaxGameBlack
{
	
	private String board;
	private int depth;
	//HashMap<String, Integer> hmap = new HashMap<String, Integer>();
	private static long numpositions=0l;
	private static long max_estimate=0l;
	
	public MiniMaxGameBlack(String board, int depth) 
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
	
	private ArrayList<String> mlist = new ArrayList<String>();
	
	public ArrayList<String> generateMove(String brd) 
	{
		mlist = new ArrayList<String>();
		
		int[] ngbrs ;
		
		char[] b = brd.toCharArray();
		
		char[] cpybrd;
		
		for(int i=0;i<b.length;i++)
		{
			if(b[i]=='W')
			{
				ngbrs = neighbours(i);
				for (int c : ngbrs)
				{
					if(b[c]=='x')
					{
						cpybrd = b.clone();
						cpybrd[i]='x';
						cpybrd[c]='W';
						if(closeMill(c,cpybrd))
						{
							mlist=generateRemove(cpybrd, mlist); 
						}
						else 
						{
							mlist.add(new String(cpybrd));
						}
					}
					
					
				}
				
			}
		}
		return mlist;
	} 
	
	private ArrayList<String> cList ;
	
	public ArrayList<String> generateHopping(String brd) 
	{
		cList = new ArrayList<String>();
		
		char[] b = brd.toCharArray();
		
		char[] cpybrd;
		
		for(int i=0;i<b.length;i++)
		{
			if(b[i]=='W')
			{
				for (int j = 0; j < b.length; j++) 
				{
					if(b[j]=='x')
					{
						cpybrd = b.clone();
						cpybrd[i]='x';
						cpybrd[j] = 'W';
						if(closeMill(j,cpybrd))
						{
							cList=generateRemove(cpybrd, cList); 
						}
						else 
						{
							cList.add(new String(cpybrd));
						}
						
					}					
					
				}
				
			}
		}
		return cList;
	}
	
	public ArrayList<String> generateMovesMidEndGame(String brd)
	{
		char[] c = brd.toCharArray();
		
		int cnt =0;
		
		for (char d : c) 
		{
			if(d == 'W')
				cnt++;			
		}
		if(cnt == 3)
		{
			return generateHopping(brd);
		}
		else
			return generateMove(brd);
		
	}
	
	
		
	public String MaxMin(String board,int depth) 
	{
		if(depth >0)
		{
			ArrayList<String> t = generateMovesMidEndGame(board);
			
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
					this.max_estimate = max;
					move = s;
					//hmap.put(s, max);
					
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
	
	
	
	public int[] neighbours(int j)
	{
		int[] adjcnt=new int[5];
		
		switch(j)
		{
		
			case 0 : adjcnt=new int[]{1,2,6}; return adjcnt;
		
			case 1 : adjcnt=new int[]{0,11}; return adjcnt;
			case 2 : adjcnt=new int[]{0,3,4,7}; return adjcnt; 
			case 3 : adjcnt=new int[]{2,10}; return adjcnt;
			case 4 : adjcnt=new int[]{2,5,8};return adjcnt;
			case 5 : adjcnt=new int[]{4,9}; return adjcnt;
			case 6 : adjcnt=new int[]{0,7,18}; return adjcnt;
			case 7 : adjcnt=new int[]{2,6,8,15}; return adjcnt;
			case 8 : adjcnt=new int[]{4,7,12};return adjcnt; 
			case 9 : adjcnt=new int[]{5,10,14}; return adjcnt;
			case 10 : adjcnt=new int[]{3,9,11,17}; return adjcnt;
			case 11 : adjcnt=new int[]{1,10,20}; return adjcnt;
			case 12 : adjcnt=new int[]{8,13}; return adjcnt;
			case 13 : adjcnt=new int[]{12,14,16}; return adjcnt;
			case 14 : adjcnt=new int[]{9,13}; return adjcnt;
			case 15 : adjcnt=new int[]{7,16}; return adjcnt;
			case 16 : adjcnt=new int[]{13,15,17,19};return adjcnt;
			case 17 : adjcnt=new int[]{10,16}; return adjcnt;
			case 18 : adjcnt=new int[]{6,19}; return adjcnt;
			case 19 : adjcnt=new int[]{16,18,20}; return adjcnt;	
			case 20 : adjcnt=new int[]{11,19}; return adjcnt;
			default: adjcnt=null;return adjcnt;
			
		}
			
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
	
	
	public ArrayList<String> generateBlackMoves(String brd)
	{
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
	
	public int staticEstimation(String brd) 
	{
		int numwhites = 0;
		int numblacks = 0;
		int bmoves =0;
		char[] b = brd.toCharArray();
		ArrayList<String> bm = generateBlackMoves(brd);
		bmoves = bm.size();
		for(int i=0;i<b.length;i++) 
		{
			if(b[i]=='W') 
			{
				numwhites++;
			}
			else if(b[i]=='B') 
			{
				numblacks++;
			}
		}
		
		if(numblacks <=2)
			return 10000;
		else if (numwhites <=2)
			return -10000;
		else if (bmoves == 0)
			return 10000;
		else		
			return (1000*(numwhites-numblacks) - bmoves);
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
				
		    	MiniMaxGameBlack obj = new MiniMaxGameBlack(brd,depth);
		    	bestmove= obj.MaxMin(brd, depth);
		    	
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
		    	minimax_estimate = obj.max_estimate;
		    	
		    }
		   		    
		    FileWriter fstream = new FileWriter(args[1]);
	        PrintWriter out = new PrintWriter(fstream);
	        System.out.println("Board Position" + bestmove + " " + "Postions evaluated " +num_positions + " " + "minimax estimate" + minimax_estimate);
	        out.println("Board Position" + bestmove + " " + "Postions evaluated " +num_positions + " " + "minimax estimate" + minimax_estimate);
			out.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
