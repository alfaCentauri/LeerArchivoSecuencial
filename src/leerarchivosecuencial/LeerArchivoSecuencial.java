/*
 * Creado el: 15/05/2019 5:27 pm.
 * Versión: 1.0.
 */
package leerarchivosecuencial;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Leer un archivo de objetos en forma secuencial con ObjectInputStream y 
 * mostrar cada registro en pantalla.
 * @author Ing. Ricardo Presilla
 * @version 1.0.
 */
public class LeerArchivoSecuencial {
    /**Almacena el archivo de entrada.*/
    private static ObjectInputStream entrada;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        abrirArchivo();
        leerRegistros();
        cerrarArchivo();
    }
    /** Permite al usuario seleccionar el archivo a abrir */
    public static void abrirArchivo(){
        try{ // abre el archivo
            entrada = new ObjectInputStream(Files.newInputStream(Paths.get("clientes.ser")));
        }catch (IOException ioException){
            System.err.println("Error al abrir el archivo.");
            System.exit(1);
        }
    }
    /** Lee el registro del archivo */
    public static void leerRegistros(){
        System.out.printf("%-10s%-15s%-14s%10s%n", "Cuenta",
        "Primer nombre", "Apellido paterno", "Saldo");
        try{
            while(true){ // itera hasta que haya una EOFException
                Cuenta registro = (Cuenta) entrada.readObject();
                // muestra el contenido del registro
                System.out.printf("%-10d%-12s%-12s%10.2f%n",
                registro.obtenerCuenta(), registro.obtenerPrimerNombre(),
                registro.obtenerApellidoPaterno(), registro.obtenerSaldo());
            }
        }catch (EOFException endOfFileException){
            System.out.printf("%No hay mas registros%n");
        }
        catch (ClassNotFoundException classNotFoundException){
            System.err.println("Tipo de objeto invalido. Terminando.");
        }
        catch (IOException ioException){
            System.err.println("Error al leer del archivo. Terminando.");
        }
    } // fin del método leerRegistros
    // cierra el archivo y termina la aplicación
    public static void cerrarArchivo(){
        try{
            if (entrada != null)
                entrada.close();
        }catch (IOException ioException){
            System.err.println("Error al cerrar el archivo. Terminando.");
            System.exit(1);
        }
    }

}
