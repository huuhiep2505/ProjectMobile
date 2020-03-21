package hiepnh.se1304_nguyenhuuhiep.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class CheckData {
    public static String viewTime(Timestamp time){
        if (time != null){
            SimpleDateFormat sp = new SimpleDateFormat("dd/MM/yyyy");
            Date view = new Date(time.getTime());
            return sp.format(view);
        }
        return "";
    }

    public static Timestamp getTimestamp(String date){
        String tmp[] = date.split("/");
        int d = Integer.parseInt(tmp[0]);
        int m = Integer.parseInt(tmp[1]);
        int y = Integer.parseInt(tmp[2]);
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, d);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static boolean checkWorkID(String id, List<String> listID){
        for (String workID:listID
             ) {
            if (id.equalsIgnoreCase(workID)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkUsername(String username, List<String> listUsername){
        for (String name:listUsername
        ) {
            if (username.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public static boolean checkGroupId(String groupId, List<String> listGroupId){
        for (String group:listGroupId
        ) {
            if (groupId.equalsIgnoreCase(group)){
                return true;
            }
        }
        return false;
    }
}
