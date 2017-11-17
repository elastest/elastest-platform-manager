package io.elastest.epm.pop.adapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.kamranzafar.jtar.TarEntry;
import org.kamranzafar.jtar.TarOutputStream;
import org.springframework.web.multipart.MultipartFile;

public class Utils {

  public static File convert(MultipartFile file) throws IOException {
    File convFile = new File(file.getOriginalFilename());
    convFile.createNewFile();
    FileOutputStream fos = new FileOutputStream(convFile);
    fos.write(file.getBytes());
    fos.close();
    return convFile;
  }

  public static File compressFileToTar(File file) throws IOException {
    File temp = File.createTempFile("archive", ".tar");
    temp.deleteOnExit();
    FileOutputStream fileOutputStream = new FileOutputStream(temp);
    TarOutputStream out = new TarOutputStream(new BufferedOutputStream(fileOutputStream));

    // Files to tar
    File[] filesToTar = new File[1];
    filesToTar[0] = file;

    for (File f : filesToTar) {
      out.putNextEntry(new TarEntry(f, f.getName()));
      BufferedInputStream origin = new BufferedInputStream(new FileInputStream(f));

      int count;
      byte data[] = new byte[2048];
      while ((count = origin.read(data)) != -1) {
        out.write(data, 0, count);
      }

      out.flush();
      origin.close();
    }

    out.close();
    fileOutputStream.close();
    return temp;
  }
}
