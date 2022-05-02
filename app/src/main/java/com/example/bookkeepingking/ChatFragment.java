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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import LocalDatabase.Chat;
import LocalDatabase.DataBaseHelper;
import LocalDatabase.Invoice;
import LocalDatabase.TempVal;


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
    RecyclerView recyclerView;
    public boolean id;
    ArrayList<Chat> list;



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
        list = new ArrayList<>();

        //used to get whether or not they are the accountant
        DataBaseHelper databasehelper = new DataBaseHelper(getContext());
        //putting messages in list to later display
        list=databasehelper.getAllMessages();
        TempVal tempVal=databasehelper.getTempVal();

        //will get current username and check if they are an accountant or not
        String name=tempVal.getUsername();
        id = databasehelper.isacc(name);

        //date
        calendar = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("h:mm a");
        String date=simpleDateFormat.format(calendar.getTime()).toString();


        //if not accountant will add intro message
        if(id==false) {
//
               list.add(0,new Chat(1, 1, true, "Hello how can I assist you today?", date));

          //  list.add(new Chat(2, 1, false, "I need to tell you something....", date));
        }
        //creating adapter with the message list
        CustomAdapter adapter = new CustomAdapter(getActivity(), list);

        //initializing components
        recyclerView = view.findViewById(R.id.recyclerViewOfChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        mGetMessage = view.findViewById(R.id.getMessage);
        mSendMessageCardView = view.findViewById(R.id.cardviewOfSendMessage);
        msendMessageButton = view.findViewById(R.id.imageViewSendMessage);
        recyclerView.scrollToPosition(list.size() - 1);
        //intent = getIntent();


        //if the send button is pushed
        view.findViewById(R.id.imageViewSendMessage).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //if message is not blank but, wont work
                if(mGetMessage.getText().toString()!="") {
                    Chat chat;
                    if(id==false) {

                            chat = new Chat(-1, 1, false, mGetMessage.getText().toString(), date);
                            list.add(chat);

                        DataBaseHelper databasehelp = new DataBaseHelper(getContext());
                        boolean success = databasehelp.addChat(chat);
                    }
                    else if(id==true){

                        try {
                            chat = new Chat(-1, 1, true, mGetMessage.getText().toString(), date);
                            list.add(chat);
                        } catch (Exception e) {
                            chat = new Chat(-1, 1, true, "error", "error");
                        }
                        DataBaseHelper databasehelp = new DataBaseHelper(getContext());
                        boolean success = databasehelp.addChat(chat);
                    }
                    ;
                    //wiping textfield and showing sent message
                    mGetMessage.setText("");
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                    recyclerView.scrollToPosition(list.size() -1);
                }
                else
                    return;
            }
        });
    }
}