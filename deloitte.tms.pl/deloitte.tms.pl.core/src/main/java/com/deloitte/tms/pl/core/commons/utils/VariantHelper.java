package com.deloitte.tms.pl.core.commons.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class VariantHelper
{
  private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
  private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Object toObject(byte value)
  {
    return new Byte(value);
  }

  public static Object toObject(short value)
  {
    return new Short(value);
  }

  public static Object toObject(int value)
  {
    return new Integer(value);
  }

  public static Object toObject(long value)
  {
    return new Long(value);
  }

  public static Object toObject(float value)
  {
    return new Float(value);
  }

  public static Object toObject(double value)
  {
    return new Double(value);
  }

  public static Object toObject(boolean value)
  {
    return new Boolean(value);
  }

  public static Object toObject(Date value)
  {
    return value;
  }

  public static Object toObject(BigDecimal value)
  {
    return value;
  }

  public static Object toObject(String value)
  {
    return value;
  }

  public static String parseString(Object o)
  {
    if (o == null) {
      return null;
    }
    if ((o instanceof Date)) {
      return String.valueOf(((Date)o).getTime());
    }

    return o.toString();
  }

  public static byte parseByte(Object o)
  {
    if (o == null) {
      return 0;
    }
    if ((o instanceof Number)) {
      return ((Number)o).byteValue();
    }
    if ((o instanceof Boolean)) {
      return (byte)(((Boolean)o).booleanValue() ? 1 : 0);
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0;
    }

    return Byte.parseByte(s);
  }

  public static short parseShort(Object o)
  {
    if (o == null) {
      return 0;
    }
    if ((o instanceof Number)) {
      return ((Number)o).shortValue();
    }
    if ((o instanceof Boolean)) {
      return (short)(((Boolean)o).booleanValue() ? 1 : 0);
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0;
    }

    return Short.parseShort(s);
  }

  public static int parseInt(Object o)
  {
    if (o == null) {
      return 0;
    }
    if ((o instanceof Number)) {
      return ((Number)o).intValue();
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue() ? 1 : 0;
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0;
    }

    return Integer.parseInt(s);
  }

  public static long parseLong(Object o)
  {
    if (o == null) {
      return 0L;
    }
    if ((o instanceof Number)) {
      return ((Number)o).longValue();
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue() ? 1L : 0L;
    }
    if ((o instanceof Date)) {
      return ((Date)o).getTime();
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0L;
    }

    return Long.parseLong(s);
  }

  public static float parseFloat(Object o)
  {
    if (o == null) {
      return 0.0F;
    }
    if ((o instanceof Number)) {
      return ((Number)o).floatValue();
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue() ? 1.0F : 0.0F;
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0.0F;
    }

    return Float.parseFloat(s);
  }

  public static double parseDouble(Object o)
  {
    if (o == null) {
      return 0.0D;
    }
    if ((o instanceof Number)) {
      return ((Number)o).doubleValue();
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue() ? 1.0D : 0.0D;
    }

    String s = o.toString();
    if (s.equals("")) {
      return 0.0D;
    }

    return Double.parseDouble(s);
  }

  public static BigDecimal parseBigDecimal(Object o)
  {
    if (o == null) {
      return BigDecimal.valueOf(0L);
    }
    if ((o instanceof BigDecimal)) {
      return (BigDecimal)o;
    }
    if ((o instanceof Number)) {
      return BigDecimal.valueOf(((Number)o).longValue());
    }
    if ((o instanceof Boolean)) {
      return BigDecimal.valueOf(((Boolean)o).booleanValue() ? 1L : 0L);
    }

    String s = o.toString();
    if (s.equals("")) {
      return BigDecimal.valueOf(0L);
    }

    return new BigDecimal(s);
  }

  public static boolean parseBoolean(String s)
  {
    if (s == null) {
      return false;
    }
    return (s.equalsIgnoreCase("true")) || (s.equals("1")) || (s.equals("-1"));
  }

  public static boolean parseBoolean(Object o)
  {
    if (o == null) {
      return false;
    }
    if ((o instanceof Boolean)) {
      return ((Boolean)o).booleanValue();
    }

    return parseBoolean(o.toString());
  }

  private static boolean isNumeric(String s)
  {
    int length = s.length();
    for (int i = 0; i < length; i++) {
      char c = s.charAt(i);
      if (((c < '0') || (c > '9')) && (c != '.') && (
        (i != 0) || (c != '-'))) {
        return false;
      }
    }

    return true;
  }

  public static Date parseDate(Object o)
  {
    if (o == null) {
      return null;
    }
    if ((o instanceof Date)) {
      return (Date)o;
    }
    if ((o instanceof Number)) {
      return new Date(((Number)o).longValue());
    }

    String s = String.valueOf(o);
    if (StringUtils.isNotEmpty(s)) {
      if (isNumeric(s)) {
        long time = Long.parseLong(s);
        return new Date(time);
      }

      int len = s.length();
      try {
        if (len < 19) {
          if (s.indexOf(":") > 0) {
            return sdf2.parse(s);
          }

          return sdf1.parse(s);
        }

        return sdf3.parse(s);
      }
      catch (ParseException ex)
      {
        ex.printStackTrace();
        return null;
      }

    }

    return null;
  }

  public static Object translate(int dataType, Object value)
  {
    if ((value == null) || (((value instanceof String)) && (((String)value).length() == 0)))
    {
      if (dataType == 1) {
        return value;
      }

      return null;
    }

    switch (dataType) {
    case 1:
      return parseString(value);
    case 9:
      return toObject(parseBoolean(value));
    case 10:
    case 11:
    case 12:
      return parseDate(value);
    case 4:
      return toObject(parseInt(value));
    case 7:
      return toObject(parseDouble(value));
    case 5:
      return toObject(parseLong(value));
    case 6:
      return toObject(parseFloat(value));
    case 8:
      return parseBigDecimal(value);
    case 2:
      return toObject(parseByte(value));
    case 3:
      return toObject(parseShort(value));
    }

    return value;
  }
}