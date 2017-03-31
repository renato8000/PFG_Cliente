package org.petero.droidfish.qr;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Created by lordvulkan on 7/03/17.
 */

public class AsyncQRGenerator extends AsyncTask<String, Void, Bitmap> {

    public final int QR_SIZE = 1024;

    private ImageView imageView = null;
    private ProgressBar progressBar = null;

    public AsyncQRGenerator(ImageView imageView, ProgressBar progressBar){
        this.imageView = imageView;
        this.progressBar = progressBar;
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
        imageView.setImageBitmap(result);
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
    }
}
