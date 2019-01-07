import java.io.IOException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNextLine())
		{
			String keyword = scanner.next();
			GoogleQuery googleQuery = new GoogleQuery(keyword);
			googleQuery.query();
			//for(int i=0;i>googleQuery.returnlength();i++) {
			//WebPage rootPage=new WebPage(googleQuery.returnciteUrl(i),googleQuery.retuenTitle(i));
			//WebTree tree=new WebTree(rootPage);
			//tree.setPostOrderScore(keywords);
			//tree.printTree();
		//
		}
		scanner.close();
	}
}