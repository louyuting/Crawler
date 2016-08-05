package test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class JsoupHTML {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		//定义一个最简单的HTML
		String html = "<html>"
						+ "<head>"
							+ "<title>First parse</title>"
						+ "</head>"
						+ "<body>"
							+ "<p>Parsed HTML into a doc.</p>"
						+ "</body>"
					+ "</html>";
		//获取文档对象模型
		Document document = Jsoup.parse(html);
		Element head = document.head();
		Element body = document.body();
		
	}

}














