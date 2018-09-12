package asykurkhamid.dropshop.Pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class DataPaymentConfirmation implements Parcelable {

    private String ID;
    private String norekSender;
    private String senderName;
    private String bankSenderName;
    private String nominal;
    private String date;
    private String noRekDest;
    private String bankDestName;
    private String image;
    private String address;
    private String kurir;
    private String pengiriman;
    private String notes;


    public DataPaymentConfirmation() {
    }

    protected DataPaymentConfirmation(Parcel in) {
        ID = in.readString();
        norekSender = in.readString();
        senderName = in.readString();
        bankSenderName = in.readString();
        nominal = in.readString();
        date = in.readString();
        noRekDest = in.readString();
        bankDestName = in.readString();
        image = in.readString();
        address = in.readString();
        kurir = in.readString();
        pengiriman = in.readString();
        notes = in.readString();
    }

    public static final Creator<DataPaymentConfirmation> CREATOR = new Creator<DataPaymentConfirmation>() {
        @Override
        public DataPaymentConfirmation createFromParcel(Parcel in) {
            return new DataPaymentConfirmation(in);
        }

        @Override
        public DataPaymentConfirmation[] newArray(int size) {
            return new DataPaymentConfirmation[size];
        }
    };

    @Override
    public String toString() {
        return "DataPaymentConfirmation{" +
                "ID='" + ID + '\'' +
                ", norekSender='" + norekSender + '\'' +
                ", senderName='" + senderName + '\'' +
                ", bankSenderName='" + bankSenderName + '\'' +
                ", nominal='" + nominal + '\'' +
                ", date='" + date + '\'' +
                ", noRekDest='" + noRekDest + '\'' +
                ", bankDestName='" + bankDestName + '\'' +
                ", image='" + image + '\'' +
                ", address='" + address + '\'' +
                ", kurir='" + kurir + '\'' +
                ", pengiriman='" + pengiriman + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNorekSender() {
        return norekSender;
    }

    public void setNorekSender(String norekSender) {
        this.norekSender = norekSender;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getBankSenderName() {
        return bankSenderName;
    }

    public void setBankSenderName(String bankSenderName) {
        this.bankSenderName = bankSenderName;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoRekDest() {
        return noRekDest;
    }

    public void setNoRekDest(String noRekDest) {
        this.noRekDest = noRekDest;
    }

    public String getBankDestName() {
        return bankDestName;
    }

    public void setBankDestName(String bankDestName) {
        this.bankDestName = bankDestName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKurir() {
        return kurir;
    }

    public void setKurir(String kurir) {
        this.kurir = kurir;
    }

    public String getPengiriman() {
        return pengiriman;
    }

    public void setPengiriman(String pengiriman) {
        this.pengiriman = pengiriman;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ID);
        parcel.writeString(norekSender);
        parcel.writeString(senderName);
        parcel.writeString(bankSenderName);
        parcel.writeString(nominal);
        parcel.writeString(date);
        parcel.writeString(noRekDest);
        parcel.writeString(bankDestName);
        parcel.writeString(image);
        parcel.writeString(address);
        parcel.writeString(kurir);
        parcel.writeString(pengiriman);
        parcel.writeString(notes);
    }
}
