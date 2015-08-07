package br.com.compraondeline;

import java.util.List;

import br.com.entidade.Produto;
import br.com.maps.CustomMapFragment;
import br.com.model.ProdutoDB;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
  
/**
 * @author Sebastiao Junio
 *
 */
public class Tab1Fragment extends Fragment implements CustomMapFragment.OnMapReadyListener {
	
	private static GoogleMap mMap;
	private static CustomMapFragment mMapFragment;
	private static View view;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (view == null) {
            /*ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);*/
    		view = inflater.inflate(R.layout.tab1, container, false);
            mMapFragment = CustomMapFragment.newInstance();
            getChildFragmentManager().beginTransaction().replace(R.id.map, mMapFragment).commit();

        }
    	        
        return view;
        
    }
    
    @Override
    public void onMapReady() {
    	
    	mMap = mMapFragment.getMap();
    	
        mMap.setMyLocationEnabled(true);
                
        atualizaProdutoMapa(getActivity());
        
    }
    
    /*@Override
    public void onDestroyView() {
        super.onDestroyView();
        CustomMapFragment f = (CustomMapFragment) getFragmentManager()
                                             .findFragmentById(R.id.map);
        if (f != null) 
            getFragmentManager().beginTransaction().remove(f).commit();
    }*/
    
    public static void atualizaMapa(Location local){    	
    	
    	CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(
    		local.getLatitude(), 
    		local.getLongitude()), 15
		);
		mMap.animateCamera(cameraUpdate);
    	
    }
    
    public static void atualizaProdutoMapa(Context contexto){
    	
    	List<Produto> produtoList ;
    	
    	ProdutoDB db = new ProdutoDB(contexto);
    	
    	produtoList = db.getAllProdutos();
    	
    	for (Produto object : produtoList) {
			mMap.addMarker(new MarkerOptions()
	        .position(new LatLng(Double.parseDouble(object.getNrLat().replace(",", ".")), 
	        		Double.parseDouble(object.getNrLong().replace(",", "."))))
	        .title(object.getNome())
	        );
		}
    	
    }

}
