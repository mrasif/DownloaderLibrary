# DownloaderLibrary
A simple android Downloader Library. It is very easy to use, to use this library follow these steps.
## For Gradle:
Step 1. Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2. Add the dependency:
```
dependencies {
        compile 'com.github.mrasif:DownloaderLibrary:v1.0.0'
}
```
### For Maven:
Step 1. Add the JitPack repository to your build file:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
Step 2. Add the dependency:
```
<dependency>
    <groupId>com.github.mrasif</groupId>
    <artifactId>DownloaderLibrary</artifactId>
    <version>v1.0.0</version>
</dependency>
```
### For SBT:
Step 1. Add the JitPack repository to your build.sbt file:
```
resolvers += "jitpack" at "https://jitpack.io"
```
Step 2. Add the dependency:
```
libraryDependencies += "com.github.mrasif" % "DownloaderLibrary" % "v1.0.0"
```
### For Leiningen:
Step 1. Add it in your project.clj at the end of repositories:
```
:repositories [["jitpack" "https://jitpack.io"]]
```
Step 2. Add the dependency:
```
:dependencies [[com.github.mrasif/DownloaderLibrary "v1.0.0"]]
```

### Add this in your layout xml file:
```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="center"
    tools:context=".MainActivity"
    android:orientation="vertical">

    <TextView
        android:id="@+id/tvProgress"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="0"/>
    <Button
        android:id="@+id/btnDownloadNow"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Download Now"/>

</LinearLayout>
```

### Add this in your activity java files:
```
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

```

You are done.