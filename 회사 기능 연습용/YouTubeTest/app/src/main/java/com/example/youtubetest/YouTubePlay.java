package com.example.youtubetest;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubetest.Adapter.MyAdapter;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;

public class YouTubePlay extends YouTubeBaseActivity
        implements YouTubeView.YouTubeFormListner, View.OnTouchListener{

    /****************************************************************************************************
     ***************************************** 변수 선언단 **********************************************
     ***************************************************************************************************/

    private static final String TAG = YouTubePlay.class.getSimpleName();

    YouTubePlayerView youTubePlayerView;
    YouTubePlayer mYouTubePlayer;
    String videoId = "0-q1KafFCLU";

    private YouTubeView myouTubeView;
    private float mDensity;   //디스플레이 dip?

    private int mOffset;
    private int mOffsetGoal;  // => offsetGoal은 player의 속도에 맞게 ui가 따라가려고 하는 변수?
    private int mFlingVelocity;
    private int mWidth;    //가로 길이
    private int mMaxPos;
    private int mStartPos;   // YouTubeView에서 mSelectionStart 변수로 받는거.
    private int mEndPos;
    private int mPlayEndMsec2; //updatedispaly에서 endpos를 받아 밀리초로 바꾼값을 저장해둘 변수이다.
    private int mPlayEndMsec;     //mPlayEndMsec는 onplay될때 음악이 중지될 지점을 계산해서 저장해둔 변수이다.

    private int mPlayStartMsec; // 음악이 시작될 위치값을 (픽셀 밀리세컨드로 만든다.) player에 시작할 위치를 넘겨줄 변수

    /* 터치 관련 변수 */
    private float mTouchStart;
    private int mTouchInitialOffset;
    private long mYouTubeFormTouchStartMsec;

    private boolean mTouchDragging;
    private boolean mIsPlaying;

    /* 유튜브 관련 변수 */
    private long lastUpdateTime = 0;
    private int updateTime;
    private int lastYoutubeTime;
    private int lastYoutubeSeekTime;


    ConstraintLayout constraintLayout;

    String title;
    String channelTitle;



//    boolean youTubePlayerFlag = true;
    int deCocount =0;




    /****************************************************************************************************
     ******************************************** 생명주기 **********************************************
     ***************************************************************************************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        title = "임시표시";
        channelTitle = "임시 표시";

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(mYouTubePlayer != null) {
            mYouTubePlayer.cueVideo(videoId, lastYoutubeSeekTime);
        } else if (mYouTubePlayer == null) {
            init();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();

        if(mYouTubePlayer != null) {
            lastYoutubeSeekTime = mYouTubePlayer.getCurrentTimeMillis();
        }

        handlePause();
    }
    @Override
    protected void onDestroy() {

        if(mYouTubePlayer != null) {
            mYouTubePlayer.release();
        }

        System.gc();

        super.onDestroy();


    }




    /***************************************************************************************************
     ****************************************** 내부 클래스 ********************************************
     **************************************************************************************************/






    /****************************************************************************************************
     **************************************** 사용자 정의 함수 ******************************************
     ***************************************************************************************************/

    /**
     * @DESC 유튜브 초기화 하는 부분 ( 유튜브 동영상 로드 하는 부분 )
     */
    private void init(){

        constraintLayout = findViewById(R.id.youTubeLayout);

        youTubePlayerView = findViewById(R.id.youtubeView);

        youTubePlayerView.initialize("develop", new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.v(TAG,"유튜브 초기화 성공");
                if(!b){
                    youTubePlayer.cueVideo(videoId);
                }

                youTubePlayer.setShowFullscreenButton(false);
                mYouTubePlayer = youTubePlayer;

                youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT); // 모든 기능 다있음
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {


                    }

                    @Override
                    public void onLoaded(String s) {

                    }

                    @Override
                    public void onAdStarted() {
                        mIsPlaying = false;
                    }

                    @Override
                    public void onVideoStarted() {


                    }

                    @Override
                    public void onVideoEnded() {

                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });

                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {
                        updateTime =0;
                        lastUpdateTime =0;
                        onPlay(youTubePlayer.getCurrentTimeMillis());

                    }

                    @Override
                    public void onPaused() {
                        handlePause();
                    }

                    @Override
                    public void onStopped() {
                        handlePause();
                    }

                    @Override
                    public void onBuffering(boolean b) {
                        handlePause();
                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

//        TextView tv_title = findViewById(R.id.title);
//        tv_title.setText(title);
//
//        TextView tv_channelTitle = findViewById(R.id.channelTitle);
//        tv_channelTitle.setText(channelTitle);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        mDensity = metrics.density;

        myouTubeView = findViewById(R.id.youtubeform);
//        myouTubeView.setListner(this);
//        myouTubeView.setContext(YouTubePlay.this);

        mMaxPos = 0;

        updateDisplay();
        lastUpdateTime = 0;
        updateTime = 0;

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {

        int id = v.getId();
        float att = event.getRawX();
        int action = event.getAction();

        if(action == MotionEvent.ACTION_DOWN){

        } else if (action == MotionEvent.ACTION_MOVE) {

        } else if (action == MotionEvent.ACTION_UP) {

        }
        return false;
    }

    @Override
    public void youTubeformTouchStart(float x) {
        mTouchDragging = true;
        mTouchStart = x;
        mTouchInitialOffset = mOffset;
        mFlingVelocity = 0;
        mYouTubeFormTouchStartMsec = getCurrentTime();
    }

    @Override
    public void youTubeformTouchMove(float x) {

        updateDisplay();

    }

    @Override
    public void youTubeformTouchEnd() {

        mTouchDragging = false;
        mOffsetGoal = mOffset;

        long elapsedMsec = getCurrentTime() - mYouTubeFormTouchStartMsec;

        if(elapsedMsec < 300) {
            if(mIsPlaying) {
                int seekMsec = myouTubeView.pixelsToMillisecs(
                        (int) (mTouchStart + mOffset));

                if(seekMsec >= 0 && seekMsec < mPlayEndMsec) {
                    
                } else {
                    likeOnPlay((int) (mTouchStart + mOffset));
                }
            }
        }

    }

    private long getCurrentTime(){
        return System.nanoTime() / 1000000;
    }

    private void updateDisplay() {
        try {
            if(mYouTubePlayer != null && !YouTubePlay.this.isFinishing()) {
                int currentYoutubeTime = mYouTubePlayer.getCurrentTimeMillis();
                int now;

                // 현재 유튜브 시간과 지난 유튜브에서 준 시간이 다르면, 현재 유튜브의 시간이 now 값이 되면 된다.
                if(lastYoutubeTime != currentYoutubeTime) {
                    updateTime = 0;
                    now = currentYoutubeTime;
                    lastYoutubeTime = now;
                } else {  // 현재 유튜브의 시간과 지난 유튜브의 시간이 같다면, 지난 유튜브 업데이트 시간에 + 1초이내에 이 메소드를 도는데 걸렸던 총 시간(updateTime)을 더하면 된다.
                    if( lastUpdateTime != 0) {
                        updateTime += (int) ((SystemClock.elapsedRealtimeNanos() - lastUpdateTime) / 1000000);
                    }
                    now = lastYoutubeTime + updateTime;
                    lastUpdateTime = SystemClock.elapsedRealtimeNanos();
                }
                int frames = myouTubeView.millisecsToPixels(now);

                mPlayEndMsec2 = myouTubeView.pixelsToMillisecs(mEndPos-2);

                myouTubeView.setPlayback(frames);
                setOffsetGoalNoUpdate(frames - mWidth /2);

                if(now > mPlayEndMsec - 100) {
                    onPlay(mStartPos);

                    handlePause();
                }

                if( now >= mPlayEndMsec2) {

                }
            }
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!mTouchDragging) {
            int offsetDelta;

            if(mFlingVelocity != 0) {
                offsetDelta = mFlingVelocity / 30;
                if( mFlingVelocity > 80) {
                    mFlingVelocity = 80;
                } else if(mFlingVelocity < -80) {
                    mFlingVelocity += 80;
                } else {
                    mFlingVelocity = 0;
                }

                mOffset += offsetDelta;

                if(mOffset + mWidth / 2 > mMaxPos) {
                    mOffset = mMaxPos - mWidth / 2;
                    mFlingVelocity = 0;
                }
                if (mOffset < 0) {
                    mOffset = 0;
                    mFlingVelocity = 0;
                }
                mOffsetGoal = mOffset;

            } else {
                mOffset = mOffsetGoal;
            }
        }

//        myouTubeView.setParameters(mStartPos, mEndPos, mOffset);
//        myouTubeView.invalidate();
    }

    private void setOffsetGoalNoUpdate(int offset) {
        if(mTouchDragging) {
            return;
        }

        mOffsetGoal = offset;

        //끝부분 다 왔을때
        if(mOffsetGoal + mWidth / 2 > mMaxPos) {
            mOffsetGoal = mMaxPos - mWidth / 2;
        }
        // 처음 시작할 때
        if(mOffsetGoal < 0) {
            mOffsetGoal = 0;
        }

    }

    /**
     * @DESC : 파라미터 startPosition에서 노래를 재생하게 하려는 메소드
     */
    private synchronized void onPlay(int startPosition) {
        if(mIsPlaying) {
            return;
        }

        if(mYouTubePlayer == null) {
            return;
        }

        try {
            mPlayStartMsec = myouTubeView.pixelsToMillisecs(startPosition);

            //시작하려고 하는 위치가 mStartpos보다 작으면 mStartPos위치에서 종료된다.
            if(startPosition < mStartPos) {
                mPlayEndMsec = myouTubeView.pixelsToMillisecs(mStartPos);
            } else if(startPosition > mEndPos) { // 시작하려는 위치가 mEndPos보다 크면 mMaxPos 위치에서 종료된다.
                mPlayEndMsec = myouTubeView.pixelsToMillisecs(mMaxPos);
            } else { // 아니면 mEndPos 위치에서 종료됨
                mPlayEndMsec = myouTubeView.pixelsToMillisecs(mEndPos);
            }

            mIsPlaying = true;

            updateDisplay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * @DESC : 재생중이면 중지시키고, 노래 위치만 옮기가 플레이는 안하는 메서드
     */
    private void likeOnPlay(int startPosition) {
        if(mIsPlaying) {
            handlePause();
            return;
        }

        if(mYouTubePlayer == null) {
            return;
        }

        try {
            mPlayStartMsec = myouTubeView.pixelsToMillisecs(startPosition);

            //시작하려고 하는 위치가 mStartPot보다 작으면 mStartPos 위치에서 종료된다.
            if(startPosition < mStartPos) {
                mPlayStartMsec = myouTubeView.pixelsToMillisecs(mStartPos);
            } else if (startPosition > mEndPos) { // 시작하는 위치가 mEndPos보다 크면 mMaxPos 위치에서 종료된다.
                mPlayEndMsec = myouTubeView.pixelsToMillisecs(mMaxPos);
            } else {
                mPlayEndMsec = myouTubeView.pixelsToMillisecs(mEndPos);
            }

            updateDisplayFoece();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePause(){
        mIsPlaying = false;
        lastUpdateTime = 0;
        updateTime = 0;
    }

    private void updateDisplayFoece(){

        if(mYouTubePlayer != null) {

            int now = mYouTubePlayer.getCurrentTimeMillis();

            int temp = myouTubeView.pixelsToMillisecs(myouTubeView.getMeasuredWidth() / 2);
            int frames = myouTubeView.millisecsToPixels(now);
            myouTubeView.setPlayback(frames);

            setOffsetGoalNoUpdate(frames - mWidth / 2);

            if(now >= mPlayEndMsec) {
                handlePause();
            }

            if(!mTouchDragging) {
                int offsetDelta;

                if(mFlingVelocity != 0) {
                    offsetDelta = mFlingVelocity / 30;

                    if(mFlingVelocity > 80) {
                        mFlingVelocity -=80;
                    } else if(mFlingVelocity < -80) {
                        mFlingVelocity +=80;
                    } else {
                        mFlingVelocity = 0;
                    }

                    mOffset += offsetDelta;

                    if(mOffset + mWidth / 2 > mMaxPos) {
                        mOffset = mMaxPos - mWidth / 2;
                        mFlingVelocity = 0;
                    }

                    if(mOffset < 0) {
                        mOffset = 0;
                        mFlingVelocity = 0;
                    }
                    mOffsetGoal = mOffset;
                } else {
                    mOffset = mOffsetGoal;
                }
            }

            myouTubeView.setParameters(mStartPos, mEndPos, mOffset);
            myouTubeView.invalidate();
        }
    }


}
