package com.example.thedude.shopandeateryactivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by The Dude on 5/10/2017.
 */

public class EatShop extends java.util.HashMap<String,String> {

    final static String host = "https://appsbytsl.com/API/V1/Nearby/Food/H4D3S";

    public EatShop(String API_Status, String AdminRemarks, String Category,
                   String ContactEmail, String ContactInfo, String ContactNumber, String ContactStatus,
                   Double Distance, String EatBookArticle,
                   String EntryAddress, String EntryDescription, Integer EntryID, String EntryName,
                   String FacebookPage, String Instagram,
                   Double Latitude, Double Longitude,
                   String MasterCategory, String OpeningHours, String PostalCode, String SubFilter,
                   String TSLArticle, String TSLReviews, String Website, String Zone) {
        put("API_Status", API_Status);
        put("AdminRemarks", AdminRemarks);
        put("Category", Category);
        put("ContactEmail", ContactEmail);
        put("ContactInfo", ContactInfo);
        put("ContactNumber", ContactNumber);
        put("ContactStatus", ContactStatus);
        put("Distance", Distance.toString());
        put("EatBookArticle", EatBookArticle);
        put("EntryAddress", EntryAddress);
        put("EntryDescription", EntryDescription);
        put("EntryID", EntryID.toString());
        put("EntryName", EntryName);
        put("FacebookPage", FacebookPage);
        put("Instagram", Instagram);
        put("Latitude", Latitude.toString());
        put("Longitude", Longitude.toString());
        put("MasterCategory", MasterCategory);
        put("OpeningHours", OpeningHours);
        put("PostalCode", PostalCode);
        put("SubFilter", SubFilter);
        put("TSLArticle", TSLArticle);
        put("TSLReviews", TSLReviews);
        put("Website", Website);
        put("Zone", Zone);
    }

    public EatShop(){}

    public static List<EatShop> shopList(double Latitude, double Longitude, int Distance) {
        List<EatShop> shops = new ArrayList<EatShop>();
        EatShop shop = null;
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"/"+Latitude+"/"+Longitude+"/"+Distance);
            for (int i=0; i<a.length(); i++) {
                JSONObject c = a.getJSONObject(i);
                //JSONObject c = new JSONObject(a.getString(i));
                shop = new EatShop(c.getString("API_Status"),
                        c.getString("AdminRemarks"),
                        c.getString("Category"),
                        c.getString("ContactEmail"),
                        c.getString("ContactInfo"),
                        c.getString("ContactNumber"),
                        c.getString("ContactStatus"),
                        Double.parseDouble(c.getString("Distance")),
                        c.getString("EatBookArticle"),
                        c.getString("EntryAddress"),
                        c.getString("EntryDescription"),
                        Integer.parseInt(c.getString("EntryID")),
                        c.getString("EntryName"),
                        c.getString("FacebookPage"),
                        c.getString("Instagram"),
                        Double.parseDouble(c.getString("Latitude")),
                        Double.parseDouble(c.getString("Longitude")),
                        c.getString("MasterCategory"),
                        c.getString("OpeningHours"),
                        c.getString("PostalCode"),
                        c.getString("SubFilter"),
                        c.getString("TSLArticle"),
                        c.getString("TSLReviews"),
                        c.getString("Website"),
                        c.getString("Zone"));
                shops.add(shop);
            }
        } catch (Exception e) {
        }
        return shops;
    }

    public static EatShop getShopDetails(int Latitude, int Longitude, int Distance) {
        EatShop shop = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host+"/"+Latitude+"/"+Longitude+"/"+Distance);
            shop = new EatShop(c.getString("API_Status"),
                    c.getString("AdminRemarks"),
                    c.getString("Category"),
                    c.getString("ContactEmail"),
                    c.getString("ContactInfo"),
                    c.getString("ContactNumber"),
                    c.getString("ContactStatus"),
                    Double.parseDouble(c.getString("Distance")),
                    c.getString("EatBookArticle"),
                    c.getString("EntryAddress"),
                    c.getString("EntryDescription"),
                    Integer.parseInt(c.getString("EntryID")),
                    c.getString("EntryName"),
                    c.getString("FacebookPage"),
                    c.getString("Instagram"),
                    Double.parseDouble(c.getString("Latitude")),
                    Double.parseDouble(c.getString("Longitude")),
                    c.getString("MasterCategory"),
                    c.getString("OpeningHours"),
                    c.getString("PostalCode"),
                    c.getString("SubFilter"),
                    c.getString("TSLArticle"),
                    c.getString("TSLReviews"),
                    c.getString("Website"),
                    c.getString("Zone"));
        } catch (Exception e) {
        }
        return shop;
    }
}