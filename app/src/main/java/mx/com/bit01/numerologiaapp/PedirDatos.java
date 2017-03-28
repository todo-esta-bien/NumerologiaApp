package mx.com.bit01.numerologiaapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;

public class PedirDatos extends AppCompatActivity {

    int dia_x, mes_x, anio_x;
    static final int DIALOG_ID=0;
    Button btnFecha;
    TextView lblFechaG;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedir_datos);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        final String[] numsOpt = {getResources().getString(R.string.MenuNum_Alma), getResources().getString(R.string.MenuNum_Karma), getResources().getString(R.string.MenuNum_Talento)
                , getResources().getString(R.string.MenuNum_Destino), getResources().getString(R.string.MenuNum_Camino), getResources().getString(R.string.MenuNum_EdadCosmica)
                , getResources().getString(R.string.MenuNum_Personalidad), getResources().getString(R.string.MenuNum_Nombre), getResources().getString(R.string.MenuNum_Activo)
                , getResources().getString(R.string.MenuNum_Hereditario), getResources().getString(R.string.MenuNum_Actitud), getResources().getString(R.string.MenuNum_Realidad)
                , getResources().getString(R.string.MenuNum_AnioPersonal), getResources().getString(R.string.MenuNum_Pinaculo), getResources().getString(R.string.MenuNum_Sombra)
                , getResources().getString(R.string.MenuNum_Madurez), getResources().getString(R.string.MenuNum_Compatibilidad)};

        showDialogOnButtonClick();

        String tipoNum = getIntent().getExtras().getString("num");

        TextView labelTst = (TextView) findViewById(R.id.lblNum);
        labelTst.setText(tipoNum);


        if(tipoNum.equalsIgnoreCase(numsOpt[0]) || tipoNum.equalsIgnoreCase(numsOpt[1]) || tipoNum.equalsIgnoreCase(numsOpt[2])
                || tipoNum.equalsIgnoreCase(numsOpt[3]) || tipoNum.equalsIgnoreCase(numsOpt[4]) || tipoNum.equalsIgnoreCase(numsOpt[5])
                || tipoNum.equalsIgnoreCase(numsOpt[10]) || tipoNum.equalsIgnoreCase(numsOpt[12])){ //Display only Date

            hideName();

        }else if(tipoNum.equalsIgnoreCase(numsOpt[6]) || tipoNum.equalsIgnoreCase(numsOpt[7]) || tipoNum.equalsIgnoreCase(numsOpt[8]) || tipoNum.equalsIgnoreCase(numsOpt[9]) ){ //Display only Name

            hideDate();

        }else{//Display Name and Date



        }

        Button btnCalcular = (Button) findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checarCambio()){
                    showInterstitial();
                }else{

                    Snackbar snackbar = Snackbar
                            .make(v, getResources().getString(R.string.PedirDatos_Error_Calcular), Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });


    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                irAResultados();
            }
        });
        return interstitialAd;
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void irAResultados() {
        // Show the next level and reload the ad to prepare for the level after.
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
        TextView lblNum = (TextView)findViewById(R.id.lblNum);
        Intent intent = new Intent (this, Resultados.class);
        intent.putExtra("numText", lblNum.getText().toString());
        intent.putExtra("numValue", calcularNumero());
        startActivity(intent);
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            irAResultados();
        }
    }

    public void showDialogOnButtonClick(){

        btnFecha = (Button) findViewById(R.id.btnFecha);

        btnFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_ID);
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id){

        final Calendar c=Calendar.getInstance();

        dia_x = c.get(Calendar.DAY_OF_MONTH);
        mes_x = c.get(Calendar.MONTH);
        anio_x = c.get(Calendar.YEAR);

        if(id == DIALOG_ID)
            return new DatePickerDialog(this, dPickerListener, anio_x, mes_x, dia_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            lblFechaG = (TextView) findViewById(R.id.lblFecha);

            anio_x=year;
            mes_x=month+1;
            dia_x=dayOfMonth;

            lblFechaG.setText(dia_x+"/"+mes_x+"/"+anio_x);

        }
    };

    public boolean checarCambio(){

        TextView lblFecha = (TextView)findViewById(R.id.lblFecha);
        EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        EditText apellido = (EditText) findViewById(R.id.editTextApellido);

        boolean resultado = false;

        if((lblFecha.getVisibility() == View.VISIBLE) &&
                !lblFecha.getText().toString().equals(getResources().getString(R.string.PedirDatos_fechaNo))){

            resultado = true;

        }

        if((!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty()) && (nombre.getVisibility() == View.VISIBLE)){

            resultado = true;

        }

        return resultado;

    }

    public int calcularNumero(){

        final String[] numsOpt = {getResources().getString(R.string.MenuNum_Alma), getResources().getString(R.string.MenuNum_Karma), getResources().getString(R.string.MenuNum_Talento)
                , getResources().getString(R.string.MenuNum_Destino), getResources().getString(R.string.MenuNum_Camino), getResources().getString(R.string.MenuNum_EdadCosmica)
                , getResources().getString(R.string.MenuNum_Personalidad), getResources().getString(R.string.MenuNum_Nombre), getResources().getString(R.string.MenuNum_Activo)
                , getResources().getString(R.string.MenuNum_Hereditario), getResources().getString(R.string.MenuNum_Actitud), getResources().getString(R.string.MenuNum_Realidad)
                , getResources().getString(R.string.MenuNum_AnioPersonal), getResources().getString(R.string.MenuNum_Pinaculo), getResources().getString(R.string.MenuNum_Sombra)
                , getResources().getString(R.string.MenuNum_Madurez), getResources().getString(R.string.MenuNum_Compatibilidad)};

        TextView labelTst = (TextView) findViewById(R.id.lblNum);

        EditText nombreET = (EditText)findViewById(R.id.editTextNombre);
        EditText apellidoET = (EditText)findViewById(R.id.editTextApellido);

        String nombre = nombreET.getText().toString();
        String apellido = apellidoET.getText().toString();

        int resultado=0;

        if(numsOpt[0].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularAlma(dia_x);
        }else if(numsOpt[1].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularKarma(mes_x);
        }else if(numsOpt[2].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularTalento(anio_x);
        }else if(numsOpt[3].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularDestino(anio_x);
        }else if(numsOpt[4].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularCamino(dia_x, mes_x, anio_x);
        }else if(numsOpt[5].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularEdadCosmica(dia_x, mes_x, anio_x);
        }else if(numsOpt[6].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularPersonalidad(nombre, apellido);
        }else if(numsOpt[7].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularNombre(nombre, apellido);
        }else if(numsOpt[8].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularActivo(nombre);
        }else if(numsOpt[9].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularHereditario(apellido);
        }else if(numsOpt[10].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularActitud(mes_x,anio_x);
        }else if(numsOpt[11].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularRealidad(nombre, apellido, dia_x,mes_x, anio_x);
        }else if(numsOpt[12].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularAnioPersonal(dia_x, mes_x, anio_x);
        }else if(numsOpt[13].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularPinaculos();
        }else if(numsOpt[14].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularSombra();
        }else if(numsOpt[15].equalsIgnoreCase(labelTst.getText().toString())){
            resultado=calcularMadurez(nombre, apellido, dia_x, mes_x, anio_x);
        }else if(numsOpt[16].equalsIgnoreCase(labelTst.getText().toString())){
            //Aquí va la compatibilidad
        }

        return resultado;

    }

    public void hideName(){

        TextView[] labelsNombre = {(TextView)findViewById(R.id.lblNombreTxt), (TextView)findViewById(R.id.lblApellidoTxt)};
        EditText[] editTextNombre = {(EditText) findViewById(R.id.editTextNombre), (EditText) findViewById(R.id.editTextApellido)};

        for(int i=0;i<labelsNombre.length;i++){

            labelsNombre[i].setVisibility(View.GONE);
            editTextNombre[i].setVisibility(View.GONE);

        }

    }

    public void hideDate(){

        TextView lblFechaTxt = (TextView) findViewById(R.id.lblFechaTxt);
        TextView lblFecha = (TextView) findViewById(R.id.lblFecha);
        Button btnFecha = (Button) findViewById(R.id.btnFecha);

        lblFecha.setVisibility(View.GONE);
        lblFechaTxt.setVisibility(View.GONE);
        btnFecha.setVisibility(View.GONE);

    }

    public int calcularAlma(int dia){

        return sumarTodos(dia+"", false);

    }

    public int calcularKarma(int mes){

        return sumarTodos(mes+"", false);

    }

    public int calcularTalento(int anio){

        String anioS = anio+"";

        return sumarTodos((anioS.charAt(anioS.length()-2))+""+anioS.charAt(anioS.length()-1), true);//se trata de abracar años con menos de 4 digitos

    }

    public int calcularDestino(int anio){

        return sumarTodos(anio+"", true);

    }

    public int calcularCamino(int dia, int mes, int anio){

        String todaFecha = dia+""+mes+""+anio;

        return sumarTodos(todaFecha, true);

    }

    public int calcularEdadCosmica(int dia, int mes, int anio){


        String todaFecha = dia+""+mes+""+anio;

        String sumaTmp=todaFecha;

        int sumaTmpInt = 0;

        int[] digs = new int[sumaTmp.length()];

        sumaTmpInt = 0;

        for(int i=0;i<sumaTmp.length();i++){

            digs[i]=Character.getNumericValue(sumaTmp.charAt(i));
            sumaTmpInt=sumaTmpInt+digs[i];

        }

        return sumaTmpInt*1000;

    }

    public int calcularPersonalidad(String nombres, String apellidos){

        String nombreComp = quitarAcentos(nombres+""+apellidos).toLowerCase().trim().replaceAll("\\s","");
        return sumarTodos(sumarConsonantesPita(nombreComp)+"",true);

    }

    public int calcularNombre(String nombres, String apellidos){

        String nombreComp = quitarAcentos(nombres+""+apellidos).toLowerCase().trim().replaceAll("\\s","");
        return sumarTodos((sumarVocalesPita(nombreComp)+sumarConsonantesPita(nombreComp))+"",true);

    }

    public int calcularActivo(String nombres){

        String nombre = quitarAcentos(nombres).toLowerCase().trim().replaceAll("\\s","");
        return sumarTodos((sumarVocalesPita(nombre)+sumarConsonantesPita(nombre))+"",true);

    }

    public int calcularHereditario(String apellidos){

        String apellido = quitarAcentos(apellidos).toLowerCase().trim().replaceAll("\\s","");
        return sumarTodos((sumarVocalesPita(apellido)+sumarConsonantesPita(apellido))+"",true);

    }

    public int calcularActitud(int mes, int anio){

        String fecha=mes+""+anio;
        return sumarTodos(fecha, false);

    }

    public int calcularRealidad(String nombres, String apellidos, int dia, int mes, int anio){

        return sumarTodos((calcularNombre(nombres, apellidos)+calcularCamino(dia, mes, anio))+"",false);

    }

    public int calcularAnioPersonal(int dia, int mes, int anio){

        String cumple = dia+""+mes+""+anio;
        return sumarTodos(cumple, false);

    }

    public int calcularPinaculos(){

        return 0;

    }

    public int calcularSombra(){

        return 0;

    }

    public int calcularMadurez(String nombres, String apellidos, int dia, int mes, int anio){

        return sumarTodos((calcularCamino(dia, mes, anio)+calcularNombre(nombres, apellidos))+"", false);

    }

    public int sumarVocalesPita(String str){

        int sumaC = 0;

        for(int i=0;i<str.length();i++){

            if(esVocal(str.charAt(i))){

                sumaC = sumaC + valorLetraPita(str.charAt(i));

            }

        }

        return sumaC;

    }

    public int sumarConsonantesPita(String str){

        int sumaC = 0;

        for(int i=0;i<str.length();i++){

            if(!esVocal(str.charAt(i))){

                sumaC = sumaC + valorLetraPita(str.charAt(i));

            }

        }

        return sumaC;

    }

    public boolean esVocal(char c){
        if((Character.toLowerCase(c)=='a') || (Character.toLowerCase(c)=='e') || (Character.toLowerCase(c)=='i') || (Character.toLowerCase(c)=='o') || (Character.toLowerCase(c)=='u'))
            return true;
        else
            return false;
    }

    public int valorLetraPita(char c){

        int valor = 0;

        switch(c){

            case 'a':
            case 'j':
            case 's':
                valor=1;
                break;

            case 'b':
            case 'k':
            case 't':
                valor=2;
                break;

            case 'c':
            case 'l':
            case 'u':
                valor=3;
                break;

            case 'd':
            case 'm':
            case 'v':
                valor=4;
                break;

            case 'e':
            case 'n':
            case 'w':
                valor=5;
                break;

            case 'f':
            case 'o':
            case 'x':
                valor=6;
                break;

            case 'g':
            case 'p':
            case 'y':
                valor=7;
                break;

            case 'h':
            case 'q':
            case 'z':
                valor=8;
                break;

            case 'i':
            case 'r':
                valor=9;
                break;
        }

        return valor;

    }

    public String quitarAcentos(String str){

        String proc = java.text.Normalizer.normalize(str,java.text.Normalizer.Form.NFD);
        StringBuilder sb = new StringBuilder();
        for (char c : proc.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.BASIC_LATIN) {
                sb.append(c);
            }
        }

        return sb.toString();

    }

    public int sumarTodos(String numero, boolean pararSiMaestro){

        String sumaTmp=numero;

        int sumaTmpInt = 0;

        int[] digs = new int[sumaTmp.length()];

        boolean mayorADiez=true, esMaestro=false;

        if(pararSiMaestro){

            while(mayorADiez && !esMaestro){

                sumaTmpInt = 0;

                for(int i=0;i<sumaTmp.length();i++){

                    digs[i]=Character.getNumericValue(sumaTmp.charAt(i));
                    sumaTmpInt=sumaTmpInt+digs[i];

                }

                sumaTmp = (sumaTmpInt)+"";

                esMaestro = esMaestro(sumaTmp);

                if(sumaTmpInt<10){

                    mayorADiez = false;

                }

            }

            return sumaTmpInt;

        }else{

            while(mayorADiez){

                sumaTmpInt = 0;

                for(int i=0;i<sumaTmp.length();i++){

                    digs[i]=Character.getNumericValue(sumaTmp.charAt(i));
                    sumaTmpInt=sumaTmpInt+digs[i];

                }

                sumaTmp = (sumaTmpInt)+"";

                if(sumaTmpInt<10){

                    mayorADiez = false;

                }

            }

            return sumaTmpInt;

        }

    }

    public boolean esMaestro(String numero){

        boolean carry = true;

        for(int i=0;i<numero.length()-1;i++){

            carry=carry && (numero.charAt(i)==numero.charAt(i+1));

        }

        return carry;

    }

}
