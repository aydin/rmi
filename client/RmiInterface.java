/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author adige
 */
public interface RmiInterface extends java.rmi.Remote {

	public String get_html_content(String url, String path, String param1, String val1, String param2, String val2) throws Exception;
	public String[] search_by_movie(String movie)throws Exception;
	public String get_top10_movies() throws Exception;
	public String get_weather_info()throws Exception;

}
