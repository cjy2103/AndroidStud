package com.example.youtubeiframe.util;

import java.util.Locale;

public class FormatUtil {
    public static String convertSecondsToMinutesAndSeconds(float duration){
        int seconds = Math.round(duration); // 소수점을 반올림하여 정수로 변환
        String timeCodeStr;
        String timeCodeMinutes = String.valueOf(seconds / 60);
        String timeCodeSeconds = String.format(Locale.getDefault(),"%02d", seconds % 60);

        // 1분이 넘으면 분단위를 붙이고, 1분이 안되면 초단위로
        if (seconds / 60 >= 1) {
            timeCodeStr = timeCodeMinutes + ":" + timeCodeSeconds;
        } else {
            timeCodeStr = "0:" + timeCodeSeconds;
        }

        return timeCodeStr;
    }
}
