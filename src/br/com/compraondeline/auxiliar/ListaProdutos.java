package br.com.compraondeline.auxiliar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.compraondeline.R;
import br.com.compraondeline.Tab3Fragment;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import br.com.entidade.Produto;
import br.com.model.ProdutoDB;

public class ListaProdutos extends ArrayAdapter<Produto> {
	
	private static Context context;
	int resource;
	List<Produto> data = null;
	private static RecipesHolder holder;
	private static View respView;
	
	public ListaProdutos(Context ct, int resource, List<Produto> object) {
		super(ct, resource, object);
		context = ct;
		this.resource = resource;
		this.data = object;
	}
	
	 public void apagaProduto(RecipesHolder h){
		 
		 h.btn_apagar.setOnClickListener(new OnClickListener() {

	            @Override
	            public void onClick(View view) {
	            	
	            	AlertDialog dialog = new AlertDialog.Builder(context).create();
	            	//dialog.setTitle("Confirmação");
	                dialog.setMessage("Deseja apagar ?");
	                dialog.setCancelable(false);
	                
	                respView = view;
	                
	                dialog.setButton(
	                	DialogInterface.BUTTON_POSITIVE, "Aceitar", 
	                	new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int buttonId) {
	                    	holder = (RecipesHolder) respView.getTag();
	    	            	
	    	            	ProdutoDB db = new ProdutoDB(context);
	    	            	TextView cd_produto = holder.cd_produto;
	    	            	
	    	            	db.deleteProduto(Integer.parseInt(cd_produto.getText().toString()));
	    	            	
	    	            	Toast.makeText(context, "Deletado com sucesso ! "
	    	            			, Toast.LENGTH_LONG).show();
	    	            	
	    	            	Tab3Fragment.listaProduto(context);
	    	            	
	                    }
	                	}
	                );
	                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int buttonId) {
	                    }
	                });
	                
	                dialog.show();
		            	
	            }
	        });
		 
	 }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
				
		if(convertView == null){
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			convertView = inflater.inflate(resource, parent,false);
			holder = new RecipesHolder();
			holder.nm_produto = (TextView) convertView.findViewById(R.id.nm_produto);
			Typeface customFont = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_bold.ttf");
			holder.nm_produto.setTypeface(customFont);
			holder.vl_produto = (TextView) convertView.findViewById(R.id.vl_produto);
			holder.dt_produto = (TextView) convertView.findViewById(R.id.dt_produto);
			holder.hr_produto = (TextView) convertView.findViewById(R.id.hr_produto);
			/*
			holder.cd_produto = (TextView) convertView.findViewById(R.id.cd_produto);
			holder.nm_produto = (TextView) convertView.findViewById(R.id.nm_produto);
			holder.qt_produto = (TextView) convertView.findViewById(R.id.qt_produto);
			*/
			convertView.setTag(holder);
		}else{
			
			holder = (RecipesHolder)convertView.getTag();
			
		}
		
		LinearLayout root = (LinearLayout) convertView.findViewById(R.id.row);
		
		if((position % 2) == 0){
			root.setBackgroundColor(convertView.getResources().getColor(R.color.white));
		}	
		else{
			root.setBackgroundColor(convertView.getResources().getColor(R.color.light_grey));
		}
		
		Produto object = data.get(position);
		
		holder.nm_produto.setText(
			object.getNome() + " " + 
			String.valueOf(object.getQtUnidade()) + " " +
			object.getTpUnidade()
		);
		
		holder.vl_produto.setText("R$ "+String.valueOf(object.getPreco()));
		
		Date data = new Date(object.getDt_hr());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
		
		String strData = dateFormat.format(data);
		
		holder.dt_produto.setText(String.valueOf(strData));
		
		String strHora = horaFormat.format(data);
		
		holder.hr_produto.setText(String.valueOf(strHora));
		
		/*
		
		holder.cd_produto = (TextView) convertView.findViewById(R.id.cd_produto);
		holder.cd_produto.setText(String.valueOf(object.getCdProd()));		
		
		holder.qt_produto = (TextView) convertView.findViewById(R.id.qt_produto);
		holder.qt_produto.setText(String.valueOf(object.getQuantidade()));
		
		holder.btn_apagar = (Button) convertView.findViewById(R.id.btn_apagar);
		
		holder.btn_apagar.setTag(holder);
		
		apagaProduto(holder);*/
		
		return convertView;
	}
	
	static class RecipesHolder {
        TextView cd_produto;
        TextView nm_produto;
        TextView qt_produto;
        TextView vl_produto;
        TextView dt_produto;
        TextView hr_produto;
        Button btn_apagar;
    }

}