package resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {
	 static ResourceBundle language = null;
	public static ResourceBundle getInstance(){
		  Locale[] locales = new Locale[]{
				  new Locale("de", "DE"),new Locale("fr", "FR"), new Locale("en", "EN")
		  };
		if(language == null){
			language = ResourceBundle.getBundle("resources.Resources",Locale.getDefault());
			return language;
		} else{
			return language;
		}
		
	}
}
