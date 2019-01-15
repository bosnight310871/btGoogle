public class Keyword 
{ 
	public String name; 
	public double score; 
	 
	public Keyword(String name, double weight) 
	{ 
		this.name = name; 
		this.score = weight; 
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