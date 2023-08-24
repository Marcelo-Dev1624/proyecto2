import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author laboratorio
 */
@ManagedBean(name = "documentosController")
@SessionScoped
public class documentosController {

    private UploadedFile originalImageFile;
    public String categoriaDoc;
    public String rutaArchivo;

    public void selectCategoria() {
    
        
        
        
        switch (categoriaDoc) {
            case "1":
                {
                    rutaArchivo = "C:\\Users\\Kari\\OneDrive\\Escritorio\\Proyecto_Ing_Soft2_certificaciones\\";
                    System.out.println("Categoria seleccionada con exito 1");

                    break;
                }
            case "2":
                {
                    rutaArchivo = "C:\\Users\\Kari\\OneDrive\\Escritorio\\Proyecto_Ing_Soft2_titulos\\";
                    System.out.println("Categoria seleccionada con exito 2");
                    break;
                }
            case "3":
                {
                    System.out.println("Categoria seleccionada con exito 3");
                    rutaArchivo = "C:\\Users\\Kari\\OneDrive\\Escritorio\\Proyecto_Ing_Soft2_curriculum\\";
                    break;
                }
            case "4":
                {
                    System.out.println("Categoria seleccionada con exito 4");
                    rutaArchivo = "C:\\Users\\Kari\\OneDrive\\Escritorio\\Proyecto_Ing_Soft2_cartaMotivacion";
                    break;
                }
            default:
                break;
        }
        
        
        
    }

    public String getCategoriaDoc() {
        return categoriaDoc;
    }

    public void setCategoriaDoc(String categoriaDoc) {
        this.categoriaDoc = categoriaDoc;
    }
    
    //public void categoriaSel(){
        
      //  System.out.println("Esto es categoriaSel");
        
   // }
    public void handleFileUpload(FileUploadEvent event) {
        try {
            this.originalImageFile = null;
            UploadedFile file = event.getFile();
            if (file != null && file.getContent() != null && file.getContent().length > 0 && file.getFileName() != null) {
                this.originalImageFile = file;
<<<<<<< HEAD
                this.copyFileInFileSystem(file.getInputStream(), "C:\\Users\\Brenda\\Desktop\\Marcelo\\docsProyecto\\", this.originalImageFile.getFileName());
=======
                this.copyFileInFileSystem(file.getInputStream(), rutaArchivo, this.originalImageFile.getFileName());
>>>>>>> Roberto
                FacesMessage msg = new FacesMessage("Successful", this.originalImageFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void copyFileInFileSystem(InputStream input, String pathCopy, String fileName) throws FileNotFoundException, IOException {
        Path path = Paths.get(pathCopy, fileName);
        if (Files.exists(path.getParent())) {
            try {
                System.out.println("PROCESS FILE PATH===> " + path);
                Files.copy(input, path, REPLACE_EXISTING);
                //Insertar en la base de datos..
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //IOUtils.closeQuietly(input);
                //IOUtils.closeQuietly(output);
            }
        } else {
            try {
                System.out.println("PROCESS FILE PATH===> " + path);
                Files.createDirectories(path.getParent());
                try {
                    Files.copy(input, path, REPLACE_EXISTING);
                    //Insertar en la base de datos..
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //IOUtils.closeQuietly(input);
                    //IOUtils.closeQuietly(output);
                }
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }
    }
    
}