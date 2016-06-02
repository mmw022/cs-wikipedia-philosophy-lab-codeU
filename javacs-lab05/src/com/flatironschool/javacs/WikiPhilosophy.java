package com.flatironschool.javacs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import org.jsoup.select.Elements;

public class WikiPhilosophy {
	
	final static WikiFetcher wf = new WikiFetcher();
	
	/**
	 * Tests a conjecture about Wikipedia and Philosophy.
	 * 
	 * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
	 * 
	 * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
        // some example code to get you started

		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
      String oldURL = "notURL";
      String urlPhi = "https://en.wikipedia.org/wiki/Philosophy";
      String wikiUrl = "https://en.wikipedia.org/wiki";
      ArrayList<String> urlList = new ArrayList<String>();
      boolean repeat = false;
      int count = 0;
		
      while(!(url.equals(urlPhi)))
      {

         Elements paragraphs = wf.fetchWikipedia(url);

		   Element firstPara = paragraphs.get(0);
	
         repeat = false;
      	
		   Iterable<Node> iter = new WikiNodeIterable(firstPara);
         Element htmlTag;

		   for (Node node: iter) {
			   if (node instanceof Element) 
            {
               
               url = node.attr("abs:href"); 
               

               if( !(url.equals("")) )
               {
                  if( url.indexOf( wikiUrl) == -1 )
                  {
                     repeat = true;
                  }
                  
                  else if (urlList.contains((url)) )
                  {
                     repeat = true;
                  }
                  
                  else if( url.indexOf( oldURL ) != -1 )
                  {
                     repeat = true;
                  }
                  
                  while( node.parent() != null )
                  {
                     Node parentNode = node.parent();
                     if( parentNode instanceof TextNode )
                     {
                    //    System.out.println(parentNode);
                     }
                     node = node.parent();
                  }

                  /*else if( url.indexOf( "Greek" ) != -1 )
                  {
                     repeat = true;
                  }*/
                  
                  if( !(repeat) )
                  {
                     System.out.println(url);
                     oldURL = new String( url );
                     urlList.add(url);
                     break;
                  }
               }
               
               repeat = false;
			   }
         }
      }

        // the following throws an exception so the test fails
        // until you update the code
        //String msg = "Complete this lab by adding your code and removing this statement.";
        //throw new UnsupportedOperationException(msg);
	}
}
