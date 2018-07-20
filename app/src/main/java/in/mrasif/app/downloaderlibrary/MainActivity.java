package in.mrasif.app.downloaderlibrary;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import in.mrasif.libs.downloader.DownloadHandler;
import in.mrasif.libs.downloader.Downloader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DownloadHandler {

    Button btnDownloadNow;
    TextView tvProgress;
    Downloader downloader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDownloadNow=findViewById(R.id.btnDownloadNow);
        tvProgress=findViewById(R.id.tvProgress);
        btnDownloadNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnDownloadNow){
            startDownload();
        }
    }

    private void startDownload(){
        String url="https://www.w3schools.com/htmL/mov_bbb.mp4";
        File rootDir=new File(Environment.getExternalStorageDirectory()+"/DownloaderDemo");
        if (!rootDir.exists()){
            try{
                rootDir.mkdir();
            }catch (Exception e){}
        }
        File file=new File(rootDir+ "/"+"mov_bbb.mp4");
        downloader=new Downloader(this,url,file);
        downloader.start();
    }

    @Override
    public void onDownloadProgressShow() {
        Toast.makeText(this, "Download started.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDownloadProgressUpdate(int progress) {
        tvProgress.setText(String.valueOf(progress));
    }

    @Override
    public void onDownloadProgressDismiss(File file) {
        Toast.makeText(this, "Download finished: "+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
    }
}
