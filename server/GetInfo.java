/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author adige
 */
public class GetInfo {
    /*
     * Bu metod verdiğimiz url, path ve parametrelerle http istek yapıp
     * çektiği içeriği string türünde döndürüyor
     */
    public String get_html_content(String url, String path, String param1, String val1, String param2, String val2){
        String html = "";
        try {
	        // postlanacak datay� yarat�yoruz
	        String data = URLEncoder.encode(param1, "UTF-8") + "=" + URLEncoder.encode(val1, "UTF-8");
	        data += "&" + URLEncoder.encode(param2, "UTF-8") + "=" + URLEncoder.encode(val2, "UTF-8");
	        	        	        
	        int port = 80;
	        InetAddress addr = InetAddress.getByName(url);
	        Socket socket = new Socket(addr, port);
	        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
	        wr.write("POST "+path+" HTTP/1.0\r\n");
	        wr.write("Content-Length: "+data.length()+"\r\n");
	        wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
            wr.write("Accept-Language: tr-TR,tr;q=0.8,en-us;q=0.5,en;q=0.3\r\n");
	        wr.write("\r\n");	       
	        wr.write(data);
	        wr.flush();

	        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            while((line = rd.readLine()) != null)
            {

                html += line;

            }

            wr.close();
            rd.close();

		 } catch (Exception e) {
		 }
        return html;        
    }
    /*
     * Bu metod verilen movie adıyla arama yapıyor ve şu sonuçları döndürüyor:
     * result[0]: rate
     * result[1]: votes
     * result[2]: director(s)
     * result[3]: relase date
     * result[4]: plot
     * result[5]: awards
     * result[6]: images
     */
    public String[] search_by_movie(String movie){
        String[] result = new String[7];
        String[] tmp = null;
        String url = "www.imdb.com";
        String path = "/find";
        String param1 = "s";
        String val1 = "all";
        String param2 = "q";
        String content = get_html_content(url, path, param1, val1, param2, movie);        
        // Gelen icerikten title'i parse edelim                
        String movie_title = content.split("/title/")[1].split("/")[0];
        System.out.println(movie_title);
        path="/title/"+movie_title+"/";
        // Parse ettigimiz title ile o filmin sayfasini alalim
        String movie_content = get_html_content(url, path, "","","","");
        tmp = movie_content.split("<div class=\"media_strip_thumbs\">");
        String[] images = tmp[1].split("</td");
        movie_content = images[1];
        images = images[0].split("<div class=\"media_strip_thumb\">");
        String images_html = "<table><tr>";
        
        for(int k = 1; k < images.length; k++){
            images[k] = images[k].split("</div")[0];
            images_html += "<td>" + images[k] + "</td>" ;
        }
        images_html += "</tr></table>";
        // User rating'i aldik
        tmp = movie_content.split("<a href=\"ratings\" class=\"tn15more\">");
        String rating = tmp[0].split("<div class=\"meta\">")[1].split("<b>")[1].split("</b>")[0];
        System.out.println(rating);
        movie_content = tmp[1];
        result[0] = rating;

        // Kullanılan oy sayisini aldik
        String votes = movie_content.split(" votes</a>")[0];        
        System.out.println(votes);
        result[1] = votes;

        // Yonetmenleri alalim
        movie_content = movie_content.split("<h5>Director")[1];
        tmp = movie_content.split("</div");        
        String director = "";
        String[] directors = tmp[0].split("<a href=\"");
        for(int k = 1; k < directors.length; k++){
            director += directors[k].split("</a><br/>")[0].split(">")[1] + ",";            
        }
        System.out.println("yönetmen = "+director);        
        result[2] = director;

        // Vizyona giris tarihini alalim        
        movie_content = movie_content.split("<h5>Release Date:</h5>")[1];
        tmp = movie_content.split("</div");        
        String release_date = tmp[0].split("<div class=\"info-content\">")[1].split("<a class")[0];
        System.out.println("yayın tarihi = "+release_date);
        result[3] = release_date;

        // Film ozetini alalim
        movie_content = movie_content.split("<h5>Plot:</h5>")[1];
        tmp = movie_content.split("</div");
        String plot = tmp[0].split("<div class=\"info-content\">")[1].split("<a class")[0];
        System.out.println("özet = "+plot);
        result[4] = plot;

        // Aldigi odulleri alalim
        movie_content = movie_content.split("<h5>Awards:</h5>")[1];
        tmp = movie_content.split("</div");
        String awards = tmp[0].split("<div class=\"info-content\">")[1].split("<a class")[0];
        System.out.println("awards = "+awards);
        result[5] = awards;

        result[6] = images_html;
        
        return result;
    }

    public String get_top10_movies(){
        String url = "www.imdb.com";
        String path = "/chart/";
        String content = get_html_content(url, path, "","","","");
        content = content.split("<div id=\"boxoffice\" class=\"chart_meta\">")[1];
        content = content.split("</table>")[0];
        System.out.println(content);
        return content + "</table>";
    }

    public String get_weather_info(){
        String url = "www.foreca.com";
        String path = "/Turkey/Izmir";        

        String content = get_html_content(url, path, "","","","");
        content = content.split("<div class=\"wrap-area entry-content\">")[1];
        content = content.split("<div id=\"mg\" class=\"wrap-area2\">")[0].split("<div class=\"right txt-tight\">")[1].split("</div>")[0];
        return "<html><div>" + content + "</div></html>";
    }

}
















