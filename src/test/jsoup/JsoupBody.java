package test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupBody {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		String html = "<div> <p>Lorem ipsum.</p>";
		//这里解析的时候会自动补全完整的HTML
		Document doc = Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		
		
	}
}
