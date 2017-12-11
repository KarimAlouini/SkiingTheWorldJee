package tn.codeinc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;



public class FileUpload {

	public static String upload(List<InputPart> inputParts, String folder) {

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

					File uploadFolder = new File(folder);
					if (!uploadFolder.exists())
						uploadFolder.mkdirs();

					//writeFile(bytes, folder + "\\" + fileName + "." + FilenameUtils.getExtension(realName));
					writeFile(bytes, folder + "\\" + realName);
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

}
