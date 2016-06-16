import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CopyOftest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String tsStr = "2016-01-19 18:00:00.0";
	
		Timestamp ts =java.sql.Timestamp.valueOf(tsStr);
		System.out.println(ts.toString());
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm a");
		String s =df.format(ts);
		System.out.println(s);
	}

}
