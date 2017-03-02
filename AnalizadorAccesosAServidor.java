import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class AnalizadorAccesosAServidor 
{
    private ArrayList<Acceso> numeroAccesos;
    
    
    public AnalizadorAccesosAServidor()
    {
        numeroAccesos = new ArrayList<Acceso>();
    }
    
    public void analizarArchivoDeLog(String archivoLog)
    {
        try {
            File archivo = new File(archivoLog);
            Scanner sc = new Scanner(archivo);
            while (sc.hasNextLine()) {                
   
                String entradaLog[] = sc.nextLine().split(" ");                  
                Acceso entradaActual = new Acceso(Integer.parseInt(entradaLog[0]),Integer.parseInt(entradaLog[1]),Integer.parseInt(entradaLog[2]),
                                            Integer.parseInt(entradaLog[3]),Integer.parseInt(entradaLog[4]));
                numeroAccesos.add(entradaActual);
                
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    
    public int obtenerHoraMasAccesos()
    {
        int horaActual = 0;
        int contActual = 0;
        int horaMasAccesos = -1;
        int contMasAccesos = 0;
        if(numeroAccesos.size()!=0){
            for (int cont = 0; cont < 24; cont++){
                horaActual = cont;
                for (Acceso acceso : numeroAccesos){
                    if (acceso.getHora() == horaActual){
                        contActual++;
                    }
                }

                if (contActual >= contMasAccesos){
                    contMasAccesos = contActual;
                    horaMasAccesos = horaActual;
                }

                contActual = 0;
            }
        }

        if (horaMasAccesos == -1) {
            System.out.println("Faltan datos para analizar.");
        }
        else {
            System.out.println("La hora con más accesos es: " + horaMasAccesos); 
        }
        return horaMasAccesos;
    }
    
    
}