package com.employee.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.UUID;

public class Util {
	private final static ThreadLocal<SimpleDateFormat>  DATE_FORMAT =  ThreadLocal.withInitial(()->{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		// Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		return df;
	});
	private final static ThreadLocal<SimpleDateFormat>  dateFormat =  ThreadLocal.withInitial(()->{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		// Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		return df;
	});

	private final static ThreadLocal<SimpleDateFormat>  DATE_TIME_FORMAT =  ThreadLocal.withInitial(()->{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone tz = TimeZone.getTimeZone("UTC");
		// Quoted "Z" to indicate UTC, no timezone offset
		df.setTimeZone(tz);
		return df;
	});
	public static Date parseDate(String date) {
	    try {
	        return new Date(DATE_FORMAT.get().parse(date).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}

	public static String getUtcDate(String dateString,String pattern){
		if(dateString==null)
			return null;
		return DateFormatUtils.format(Util.getFormattedInstant(dateString), pattern);
	}

	public static String getUtcDateOrDefault(String dateString,String pattern,String defaultDateString){
		try {
			return DateFormatUtils.format(Util.getFormattedInstant(dateString), pattern);
		}catch (Exception e){
			return defaultDateString;
		}
	}


	public static java.util.Date getFormattedInstant(String isoDateString){
		try {
			return dateFormat.get().parse(isoDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	 
	public static Timestamp parseTimestamp(String timestamp) {
	    try {
	        return new Timestamp(DATE_TIME_FORMAT.get().parse(timestamp).getTime());
	    } catch (ParseException e) {
	        throw new IllegalArgumentException(e);
	    }
	}
	public static Timestamp currentTimestamp() {
			return new Timestamp(System.currentTimeMillis());
	}
	
	public static String currentDate() {
		return DateFormatUtils.format(new java.util.Date(), "yyyy-MM-dd HH:mm:s");
	}
	
	public static String generateNumber() {
		return DateFormatUtils.format(new java.util.Date(), "yyyyMMddHHmm");
	}
	
	public static String currentEpoch() {
		return Instant.now().toEpochMilli()+"";
	}
	
	public static String uuid() {
		return UUID.randomUUID().toString();
	}

	public static LocalDate calculateNextEmiDate(LocalDate localDate,long month){
		return localDate.plusMonths(month);
	}

	public static Double calculateMonthlyInterest(Double principal,Double interestRate){
		Double interestAmount=((principal*interestRate)/100);
		return interestAmount;
	}

	public static Double calculateMonthlyBankShare(Double principal,Double interestRate,double deductionRate,int personToShareProfit){
		Double totalInterestAmount=calculateMonthlyInterest(principal,interestRate);
		Double deductionAmount = calculateMonthlyInterest(principal,deductionRate);
		Double pershare=((totalInterestAmount-deductionAmount)/personToShareProfit);
		Double totalBankShare = deductionAmount+(pershare*(personToShareProfit-1));
		return totalBankShare;
	}

	public static Double calculateMonthlyCommission(Double principal,Double interestRate,double deductionRate,int personToShareProfit){
		return calculateMonthlyInterest(principal,interestRate)-calculateMonthlyBankShare(principal,interestRate,deductionRate,personToShareProfit);
	}
	public static Long getLong(Long value){
		return getLong(value,0l);
	}
	public static Long getLong(Long value,Long defaultValue){
		return Objects.isNull(value)?defaultValue:value;
	}
	public static Double getDouble(Double value){
		return getDouble(value,0.0);
	}

	public static Double getDouble(Double value,Double defaultValue){
		return Objects.isNull(value)?defaultValue:value;
	}

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.of ( 2017, Month.DECEMBER, 23 );
		//System.out.println(calculateNextEmiDate(localDate,1));
		//System.out.println(calculateMonthlyInterest(100000.0,3.25));
		//System.out.println(calculateMonthlyBankShare(100000.0,3.25,2.8,1));
		//System.out.println(calculateMonthlyCommission(100000.0,3.25,2.8,1));
		Calendar cal = Calendar.getInstance();
		int res = cal.getActualMaximum(Calendar.DATE);
	}
}
