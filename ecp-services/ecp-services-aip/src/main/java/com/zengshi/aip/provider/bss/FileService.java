package com.zengshi.aip.provider.bss;

import java.util.List;

import com.zengshi.ecp.aip.dao.model.FileServerConfig;


public interface FileService {
	
	/**
	 * 
	 * @param remoteUri 例如:sftp://username:password@10.10.19.4:22/home/panght/vfsdesc/20140707/test.gif
	 * @param localUri 例如:sftp://username:password@10.10.19.4:22/home/panght/vfsdesc/20140707/test.gif
	 * @throws Exception
	 */
	public void upload(String remoteUri,String localUri) throws Exception;
	public void download(String remoteUri,String localUri) throws Exception;
	public void fileOperate(String serverName,String localPath,String fileName) throws Exception;
	public void delete(String localServerName,String fileName) throws Exception;
	/**
	 * 使用sftp根据远程文件路径下载文件（不重命名）
	 * @param userId
	 * @param remoteFileName
	 * @param remoteExtName
	 * @param remoteFileServerConfig
	 * @param localFileServerConfig
	 * @throws Exception
	 */
	public void download(String userId,String remoteFileName, String remoteExtName, FileServerConfig remoteFileServerConfig,FileServerConfig localFileServerConfig) throws Exception;
	public void upload(String userId,String localFileName, String localExtName, FileServerConfig remoteFileServerConfig,FileServerConfig localFileServerConfig) throws Exception;
	/**
	 * 使用sftp根据远程文件路径进行批量文件下载
	 * @param userId
	 * @param remoteFileName
	 * @param regex
	 * @param remoteFileServerConfig
	 * @param localFileServerConfig
	 * @throws Exception
	 */
	public void batchDownloadWithSftp(String userId, String remoteFileName, String regex, FileServerConfig remoteFileServerConfig,
			FileServerConfig localFileServerConfig) throws Exception;
	
	/**
	 * 使用sftp根据远程文件路径移动到远程目标文件
	 * @param userId
	 * @param fileName	
	 * @param destName
	 * @param remoteFileServerConfig
	 * @param localFileServerConfig
	 * @throws Exception
	 */
	public void moveFile(String userId, String fileName, String destName, FileServerConfig fromFileServerConfig, 
			FileServerConfig toFileServerConfig) throws Exception;
	
	/**
	 * 使用sftp根据远程文件路径批量获取文件名列表
	 * @param remoteFileServerConfig
	 * @throws Exception
	 */
	public List<String> getFilelistBySftpUri(FileServerConfig remoteFileServerConfig) throws Exception;
	
	/**
	 * 使用sftp根据远程文件路径下载文件并重命名
	 * @param userId			操作的用户id
	 * @param remoteFileName	远程文件名
	 * @param localFileName		下载后的文件名
	 * @param remoteFileServerConfig	从哪个远程路径下载配置
	 * @param localFileServerConfig		下载到哪个服务器路径配置
	 * @throws Exception
	 */
	public void downloadAndRename(String userId, String remoteFileName, String localFileName, 
			FileServerConfig remoteFileServerConfig, FileServerConfig localFileServerConfig) throws Exception;
}
