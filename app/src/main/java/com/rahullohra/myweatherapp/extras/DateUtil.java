package com.rahullohra.myweatherapp.extras;

import android.content.Context;

import com.rahullohra.myweatherapp.extras.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {
    private static final String GMT = "GMT";
    private final static String ISO_FORMAT = "yyyy-MM-dd";

    public static String millsToDay(Context context, String dateText) {
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_FORMAT, Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone(GMT));

        Date date = null;
        try {
            date = sdf.parse(dateText);
        } catch (ParseException e) {
            date = new Date();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int num = calendar.get(Calendar.DAY_OF_WEEK);
        return Util.INSTANCE.getWeek(context, num);
    }

}
