package de.tubs.ibr.dtn.api;

import android.os.Parcel;
import android.os.Parcelable;

public class Node implements Parcelable {
	public SingletonEndpoint endpoint;
	public String type;
	
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeParcelable(endpoint, 0);
		dest.writeString(type);
	}
	
    public static final Creator<Node> CREATOR = new Creator<Node>() {
        public Node createFromParcel(final Parcel source) {
        	Node n = new Node();
        	n.endpoint = source.readParcelable(null);
        	n.type = source.readString();        	
        	return n;
        }

        public Node[] newArray(final int size) {
            return new Node[size];
        }
    };
}
