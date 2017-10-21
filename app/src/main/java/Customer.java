/**
 * Created by Quan on 10/21/2017.
 */

public class Customer {

    private String mName;
    private int mCreditCard;
    private String mBilling;

    public Customer(String mName, int mCreditCard, String mBilling){
        this.mName = mName;
        this.mCreditCard = mCreditCard;
        this.mBilling =  mBilling;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmCreditCard() {
        return mCreditCard;
    }

    public void setmCreditCard(int mCreditCard) {
        this.mCreditCard = mCreditCard;
    }

    public String getmBilling() {
        return mBilling;
    }

    public void setmBilling(String mBilling) {
        this.mBilling = mBilling;
    }
}
