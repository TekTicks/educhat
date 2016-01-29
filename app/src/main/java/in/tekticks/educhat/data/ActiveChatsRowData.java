package in.tekticks.educhat.data;

import android.widget.ImageView;

public class ActiveChatsRowData {
    private String profileImageUrl;
    private String contactName;
    private String contactMessage;
    private String chatDate;

    public String getChatNoNotification() {
        return chatNoNotification;
    }

    public ActiveChatsRowData(String profileImageUrl, String contactName, String contactMessage, String chatDate, String chatNoNotification) {
        this.profileImageUrl = profileImageUrl;
        this.contactName = contactName;
        this.contactMessage = contactMessage;
        this.chatDate = chatDate;
        this.chatNoNotification = chatNoNotification;
    }

    public void setChatNoNotification(String chatNoNotification) {
        this.chatNoNotification = chatNoNotification;
    }

    public String getChatDate() {
        return chatDate;
    }

    public void setChatDate(String chatDate) {
        this.chatDate = chatDate;
    }

    public String getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(String contactMessage) {
        this.contactMessage = contactMessage;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    private String chatNoNotification;
}
