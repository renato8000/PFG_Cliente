package org.petero.droidfish.qr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.FileProvider;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Hashtable;
/**
 * Created by lordvulkan on 7/03/17.
 */

public class AsyncQRGenerator extends AsyncTask<String, Void, Bitmap> {

    public final int QR_SIZE = 1024;

    private ImageView imageView = null;
    private ProgressBar progressBar = null;
    private MenuItem item = null;
    private ShareActionProvider shareActionProvider = null;
    private Bitmap result = null;
    private Context context= null;

    public AsyncQRGenerator(ImageView imageView, ProgressBar progressBar, MenuItem item, ShareActionProvider shareActionProvider,Context context){
        this.imageView = imageView;
        this.progressBar = progressBar;
        this.item = item;
        this.shareActionProvider = shareActionProvider;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = null;
        try {
            byteMatrix = qrCodeWriter.encode(params[0], BarcodeFormat.QR_CODE,QR_SIZE,QR_SIZE,hintMap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        int matrixWidth = byteMatrix.getWidth();
        Bitmap bitmap = Bitmap.createBitmap(matrixWidth,matrixWidth, Bitmap.Config.RGB_565);

        Rect rect = new Rect();
        for ( int i = 0;i<matrixWidth;i++){
            for(int j = 0; j< matrixWidth;j++){
                bitmap.setPixel(i,j,byteMatrix.get(i,j) ? Color.BLACK:Color.WHITE);
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        this.result = result;
        imageView.setImageBitmap(result);
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);

        // save bitmap to cache directory
        try {

            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs(); // don't forget to make the directory
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png"); // overwrites this image every time
            result.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        item.setVisible(true);
        File imagePath = new File(context.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "org.petero.droidfish.fileprovider", newFile);

        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareActionProvider.setShareIntent(shareIntent);
        }
    }
}
