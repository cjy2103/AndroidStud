package com.jytest555.inapptest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.ConsumeParams;
import com.android.billingclient.api.ConsumeResponseListener;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    /*************************************************************************************
     ********************************** 변수 선언 부 ************************************
     ************************************************************************************/

    Button button,button2;

    private List<String> skuList = new ArrayList<>();
    BillingClient billingClient;

    private SkuDetails skudetails;

    private static final String TAG = "MainActivity";


    /*************************************************************************************************************
     ************************************************ OnCreate ***************************************************
     *************************************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button      = (Button) findViewById(R.id.button);

        billingClient = BillingClient.newBuilder(MainActivity.this)
                .setListener(purchasesUpdatedListener)
                .enablePendingPurchases()
                .build();

        getStoreBillingConnection();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(billingClient.isReady()){
                    purchase();
                }
            }
        });

    }

    /******************************************************************************************************
     ****************************************** 함수 선언 단 **********************************************
     *****************************************************************************************************/

    /**
     * @DESC : 소모성 아이템이 아닐경우 쓰는 생성자 -> 자동 Consume이 아니기 때문에 직접 콘솔로가서 환불처리를 해야함
     *
     */
    AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener = new AcknowledgePurchaseResponseListener() {
        @Override
        public void onAcknowledgePurchaseResponse(@NonNull BillingResult billingResult) {
            Log.v(TAG,"onAcknowledgePurchaseResponse 들어옴");
        }
    };

    /**
     * @DESC : 결제 처리를 하는 메소드
     *
     */
    private PurchasesUpdatedListener purchasesUpdatedListener = new PurchasesUpdatedListener() {
        // 결제 처리를 하는 메소드
        @Override
        public void onPurchasesUpdated(@NonNull BillingResult billingResult, @Nullable List<Purchase> purchases) {

            Log.v(TAG,"billingResult.getResponseCode() : "+billingResult.getResponseCode());

            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK
                    && purchases != null) {
                for (Purchase purchase : purchases) {
                    handlePurchase(purchase);  // 이부분이 주석처리되면 자동으로 3분경과시 환불처리됨
                }
            } else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
                // Handle an error caused by a user cancelling the purchase flow.
            } else {
                // Handle any other error codes.
            }
        }
    };

    /**
     * @DESC : 소비성 아이템일 때 사용 -> 결제가 계속 가능 ( 테스트를 위해서 사용 )
     *
     */
    private void handlePurchase (Purchase purchase){
        ConsumeParams consumeParams =
                ConsumeParams.newBuilder()
                        .setPurchaseToken(purchase.getPurchaseToken())
                        .build();

        ConsumeResponseListener listener = new ConsumeResponseListener() {
            @Override
            public void onConsumeResponse(BillingResult billingResult, String purchaseToken) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    // Handle the success of the consume operation.
                }
            }
        };
        billingClient.consumeAsync(consumeParams, listener);

    }

    /**
     * @DESC : 지속성 아이템일경우 사용 -> 한번결제하면 환불할때 까지 결제  X  ( 환불할때 콘솔로가서 자격삭제 누르고 환불 )
     *
     */
//    void handlePurchase(Purchase purchase) {
//        if (purchase.getPurchaseState() == Purchase.PurchaseState.PURCHASED) {
//            if (!purchase.isAcknowledged()) {
//                AcknowledgePurchaseParams acknowledgePurchaseParams =
//                        AcknowledgePurchaseParams.newBuilder()
//                                .setPurchaseToken(purchase.getPurchaseToken())
//                                .build();
//                billingClient.acknowledgePurchase(acknowledgePurchaseParams, acknowledgePurchaseResponseListener);
//            }
//        }
//    }


    /**
     * @DESC : 개발자 콘솔에 등록한 아이템을 연결함
     *
     */
    private void getStoreBillingConnection(){
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if(billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK){
                    // The BillingClient is ready. You can query purchases here

                    skuList.add("premium");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(),
                            new SkuDetailsResponseListener() {
                                @Override
                                public void onSkuDetailsResponse(BillingResult billingResult,
                                                                 List<SkuDetails> skuDetailsList) {
                                    // Process the result.
                                    for(SkuDetails skuDetails : skuDetailsList) {
                                        if(skuDetails.getSku().equals("premium")){
                                            skudetails = skuDetails;
                                            Log.v(TAG, "sku : "+skuDetails.getSku());
                                        }
                                    }
                                }
                            });
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                //Try to restart the connection on the next request to
                // Google Play by calling t he startConnection() method;
            }

        });
    }

    /**
     * @DESC : 구매흐름 ->  결제창 뜸
     *
     */
    public void purchase() {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(skudetails)
                .build();
        int responseCode = billingClient.launchBillingFlow(MainActivity.this, billingFlowParams).getResponseCode();
        Log.v(TAG, "responseCode: "+responseCode);

    }


}