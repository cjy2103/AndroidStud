package com.example.youtubetest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

public class YouTubeView extends View {

    private int mSampleRate;
    private int mSamplesPerFrame;
    private int mOffset;
    private int mSelectionStart;
    private int mSelectionEnd;
    private int mPlaybackPos;
    private float mDensity;
    private float mInitialScaleSpan;

    private YouTubeFormListner mListner;
    private GestureDetector mGestureDetector;
    private ScaleGestureDetector mSacleGestureDetector;

    private boolean mInitialized;

    private Paint mTimeCodePaint;
    private Paint mPlaybackLinePaint;

    Resources res = getResources();

    private int tempZommLevel = 3;
    private int[] mHeightsAtThisZoomLevel;

    int ticket = 0;
    private int timeStrsavevalue=0;
    int lastInt_new10ms = -1;

    private int mZoomLevel;

    Context context;


    public interface YouTubeFormListner {
        public void youTubeformTouchStart(float x);
        public void youTubeformTouchMove(float x);
        public void youTubeformTouchEnd();
    }

    public YouTubeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPlaybackLinePaint = new Paint();
        mPlaybackLinePaint.setAntiAlias(true);
        mPlaybackLinePaint.setStrokeWidth(1.0f);
        mPlaybackLinePaint.setColor(res.getColor(R.color.playback_indicator));

        mTimeCodePaint = new Paint();
        mTimeCodePaint.setTextSize(12);
        mTimeCodePaint.setAntiAlias(true);
        mTimeCodePaint.setColor(res.getColor(R.color.chord_color));
        mTimeCodePaint.setShadowLayer(2,1,1,res.getColor(R.color.timecode_shadow));

        mSacleGestureDetector = new ScaleGestureDetector(
                context,
                new ScaleGestureDetector.OnScaleGestureListener() {

                    @Override
                    public boolean onScaleBegin(ScaleGestureDetector detector) {
                        mInitialScaleSpan = Math.abs(detector.getCurrentSpanX());
                        return true;
                    }

                    @Override
                    public boolean onScale(ScaleGestureDetector detector) {
                        float scale = Math.abs(detector.getCurrentSpanX());
                        if(scale - mInitialScaleSpan > 40) {
                            /**
                             * @TODO : 줌인 기능
                             */

                            mInitialScaleSpan = scale;
                        }
                        if(scale - mInitialScaleSpan < -40) {
                            /**
                             * @TODO : 줌아웃 기능
                             */
                            mInitialScaleSpan = scale;
                        }
                        return true;
                    }
                    @Override
                    public void onScaleEnd(ScaleGestureDetector detector) {

                    }
                }
        );

        mOffset = 0;
        mPlaybackPos = 0;
        mSelectionStart = 0;
        mSelectionEnd = 0;
        mDensity = 1.0f;
        mInitialized = false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(mInitialized) {
            /**
             * @TODO : 줌인 중아웃
             */

            if(mGestureDetector.onTouchEvent(event)) {
                return true;
            }

            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN :
                    if(mListner != null) {
                        mListner.youTubeformTouchStart(event.getX());
                    }
                    break;
                case MotionEvent.ACTION_MOVE :
                    if(mListner != null) {
                        mListner.youTubeformTouchMove(event.getX());
                    }
                    break;
                case MotionEvent.ACTION_UP :
                    if(mListner != null) {
                        mListner.youTubeformTouchEnd();
                    }
                    break;
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean ismInitialized () { return mInitialized; }

    /**
     * @DESC : 초 -> 픽셀
     */
    public int secondsToFrames(double seconds) {
        double z = tempZommLevel; // TODO : 추후에 줌레벨에 맞게 수정
        return (int)(z * seconds * mSampleRate / mSampleRate + 0.5);
    }

    /**
     * @DESC : 픽셀 -> 초
     */
    public double pixelsToSeconds(int pixels) {
        double z = tempZommLevel;
        return (pixels * (double)mSamplesPerFrame / (mSampleRate * z));
    }

    public int millisecsToPixels(int msecs) {
        double z = tempZommLevel;
        return (int)((msecs * 1.0 * mSampleRate * z) / (1000.0 * mSamplesPerFrame) + 0.5);
    }

    public int pixelsToMillisecs(int pixels) {
        double z = tempZommLevel;
        return (int)(pixels * (1000.0 * mSamplesPerFrame) / (mSampleRate * z) + 0.5);
    }

    public void setParameters(int start, int end, int offset) {
        mSelectionStart = start;
        mSelectionEnd = end;
        mOffset = offset;
    }

    public  int getTicket(){return ticket;}

    public int getStart() {
        return mSelectionStart;
    }

    public int getEnd() {
        return mSelectionEnd;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setPlayback(int pos) {
        mPlaybackPos = pos;
    }

    public int getPlaybackPos()
    {
        return mPlaybackPos;
    }

    public void setListner(YouTubeFormListner listner) { mListner = listner; }


    /**
     * @DESC : TODO 이식후 기타탭
     */
    public void recomputeHeights(float density){
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int measureWidth = getMeasuredWidth();
        int measureHeight = getMeasuredHeight();

        int start = mOffset;
        int width = 3- start;
        //int width = mHeightsAtThisZoomLevel.length - start;

        double onePixelInSecs = pixelsToSeconds(1);
        double fractionalSecs = mOffset * onePixelInSecs;
        int integerSecs = (int) fractionalSecs;
        int int_new10ms = 0;

        int i =0;

        //Draw TimeCode
        double timecodeIntervalSecs = 1.0;
        if( timecodeIntervalSecs / onePixelInSecs < 50) {
            timecodeIntervalSecs = 5.0;
        }
        if ( timecodeIntervalSecs / onePixelInSecs < 50) {
            timecodeIntervalSecs = 15.0;
        }

        fractionalSecs = mOffset * onePixelInSecs;

        int integerTimecode = (int) (fractionalSecs / timecodeIntervalSecs);

        i = 0;

        while ( i < width) {
            Paint paint;
            fractionalSecs += onePixelInSecs;

            int_new10ms = (int)(fractionalSecs * 100);

            if ( i + start == mPlaybackPos) {
                if (mPlaybackPos < measureWidth / 2) {
                    canvas.drawLine(i, 0, i, measureHeight, mPlaybackLinePaint);
                }
                int ms = int_new10ms * 10;
                int sec = (ms / 1000);
                int min = (ms / (60000)) % 60;
                int mili = (ms % 1000);

                String timeStr = sec + "." + mili;
                float time_offset = (float) (0.5 * mTimeCodePaint.measureText(timeStr));
                timeStrsavevalue = min + sec + mili;
            } else {
                // 인디 케이터 중앙 이상
                if(mPlaybackPos >= measureWidth/2){
                    canvas.drawLine(measureWidth/2, 0, measureWidth/2, measureHeight, mPlaybackLinePaint);
                }
            }

            integerSecs = (int) fractionalSecs;

            int integerTimecodeNew = (int) (fractionalSecs /
                    timecodeIntervalSecs);

            if(integerTimecodeNew != integerTimecode) {
                integerTimecode = integerTimecodeNew;

                String timecodeMinutes = "" + (integerSecs / 60);
                String timecodeSeconds = "" + (integerSecs % 60);

                if((integerSecs % 60) < 10) {
                    timecodeSeconds = "0" + timecodeSeconds;
                }
                String timecodeStr = "";

                if(integerSecs / 60 >=1) {
                    timecodeStr = timecodeMinutes + ":" + timecodeSeconds;
                } else {
                    timecodeStr = timecodeSeconds;
                }
                float offset = (float) (
                        0.5 * mTimeCodePaint.measureText(timecodeStr));
                    canvas.drawText(timecodeStr,
                                    i - offset,
                            (int)(12 * mDensity),
                            mTimeCodePaint);
            }

            if(lastInt_new10ms != int_new10ms) {
                boolean overinterval_10ms = false;

                if( (int_new10ms > 1 + lastInt_new10ms)) overinterval_10ms = true;

                lastInt_new10ms = int_new10ms;

            }
        }
    }

    public void setContext(Context context){
        this.context = context;
    }
    /**
     * Called the first time we need to draw when the zoom level has changed
     * or the screen is resized
     */
//    private void computeIntsForThisZoomLevel() {
//        //int halfHeight = (getMeasuredHeight() / 2) - 1;
//        int halfHeight = (getMeasuredHeight() / 4) - 1;
//        mHeightsAtThisZoomLevel = new int[mLenByZoomLevel[mZoomLevel]];
//        for (int i = 0; i < mLenByZoomLevel[mZoomLevel]; i++) {
//            mHeightsAtThisZoomLevel[i] =
//                    (int)(mValuesByZoomLevel[mZoomLevel][i] * halfHeight);
//
//            //Log.v("mHeightsAtThisZoomLe?","mHeightsAtThisZoomLevel[i]" + mHeightsAtThisZoomLevel[i] );
//        }
//    }

}
