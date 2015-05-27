package br.com.compraondeline;

import br.com.maps.CustomMapFragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
  
/**
 * @author mwho
 *
 */
public class Tab2Fragment extends Fragment implements OnMapReadyCallback {
	
	private Tab2Fragment mMapFragment;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.tab2, container, false);
    	
        getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();
        
        return view;
        
    }

	@Override
	public void onMapReady(GoogleMap arg0) {

		LatLng sydney = new LatLng(-33.867, 151.206);
		
		mMap = mMapFragment.getFragmentManager().;

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
		
	}
    

}
