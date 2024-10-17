package com.inovisoft.backend_miatraining.logic.services;

import com.google.cloud.WriteChannel;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;



@Service
public class GoogleCloudStorageService {
    private final String bucketName = "miatraining-bucket";
    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private static final Logger LOGGER = Logger.getLogger(GoogleCloudStorageService.class.getName());


    public String uploadFile(MultipartFile file) throws IOException {
        String blobName = file.getOriginalFilename();

        // Define la información del blob (nombre del archivo, bucket)
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, blobName).build();

        // Abre un canal para escribir el archivo en el bucket
        try (WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024 * 1024]; // Tamaño del buffer: 1MB para mayor eficiencia
            int bytesRead;

            // Lee el archivo en bloques y lo sube al bucket
            while ((bytesRead = file.getInputStream().read(buffer)) != -1) {
                writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
            }

        }

        // Devuelve la URL pública del archivo subido
        return getPublicUrl(blobName);
    }

    public String uploadUserDetailPicture(MultipartFile file, Long userId) throws IOException{
        // Obtener el nombre original del archivo
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new FileUploadException();
        }

        // Construir el nombre final del archivo: userId_fileName
        String newFileName = userId + "_" + originalFileName;

        // Definir la ruta dentro del bucket: usuarios/{ID}/detalles-usuario/
        String filePath = String.format("usuarios/%d/detalles-usuario/%s", userId, newFileName);

        // Crear la información del blob (objeto en el bucket)
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, filePath).build();
        // Abre un canal para escribir el archivo en el bucket
        try (WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024 * 1024]; // Tamaño del buffer: 1MB para mayor eficiencia
            int bytesRead;

            // Lee el archivo en bloques y lo sube al bucket
            while ((bytesRead = file.getInputStream().read(buffer)) != -1) {
                writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error while uploading file to Google Cloud Storage", e);
            throw e; // Lanzar la excepción para manejarla adecuadamente
        }

        // Devuelve la URL pública del archivo subido
        return getPublicUrl(filePath);
    }

    private String getPublicUrl(String blobName) {
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);
    }
}
