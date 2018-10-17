package yan.study.tcfs.service;

/**
 * Created
 * User  wankunYan
 * Date  2018/8/21
 * Time  14:33
 */
public interface TCFSInterface {

    /**
     * 文件上传
     */
    int fileUpLode(String fileName);

    /**
     * 文件下载
     */
    int fileDownLoad(String fileHash, long fileSize, String fileName);

    /**
     * 文件删除
     */
    int fileDelete(String fileHash);

    /**
     * 文件复制
     */
    int fileCopy(String descAddress);

}
