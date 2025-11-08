package com.example.chatly;

public class ChatItem {
    private int id;
    private String title;
    private String lastMessage;
    private String time;
    private boolean isSavedMessages;
    private String avatarUrl; // اختیاری، برای چت‌های عادی

    // سازنده (Constructor)
    public ChatItem(int id, String title, String lastMessage, String time, boolean isSavedMessages, String avatarUrl) {
        this.id = id;
        this.title = title;
        this.lastMessage = lastMessage;
        this.time = time;
        this.isSavedMessages = isSavedMessages;
        this.avatarUrl = avatarUrl;
    }

    // اگر lastMessage یا avatarUrl نداریم (مثل Saved Messages)
    public ChatItem(int id, String title, String time, boolean isSavedMessages) {
        this(id, title, null, time, isSavedMessages, null);
    }

    // Getter ها
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getLastMessage() { return lastMessage; }
    public String getTime() { return time; }
    public boolean isSavedMessages() { return isSavedMessages; }
    public String getAvatarUrl() { return avatarUrl; }

    // Setter ها (اختیاری، اگر بعداً بخوای تغییر بدی)
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }
}
