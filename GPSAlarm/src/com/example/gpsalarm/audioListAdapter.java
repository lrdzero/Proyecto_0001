package com.example.gpsalarm;
/*
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class audioListAdapter extends BaseAdapter {
	private final List<AlarmMap> mItems = new ArrayList<AlarmMap>();
	private Context context;

	public audioListAdapter(Context c){
		context= c;
	
	}
	
	public void addAlarm(AlarmMap al){
		mItems.add(al);
	}
	
	
	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.size();
	}

	@Override
	public Object getItem(int arg) {
		// TODO Auto-generated method stub
		return mItems.get(arg);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		    final AlarmMap Item = (AlarmMap) getItem(arg);
		    final int aux=arg;
		    ImageButton ib=null;
		    final int nchild=arg;
			
		   

			// TODO - Inflate the View for this ToDoItem
			// from todo_item.xml
		    LayoutInflater inflater = null;
			if(convertView == null){
			    // rest of the code
				inflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			    convertView = inflater.inflate(R.layout.activity_lista_grabaciones, null);
			    //convertView = inflater.inflate(R.layout.options, null);
			}
			listatotal.add(convertView);
			
			
			
			
		   
			convertView.setOnClickListener(new View.OnClickListener() {
				
				@SuppressLint("NewApi")
				@Override
				//Cuando un item es pulsado se despliegan las herramientas
				public void onClick(final View vista) {
					
					LinearLayout ll = null;
					// TODO Auto-generated method stub
					
					
					boolean itself=false;
					
					//este bucle contrae los items que están expandidos 
					for(View i : listatotal){
						if(i.isActivated()){						
							ll=(LinearLayout) i.findViewById(R.id.tools);
							ll.removeAllViews();
							i.setActivated(false);
							
							//si se trata del mismo item
							if(i==vista){
								itself=true;
								
							}
							break;		
						}
					}
					
					
					
					if(!itself){
						ll= (LinearLayout)vista.findViewById(R.id.tools);
						LayoutInflater inflater2 = LayoutInflater.from(context);
						
						ll = (LinearLayout)inflater2.inflate(R.layout.options,ll,true);
						final ImageButton DeleteButton= (ImageButton) vista.findViewById(R.id.delete);
						final ImageButton ShareButton= (ImageButton) vista.findViewById(R.id.share);
						final ImageButton PlusButton= (ImageButton) vista.findViewById(R.id.plus);
						PlusButton.setAdjustViewBounds(true);
						DeleteButton.setAdjustViewBounds(true);
						ShareButton.setAdjustViewBounds(true);
						vista.setActivated(true);
						
						//Implementación del botón de borrado
						DeleteButton.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if(!q.isEmpty())
									q.peek().cancel(true);
								AlertDialog.Builder alertaDelete = new AlertDialog.Builder(context);
								AlertDialog dialog;
								alertaDelete.setMessage("¿Está seguro de eliminar la grabación '"+mItems.get(aux).getName()+"'?");
								alertaDelete.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
									 public void onClick(DialogInterface dialog, int whichButton) {
										 
										 listatotal.remove(vista);	
										 File borrado;
										 borrado = new File(mItems.get(aux).getPath());
										 if(borrado.delete())
											 Toast.makeText(context, mItems.get(aux).getName()+" borrado con éxito", Toast.LENGTH_SHORT).show();
										 mItems.remove(aux); 
										 notifyDataSetChanged();	
										 
								    }
								 });

								alertaDelete.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
									 public void onClick(DialogInterface dialog, int whichButton) {
									     // Canceled.
										   dialog.cancel();
									 }
								 });

						  	
						  	
								 dialog = alertaDelete.create();
								 dialog.show();
		 		
							}
			
						});
						
						//Implementación del botón de compartir
						ShareButton.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View v) {
								if(!q.isEmpty())
									q.peek().cancel(true);
								Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
								sharingIntent.setType("audio/*");
								Uri uri = Uri.parse(mItems.get(aux).getPath());
								sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,mItems.get(aux).getName());
								sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, uri );
								
								context.startActivity(Intent.createChooser(sharingIntent, "Compartir en:"));
							}
							
						});
						
						//Implementación del botón +
						PlusButton.setOnClickListener(new View.OnClickListener(){

							@Override
							public void onClick(View v) {
								/*if(!q.isEmpty())
									q.peek().cancel(true);
								String[] testArray = new String[] {"Renombrar","Añadir a categoría"};
								AlertDialog dialog1;
								
								 AlertDialog.Builder builder = new AlertDialog.Builder(context);
								    builder.setTitle(R.string.chooseExtraOptions)
								           .setItems(testArray, new DialogInterface.OnClickListener() {
								               public void onClick(DialogInterface dialog, int which) {
								               // The 'which' argument contains the index position
								               // of the selected item
								            	   switch(which){
								            	   		case 0://renombrar
								            	   			AlertDialog dialog2;
								            	   			AlertDialog.Builder rename = new AlertDialog.Builder(context);
								            	   			

								            	   			rename.setTitle("Renombrar:");
								            	   			rename.setMessage("Inserte un nuevo nombre a la grabación");

								            	   			// Set an EditText view to get user input 
								            	   			final EditText input = new EditText(context);
								            	   			rename.setView(input);
								            	   			rename.setPositiveButton("OK", new DialogInterface.OnClickListener() {
								            	   				public void onClick(DialogInterface dialog, int whichButton) {
								            	   					
								            	   					mItems.get(aux).setName(input.getText().toString()+".3gp");
								            	   					File filenew=new File(mItems.get(aux).getPath());
								            	   					File sdCard = Environment.getExternalStorageDirectory();
								            	   					File f= new File( sdCard.getAbsolutePath()+"/MisGrabaciones");
								            	   					filenew.renameTo(new File(f,input.getText().toString()+".3gp"));
								            	   					
								            	   					notifyDataSetChanged();
								            	   				}
								            	   			});
								            	   			rename.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
								            	   				public void onClick(DialogInterface dialog, int whichButton) {
								            	   					dialog.cancel();
								            	   				}
								            	   			});
								            	   			
								            	   			dialog2=rename.create();
														    dialog2.show();
								            		   		break;
								            	   		case 1:
								            	   			break;
								            	   
								            	   }
								           }
								    });
								    dialog1=builder.create();
								    dialog1.show();
							}
							
						});
						
					}
	
				}
			});
			
			ib = (ImageButton)convertView.findViewById(R.id.imageButton1);
			final ProgressBar pb = (ProgressBar) convertView.findViewById(R.id.playbar);
			ib.setAdjustViewBounds(true);
			
			
			ib.setOnClickListener(new View.OnClickListener(){			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//el reproductor no está siendo usado
					if(freeplayer){
						
						pt=new PlayTask(Item.getPath(),(ImageButton) v,pb,nchild);
						q.add(pt);
						freeplayer=false;
						q.peek().execute();
						
					}
					//el reproductor está ocupado
					else{
						itemCancelador=nchild;
						ibNextAudio=(ImageButton)v;
						pNextAudio=pb;
						q.peek().cancel(true);
						
						
					}
					
				}
				
				
				
				
			});
			

		    final TextView nombre= (TextView)convertView.findViewById(R.id.nombreAudio);
		    
		    if(Item.getName().length()>14)
		    	nombre.setText(Item.getName().substring(0, 14)+"...");
		    else
		    	nombre.setText(Item.getName());

			// Return the View you just created
			return convertView;
			
		
	}
	
	

	  private void startPlaying(String path, ImageButton ib, ProgressBar pb) {
		   
		    
	        mPlayer = new MediaPlayer();
	      
	        try {
	        	
	        	if(ib!=null&&pb!=null){
	        		ib.setImageResource(R.drawable.stop);
	        		pb.setVisibility(View.VISIBLE);
	        	}
	            mPlayer.setDataSource(path);
	            mPlayer.prepare();
	            mPlayer.start();
	            
	        
	          
	        } catch (IOException e) {
	            Log.e(LOG_TAG, "prepare() failed");
	        }
	  }
	  
	  private void stopPlaying(ImageButton ib, ProgressBar pb) {
	        mPlayer.release();
	        mPlayer = null;
	        ib.setImageResource(R.drawable.play);
            pb.setVisibility(View.INVISIBLE);
	  }
	  
	  
	  //clase que implementa el proceso de reproducción
	  private class PlayTask extends AsyncTask<Void, Void, Void> {
		     private String path;
		     private ImageButton imb;
		     private ProgressBar prb;
		     private int nitem;
		     
		     public PlayTask(String p,ImageButton ib, ProgressBar pb, int n){
		    	 path=p;
		    	 imb=ib;
		    	 prb=pb;
		    	 nitem=n;
		    	
		     }
		  	
		  	 protected void onPreExecute(){
		  		
		  		 startPlaying(path,imb,prb);
		  		 
		  	 }
		   

		  

		  	 @Override
		     protected void  onPostExecute(Void result) {
		    	 stopPlaying(imb,prb);
		    	 freeplayer=true;
		    	 q.remove();
		     
		      
		    }
		     
		    @Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
		    	if(mPlayer!=null){
					 while(mPlayer.isPlaying()){
			    		 if (isCancelled()){
			    			 break;
			    		 }
			    		 
			    	 }
		    	}
				return null;
			}
			
			protected void onCancelled(){
				stopPlaying(imb,prb);
				
				q.remove();
				if(itemCancelador!=nitem){			 
					freeplayer=false;
					q.add(new PlayTask(mItems.get(itemCancelador).getPath(),ibNextAudio ,pNextAudio,itemCancelador));
					q.peek().execute();//se reproduce el item seleccionado
					
				}
				else
					freeplayer=true;
				
				
				
			}

			
			
			
		 }


}
*/