package youtube

import model.GetHttpBody
import org.json.JSONArray
import org.json.JSONObject

data class youtubeData (
    val id: Int,
    val title: String,
    val description: String,
    val thumbnail: String,
    val playerId: String
)

fun GetYoutubeVideo(videoId:String) : JSONObject{
    var url = "https://www.googleapis.com/youtube/v3/videos?";
    url += "key=AIzaSyCgMGMaylmSaFPQAhu36frrK_dJXDFla3A";
    url += "&id=$videoId";
    url += "&part=snippet"

    var result = GetHttpBody(url)
    return JSONObject(result);
}

fun GetSearchList(search : String) : JSONArray{
    var url = "https://www.googleapis.com/youtube/v3/search?";
    url += "key=AIzaSyCgMGMaylmSaFPQAhu36frrK_dJXDFla3A";
    url += "&q=$search";
    url += "&type=video"
    url += "&maxResults=20"

    var result = GetHttpBody(url);
    var json = JSONObject(result);
    var arr = json.getJSONArray("items");
    return arr
}

fun getSearchData(search : String) : Array<youtubeData?> {
    var list = GetSearchList(search);
    val vals: Array<youtubeData?> = arrayOfNulls<youtubeData>(20)
    var i = 0;
    (0 until list.length()).forEach {
        val item = list.getJSONObject(it)
        var videoId = item.getJSONObject("id").get("videoId").toString();
        var videoDetails = GetYoutubeVideo(videoId);
        var videoTitle = JSONObject(videoDetails.getJSONArray("items").get(0).toString()).getJSONObject("snippet").getString("title").toString();
        var thumbnailUrl = JSONObject(videoDetails.getJSONArray("items").get(0).toString()).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url");
        vals[i] = youtubeData(0, videoTitle, "", thumbnailUrl, videoId);
        i += 1
    }

    return vals;
}

fun Initialize() {
    var url = "https://www.googleapis.com/youtube/v3/search?";
    url += "key=AIzaSyCgMGMaylmSaFPQAhu36frrK_dJXDFla3A";
    url += "&q=joji";
    url += "&type=video"

    var result = GetHttpBody(
        url
    );
    println(result)
}

