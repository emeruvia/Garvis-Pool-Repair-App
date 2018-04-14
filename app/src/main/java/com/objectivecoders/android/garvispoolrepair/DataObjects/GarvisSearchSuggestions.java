package com.objectivecoders.android.garvispoolrepair.DataObjects;

import android.os.Parcel;
import android.os.Parcelable;

import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

/**
 * Created by jeffr on 4/13/2018.
 */

public class GarvisSearchSuggestions implements SearchSuggestion {
    public static Parcelable.Creator CREATOR;

    @Override
    public String getBody() {
        return "BoB";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
