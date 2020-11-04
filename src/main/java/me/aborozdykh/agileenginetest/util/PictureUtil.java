package me.aborozdykh.agileenginetest.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import me.aborozdykh.agileenginetest.entity.Author;
import me.aborozdykh.agileenginetest.entity.Camera;
import me.aborozdykh.agileenginetest.entity.Picture;
import me.aborozdykh.agileenginetest.entity.Tag;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class PictureUtil {
    private static final String URL = "http://interview.agileengine.com/images";
    private final RequestUtil requestUtil;

    public PictureUtil(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }

    public List<String> getPicturesId(String token) {
        Boolean hasMore = true;
        List<String> picturesId = new ArrayList<>();
        int pageNumber = 1;
        while (hasMore) {
            try {
                URL url = new URL(URL + "?page=" + pageNumber);
                String responseFromGet = requestUtil.getResponseFromGet(url, token);
                Object obj = new JSONParser().parse(responseFromGet);
                JSONObject jsonObject = (JSONObject) obj;
                hasMore = (Boolean) jsonObject.get("hasMore");
                JSONArray pictures = (JSONArray) jsonObject.get("pictures");
                Iterator picturesIterator = pictures.iterator();
                while (picturesIterator.hasNext()) {
                    JSONObject picture = (JSONObject) picturesIterator.next();
                    String id = (String) picture.get("id");
                    picturesId.add(id);
                }
                pageNumber++;
            } catch (MalformedURLException | ParseException e) {
                e.printStackTrace();
            }

        }
        return picturesId;
    }

    public List<Picture> getPictures(List<String> picturesId, String token) {
        List<Picture> pictures = new ArrayList<>();
        for (int i = 0; i < picturesId.size(); i++) {
            Picture picture = new Picture();
            try {
                URL url = new URL(URL + "/" + picturesId.get(i));
                String responseFromGet = requestUtil.getResponseFromGet(url, token);
                Object obj = new JSONParser().parse(responseFromGet);
                JSONObject jsonObject = (JSONObject) obj;
                Author author = new Author();
                author.setAuthor((String) jsonObject.get("author"));
                Camera camera = new Camera();
                camera.setCamera((String) jsonObject.get("camera"));
                List<Tag> tags = getTags((String) jsonObject.get("tags"));
                String fullPictureUrl = (String) jsonObject.get("full_picture");
                String croppedPicture = (String) jsonObject.get("cropped_picture");

                picture.setId(picturesId.get(i));
                picture.setAuthor(author);
                picture.setCamera(camera);
                picture.setCroppedPicture(croppedPicture);
                picture.setFullPicture(fullPictureUrl);
                picture.setTags(tags);
            } catch (MalformedURLException | ParseException e) {
                e.printStackTrace();
            }
            pictures.add(picture);
        }
        return pictures;
    }

    private List<Tag> getTags(String tagString) {
        tagString = tagString.replaceAll(" ", "");
        String[] tagArray = tagString.split("#");
        List<Tag> tags = new ArrayList<>();
        for (int i = 0; i < tagArray.length; i++) {
            Tag tag = new Tag();
            tag.setTag(tagArray[i]);
            tags.add(tag);
        }
        return tags;
    }
}
