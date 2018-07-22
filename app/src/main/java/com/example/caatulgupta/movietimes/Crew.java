
package com.example.caatulgupta.movietimes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Crew {

    @SerializedName("credit_id")
    String creditId;
    String department;
    Long gender;
    Long id;
    String job;
    String name;
    @SerializedName("profile_path")
    String profilePath;


}
