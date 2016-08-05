package test.jsoup;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupConnection {
	
	private static Logger logger = Logger.getLogger(JsoupConnection.class);
	
	public static void main(String[] args) throws Exception {
		
		Document doc = Jsoup.connect("http://baike.baidu.com/").get();
        Elements links = doc.select("a[href]");
        Elements media = doc.select("[src]");
        Elements imports = doc.select("link[href]");

        logger.error("Media的数量是: " + media.size());
        
        for (Element src : media) {
        	//如果media为图片
            if (src.tagName().equals("img"))
            	logger.debug(src.tagName() +"地址是："+ src.attr("abs:src") + src.attr("width") + src.attr("height") ); 
            else
            	logger.debug( src.tagName()+src.attr("abs:src") );
        }

        logger.error("Imports的数量是: " + imports.size());
        for (Element link : imports) {
        	logger.debug(link.tagName() + link.attr("abs:href") + link.attr("rel"));
        }

        logger.error("Links的数量是: " + links.size());
        for (Element link : links) {
        	 logger.debug(link.attr("abs:href"));
        }
	}
}
