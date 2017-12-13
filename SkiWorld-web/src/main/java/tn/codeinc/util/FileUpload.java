package tn.codeinc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;

import tn.codeinc.persistance.Event;
import tn.codeinc.persistance.JobApply;

public class FileUpload {

	public static String upload(List<InputPart> inputParts, String folder) {

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				String realName = getFileName(header);

				if (realName.equals("unknown")) {
					return null;
				} else {
					// convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class, null);

					byte[] bytes = IOUtils.toByteArray(inputStream);

					// constructs upload file path

					File uploadFolder = new File(folder);
					if (!uploadFolder.exists())
						uploadFolder.mkdirs();

					// writeFile(bytes, folder + "\\" + fileName + "." +
					// FilenameUtils.getExtension(realName));
					writeFile(bytes, folder + "\\" + realName);
				}
				return realName;

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		return null;
	}
	
	
	
	public static String uploadEventPic(List<InputPart> inputParts, String folder,Event ev) {

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				String realName = getFileName(header);

				if (realName.equals("unknown")) {
					return null;
				} else {
					// convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class, null);

					byte[] bytes = IOUtils.toByteArray(inputStream);

					// constructs upload file path

					File uploadFolder = new File(folder+"\\"+ev.getId());
					if (!uploadFolder.exists())
						uploadFolder.mkdirs();
					
					String extension = FilenameUtils.getExtension(realName);

					// writeFile(bytes, folder + "\\" + fileName + "." +
					// FilenameUtils.getExtension(realName))
					
					writeFile(bytes, folder + "\\"+ev.getId()+"\\" + realName);
					System.out.println("FileUpload.uploadEventPic() "+folder + "\\"+ev.getId()+"\\" + realName);
				}
				return realName;

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		return null;
	}
	
	

	private static String getFileName(MultivaluedMap<String, String> header) {

		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

		for (String filename : contentDisposition) {
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");

				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}

	private static void writeFile(byte[] content, String filename) throws IOException {
		System.out.println("FileUpload.writeFile() " + filename);
		File file = new File(filename);

		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();

	}

	public static List<String> uploadEventPictures(List<InputPart> inputParts, String folder, Event ev) {

		List<String> fileNames = new ArrayList<>();
		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				String realName = getFileName(header);

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);

				// constructs upload file path

				File uploadFolder = new File(folder + "\\" + ev.getId());
				if (!uploadFolder.exists())
					uploadFolder.mkdirs();

				// writeFile(bytes, folder + "\\" + fileName + "." +
				// FilenameUtils.getExtension(realName));
				writeFile(bytes, folder + "\\" + ev.getId() + "\\" + realName);

				fileNames.add(realName);

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
		return fileNames;
	}
	
	public static String uploadOffer(List<InputPart> inputParts, String folder,JobApply ja) {

		for (InputPart inputPart : inputParts) {

			try {

				MultivaluedMap<String, String> header = inputPart.getHeaders();
				String realName = getFileName(header);

				if(realName.equals("unknown")){
					return null;
				}
				else{
					// convert the uploaded file to inputstream
					InputStream inputStream = inputPart.getBody(InputStream.class, null);

					byte[] bytes = IOUtils.toByteArray(inputStream);

					// constructs upload file path

					File uploadFolder = new File(folder+"\\" +ja.getOffer().getId()+"\\"+ja.getClient().getId());
					if (!uploadFolder.exists())
						uploadFolder.mkdirs();

					System.out.println("FileUpload.uploadOffer() "+ folder + "\\" +ja.getOffer().getId()+"\\"+ja.getClient().getId()+"\\"+ realName);
					writeFile(bytes, folder + "\\" +ja.getOffer().getId()+"\\"+ja.getClient().getId()+"\\"+ realName);
				}
				return realName;
				
				

			} catch (IOException e) {
				e.printStackTrace();
				
			}

		}
		return null;

}
}
