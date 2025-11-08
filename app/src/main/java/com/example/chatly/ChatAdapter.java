package com.example.chatly;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.chatly.OnChatClickListener;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final Context context;
    private final List<ChatItem> chatList;
    private final OnChatClickListener listener;

    // سازنده با listener
    public ChatAdapter(Context context, List<ChatItem> chatList, OnChatClickListener listener) {
        this.context = context;
        this.chatList = chatList;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return chatList.get(position).isSavedMessages() ? 0 : 1;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem item = chatList.get(position);
        holder.bind(item); // فقط از bind استفاده کن

        // کلیک فقط اینجا
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChatClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    // ViewHolder
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAvatar, imageSavedIcon;
        TextView textTitle, textLastMessage, textTime;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            imageSavedIcon = itemView.findViewById(R.id.imageSavedIcon);
            textTitle = itemView.findViewById(R.id.textTitle);
            textLastMessage = itemView.findViewById(R.id.textLastMessage);
            textTime = itemView.findViewById(R.id.textTime);
        }

        public void bind(ChatItem item) {
            textTitle.setText(item.getTitle());
            textTime.setText(item.getTime());

            if (item.isSavedMessages()) {
                imageAvatar.setVisibility(View.GONE);
                imageSavedIcon.setVisibility(View.VISIBLE);
                textLastMessage.setText(""); // یا "Saved Messages"
            } else {
                imageAvatar.setVisibility(View.VISIBLE);
                imageSavedIcon.setVisibility(View.GONE);
                textLastMessage.setText(item.getLastMessage() != null ? item.getLastMessage() : "");
            }

            // کلیک حذف شد — فقط در onBindViewHolder
        }
    }
}