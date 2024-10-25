package com.inovisoft.backend_miatraining.logic.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.WriteChannel;
import com.google.cloud.storage.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.logging.Logger;



@Service
public class GoogleCloudStorageService {
    private final String bucketName = "miatraining-bucket";
    private final String projectId = "miatraining-438819";
    private final Storage storage;
    private static final Logger LOGGER = Logger.getLogger(GoogleCloudStorageService.class.getName());

    public GoogleCloudStorageService() throws IOException {
        InputStream credentialsStream = new ClassPathResource("miatraining-438819-208477c8f9d7.json").getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream)
                .createScoped("https://www.googleapis.com/auth/cloud-platform");

        this.storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build()
                .getService();
    }

    public String uploadImage(MultipartFile file, Long itemID, String filePath){
        return null;
    }

    public String uploadUserDetailPicture(MultipartFile file, Long userId) throws IOException {

        // Obtener el nombre original del archivo
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IOException("El archivo no tiene nombre.");
        }

        // Construir el nombre final del archivo: userId_fileName
        String newFileName = userId + "_" + originalFileName;

        // Definir la ruta dentro del bucket: usuarios/{ID}/detalles-usuario/
        String filePath = String.format("usuarios/%d/detalles-usuario/%s", userId, newFileName);

        // Crear la información del blob (objeto en el bucket)
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, filePath).build();

        InputStream inputStream = file.getInputStream();
        try (WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
            }
        } finally {
            inputStream.close(); // Cerrar el flujo de entrada manualmente
        }

        // Hacer que el archivo sea público
        makeFilePublic(blobInfo.getBlobId());

        // Devuelve la URL pública del archivo subido
        return getPublicUrl(filePath);
    }

    public String uploadProfilePicture(MultipartFile file, Long userId) throws IOException {
        // Validar que el archivo tenga nombre
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IOException("El archivo no tiene nombre.");
        }

        // Construir el nombre final del archivo: ID_profilePicture
        String newFileName = userId + "_profilePicture";

        // Definir la ruta dentro del bucket: usuarios/{ID}/foto/
        String filePath = String.format("usuarios/%d/%s", userId, newFileName);

        // Eliminar la imagen anterior si tiene un formato diferente
        deletePreviousProfilePicture(userId, getFileExtension(originalFileName));

        // Crear la información del blob (objeto en el bucket)
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, filePath).build();

        // Leer y subir el archivo
        try (InputStream inputStream = file.getInputStream(); WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
            }
        }

        // Hacer que el archivo sea público
        makeFilePublic(blobInfo.getBlobId());

        // Devuelve la URL pública del archivo subido
        return getPublicUrl(filePath);
    }

    public String uploadImagenEjercicio(MultipartFile file, Long exerciseID) throws IOException {
        // Validar que el archivo tenga nombre
        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null) {
            throw new IOException("El archivo no tiene nombre.");
        }

        // Construir el nombre final del archivo: ID_profilePicture
        String newFileName = exerciseID + "_vista_previa";

        // Definir la ruta dentro del bucket: usuarios/{ID}/foto/
        String filePath = String.format("ejercicios/%d/%s", exerciseID, newFileName);

        // Eliminar la imagen anterior si tiene un formato diferente
        deletePreviousProfilePicture(exerciseID, getFileExtension(originalFileName));

        // Crear la información del blob (objeto en el bucket)
        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, filePath).build();

        // Leer y subir el archivo
        try (InputStream inputStream = file.getInputStream(); WriteChannel writer = storage.writer(blobInfo)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                writer.write(ByteBuffer.wrap(buffer, 0, bytesRead));
            }
        }

        // Hacer que el archivo sea público
        makeFilePublic(blobInfo.getBlobId());

        // Devuelve la URL pública del archivo subido
        return getPublicUrl(filePath);
    }

    // Método para hacer el archivo público
    private void makeFilePublic(BlobId blobId) {
        storage.createAcl(blobId, Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
    }

    private String getPublicUrl(String blobName) {
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, blobName);
    }

    private void deletePreviousProfilePicture(Long userId, String newExtension) {
        String[] possibleExtensions = {"jpg", "png", "jpeg"}; // Lista de extensiones comunes
        for (String ext : possibleExtensions) {
            if (!ext.equals(newExtension)) { // Si la extensión es diferente a la nueva
                String oldFileName = String.format("usuarios/%d/%d_profilePicture.%s", userId, userId, ext);
                Blob blob = storage.get(BlobId.of(bucketName, oldFileName));
                if (blob != null && blob.exists()) {
                    System.out.println("Eliminando imagen anterior: " + oldFileName);
                    storage.delete(blob.getBlobId());
                }
            }
        }
    }

    public void deleteResourceByPath(String url) {
        String filePath = extractFilePathFromUrl(url);
        Blob blob = storage.get(BlobId.of(bucketName, filePath));
        if (blob != null && blob.exists()) {
            storage.delete(blob.getBlobId());
        }
    }


    private String extractFilePathFromUrl(String url) {
        // Encuentra la posición de la base URL de tu bucket y extrae el file path
        String baseUrl = "https://storage.googleapis.com/"+bucketName+"/";

        if (url.startsWith(baseUrl)) {
            return url.substring(baseUrl.length());
        }

        // Si la URL no coincide con el formato esperado, se puede lanzar una excepción o retornar null
        throw new IllegalArgumentException("URL no tiene el formato esperado");
    }


    private String getFileExtension(String fileName) {
        String[] parts = fileName.split("\\.");
        return parts[parts.length - 1]; // La última parte después del punto es la extensión
    }
}
