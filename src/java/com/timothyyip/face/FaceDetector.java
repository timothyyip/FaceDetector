/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timothyyip.face;

import detection.Detector;
import flickr.photoservice.flickrresponse.Rsp.Photos.Photo;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.sanselan.ImageFormat;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.Sanselan;
import org.netbeans.saas.flickr.FlickrPhotoService;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author timothy.yip
 */
public class FaceDetector
{

    public static void main(String[] args) throws ImageReadException, IOException, ImageWriteException
    {



        Detector detector = new Detector("C:\\Code\\Java\\FaceDetector\\lib\\haarcascade_frontalface_default.xml");

        flickr.photoservice.flickrresponse.Rsp response = getFlickrPhotos();

        for (Photo photo : response.getPhotos().getPhoto())
        {
            String timestamp = String.valueOf(Calendar.getInstance().getTimeInMillis());
            BufferedImage originalImage = getPhysicalFlickrImage(photo);

            if (originalImage != null)
            {
                File originalImageFile = new File("C:\\Code\\Java\\FaceDetector\\images\\original\\" + timestamp + ".jpg");

                //Sanselan.writeImage(originalImage, originalImageFile, ImageFormat.IMAGE_FORMAT_JPEG, null);
                ImageIO.write(originalImage, "JPG", originalImageFile);
                List<Rectangle> res = detector.getFaces(originalImageFile.getAbsolutePath(), 3, 1.25f, 0.1f, 1, false);

                for (Rectangle face : res)
                {
                    BufferedImage faceImage = originalImage.getSubimage(face.x, face.y, face.width, face.height);
                    
                    Sanselan.writeImage(faceImage, new File("C:\\Code\\Java\\FaceDetector\\images\\" + String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".png"), ImageFormat.IMAGE_FORMAT_PNG, null);
                }

            }
        }
    }

    public static flickr.photoservice.flickrresponse.Rsp getFlickrPhotos()
    {
        flickr.photoservice.flickrresponse.Rsp resultObj = null;

        try
        {
            String userId = null;
            String tags = "business";
            String tagMode = null;
            String text = null;
            String minUploadDate = null;
            String maxUploadDate = null;
            String minTakenDate = null;
            String maxTakenDate = null;
            String license = "4";
            String sort = null;
            String privacyFilter = null;
            String bbox = null;
            String accuracy = null;
            String safeSearch = null;
            String contentType = null;
            String machineTags = null;
            String machineTagMode = "";
            String groupId = null;
            String placeId = null;
            String extras = null;
            String perPage = "500";
            String page = "3";

            RestResponse result = FlickrPhotoService.photosSearch(userId, tags, tagMode, text, minUploadDate, maxUploadDate, minTakenDate, maxTakenDate, license, sort, privacyFilter, bbox, accuracy, safeSearch, contentType, machineTags, machineTagMode, groupId, placeId, extras, perPage, page);
            if (result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class) instanceof flickr.photoservice.flickrresponse.Rsp)
            {
                resultObj = result.getDataAsObject(flickr.photoservice.flickrresponse.Rsp.class);
                System.out.println(result.getDataAsString());
            }
            //TODO - Uncomment the print Statement below to print result.

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return resultObj;

    }

    public static BufferedImage getPhysicalFlickrImage(Photo flickrPhoto) throws IOException, ImageReadException
    {
        String urlString = "http://farm7.static.flickr.com/"
                + flickrPhoto.getServer() + "/" + flickrPhoto.getId() + "_" + flickrPhoto.getSecret() + ".jpg";
        System.out.println(urlString);
        BufferedImage im = null;
        try
        {
            im = ImageIO.read(new URL(urlString));
        }
        finally
        {
            return im;
        }
    }
}
