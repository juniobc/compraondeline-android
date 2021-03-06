package br.com.compraondeline;

import java.util.List;

import br.com.entidade.Produto;
import br.com.localizacao.GPSTracker;
import br.com.model.ProdutoDB;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
  
/**
 * @author Sebastiao Junio
 *
 */
public class Tab2Fragment extends Fragment {
	
	private Spinner tp_cad;
	private Spinner tp_un;
	private ArrayAdapter<CharSequence> adapter;
	
	private Button cadastrar;
	private EditText nmProd;
	private EditText nmPreco;
	private EditText nmQuant;
	private EditText qt_tp_un;
	private TextView end_prod;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	View view = inflater.inflate(R.layout.tab2, container, false);
    	
    	tp_cad = (Spinner) view.findViewById(R.id.tp_cad);
    	adapter = ArrayAdapter.createFromResource(getActivity(),
    	        R.array.tp_cad, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	tp_cad.setAdapter(adapter);
    	
    	tp_un = (Spinner) view.findViewById(R.id.tp_un);
    	adapter = ArrayAdapter.createFromResource(getActivity(),
    	        R.array.tp_un, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	tp_un.setAdapter(adapter);
    	
    	end_prod = (TextView) view.findViewById(R.id.end_prod);
    	
    	nmProd = (EditText) view.findViewById(R.id.nm_prod);
    	nmPreco = (EditText) view.findViewById(R.id.preco_prod);
    	nmQuant = (EditText) view.findViewById(R.id.quant_prod);
    	qt_tp_un = (EditText) view.findViewById(R.id.qt_tp_un);
    	
    	cadastrar = (Button) view.findViewById(R.id.btn_busca_prod);
    	
    	cadastrar(cadastrar);
        
        return view;
        
    }
    
    public void cadastrar(Button cadastrar){

    	cadastrar.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
            	
            	Location local;
            	
            	tp_cad.getSelectedItem().toString();
            	
            	if(nmProd.getText().toString().trim().equals("")){            		
            		
            		Toast.makeText(getActivity(), "Informe o nome do produto", Toast.LENGTH_LONG).show();
            		
            	}else if(tp_un.getSelectedItem().toString().trim().equals("")){            		
            		
            		Toast.makeText(getActivity(), "Informe o tipo de unidade", Toast.LENGTH_LONG).show();
            		
            	}else if(qt_tp_un.getText().toString().trim().equals("")){            		
            		
            		Toast.makeText(getActivity(), "Informe a quantidade do tipo de unidade", Toast.LENGTH_LONG).show();
            		
            	}else if(nmPreco.getText().toString().trim().equals("")){            		
            		
            		Toast.makeText(getActivity(), "Informe o preco", Toast.LENGTH_LONG).show();
            		
            	}else if(nmQuant.getText().toString().trim().equals("")){            		
					
					Toast.makeText(getActivity(), "Informe a quantidade", Toast.LENGTH_LONG).show();
					
				}else{
					
					ProdutoDB db = new ProdutoDB(getActivity());
					boolean tp_cad_b;
					
					if(tp_un.getSelectedItem().toString().trim().equals("Cadastro")){
						
						tp_cad_b = true;
						
					}else{
						
						tp_cad_b = false;
						
					}
					
					//gps.getLocation();
					
					local = GPSTracker.ultimoLocal();

	                db.addProduto(new Produto(
	                		nmProd.getText().toString().trim(), 
	                		tp_un.getSelectedItem().toString().trim(), 
	                		Float.parseFloat(qt_tp_un.getText().toString().trim()), 
	                		Float.parseFloat(nmPreco.getText().toString().trim()), 
	                		Integer.parseInt(nmQuant.getText().toString().trim()), 
	                		Double.toString(local.getLatitude()),
	                		Double.toString(local.getLongitude()),
	                		tp_cad_b	                		
	                )); 
	                
	                Toast.makeText(getActivity(), Double.toString(local.getLatitude()), Toast.LENGTH_LONG).show();
					Toast.makeText(getActivity(), Double.toString(local.getLongitude()), Toast.LENGTH_LONG).show();
	                
	                Toast.makeText(getActivity(), "Cadastrado com sucesso !", Toast.LENGTH_LONG).show();
					
				}  	
            	
            }
        });
    	
    }

}
