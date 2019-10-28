import java.util.regex.*;

public class urlValidator{
	public static boolean validURL(String url){
		boolean valid = Pattern.matches("[\\S]+\\.m3u8", url);
		return valid;
	}
}
