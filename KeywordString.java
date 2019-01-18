
public class KeywordString
{ 
	public String title;
	public String name; 
	public double score; 
	 
	public KeywordString(String title, String name, double score) 
	{ 
		this.title = title;
		this.name = name; 
		this.score = score; 
	} 
	@Override
	public String toString()
	{ 
		return "["+ title+", " + name + ", " + score + "]"; 
	} 
	public static int size()
	{ 
		return KeywordString.size(); 
	}
	public String gettitle()
	{
		return this.title;
	}
	public String geturl()
	{
		return this.name;
	}
	public double getScore()
	{
		return this.score;
	}
	
	/*
	 * public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	 */
	 
}