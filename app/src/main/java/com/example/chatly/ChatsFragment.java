package com.example.chatly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
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
        // لود کردن layout مربوط به fragment
        return inflater.inflate(R.layout.fragment_chats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // پیدا کردن RecyclerView از layout
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // داده‌های نمونه
        List<ChatItem> chatList = new ArrayList<>();
        chatList.add(new ChatItem(1, "Saved Messages", "", "11:05 AM", true, null));
        chatList.add(new ChatItem(2, "علی", "سلام، چطوری؟", "10:30 AM", false, null));
        chatList.add(new ChatItem(3, "گروه دوستان", "عکس جدید", "9:45 AM", false, null));

        // ✅ ساخت آداپتر با 3 پارامتر (Context, List, Listener)
        ChatAdapter adapter = new ChatAdapter(
                getContext(),
                chatList,
                chat -> {
                    if (chat.isSavedMessages()) {
                        ((FirstPage) requireActivity()).loadSavedMessages();
                    } else {
                        Toast.makeText(requireContext(), "چت با " + chat.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
        );

        recyclerView.setAdapter(adapter);
    }
}
