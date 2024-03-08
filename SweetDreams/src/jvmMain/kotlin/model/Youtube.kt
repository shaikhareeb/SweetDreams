package youtube

import model.GetHttpBody
import org.json.JSONObject

fun GetYoutubeVideo(videoId:String){
    var url = "https://www.googleapis.com/youtube/v3/videos?";
    url += "key=AIzaSyCwu-oCqEIfcEJOTBrNAIK2wmRyLXTXeBw";
    url += "&id=$videoId";
    url += "&part=snippet"


    var result = GetHttpBody(url)
    println(result);
}

fun Initialize() {
    var url = "https://www.googleapis.com/youtube/v3/search?";
    url += "key=AIzaSyCwu-oCqEIfcEJOTBrNAIK2wmRyLXTXeBw";
    url += "&q=joji";
    url += "&type=video"

    var result = GetHttpBody(
        url
    );
    println(result)
}

