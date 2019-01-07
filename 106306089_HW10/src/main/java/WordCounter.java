import java.net.URL;
import java.net.URLConnection;
import java.io.*;
public class WordCounter {

	private String urlString;
	private String content;
	public WordCounter(String urlString){
	this.urlString=urlString;
	}

	private String fetchContent() throws IOException {
		URL url=new URL(this.urlString);
		URLConnection conn =url.openConnection();
		InputStream in = conn.getInputStream();
		BufferedReader br= new BufferedReader(new InputStreamReader(in));
		
		String retVal=" ";
		String line=null;
		
		while((line=br.readLine())!=null){
			retVal=retVal+line+"\n";
			
		}
		
		return retVal;
	} 
	//IF THE KEYWORD IS FIND THEN ADD THE LENGHTH TO FIND THE NEXT ONE,IF FIND NOTHING,IT WILL BE -1
	public int  countKeyword (String keyword)throws IOException {
	    int count = 0;
	    int index = 0;
	    if(content==null) {
	    			content =fetchContent();
	    		}
			content= content.toUpperCase();
			keyword=keyword.toUpperCase();
	    while ((index = content.indexOf(keyword, index)) != -1) {
	        count++;
	        index = index + keyword.length();
	    }
	    return count;
	}
	    }  

