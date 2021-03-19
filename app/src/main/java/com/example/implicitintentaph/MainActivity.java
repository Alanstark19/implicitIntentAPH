package com.example.implicitintentaph;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //campos para las varibales
//modificador de acceso//objeto EditText//nombre de la variable
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    /**
     * Mértodo onCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //nos permitirá que la parte logica se comunique con la gráfica
        //asignale a esta variable// busca en esta vista el elemento con el sig id
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);
        mShareTextEditText = findViewById(R.id.share_edittext);

    }

    /**
     * método para compartir un msj
     * @param view
     */
    public void shareText(View view) {
        //obtenemos el valor que se encuentra en el EditText con el método .get
        String txt = mShareTextEditText.getText().toString();
        //parseamos a un objeto Mime
        String mimeType ="text/plain";
        //método ShareCompact.IntentBuilder para compartir el msj
        ShareCompat.IntentBuilder
                //necesita saber donde en que activity se ejecutará
                .from(this)
                //tipo del texto
                .setType(mimeType)
                //título del intent
                .setChooserTitle("Share this text with: ")
                //se le asigna al intent el texto que ingreso el usuario
                .setText(txt)
                //iniciamos el intent
                .startChooser();
    }//fin del método shareText

    /**
     * método para abrir un sitio web en el navegador de google
     * @param view
     */
    public void openWebsite(View view) {
        //obtenemos el valor que se encuentra en el EditText con el método .get
        String url = mWebsiteEditText.getText().toString();
        //parseamos a un objeto uri
        Uri webpage = Uri.parse(url);
        //intent que nos mostrara las diferentes apps con las que se puede abrir esta url
        Intent intentUrl = new Intent(Intent.ACTION_VIEW, webpage);

        //if para saber si se puede o no realizar la peticion
        if(intentUrl.resolveActivity(getPackageManager()) != null){
            startActivity(intentUrl);
        }else{
            //de lo contrario se manda un msj
            Toast.makeText(this,"Can't handle this",Toast.LENGTH_SHORT).show();
        }
    }//fin del método openWebsite
    /**
     *método para ver mi ubicación
     * @param view
     */
    public void openLocation(View view) {
        //obtenemos el valor que se encuentra en el EditText con el método .get
        String loc = mLocationEditText.getText().toString();
        //parseamos a un objeto uri, con la cagena "geo:0,0?=" + la localización
        Uri addressUri = Uri.parse("geo:0,0?=" + loc);
        //intent que nos mostrara las diferentes apps con las que se puede abrir estas coordenadas
        Intent intentGeo = new Intent(Intent.ACTION_VIEW, addressUri);

        //if para saber si se puede o no realizar la peticion
        if(intentGeo.resolveActivity(getPackageManager()) != null){
            startActivity(intentGeo);
        }else{
            //de lo contrario se manda un msj
            Toast.makeText(this,"Can't handle this",Toast.LENGTH_SHORT).show();
        }
    }//fin del método openLocation
}//fin de la clase