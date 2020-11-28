package sns_assignment;

import java.io.*;
import java.net.Socket;

public class RC4client {
	
	public static void main(String args[]) {
		try
		{
			System.out.println("client started");
			Socket soc = new Socket("localhost",9990);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			
			////////////////////////
			int temp=0; 
			String ptext; 
			String key; 
			int s[]=new int[256]; 
			int k[]=new int[256]; 

			ptext=args[0]; 

			key=args[1]; 

			char ptextc[]=ptext.toCharArray(); 
			char keyc[]=key.toCharArray(); 
			int cipher[]=new int[ptext.length()]; 
			int decrypt[]=new int[ptext.length()]; 
			int ptexti[]=new int[ptext.length()]; 
			int keyi[]=new int[key.length()]; 
			for(int i=0;i<ptext.length();i++) 
			{ 
				ptexti[i]=(int)ptextc[i]; 
			} 
			for(int i=0;i<key.length();i++) 
			{ 
				keyi[i]=(int)keyc[i]; 
			} 
			for(int i=0;i<255;i++) 
			{ 
				s[i]=i; k[i]=keyi[i%key.length()]; 
			} 
			int j=0; 
			for(int i=0;i<255;i++) 
			{ 
				j=(j+s[i]+k[i])%256; 
				temp=s[i]; 
				s[i]=s[j]; 
				s[j]=temp; 
			} 
			int i=0; 
			j=0; 
			int z=0; 
			for(int l=0;l<ptext.length();l++) 
			{ 
				i=(l+1)%256; 
				j=(j+s[i])%256; 
				temp=s[i]; 
				s[i]=s[j]; 
				s[j]=temp; 
				z=s[(s[i]+s[j])%256]; 
				cipher[l]=z^ptexti[l]; 
				
			}
			String ss=new String();
			for(int l=0;l<cipher.length;l++) 
			{ 
				String str1 = Integer.toString(cipher[l]);
				ss=ss.concat(str1);
				ss=ss.concat(" ");
			} 
			System.out.print("\nMessage send to server \t\t"+ ptext); 
			 
			System.out.print("\n\nENCRYPTION:\t\t"); 
			display(cipher);
			 
			
			
			////////////////////
			
			
			
			PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
			out.println(ss);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	static void display(int disp[]) 
	{ 
		char convert[]=new char[disp.length]; 
		for(int l=0;l<disp.length;l++) 
		{ 
			convert[l]=(char)disp[l]; 
			System.out.print(convert[l]); 
		} 
	}
}
