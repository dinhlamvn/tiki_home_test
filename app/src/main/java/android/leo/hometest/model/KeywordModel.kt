package android.leo.hometest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class KeywordModel(
        @SerializedName("keyword")
        @Expose
        var keyword : String
)