public class Keyword 
{ 
	public String name; 
	public double score; 
	 
	public Keyword(String name, double score) 
	{ 
		this.name = name; 
		this.score = score; 
	} 
	public String toString()
	{ 
		return "[" + name + ", " + score + "]"; 
	} 
	public static int size()
	{ 
		return Keyword.size(); 
	} 
	 
}