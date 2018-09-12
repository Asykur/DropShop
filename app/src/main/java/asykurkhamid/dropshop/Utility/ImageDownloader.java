package asykurkhamid.dropshop.Utility;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

public class ImageDownloader extends AsyncTask<String, Void, Boolean> {

    public interface ImageDownloadListener {
        void onComplete(boolean isSuccess);
    }

    private ImageDownloadListener listener;

    public ImageDownloader(ImageDownloadListener listener) {
        this.listener = listener;
    }

    public ImageDownloader() { }

    @Override
    protected Boolean doInBackground(String... strings) {
        try
        {
            String url_image = strings[0];
            String[] split = url_image.split("[/]");
            String image_name = split[split.length-1].split("[?]")[0];

            Log.i("URL", "Download image from " + url_image);
            URL url = new URL(url_image);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            File folder = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES);
            if (!folder.exists()) folder.mkdirs();

           // File file = new File(folder, image_name);
            File file = new File(folder, UUID.randomUUID().toString() + ".jpg");
            InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);

            byte[] data = new byte[1024];
            int count;

            OutputStream outputStream = new FileOutputStream(file);
            while ( (count = inputStream.read(data)) != -1 ) {
                outputStream.write(data, 0, count);
            }

            inputStream.close();
            outputStream.close();
            Log.i("Download", "download image complete !");
            return true;

        } catch (IOException e) {
            Log.i("Download", e.getMessage(), e);
            return false;
        }

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (listener != null) listener.onComplete(aBoolean);
    }
}