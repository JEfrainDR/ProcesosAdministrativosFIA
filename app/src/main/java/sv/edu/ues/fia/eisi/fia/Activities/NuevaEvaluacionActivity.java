package sv.edu.ues.fia.eisi.fia.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import sv.edu.ues.fia.eisi.fia.Adapters.RecursosAdapter;
import sv.edu.ues.fia.eisi.fia.Fragments.SetFechaDialogFragment;
import sv.edu.ues.fia.eisi.fia.Fragments.SetHoraDialogFragment;
import sv.edu.ues.fia.eisi.fia.R;
import sv.edu.ues.fia.eisi.fia.entity.Recurso;

public class NuevaEvaluacionActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_OPEN = 1;
    static final int READ_STORAGE_PERMISSION = 2;

    private TextView textFechaInicio, textFechaFin, textHoraInicio, textHoraFin;
    private ImageView imgFechainicio, imgFechaFin, imgHoraInicio, imgHoraFin, imgAdd;
    private RecyclerView recyclerRecuros;
    private LinearLayout layout_recursos;
    private List<Recurso> listRecurso;
    private RecursosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_evaluacion);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        solicitarPermisos();

        listRecurso = new ArrayList<>();

        adapter = new RecursosAdapter(listRecurso);

        textFechaInicio = findViewById(R.id.text_fecha_inicio);
        textFechaFin = findViewById(R.id.text_fecha_fin);
        textHoraInicio = findViewById(R.id.text_hora_inicio);
        textHoraFin = findViewById(R.id.text_hora_fin);
        imgFechainicio = findViewById(R.id.img_fecha_inicio);
        imgFechaFin = findViewById(R.id.img_fecha_fin);
        imgHoraInicio = findViewById(R.id.img_hora_inicio);
        imgHoraFin = findViewById(R.id.img_hora_fin);
        imgAdd = findViewById(R.id.img_icon_add);
        recyclerRecuros = findViewById(R.id.recycler_recursos);
        layout_recursos = findViewById(R.id.layout_recursos);
        recyclerRecuros.setLayoutManager(new LinearLayoutManager(NuevaEvaluacionActivity.this));
        recyclerRecuros.setAdapter(adapter);
        recyclerRecuros.setVisibility(View.INVISIBLE);

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat formatFecha = new SimpleDateFormat("yyy-MM-dd");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        formatFecha.setTimeZone(TimeZone.getDefault());
        formatHora.setTimeZone(TimeZone.getDefault());

        textFechaInicio.setText(formatFecha.format(date));
        textFechaFin.setText(formatFecha.format(date));
        textHoraInicio.setText(formatHora.format(date));
        textHoraFin.setText(formatHora.format(date));

        imgFechainicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaDialog();
            }
        });
        imgHoraInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHoraDialog();
            }
        });
        imgFechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFechaDialog();
            }
        });
        imgHoraFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHoraDialog();
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirRecurso();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_nueva_evaluacion,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setFechaDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("fecha_actual",textFechaInicio.getText().toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        SetFechaDialogFragment fechaDialogFragment = new SetFechaDialogFragment();
        fechaDialogFragment.setArguments(bundle);
        fechaDialogFragment.show(fragmentManager, "setFechaDialog");
    }

    public void setHoraDialog(){
        Bundle bundle = new Bundle();
        bundle.putString("hora_actual",textHoraInicio.getText().toString());

        FragmentManager fragmentManager = getSupportFragmentManager();
        SetHoraDialogFragment horaDialogFragment = new SetHoraDialogFragment();
        horaDialogFragment.setArguments(bundle);
        horaDialogFragment.show(fragmentManager, "setHoraDialog");
    }

    private void añadirRecurso(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
                Uri fullPhotoUri = data.getData();
                if(recyclerRecuros.getVisibility() == View.INVISIBLE){
                    recyclerRecuros.setVisibility(View.VISIBLE);
                }
                // Do work with full size photo saved at fullPhotoUri
                //Toast.makeText(this,getFileName(getPathMethod(this, fullPhotoUri)),Toast.LENGTH_SHORT).show();
                String ruta = getPathMethod(this, fullPhotoUri);
                String nombre = getFileName(ruta);
                int icon_res=0;
                if(nombre.endsWith("jpg") || nombre.endsWith("png") || nombre.endsWith("gif")){
                    icon_res = R.drawable.ic_baseline_image_24;
                } else if(nombre.endsWith("pdf") || nombre.endsWith("docx") || nombre.endsWith("txt") || nombre.endsWith("xlsx") || nombre.endsWith("pptx")){
                    icon_res = R.drawable.ic_baseline_picture_as_pdf_24;
                } else if(nombre.endsWith("mp4") || nombre.endsWith("mkv")){
                    icon_res = R.drawable.ic_baseline_video_library_24;
                } else if(nombre.endsWith("rar")){
                    icon_res = R.drawable.ic_baseline_table_rows_24;
                } else {
                    icon_res = R.drawable.ic_baseline_archive_24;
                }
                File file = new File(ruta);
                int file_size = Integer.parseInt(String.valueOf(file.length()/1024));
                String tamaño = file_size +" KB";
                if(file_size > 1000){
                    file_size /= 1024;
                    tamaño = file_size + " MB";
                    if(file_size > 1000){
                        file_size /= 1024;
                        tamaño = file_size + " GB";
                    }
                }
                Recurso recurso = new Recurso(nombre,tamaño,ruta,icon_res);
                listRecurso.add(recurso);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    public static String getPathMethod(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // DocumentProvider
            if (DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        //Almacenamiento interno
                        return "/storage/emulated/0/"+split[1];
                        //return Environment.getExternalStorageDirectory()+"/"+split[1];
                    }else{
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {
                    String id = DocumentsContract.getDocumentId(uri),path="",result="";
                    Cursor cursor = null;
                    try {
                        cursor = context.getContentResolver().query(uri, new String[]{MediaStore.MediaColumns.DISPLAY_NAME}, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            String fileName = cursor.getString(0);
                            path = Environment.getExternalStorageDirectory().toString() + "/Download/" + fileName;
                            if (!TextUtils.isEmpty(path)) {
                                return path;
                            }
                        }
                    } finally {
                        if (cursor != null)
                            cursor.close();
                    }
                    try {
                        final Uri contentUri = ContentUris.withAppendedId(
                                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                        return getDataColumn(context, contentUri, null, null);
                    } catch (NumberFormatException e) {
                        return uri.getPath().replaceFirst("^/document/raw:", "").replaceFirst("^raw:", "");
                    }
                }
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];
                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[]{split[1]};
                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            if (isGooglePhotosUri(uri)) {
                String contentPath = getContentFromSegments(uri.getPathSegments());
                if (contentPath != "") {
                    return getPathMethod(context, Uri.parse(contentPath));
                } else {
                    return null;
                }
            }

            if (isGoogleDriveUri(uri)) {
                return getDriveFilePath(uri, context);
            }
            return getDataColumn(context, uri, null, null);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    //A través de este método obtenemos la ruta original del documento consultando al proveedor de contenido correspondiente.
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    /*Se valida que la ruta corresponda a cualquiera de estos proveedores de contenido y se devuelve el contenedor padre de este*/
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    private static boolean isGoogleDriveUri(Uri uri) {
        return "com.google.android.apps.docs.storage".equals(uri.getAuthority()) || "com.google.android.apps.docs.storage.legacy".equals(uri.getAuthority());
    }
    private static String getContentFromSegments(List<String> segments) {
        String contentPath = "";
        for (String item : segments) {
            if (item.startsWith("content://")) {
                contentPath = item;
                break;
            }
        }
        return contentPath;
    }
    private static String getDriveFilePath(Uri uri, Context context) {
        Uri returnUri = uri;
        Cursor returnCursor = context.getContentResolver().query(returnUri, null, null, null, null);
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = (returnCursor.getString(nameIndex));
        File file = new File(context.getCacheDir(), name);
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            FileOutputStream outputStream = new FileOutputStream(file);
            int read = 0;
            int maxBufferSize = 1 * 1024 * 1024;
            int bytesAvailable = inputStream.available();
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);

            final byte[] buffers = new byte[bufferSize];
            while ((read = inputStream.read(buffers)) != -1) {
                outputStream.write(buffers, 0, read);
            }
            Log.e("File Size", "Size " + file.length());
            inputStream.close();
            outputStream.close();
            Log.e("File Path", "Path " + file.getPath());
            Log.e("File Size", "Size " + file.length());
        } catch (Exception e) {
            Log.e("Exception", e.getMessage());
        }
        return file.getPath();
    }

    //Con este metodo obtenemos el nombre del documento dada la ruta especifica donde este se encuentra.
    private String getFileName(String filePath){
        String[] path=filePath.split("/");
        String name;
        int position=0;
        position=path.length-1;
        name=path[position];
        return name;
    }

    private void solicitarPermisos() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(NuevaEvaluacionActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    READ_STORAGE_PERMISSION);
        }
    }

}