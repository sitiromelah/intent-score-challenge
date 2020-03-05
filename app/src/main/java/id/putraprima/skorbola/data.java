package id.putraprima.skorbola;

import android.os.Parcel;
import android.os.Parcelable;

public class data implements Parcelable {
    public String homeTeam;
    public  String awayTeam;

    public data(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    protected data(Parcel in) {
        homeTeam = in.readString();
        awayTeam = in.readString();
    }

    public static final Creator<data> CREATOR = new Creator<data>() {
        @Override
        public data createFromParcel(Parcel in) {
            return new data(in);
        }

        @Override
        public data[] newArray(int size) {
            return new data[size];
        }
    };

    public String getHomeTeam(){ return homeTeam; }

    public void setHomeTeam(String homeTeam) {this.homeTeam = homeTeam;}

    public String getAwayTeam(){ return awayTeam ; }

    public void setAwayTeam(String awayTeam) { this.awayTeam = awayTeam; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homeTeam);
        dest.writeString(awayTeam);
    }
}
