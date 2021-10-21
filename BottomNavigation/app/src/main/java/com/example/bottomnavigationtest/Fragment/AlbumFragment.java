package com.example.bottomnavigationtest.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bottomnavigationtest.R;
import com.example.bottomnavigationtest.util.MusicAdapter;
import com.example.bottomnavigationtest.util.MusicModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlbumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<MusicModel> arrayList;
    private MusicAdapter adapter;
    private RecyclerView recyclerView;

    public AlbumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlbumFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlbumFragment newInstance(String param1, String param2) {
        AlbumFragment fragment = new AlbumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_album, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initBingding(view);

        adapter = new MusicAdapter(requireContext(),arrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        arrayList = new ArrayList<>();

        new GetAudioAsyncTask(requireContext()).execute((Void) null);


    }

    private void initBingding(View view){
        recyclerView = view.findViewById(R.id.music_recycler_view);
    }


    public ArrayList<MusicModel> getallAudio(){
        //외부영역에서 가져오는 노래리스트들의 앨범id의 값들이 겹치지 않도록 하려고 사용하는 리스트.
        ArrayList<MusicModel> tempAlbumList = new ArrayList<>();

        // 외부저장소에서 노래 가져오기
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        final String[] musicCursor = {MediaStore.Audio.Media._ID,MediaStore.Audio.Media.ALBUM,MediaStore.Audio.Media.TITLE
                , MediaStore.Audio.Media.DATA, MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.DURATION};

        String selection = MediaStore.Audio.Media.IS_MUSIC + "=1";
        ContentResolver contentResolver = requireActivity().getContentResolver();
        @SuppressLint("Recycle") Cursor cursor = contentResolver.query(uri, musicCursor, selection, null, null);

        while (cursor.moveToNext()){
            String artist    = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
            String album     = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            String title     = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
            String songUri   = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
            Long albumid     = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
            int duration     = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
            Uri imagepath    = Uri.parse("content://media/external/audio/albumart");
            Uri imagepathuri = ContentUris.withAppendedId(imagepath,albumid);
            MusicModel songItem = new MusicModel(title,album,artist,String.valueOf(duration),uri.toString(),String.valueOf(albumid),imagepathuri.toString());
            tempAlbumList.add(songItem);
            //            arrayList.add(songItem);
        }
        return tempAlbumList;
    }


    public class GetAudioAsyncTask extends AsyncTask<Void,Void,Boolean>{
        private Context context;
        private ProgressDialog progressDialog;

        public GetAudioAsyncTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(requireContext());
            progressDialog.setTitle("Progressing.");
            progressDialog.setMessage("Scanning...");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                arrayList = getallAudio();
                return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            adapter = new MusicAdapter(requireContext(),arrayList);
            recyclerView.setAdapter(adapter);

            super.onPostExecute(aBoolean);
        }
    }




}

