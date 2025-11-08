package com.example.chatly; // پکیج خودت رو جایگزین کن

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // این layout رو لود می‌کنه
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView رو پیدا کن
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // داده‌های نمونه
        List<ChatItem> chatList = new ArrayList<>();
        chatList.add(new ChatItem(1, "Saved Messages", "11:05 AM", true));
        chatList.add(new ChatItem(2, "علی", "سلام، چطوری؟", "10:30 AM", false, null));
        chatList.add(new ChatItem(3, "گروه دوستان", "عکس جدید", "9:45 AM", false, null));

        // آداپتور
        ChatAdapter adapter = new ChatAdapter(chatList);
        recyclerView.setAdapter(adapter);
    }
}