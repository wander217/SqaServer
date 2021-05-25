package com.wander.sqa.exception;

import java.time.LocalDateTime;

public class OutOfRegistrationTimeException extends Exception{
    public OutOfRegistrationTimeException(LocalDateTime start, LocalDateTime end) {
        super("Thời gian đăng kí tín bắt đầu từ "+start.getHour()
                +":"+start.getMinute()+":"+start.getSecond()
                +" "+start.getDayOfMonth()+"/"+start.getMonthValue()+"/"+start.getYear()
                +" đến "+end.getHour()+":"+end.getMinute()+":"+end.getSecond()
                +" "+end.getDayOfMonth()+"/"+end.getMonthValue()+"/"+end.getYear());
    }
}
