package hcmute.edu.vn.food_22;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class GeocodingLocation
{
    private static final String TAG = "GeocodingLocation";

    public GeocodingLocation() {

    }

    public Location getLatLngFromAddress(String locationAddress,
                                         Context context) {
        Location location=new Location("ABC");
        Geocoder g = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addressList = g.getFromLocationName(locationAddress, 1);
            if (addressList != null && addressList.size() > 0) {
                Address address = addressList.get(0);
                location.setLatitude(address.getLatitude());
                location.setLongitude(address.getLongitude());
                MainActivity.isWifiEnabled = true;
            }
        } catch (IOException e) {
            MainActivity.isWifiEnabled = false;
        }
        return location;
    }
    public double Calculate(double s1, double s2, double e1, double e2)
    {
        Location l1 = new Location("ABC");
        Location l2 = new Location("ABC");
        l1.setLatitude(s1);
        l1.setLongitude(s2);
        l2.setLatitude(e1);
        l2.setLongitude(e2);
        return l1.distanceTo(l2)/1000;
    }
}
