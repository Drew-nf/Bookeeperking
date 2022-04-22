package com.example.bookkeepingking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ChatFragment extends Fragment {

    EditText mGetMessage;
    ImageButton msendMessageButton;

    CardView mSendMessageCardView;
    ImageView mImageViewOfUser;
    TextView mNameOfUser;

    private String enteredMessage;
    Intent intent;
    String mRecieverName, senderName, mRecieverUserId, mSenderUserId;
    String senderRoom, recieverRoom;

    RecyclerView mMessagerecyclerView;

    String currentTime;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ){
        View fragmentLayout = inflater.inflate(R.layout.fragment_chat, container, false);
        return fragmentLayout;
    }
    public void onViewCreated (@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mGetMessage = view.findViewById(R.id.getMessage);
        mSendMessageCardView = view.findViewById(R.id.cardviewOfSendMessage);
        msendMessageButton = view.findViewById(R.id.imageViewSendMessage);
        //intent = getIntent();

        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm a");
    }
}