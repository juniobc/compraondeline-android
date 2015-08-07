package br.com.compraondeline;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import br.com.compraondeline.auxiliar.ListaProdutos;
import br.com.entidade.Produto;
import br.com.model.ProdutoDB;
  
/**
 * @author Sebastiao Junio
 *
 */
public class Tab3Fragment extends Fragment {
	
	private static ListView listProdView;
	private static View view;
	private Button btn_apagar;
	
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	if (view == null) {
    	
    		view = inflater.inflate(R.layout.tab3, container, false);
    	
    	}
    	
    	listaProduto(getActivity());
    	     
        return view;
        
        
        
    }
    
    public static void listaProduto(Context contexto){
    	
    	List<Produto> produtoList ;
    	
    	ProdutoDB db = new ProdutoDB(contexto);
    	
    	produtoList = db.getAllProdutos();
    	
    	ListaProdutos adapter = new ListaProdutos(contexto,R.layout.lista_prod,produtoList);       
		
		listProdView = (ListView) view.findViewById(R.id.list_prod);
		
		listProdView.setAdapter(adapter);
    	
    }
    

}
