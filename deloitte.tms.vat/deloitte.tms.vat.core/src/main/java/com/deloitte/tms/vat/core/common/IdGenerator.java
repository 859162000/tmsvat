package com.deloitte.tms.vat.core.common;

import java.text.NumberFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.lang3.time.FastDateFormat;






/**
 * @author blzhang
 */
public final class IdGenerator {

	private static final AtomicLong atomicSeqContinue = new AtomicLong(0L);
	private static volatile Date lastTimestamp = new Date();
	private static final AtomicLong atomicSeqTimestamp = new AtomicLong(0L);
	private static final int MAX_SEQ_OF_MILLIS = 999999;
	private static final int SEQ_DIGIT = 6;
	private static NumberFormat numberFormatter = NumberFormat
			.getNumberInstance();
	private static final String TIMESTAMP_DATE_FORMAT = "yyyyMMddHHmmssSSS";

	private static final FastDateFormat timeStampFormatter = FastDateFormat
			.getInstance(TIMESTAMP_DATE_FORMAT);



	/**
	 * 
	 * @return
	 */
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
		//return StringUtils.uuid();
	}

	private IdGenerator() {
	}

	private static String uuid = getUUID();

	public static int getUUIDLength() {
		return uuid.length();
	}

	public static long getTimestampSeq() {
		long timestamp = System.currentTimeMillis();
		long seq = atomicSeqContinue.getAndIncrement();
		if (seq > MAX_SEQ_OF_MILLIS) {
			atomicSeqContinue.set(0L);
			seq = atomicSeqContinue.getAndIncrement();
		}

		// 14位的时间戳 + 6位序号
		return Long.valueOf(String.format("%d%s", timestamp,
				formatNumber(seq, SEQ_DIGIT)));
	}

	public static synchronized String getTimestamp(Integer seqDigit) {
		Date timestamp =new Date();
		String timepstampString=getTimeStampDateString(timestamp);
		if (seqDigit != null && seqDigit > 0) {
			if (lastTimestamp.getTime() != timestamp.getTime()) {
				lastTimestamp = timestamp;
				atomicSeqTimestamp.set(0L);
			}

			long seq = atomicSeqTimestamp.getAndIncrement();

			// 18位的时间戳 + seqDigit位序号
			return String.format("%s%s", timepstampString,
					formatNumber(seq, seqDigit));
		} else {
			return timepstampString;
		}
	}
	
	public static String  formatNumber(long number, int numberDigit) {
		numberFormatter.setMaximumIntegerDigits(numberDigit);
		numberFormatter.setMinimumIntegerDigits(numberDigit);
		numberFormatter.setGroupingUsed(false);
		numberFormatter.setMaximumFractionDigits(0);
		return numberFormatter.format(number);
	}
	
	public static String getTimeStampDateString(Date date) {
		return timeStampFormatter.format(date);
	}
	
	public static String getPrefixTimestamp(String prex,Integer seqDigit){
		String timeString = getTimestamp(seqDigit);
		return prex+timeString;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			String a = IdGenerator.getPrefixTimestamp("Req",2);
			System.out.println(a);
		}

	}



}
