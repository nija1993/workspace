package glass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class glass {
	public static void main(String[] args)throws IOException
	{
		int t;
		String str;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		t=Integer.parseInt(br.readLine());
		while(t!=0)
		{
		str=br.readLine();
		int min=1;
		String strless=str;
		int i;
		for(i=1;i<str.length();i++)
		{
			String strnew;
			strnew=str.substring(i)+str.substring(0, i);
			if(strnew.compareTo(strless)<0)
			{
				min=i+1;
				strless=strnew;
			}
		}
		System.out.println(min);
		t--;
		}
	}

}
