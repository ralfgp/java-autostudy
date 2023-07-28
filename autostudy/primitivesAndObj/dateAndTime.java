package autostudy.primitivesAndObj;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.MessageFormat;
import java.text.NumberFormat;

public class dateAndTime {

    /*
     * LocalDateTime.of(year, month, day, hours, minutes, seconds, nanoseconds)
     * LocalTime.of(hours, minutes, seconds, nanoseconds)
     * LocalDate.of(year, month, day)
     */
    public void TimeFunc(){
        LocalDate today = LocalDate.now();
        LocalTime thisTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDate someDay = LocalDate.of(2019,Month.APRIL,1);
        LocalTime someTime = LocalTime.of(10,6);
        LocalDateTime otherDateTime = LocalDateTime.of(2019, Month.APRIL, 31, 23, 59);
        LocalDateTime someDateTime = someDay.atTime(someTime);
        LocalDate wathWasTheDate = someDateTime.toLocalDate();
    }

    /*
     * Local Date and Time objects are immutable
     * All date and time manipulation will produce new date and time objects
     * New date and time objects can be produced out of existing objectis with
     * plusXXX() or minusXXX() or withXXX() methods
     * Operations isBefore() and isAfter() returns true or false
     */
    public void timeOperations(){
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime different = current.withMinute(14).withDayOfMonth(3).plusHours(12);
        int year = current.getYear();
        boolean before = current.isBefore((different));
    }

    /*
     * Duration class can represent an amount of time in nanoseconds
     * Period class can represent an amount of time in years or days
     * Instant calss can represent an instantaneous point of time (time-stamp)
     * They're immutable
     * Provide methods now(), ofXXX(), plusXXX(), minusXXX(), withXXX(), and getXXX()
     * Provide methosd between(), isNegative() to handle distances between points in time
     * Instant and duration ar more suitable for system tasks such as logs, Period is more suitable for business logic
     */
    public void instDurAndPeriods(){
        LocalDate today = LocalDate.now();
        LocalDate foolsDay = LocalDate.of(2019, Month.APRIL,1);
        Instant timeStamp = Instant.now();
        int nanoSecondsFromLastSecond = timeStamp.getNano();
        Period howLong = Period.between(foolsDay, today);
        Duration twoHours = Duration.ofHours(2);
        long seconds = twoHours.minusMinutes(15).getSeconds();
        int days = howLong.getDays();
    }

    /*
     * 
     */
    public void zonedDateAndTime(){
        ZoneId london = ZoneId.of("Europe/London");
        ZoneId la = ZoneId.of("America/Los_Angeles");
        LocalDateTime someTime = LocalDateTime.of(2019, Month.APRIL, 1, 7, 14);
        ZonedDateTime londonTime = ZonedDateTime.of(someTime, london);
        ZonedDateTime lDateTime = londonTime.withZoneSameInstant(la);

        //Timezones can be set as:
        ZoneId.of("America/Los_Angeles");
        ZoneId.of("GMT+2");
        ZoneId.of("UTC-05:00");
        ZoneId.systemDefault();
    }

    public void reprLangAndCount(){
        Locale uk = new Locale("en","GB");                      // English Britain
        Locale ukEuro = new Locale("en","GB", "EURO");  // English Bitain Euro (custom variant)
        Locale us = new Locale("en","US");                      // English America
        Locale fr  = new Locale("fr","FR");                     // French France
        Locale cf = new Locale("fr","CA");                      // French Canada
        Locale frCaribbean = new Locale("fr","029");            // French Caribbean
        Locale es = new Locale("fr");                                   // French
        Locale current = Locale.getDefault();                                    // current default locale
        // Example constructing locale that use Thai numbers and Buddhist calendar:
        Locale th = Locale.forLanguageTag("th-TH-u-ca-buddhist-nu-thai");
    }

    public void formatAndParseNumericValues() throws ParseException{
        BigDecimal price = BigDecimal.valueOf(2.99);
        Double tax = 0.2;
        int quantity = 123455;
        Locale locale = new Locale("en","GB");
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentageFormat = NumberFormat.getPercentInstance(locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        String formattedPrice = currencyFormat.format(price);
        String formattedTax = percentageFormat.format(tax);
        String formattedQuantity = numberFormat.format(quantity);
        System.out.println(formattedPrice+"  "+formattedTax+"  "+formattedQuantity);

        BigDecimal p = BigDecimal.valueOf((Double)currencyFormat.parse("£1.7"));
        Double t = (Double)percentageFormat.parse("12%");
        int q = numberFormat.parse("54,321").intValue();
        System.out.println(p+"  "+t+"  "+q);
    }

    public void formatAndParseDateAndTimeValues(){
        LocalDate date = LocalDate.of(2019,Month.APRIL,1);
        Locale locale = new Locale("en","GB");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("EEE dd MMM yyyy", locale);
        String result = date.format(format);
        System.out.println(result);

        date = LocalDate.parse("Tuesday 31 Mar 2020", format);
        locale = new Locale("ru");
        format = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).localizedBy(locale);
        result = date.format(format);
        System.out.println(result);
    }

    public void localizableResources(){
        Locale locale = new Locale("en","GB");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages",locale);
        String helloPattern = bundle.getString("hello");
        String otherMessage = bundle.getString("other");
        System.out.println(helloPattern);
        System.out.println(otherMessage);
    }

    public void formatMessagePatterns(){
        Locale locale = new Locale("en","GB");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages",locale);
        DateTimeFormatter date = DateTimeFormatter.ofPattern("EEE dd MMM yyyy", locale);
        NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
        NumberFormat number = NumberFormat.getNumberInstance(locale);

        // assume following values are already formatted
        String name = "Cookie";
        String price = currency.format(BigDecimal.valueOf(2.99));
        String quantity = number.format(4);
        String bestBefore = date.format(LocalDate.of(2019,Month.APRIL,1));

        String pattern = bundle.getString("product");
        String message = MessageFormat.format(pattern, name, price, quantity, bestBefore);
        System.out.println(message);
    }

    public void formattingAndLocalization(){
        String name = "Cookie";
        BigDecimal price = BigDecimal.valueOf(2.99);
        LocalDate bestBefore = LocalDate.of(2019,Month.APRIL,1);
        int quantity = 4;

        Locale locale = new Locale("en","GB");
        ResourceBundle bundle = ResourceBundle.getBundle("resources.messages",locale);

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy", locale);

        String fPrice = currencyFormat.format(price);
        String fQuantity = numberFormat.format(quantity);
        String fBestBefore = dateFormat.format(bestBefore); // or bestBefore.format(dateFormat);

        String pattern = bundle.getString("product");
        String message = MessageFormat.format(pattern, name, fPrice, fQuantity, fBestBefore);
        System.out.println(message);
    }
}
