
import connections.RmiDomain;
import org.apache.log4j.Logger;
import ui.MainMenu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The class Main represents the start class. Contains static main method.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Date formatters
        /*SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        //SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
        
        String t = "15:30:00";
        Date dat = sdf2.parse(t);
        
        System.out.println(dat);*/
        LOGGER.info("Starting Flight Client application..");
        RmiDomain.lookUpRmiDomain();
        new MainMenu();
    }
}
