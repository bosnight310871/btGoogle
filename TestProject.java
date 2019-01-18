import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		//set keyword ready to search
		if(request.getParameter("keyword")== null) 
		{
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		
		// create google query to search
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		System.out.println("Searching...");
		
		HashMap<String, String> query0 = google.query();
		
		System.out.println("Sorting...");
		
		ArrayList<String> urlarr = new ArrayList<String>();
		ArrayList<String> namearr = new ArrayList<String>();
		ArrayList<Double> scorearr = new ArrayList<Double>();
		ArrayList<KeywordString> kStrings = new ArrayList<KeywordString>();
		
		ArrayList<String> urls = new ArrayList<String>();

		for(Entry<String, String> entry : query0.entrySet()) 
		{
		    String key = entry.getKey();
		    String value = entry.getValue();
		    urlarr.add(value);
		    namearr.add(key);
		}
		
		
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		 //English keyword
		keywords.add(new Keyword("video", 100));		keywords.add(new Keyword("salt", 1));
		keywords.add(new Keyword("recipe", 100));	keywords.add(new Keyword("sugar", 1));
		keywords.add(new Keyword("skillet",1));		keywords.add(new Keyword("scoop",1));
		keywords.add(new Keyword("thyme",1));		keywords.add(new Keyword("ingredient",1));
		keywords.add(new Keyword("oven",1));		keywords.add(new Keyword("oil",1));
		keywords.add(new Keyword("stew",1));		keywords.add(new Keyword("slice",1));
		keywords.add(new Keyword("cup",1));			keywords.add(new Keyword("sauce",1));
		keywords.add(new Keyword("mix",1));			keywords.add(new Keyword("wine",1));
		keywords.add(new Keyword("basil",1));		keywords.add(new Keyword("chillies",1));
		keywords.add(new Keyword("slice",1));		keywords.add(new Keyword("garlic",1));
		keywords.add(new Keyword("mustard",1));		keywords.add(new Keyword("teaspoon",1));
		keywords.add(new Keyword("mint",1));		keywords.add(new Keyword("stir",1));		
		keywords.add(new Keyword("butter",1));		keywords.add(new Keyword("parsley",1));
		keywords.add(new Keyword("pan",1));			keywords.add(new Keyword("vinegar",1));
		keywords.add(new Keyword("steam",1));		keywords.add(new Keyword("season",1));
		keywords.add(new Keyword("saute",1)); 		keywords.add(new Keyword("plate",1));	
		keywords.add(new Keyword("grill",1));		keywords.add(new Keyword("bowl",1));
		keywords.add(new Keyword("define",-10));	keywords.add(new Keyword("pedia",-10));
		keywords.add(new Keyword("history",-10));	keywords.add(new Keyword("year",-10));
		keywords.add(new Keyword("wiki",-10));		keywords.add(new Keyword("tutorial",100));
		keywords.add(new Keyword("facebook",-100));	keywords.add(new Keyword("official",100));
		keywords.add(new Keyword("restaurant",-100));keywords.add(new Keyword("menu",100));
				
		for (String url : google.urlString) 
		{ 
			 WebPage rootPage = new WebPage(url, url); 
			 WebTree tree = new WebTree(rootPage); 
			 //calculate
			 tree.setPostOrderScore(keywords); 
			 scorearr.add(rootPage.score);
		}
		
		//formate keywordString
		for(int i = 0; i<google.urlString.size(); i++)
		{
			kStrings.add(new KeywordString( namearr.get(i), urlarr.get(i), scorearr.get(i) ) );
		}
		
		//sort
		class keywordStringComparator implements Comparator<KeywordString>
		{
			public int compare(KeywordString k1, KeywordString k2) 
			{
				if(k1.getScore()<k2.getScore())
				{
					return 1;
				}
				if(k1.getScore()>k2.getScore())
				{
					return -1;
				}
				return 0;
			}
		}
		Collections.sort(kStrings, new keywordStringComparator());
	   


		HashMap<String, String> query = new HashMap<String, String>();
		
		for(KeywordString k : kStrings)
		{
			query.put(k.gettitle(), k.geturl());
		}
		
		//the matrix result
		String[][] s = new String[query.size()][2];
		request.setAttribute("query", s);
		//output result by url and name
		int num = 0;
		for(Entry<String, String> entry : query.entrySet()) 
		{
		    String key = entry.getKey();
		    String value = entry.getValue();
		    s[num][0] = key;
		    s[num][1] = value;
		    num++;
		}
	
		request.getRequestDispatcher("googleitem.jsp").forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
