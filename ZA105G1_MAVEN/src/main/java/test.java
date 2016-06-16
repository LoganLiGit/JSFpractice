import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.group.table.model.GroupTableVO;
import com.store.model.StoreService;
import com.store.model.StoreVO;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<100; i+=3){
		for(int j=i ,count=0; count<3; j++,count++){
			System.out.print(j+" ");
		}
		System.out.println();
	}
	}

	public static String changeTime(String str){

		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append(str.substring(str.lastIndexOf("/")+1,str.lastIndexOf("/")+5));
		strBuffer.append("-");
		strBuffer.append(str.substring(0,2));
		strBuffer.append("-");
		strBuffer.append(str.substring(str.lastIndexOf("/")-2,str.lastIndexOf("/")));
		strBuffer.append(" ");
		if(str.substring(str.length()-2,str.length()).equals("PM")){
			if(str.length()==18){
				String hour =str.substring(11, 12);
				Integer tmp=Integer.parseInt(hour)+12;
				strBuffer.append(tmp.toString());
				strBuffer.append(":");
				strBuffer.append(str.substring(str.lastIndexOf(" ")-2,str.lastIndexOf(" ")));
				strBuffer.append(":00");
				
			}else{
				String hour =str.substring(11, 13);
				Integer tmp=Integer.parseInt(hour)+12;
				strBuffer.append(tmp.toString());
				strBuffer.append(":");
				strBuffer.append(str.substring(str.lastIndexOf(" ")-2,str.lastIndexOf(" ")));
				strBuffer.append(":00");
				
			}
		}else{
			if(str.length()==18){
			strBuffer.append("0");
			strBuffer.append(str.substring(str.lastIndexOf(" ")-4,str.lastIndexOf(" ")));
			strBuffer.append(":00");
			
			}
			else{
				strBuffer.append(str.substring(str.lastIndexOf(" ")-5,str.lastIndexOf(" ")));
				strBuffer.append(":00");
				
			}
		}
		return strBuffer.toString();
	}
}
