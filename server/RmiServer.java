/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.Naming;

/**
 *
 * @author adige
 */
public class RmiServer extends java.rmi.server.UnicastRemoteObject  implements RmiInterface {

	private static final long serialVersionUID = 1L;
    GetInfo inf;
	public RmiServer() throws Exception
     {
       super();
       inf = new GetInfo();
     }
	public static void main(String[] args) throws Exception {

		RmiInterface server_obj=new RmiServer();
	    Naming.rebind("rmi://localhost/Service",server_obj);	     
	}
    public String get_html_content(String url, String path, String param1, String val1, String param2, String val2) throws Exception{
        return inf.get_html_content(url, path, param1, val1, param2, val2);
    }

	public String[] search_by_movie(String movie)throws Exception{
        return inf.search_by_movie(movie);
    }
	public String get_top10_movies() throws Exception{
        return inf.get_top10_movies();
    }
	public String get_weather_info()throws Exception{
        return inf.get_weather_info();
    }

}
