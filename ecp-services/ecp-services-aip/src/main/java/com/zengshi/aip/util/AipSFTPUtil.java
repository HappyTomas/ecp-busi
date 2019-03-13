package com.zengshi.aip.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import com.zengshi.ecp.aip.dao.model.FileServerConfig;
import com.zengshi.ecp.aip.dao.model.FileUploadRecord;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class AipSFTPUtil {
	private String host;// sftp服务器ip
	private String username;// 用户名
	private String password;// 密码
	private String privateKey;// 密钥文件路径
	private String passphrase;// 密钥口令
	private int port = 22;// 默认的sftp端口号是22
	private static final short OPTR_DOWLOAD = 2;// 下载
	
	private List<FileUploadRecord> recordList = null;
	
	public AipSFTPUtil (List<FileUploadRecord> records) {
		if (records == null)
			records = new ArrayList<FileUploadRecord>();
		recordList = records;
	}

	/**
	 * 获取连接
	 * 
	 * @return channel
	 * @throws JSchException 
	 */
	public ChannelSftp connectSFTP(FileServerConfig localFileServerConfig) throws JSchException {
		return connectSFTP(localFileServerConfig.getServerIp(), localFileServerConfig.getServerPort(), localFileServerConfig.getUserName(), localFileServerConfig.getPassword());
	}
	
	public ChannelSftp connectSFTP(String serverHost, int serverPort, String userName, String passWord) throws JSchException {
		this.host = serverHost;
		this.port = serverPort;
		this.username = userName;
		this.password = passWord;
		JSch jsch = new JSch();
		Channel channel = null;
		if (privateKey != null && !"".equals(privateKey)) {
			// 使用密钥验证方式，密钥可以使有口令的密钥，也可以是没有口令的密钥
			if (passphrase != null && "".equals(passphrase)) {
				jsch.addIdentity(privateKey, passphrase);
			} else {
				jsch.addIdentity(privateKey);
			}
		}
		Session session = jsch.getSession(username, host, port);
		if (password != null && !"".equals(password)) {
			session.setPassword(password);
		}
		Properties sshConfig = new Properties();
		sshConfig.put("StrictHostKeyChecking", "no");// do not verify host
														// key
		session.setConfig(sshConfig);
		// session.setTimeout(timeout);
		session.setServerAliveInterval(92000);
		session.connect();
		// 参数sftp指明要打开的连接是sftp连接
		channel = session.openChannel("sftp");
		channel.connect();
		return (ChannelSftp) channel;
	}
	/**
	 * 上传文件
	 * 
	 * @param directory
	 *            上传的目录
	 * @param uploadFile
	 *            要上传的文件
	 * @param sftp
	 * @throws SftpException 
	 * @throws FileNotFoundException 
	 */
	public void upload(String directory, String uploadFile, ChannelSftp sftp) throws SftpException, FileNotFoundException {
		sftp.cd(directory);
		File file = new File(uploadFile);
		sftp.put(new FileInputStream(file), file.getName());
	}

	/**
	 * 下载文件
	 * 
	 * @param directory
	 *            下载目录
	 * @param downloadFile
	 *            下载的文件
	 * @param saveFile
	 *            存在本地的路径
	 * @param sftp
	 * @throws SftpException 
	 */
	public void download(String directory, String downloadFile,
			String saveFile, ChannelSftp sftp) throws SftpException {
			sftp.cd(directory);
			sftp.get(downloadFile, saveFile);
	}
	
	/**
	 * 批量下载 符合regex正规表达式
	 * @param sftp
	 * @param directory
	 * @param downloadFile
	 * @param regex 
	 * @throws SftpException
	 */
	@SuppressWarnings("unchecked")
	public void batchDownload(int userId, ChannelSftp sftp, String remoteFileName,String regex, FileServerConfig remoteFileServerConfig, FileServerConfig localFileServerConfig) throws SftpException{
		String remotePath = remoteFileServerConfig.getRootPath() + "/" + remoteFileName;
		//String localPath = localFileServerConfig.getRootPath();
		sftp.cd(remotePath);
		Vector files = sftp.ls(remotePath);
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			String infoStr = ((Object) iterator.next()).toString();
			String fileName = infoStr.substring(infoStr.lastIndexOf(" ") + 1);
			if(fileName.matches(regex)){
				System.out.println("===================================");
				//sftp.get(fileName, localPath+File.separator+fileName);
				FileUploadRecord record = new FileUploadRecord();
				record.setFileId(fileName);
				record.setFileName(fileName);
				record.setFilePath(remotePath);
				record.setFileServer(remoteFileServerConfig.getServerIp());
				record.setOptrType(OPTR_DOWLOAD);
				record.setUserId(new Integer(userId));
				record.setOptrDate(new Timestamp(new Date().getTime()));
				recordList.add(record);
				//System.out.println("download file from " + remotePath+"/" +fileName + " to " + localPath+fileName + " success!");
			} else {
			}
		}
	}
	
	/**
	 * 批量下载 符合regex正规表达式
	 * @param sftp
	 * @param directory
	 * @param downloadFile
	 * @param regex 
	 * @throws SftpException
	 */
	@SuppressWarnings("unchecked")
	public List<String> batchDownloadWithLocalFiles(ChannelSftp sftp,String localPath,String remotePath,String regex) throws SftpException{
		List<String> fileset= new ArrayList<String>();
		sftp.cd(remotePath);
		Vector files = sftp.ls(remotePath);
		for (Iterator iterator = files.iterator(); iterator.hasNext();) {
			String infoStr = ((Object) iterator.next()).toString();
			String fileName = infoStr.substring(infoStr.lastIndexOf(" ") + 1);
			if(fileName.matches(regex)){
				System.out.println("===================================");
				String fn=localPath+File.separator+fileName;
				sftp.get(fileName, fn);
				fileset.add(fn);
				System.out.println("download file from " + remotePath+"/" +fileName + " to " + localPath+fileName + " success!");
			} else {
			}
		}
		return fileset;
	}

	/**
	 * 删除文件
	 * 
	 * @param directory
	 *            要删除文件所在目录
	 * @param deleteFile
	 *            要删除的文件
	 * @param sftp
	 * @throws SftpException 
	 */
	public void delete(String directory, String deleteFile, ChannelSftp sftp) throws SftpException {
		sftp.cd(directory);
		sftp.rm(deleteFile);
	}

	public void disconnected(ChannelSftp sftp) throws JSchException {
		if (sftp != null) {
			sftp.getSession().disconnect();
			sftp.disconnect();
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<FileUploadRecord> records = new ArrayList<FileUploadRecord>();
		AipSFTPUtil sftpClient = new AipSFTPUtil(records);
		FileServerConfig remoteFileServerConfig = new FileServerConfig();
		remoteFileServerConfig.setRootPath("/uniiof/ftpusers/ftpshcp01");
		remoteFileServerConfig.setServerIp("133.0.192.193");
		remoteFileServerConfig.setUserName("ftpshcp01");
		remoteFileServerConfig.setPassword("Linuxshcp_4321");
		remoteFileServerConfig.setServerPort(22);
		ChannelSftp sftp = sftpClient.connectSFTP(remoteFileServerConfig);
		FileServerConfig localFileServerConfig = new FileServerConfig();
		localFileServerConfig.setRootPath("D:\\");
		localFileServerConfig.setServerIp("127.0.0.1");
//		sftpClient.download("/uniapp/tstusers/tstweg01", "404.jpg", "\\web\\file-temp\\404.jpg", sftp);
//		sftpClient.upload("/uniapp/tstusers/tstweg01/logs", "D:\\setup.log", sftp);
//		sftpClient.upload("/uniapp/tstusers/tstweg01/logs", "C:\\Users\\yafei\\Documents\\CommissionJob.class", sftp);
//		sftpClient.delete("/uniapp/tstusers/tstweg01/logs", "setup.log", sftp);
		sftpClient.batchDownload(1, sftp, "INCOMING", "GXUnicom_20141013_201410"+"[0-9]{8}.txt", remoteFileServerConfig, localFileServerConfig);
		sftpClient.disconnected(sftp);
		
	}
}
