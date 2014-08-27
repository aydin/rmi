/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adige
 */
public class UpdateWeather extends Thread {
    RmiView container;
    
    public UpdateWeather(RmiView container){
        this.container = container;
    }    
	public void run()
	{
        while(true){            
            try {
                container.jLabel4.setText(container.inf.get_weather_info());
            } catch (Exception ex) {
                Logger.getLogger(UpdateWeather.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateWeather.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("weather updated");
        }
        
    }

}
