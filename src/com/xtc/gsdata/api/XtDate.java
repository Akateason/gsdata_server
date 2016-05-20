package com.xtc.gsdata.api;


import java.util.Calendar;
import java.util.Date;

public class XtDate {
	
	private final static long kDurationOneDay 	= 1000 * 60 * 60 * 24 ;
	private final static long kDurationHalfDay  = 1000 * 60 * 60 * 12 ;
	
	/**
	 * getCurrentDayOriginalDate
	 * @return today .
	 */
	public static Date getCurrentDayOriginalDate() 
	{
		Calendar calendar = Calendar.getInstance() ;
		Date today = calendar.getTime() ;
		return today ;
	}
	
	/**
	 * dateDiffFromSqlTime 
	 * @param dateFromSql
	 * @param duration
	 * @return [ how many times between datefromsql and now . ]
	 */
	private static long dateDiffFromSqlTime(Date dateFromSql , long duration) 
	{	
		long timeDateSql	= dateFromSql.getTime() ;					// forward 
		long todayDate		= getCurrentDayOriginalDate().getTime() ;   // now
		
        long diff = todayDate - timeDateSql ; 
        long days = diff / duration ;
		
		return days ;
	}
	
	/**
	 * isMoreThanOneDay
	 * @param dateFromSql
	 * @return 
	 */
	public static boolean isMoreThanOneDay(Date dateFromSql) {
		long days = dateDiffFromSqlTime(dateFromSql, kDurationOneDay) ;
		return (days >= 1) ;
	}
	
	/**
	 * isMoreThanOneWeek
	 * @param dateFromSql
	 * @return
	 */
	public static boolean isMoreThanOneWeek(Date dateFromSql) {
		long days = dateDiffFromSqlTime(dateFromSql, kDurationOneDay) ;
		return (days >= 7) ;
	}
	
	public static boolean isMoreThanHalfDay(Date datefromSal) {
		long days = dateDiffFromSqlTime(datefromSal, kDurationHalfDay) ; // 12h
		return (days >= 1) ; // more than half day .
	}
	
}
