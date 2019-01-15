import java.io.IOException; 
import java.util.ArrayList; 
import java.util.Scanner; 
 
public class Main { 
 
	public static void main(String[] args) throws IOException 
	{ 
		
		System.out.println("Which dish do you want ?");
		Scanner sc = new Scanner(System.in);
		String queryStr = sc.nextLine();
		System.out.println("Wating for search & sort...");
		GoogleQuery gSearch = new GoogleQuery(queryStr);
		gSearch.query();
		
		ArrayList<Keyword> urlarr = new ArrayList<Keyword>();

		//ArrayList<String> namearr = new ArrayList<String>();
		//ArrayList<Double> weightarr = new ArrayList<Double>();
		
		ArrayList<String> urls = new ArrayList<String>();
		
		for (int i =0; i<gSearch.urlarr.size();i++)
		{
			urls.add(gSearch.urlarr.get(i));
		}

		for (String url : urls) 
		{ 
			 WebPage rootPage = new WebPage(url, url); 
			 WebTree tree = new WebTree(rootPage); 
			 //keyword
			 ArrayList<Keyword> keywords = new ArrayList<Keyword>(); 
			 //English keyword
			 keywords.add(new Keyword("spicy",1)); 
			 keywords.add(new Keyword("sweet",3)); 
			 keywords.add(new Keyword("seafood",-3));
			 keywords.add(new Keyword("dessert",2));
			 keywords.add(new Keyword("video", 5));
			 //Chinese keyword
			 keywords.add(new Keyword("¼v",1));			 
			 keywords.add(new Keyword("¤ù",1)); 			
			 keywords.add(new Keyword("ÆQ",1)); 
			 keywords.add(new Keyword("¿}",1)); 
			 keywords.add(new Keyword("¦Ì°s",1)); 
			 keywords.add(new Keyword("Á¤",1)); 
			 keywords.add(new Keyword("»[",1)); 
			 keywords.add(new Keyword("½µ",1)); 
			 keywords.add(new Keyword("Âæªo",1));
			 keywords.add(new Keyword("¨ýµY",1));
			 keywords.add(new Keyword("ªo",1));
			 keywords.add(new Keyword("¾L",1));
			 keywords.add(new Keyword("½Õ¨ý",1));
			 keywords.add(new Keyword("»¶",1)); 
			 keywords.add(new Keyword("ÄÐ",1)); 
			 keywords.add(new Keyword("²¢",1)); 
			 keywords.add(new Keyword("»Ä",1));
			 keywords.add(new Keyword("­¹§÷",1)); 
			 keywords.add(new Keyword("®üÂA",1));
			 keywords.add(new Keyword("¦×",1)); 
			 keywords.add(new Keyword("µæ",1)); 
			 keywords.add(new Keyword("¥Ê",1));
			 keywords.add(new Keyword("¸­",1));
			 keywords.add(new Keyword("·Î",1));
			 keywords.add(new Keyword("ª£",1)); 
			 keywords.add(new Keyword("µN",1)); 
			 keywords.add(new Keyword("¬µ",1)); 
			 keywords.add(new Keyword("¿L",1)); 
			 keywords.add(new Keyword("Ãz­»",1)); 
			 keywords.add(new Keyword("»]",1)); 
			 tree.setPostOrderScore(keywords); 
			 urlarr.add(new Keyword(url,rootPage.score)); 
			 //tree.eularPrintTree(); 
		}
		
		/*  
		for(int i=0; i<urlarr.size();i++)
		{
			namearr.add(urlarr.get(i).name);
			weightarr.add(urlarr.get(i).weight);
		}
		*/
		
		//System.out.println(namearr);
		//System.out.println(weightarr);
		  
		//sort by collection sort
		  
		//System.out.println(namearr);
		//System.out.println(weightarr);
		
		  
		//Print Result 
		System.out.println("During the search there are "+gSearch.error403+" URL_FORBIDDEN, "+gSearch.error404+" URL_NOT_FOUND_, "+gSearch.error503+" URL_UNAVALIABLE.");
		
		int i = 0; 
		for(Keyword k : urlarr) 
		{ 
		 System.out.print(i); 
		 System.out.println(gSearch.namearr.get(i));
		 i++; 
		 System.out.println(k.toString()); 
		}

	  
	} 
}