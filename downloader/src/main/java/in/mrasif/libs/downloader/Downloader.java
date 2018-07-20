package in.mrasif.libs.downloader;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class Downloader extends AsyncTask<String, String, String> {

    DownloadHandler handler;
    String fileUrl;
    File filePath;

    public Downloader(DownloadHandler handler, String fileUrl, File filePath) {
        this.handler = handler;
        this.fileUrl=fileUrl;
        this.filePath=filePath;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        handler.onDownloadProgressShow();
    }

    public void start(){
        execute();
    }

    @Override
    protected String doInBackground(String... params) {
        int count;
        try {
            URL url = new URL(fileUrl);
            URLConnection conection = url.openConnection();
            conection.setRequestProperty("Accept-Encoding", "identity");
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();
//            System.out.println("###################################");
//            System.out.println("Total Length: "+lenghtOfFile);
//            System.out.println("###################################");

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file
            OutputStream output = new FileOutputStream(filePath);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress(""+(int)((total*100)/lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }

    protected void onProgressUpdate(String... progress) {
        handler.onDownloadProgressUpdate(Integer.parseInt(progress[0]));
    }

    @Override
    protected void onPostExecute(String file_url) {
        handler.onDownloadProgressDismiss(filePath);
    }

}

