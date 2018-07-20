package in.mrasif.libs.downloader;

import java.io.File;

public interface DownloadHandler {

    void onDownloadProgressShow();

    /**
     *
     * @param progress int to update ui.
     */
    void onDownloadProgressUpdate(int progress);

    /**
     *
     * @param file File will return saved file path.
     */
    void onDownloadProgressDismiss(File file);
}
