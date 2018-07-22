package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("cast_id")
    int castId;
    @SerializedName("character")
    String characterName;
    int gender;
    int id;
    String name;
    int order;
    @SerializedName("profile_path")
    String profilePath;

}
