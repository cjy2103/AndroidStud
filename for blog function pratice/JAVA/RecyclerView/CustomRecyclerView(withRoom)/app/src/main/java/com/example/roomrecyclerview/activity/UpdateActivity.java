package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.activity.dialog.ImageSelectDialog;
import com.example.roomrecyclerview.databinding.ActivityUpdateBinding;
import com.example.roomrecyclerview.model.ListItemModel;
import com.example.roomrecyclerview.model.MyListItem;
import com.example.roomrecyclerview.room.Data;
import com.example.roomrecyclerview.room.RoomDB;
import com.example.roomrecyclerview.util.SystemUtil;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UpdateActivity extends AppCompatActivity {

    private ActivityUpdateBinding binding;
    private SystemUtil systemUtil;

    private MyListItem myListItems;

    private String imagePath;
    private String title;
    private String describe;
    private String youtubeLink;
    private String imageCase;

    public static Context context;

    private RoomDB roomDB;
    private Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        dataLoad();

        uiSetting();

        clickCancel();

        clickImageInsert();

        clickUpdate();
    }

    private void viewBinding(){
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        context = this;

        systemUtil = new SystemUtil();

        systemUtil.statusbarSetting(getWindow(),this, binding.consUpdate);
        systemUtil.sofNavigationBarHide(getWindow());

        roomDB = RoomDB.getInstance(this);
        data = new Data();
    }

    /**
     * @DESC: 데이터 로드
     */
    private void dataLoad(){

        Bundle bundle = getIntent().getExtras();
        myListItems = (MyListItem) bundle.getSerializable("itemObject");
        imagePath   = myListItems.getList().get(0).getUri();
        title       = myListItems.getList().get(0).getTitle();
        describe    = myListItems.getList().get(0).getDescribe();
        youtubeLink = myListItems.getList().get(0).getChannelLink();
        imageCase   = myListItems.getList().get(0).getImageCase();

    }

    private void uiSetting(){
        Uri image;
        if(imageCase.equals("Local")) {
            image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imagePath);
        } else {
            image = Uri.parse(imagePath);
        }
        Glide.with(this).load(image).into(binding.ivImage);
        binding.edtTitle.setText(title);
        binding.edtDescribe.setText(describe);
        binding.edtYoutubeLink.setText(youtubeLink);

    }

    private void clickCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }

    private void clickImageInsert(){
        binding.btnInsert.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();

            DialogFragment dialogFragment = new ImageSelectDialog();
            Bundle bundle = new Bundle();
            bundle.putString("RefreshImage","Update");
            dialogFragment.setArguments(bundle);
            dialogFragment.show(fragmentManager,"Dialog");
        });
    }

    public void albumSelectCallback(boolean localImage){
        SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
        imagePath = sharedPreferences.getString("path","");
        if(!imagePath.isEmpty()){
            Uri image;
            if(localImage){
                image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imagePath);
                imageCase = "Local";
            } else {
                image = Uri.parse(imagePath);
                imageCase = "Gallery";
            }
            Glide.with(this).load(image).into(binding.ivImage);
        }
    }

    @SuppressLint("CheckResult")
    private void clickUpdate(){
        binding.btnSave.setOnClickListener(v->{
            data.setTitle(binding.edtTitle.getText().toString());
            data.setDescribe(binding.edtDescribe.getText().toString());
            data.setYoutubeLink(binding.edtYoutubeLink.getText().toString());
            data.setImageKey(imagePath);
            data.setImageCase(imageCase);



            roomDB.dataDao().update(data).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(()->{
                        Toast.makeText(this, "데이터 변경이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                        ((MainActivity)MainActivity.context).mainRecyclerRefresh();
                        refreshData();
                    });
        });
    }

    private void refreshData(){
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setTitle(binding.edtTitle.getText().toString());
        listItemModel.setDescribe(binding.edtDescribe.getText().toString());
        listItemModel.setChannelLink(binding.edtYoutubeLink.getText().toString());
        listItemModel.setUri(imagePath);
        listItemModel.setImageCase(imageCase);

        MyListItem myListItem = new MyListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        myListItem.setList(items);

        ((RecyclerItemDetailActivity)RecyclerItemDetailActivity.context).refreshActivity(myListItem);
    }
}