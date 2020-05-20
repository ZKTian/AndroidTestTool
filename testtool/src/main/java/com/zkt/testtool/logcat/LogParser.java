package com.zkt.testtool.logcat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private Pattern mLevelPattern;

    public LogParser(Format format) {
        this.mLevelPattern = format.getPattern();
    }

    public Log parseLine(String line) {
        Log log = new Log(line);
        log.setLevel(getLevel(line));
        return log;
    }

    private Level getLevel(String line) {
        if (mLevelPattern == null) {
            return Level.U;
        }
        Matcher m = mLevelPattern.matcher(line.substring(19, line.length() - 1));
        if (m.find()) {
            return Level.valueOf(m.group(1));
        }
        //UNKNOWN LEVEL
        return Level.U;
    }

    public static String getTimeStamp(Log log) {
        String line = log.getMessage();
        return line.substring(0, 17);

    }
}
