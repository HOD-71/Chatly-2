package com.example.chatly;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private static final int VIEW_TYPE_SAVED = 0;
    private static final int VIEW_TYPE_CHAT = 1;

    private List<ChatItem> chatList;

    public ChatAdapter(List<ChatItem> chatList) {
        this.chatList = chatList;
    }

    @Override
    public int getItemViewType(int position) {
        return chatList.get(position).isSavedMessages() ? VIEW_TYPE_SAVED : VIEW_TYPE_CHAT;
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
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    // ViewHolder داخلی
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
                // حالت Saved Messages
                imageAvatar.setVisibility(View.GONE);
                imageSavedIcon.setVisibility(View.VISIBLE);
                textLastMessage.setText(""); // یا "Saved Messages"
            } else {
                // چت عادی
                imageAvatar.setVisibility(View.VISIBLE);
                imageSavedIcon.setVisibility(View.GONE);
                textLastMessage.setText(item.getLastMessage() != null ? item.getLastMessage() : "");
            }

            // کلیک روی آیتم (اختیاری)
            itemView.setOnClickListener(v -> {
                if (item.isSavedMessages()) {
                    // برو به صفحه Saved Messages
                    // مثلاً: v.getContext().startActivity(new Intent(v.getContext(), SavedMessagesActivity.class));
                } else {
                    // برو به چت عادی
                }
            });
        }
    }
}