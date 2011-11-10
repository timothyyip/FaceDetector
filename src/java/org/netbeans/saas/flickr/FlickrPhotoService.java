/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.saas.flickr;

import java.io.IOException;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 * FlickrPhotoService Service
 *
 * @author timothy.yip
 */
public class FlickrPhotoService
{

    /** Creates a new instance of FlickrPhotoService */
    public FlickrPhotoService()
    {
    }
    
    private static void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (Throwable th)
        {
        }
    }

    /**
     *
     * @param userId
     * @param tags
     * @param tagMode
     * @param text
     * @param minUploadDate
     * @param maxUploadDate
     * @param minTakenDate
     * @param maxTakenDate
     * @param license
     * @param sort
     * @param privacyFilter
     * @param bbox
     * @param accuracy
     * @param safeSearch
     * @param contentType
     * @param machineTags
     * @param machineTagMode
     * @param groupId
     * @param placeId
     * @param extras
     * @param perPage
     * @param page
     * @return an instance of RestResponse
     */
    public static RestResponse photosSearch(String userId, String tags, String tagMode, String text, String minUploadDate, String maxUploadDate, String minTakenDate, String maxTakenDate, String license, String sort, String privacyFilter, String bbox, String accuracy, String safeSearch, String contentType, String machineTags, String machineTagMode, String groupId, String placeId, String extras, String perPage, String page) throws IOException
    {
        String method = "flickr.photos.search";
        FlickrPhotoServiceAuthenticator.login();
        String apiKey = FlickrPhotoServiceAuthenticator.getApiKey();
        String authToken = FlickrPhotoServiceAuthenticator.getAuthToken();
        String apiSig = FlickrPhotoServiceAuthenticator.sign(new String[][]{{"api_key", apiKey}, {"user_id", userId}, {"tags", tags}, {"tag_mode", tagMode}, {"text", text}, {"min_upload_date", minUploadDate}, {"max_upload_date", maxUploadDate}, {"min_taken_date", minTakenDate}, {"max_taken_date", maxTakenDate}, {"license", license}, {"sort", sort}, {"privacy_filter", privacyFilter}, {"bbox", bbox}, {"accuracy", accuracy}, {"safe_search", safeSearch}, {"content_type", contentType}, {"machine_tags", machineTags}, {"machine_tag_mode", machineTagMode}, {"group_id", groupId}, {"place_id", placeId}, {"extras", extras}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}});
        String[][] pathParams = new String[][]{};
        String[][] queryParams = new String[][]{{"api_key", "" + apiKey + ""}, {"user_id", userId}, {"tags", tags}, {"tag_mode", tagMode}, {"text", text}, {"min_upload_date", minUploadDate}, {"max_upload_date", maxUploadDate}, {"min_taken_date", minTakenDate}, {"max_taken_date", maxTakenDate}, {"license", license}, {"sort", sort}, {"privacy_filter", privacyFilter}, {"bbox", bbox}, {"accuracy", accuracy}, {"safe_search", safeSearch}, {"content_type", contentType}, {"machine_tags", machineTags}, {"machine_tag_mode", machineTagMode}, {"group_id", groupId}, {"place_id", placeId}, {"extras", extras}, {"per_page", perPage}, {"page", page}, {"method", method}, {"auth_token", authToken}, {"api_sig", apiSig}};
        RestConnection conn = new RestConnection("http://api.flickr.com/services/rest", pathParams, queryParams);
        sleep(1000);
        return conn.get(null);
    }
}
