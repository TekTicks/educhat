package in.tekticks.educhat.data;

/**
 * Created by Admin on 29-01-2016.
 */
public class ContactsRowData {
    private String contactName,imageUrl;

    public ContactsRowData(String contactName, String imageUrl) {
        this.contactName = contactName;
        this.imageUrl = imageUrl;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
