
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
	public ArrayList<String> urlString = new ArrayList<String>();
	public ArrayList<String> namearr = new ArrayList<String>();
	public int error403 = 0;
	
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		this.url = "http://www.google.com/search?q="+searchKeyword+"&oe=utf8&num=20";
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";
		URL urlStr = new URL(this.url);
		URLConnection conn = urlStr.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in,"utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		
		String line = null;
		while((line=bufReader.readLine())!=null)
		{
			retVal += line;
		}
		
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content==null)
		{
			content= fetchContent();
		}
		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("div.g");
		
		for(Element li : lis)
		{
			try 
			{
				Element h3 = li.select("h3.r").get(0);
				String title = h3.text();
				
				Element cite = li.select("cite").get(0);
				String citeUrl = cite.text();
				
				//Element cite = li.getElementsByTag("a").first();
				//String citeUrl = cite.attr("href");
		
				//filter websites that can't be count
				URL testurl = new URL(citeUrl);
				HttpURLConnection urlconnection = (HttpURLConnection) testurl.openConnection();
				urlconnection.setReadTimeout(10000);
				urlconnection.connect();
				
				//filter some social media
				if (citeUrl.indexOf("facebook") != -1) 
				{
			        continue;
				}
				if (citeUrl.indexOf("twitter") != -1) 
				{
			        continue;
				}
				//filter forbidden website
				int statusCode = urlconnection.getResponseCode();
				if(statusCode == HttpURLConnection.HTTP_UNAVAILABLE)
				{
					continue;
				}
				else if (statusCode == HttpURLConnection.HTTP_NOT_FOUND) //404
				{
					continue;
				}
				else if(statusCode == HttpURLConnection.HTTP_FORBIDDEN) //403
				{
					continue;
				}
				else if(statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) //401
				{
					continue;
				}
				else if(statusCode == HttpURLConnection.HTTP_BAD_REQUEST)//400
				{
					continue;
				}
				
				urlString.add(citeUrl);
				namearr.add(title);
				retVal.put(title, citeUrl);
			} catch (Exception e) {
				
			}
			
		}
		
		return retVal;
	}
	
	
}
