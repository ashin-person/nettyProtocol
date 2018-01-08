package ljx.ashin.server;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ashin Liang on 2017/9/22.
 */
public class NewServerHandler  {

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String dateString = formatter.format(currentTime);
        Date newDate = null;
        try {
            newDate = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return newDate;
    }

    public static void main(String[] args) {
        Date now = new Date();
        Date date = getNowDate();
        System.out.println(date);

        Date d = getFormatterDate(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println(d);
    }

    public static Date getFormatterDate(Date date,String text){
        Date resultDate = null;
        if (date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat(text);
            String soure = sdf.format(date);
            try {
                resultDate = sdf.parse(soure);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return resultDate;
    }
}
