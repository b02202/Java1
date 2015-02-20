// Robert Brooks
// Java 1
// Project 3
//TourInfo.java
// Created on 2/19/2015
package TourInfo;



public class TourInfo {

    // Member Variables
    private String Artist;
    private String tourName;
    private String tourGross;

    // Class Constructor
    public TourInfo()
    {
        tourGross = "0";
        Artist = tourName = "";
    }

    // TourInfo Method
    public TourInfo(String _artist, String _tourName, String _tourGross)
    {
        Artist = _artist;
        tourName = _tourName;
        tourGross = _tourGross;
    }

    // Getter and Setter Methods
    public String getTourGross()

    {
        return tourGross;
    }

    public void setTourGross(String _tourGross)
    {
        tourGross = _tourGross;
    }

    public String getArtistName()
    {
        return Artist;
    }

    public void setArtist(String _artist)
    {
        Artist = _artist;
    }

    public String getTourName()
    {
        return tourName;
    }

    public void setTourName(String _tourName)
    {
        tourName = _tourName;
    }



}
