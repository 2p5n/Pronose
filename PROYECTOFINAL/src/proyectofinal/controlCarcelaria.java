package proyectofinal;
//AUTOR: Ricardo Espinosa Y Juan Diego Camargo
import static java.awt.AWTEventMulticaster.add;
import java.awt.Desktop;
import java.util.*;
import java.io.*;
import static java.lang.Double.parseDouble;
import static java.lang.reflect.Array.get;
public class controlCarcelaria {
    public static void main(String[] args) {
        String archivoPrincipal = "datosCarcel.csv", archivoInfo = "informacionCarcel.txt", contraseña = null;
        String pabellon1 = "pabellon1.csv", pabellon2 = "pabellon2.csv",pabellon3 = "pabellon3.csv",pabellon4 = "pabellon4.csv",pabellon5 = "pabellon5.csv";
        String cedula = null;
        int filas = 801,filas5 = 180, filasPabe234 = 181, filasPabe1 = 81, columnas = 13, opcion = 0, seguir = 0, p1 = 1, p2 = 2, p3 = 3, p4 = 4, p5 = 5;
        int promedioPenas[] = new int[10];
        Scanner tc = new Scanner(System.in);
        String [][] principal = new String [filas][columnas];
        String [][] mpabellon1 = new String [filasPabe1][columnas];
        String [][] mpabellon2 = new String [filasPabe234][columnas];
        String [][] mpabellon3 = new String [filasPabe234][columnas];
        String [][] mpabellon4 = new String [filasPabe234][columnas];
        String [][] mpabellon5 = new String [filas5][columnas];
        do{
            leercsv(principal, archivoPrincipal);
            presentarMatriz(principal, filas, columnas);
            System.out.println(opcionesInicio());
            opcion = tc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("ABRIENDO ARCHIVO...");
                    abrirArchivoInfo(archivoInfo);
                    break;
                case 2:
                    System.out.println(opcionesBaseDatos());
                    opcion = tc.nextInt();
                    switch(opcion){
                        case 1:
                            System.out.println("ABRIENDO ARCHIVO...");
                            abrirArchivoCompleto(archivoPrincipal);
                            break;
                        case 2:
                            System.out.println(opcionesDatosPabellones());
                            opcion = tc.nextInt();
                            switch(opcion){
                                case 1:
                                    System.out.println("ABRIENDO ARCHIVO...");
                                    abrirArchivoPabellon1(pabellon1);
                                    break;
                                case 2:
                                    System.out.println("ABRIENDO ARCHIVO...");
                                    abrirArchivoPabellon2(pabellon2);
                                    break;
                                case 3:
                                    System.out.println("ABRIENDO ARCHIVO...");
                                    abrirArchivoPabellon3(pabellon3);
                                    break;
                                case 4:
                                    System.out.println("ABRIENDO ARCHIVO...");
                                    abrirArchivoPabellon4(pabellon4);
                                    break;
                                case 5:
                                    System.out.println("ABRIENDO ARCHIVO...");
                                    abrirArchivoPabellon5(pabellon5);
                                    break;
                                default:
                                    System.out.println("Opcion incorrecta");
                            }
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                    break;
                case 3:
                    System.out.println(opcionesBuscarPPL());
                    opcion = tc.nextInt();
                    switch(opcion){
                        case 1:
                            System.out.println("Ingresa la identificacion del PPL");
                            cedula = tc.next();
                            leercsv(principal, archivoPrincipal);
                            buscarPPLcedula(cedula, principal);
                            break;
                        case 2:
                            System.out.println("Ingresa la celda");
                            break;
                        default:
                            System.out.println("Opcion incorrecta");
                    }
                    break;
                case 4:
                    System.out.println("ESTADISTICAS DE LA CARCEL:");
                    leercsvPabe(mpabellon1, pabellon1);
                    leercsvPabe(mpabellon2, pabellon2);
                    leercsvPabe(mpabellon3, pabellon3);
                    leercsvPabe(mpabellon4, pabellon4);
                    leercsvPabe(mpabellon5, pabellon5);
                    promedioDePenas(filas, principal, archivoPrincipal, promedioPenas);
                    delitoMasCometido(promedioPenas);
                    promedioEdadesCompleto(principal);
                    promedioEdadesPabe1(mpabellon1, p1);
                    promedioEdadesPabe1(mpabellon2, p2);
                    promedioEdadesPabe1(mpabellon3, p3);
                    promedioEdadesPabe1(mpabellon4, p4);
                    promedioEdadesPabe1(mpabellon5, p5);
                    modaCarcelEdadesPrincipal(principal);
                    modaCarcelEdades(mpabellon1, p1);
                    modaCarcelEdades(mpabellon2, p2);
                    modaCarcelEdades(mpabellon3, p3);
                    modaCarcelEdades(mpabellon4, p4);
                    modaCarcelEdades(mpabellon5, p5);
                    break;
                case 5:
                    do{
                        System.out.println("Ingrese la clave de acceso (12345)");
                        contraseña = tc.next();
                        if(contraseña.equals("12345")){
                            System.out.println("ACCESO EXITOSO");
                            System.out.println(opcionesAdministracion());
                            opcion = tc.nextInt();
                            switch(opcion){
                                case 1:
                                    System.out.println("Celdas disponibles");
                                    break;
                                case 2:
                                    System.out.println(opcionesCastigos());
                                    opcion = tc.nextInt();
                                    switch(opcion){
                                        case 1:
                                            System.out.println("Disminuir horas de visita");
                                            break;
                                        case 2:
                                            System.out.println("Aumentar pena");
                                            break;
                                        default:
                                            System.out.println("Opcion Incorrecta");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Ingrese la identificacion del PPl a trasladar");
                                    break;
                                default:
                                    System.out.println("Opcion incorrecta");
                            }
                    }else
                        System.out.println("Clave de acceso incorrecta");
                    }
                    while(!contraseña.equals("12345"));
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    
            }
            System.out.println("DESEA CONTINUAR CON EL PROGRAMA?([1]SI/[2]NO)");
            seguir = tc.nextInt();
            if(seguir == 2)
                System.out.println("GRACIAS POR USAR ESTE PROGRAMA");
        }while(seguir == 1);
    }
    
    //Cadenas para presentar listas de opciones
    public static String opcionesInicio(){
        String cadena = "SISTEMA DE CONTROL CARCELARIA\n"
                        + "Ingrese el numero correspondiente a la opcion deseada\n"
                        + "[1] -> Ver informacion de la carcel\n"
                        + "[2] -> Ingresar a la base de datos\n"
                        + "[3] -> Buscar PPL\n"
                        + "[4] -> Observar estadisticas\n"
                        + "[5] -> Administracion (SOLO PERSONAL AUTORIZADO)";
        return cadena;
    }
    public static String opcionesBaseDatos(){
        String cadena = "Ingrese el numero correspondiente a la opcion deseada\n"
                        + "[1] -> Ver la base de datos completa\n"
                        + "[2] -> Ver la base de datos distribuida por pabellones";
        return cadena;
    }
    public static String opcionesDatosPabellones(){
        String cadena = "Ingrese el numero correspondiente al pabellon que desea verificar\n"
                        + "[1] -> Pabellon 1\n"
                        + "[2] -> Pabellon 2\n"
                        + "[3] -> Pabellon 3\n"
                        + "[4] -> Pabellon 4\n"
                        + "[5] -> Pabellon 5";
        return cadena;
    }
    public static String opcionesBuscarPPL(){
        String cadena = "Ingrese el numero correspondiente a la opcion deseada\n"
                        + "[1] -> Buscar PPl por su identificacion\n"
                        + "[2] -> Buscar PPls por su celda";
        return cadena;
    }
    public static String opcionesAdministracion(){
        String cadena = "Ingrese el numero correspondiente a la opcion deseada\n"
                        + "[1] -> Consultar celdas disponibles\n"
                        + "[2] -> Castigar PPLs\n"
                        + "[3] -> Trasladar PPL";
        return cadena;
    }
    public static String opcionesCastigos(){
        String cadena = "Ingrese el numero correspondiente a la opcion deseada\n"
                        + "[1] -> Disminuir horas de visita\n"
                        + "[2] -> Aumentar pena";
        return cadena;
    }
    
    //Leer archivo principal
    public static void leercsv(String[][] principal, String archivoPrincipal){
        try {
            int i = 0;
            Scanner l = new Scanner(new File(archivoPrincipal));
            while(l.hasNext()){
                String []arreglo = l.nextLine().split(";");
                principal[i][0] = arreglo[0];
                principal[i][1] = arreglo[1];
                principal[i][2] = arreglo[2];
                principal[i][3] = arreglo[3];
                principal[i][4] = arreglo[4];
                principal[i][5] = arreglo[5];
                principal[i][6] = arreglo[6];
                principal[i][7] = arreglo[7];
                principal[i][8] = arreglo[8];
                principal[i][9] = arreglo[9];
                principal[i][10] = arreglo[10];
                principal[i][11] = arreglo[11];
                principal[i][12] = arreglo[12];
                i++;
            }
            l.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    public static void leercsvPabe(String[][] mpabellon, String pabellon){
        try {
            int i = 0;
            Scanner l = new Scanner(new File(pabellon));
            while(l.hasNext()){
                String []arreglo = l.nextLine().split(";");
                mpabellon[i][0] = arreglo[0];
                mpabellon[i][1] = arreglo[1];
                mpabellon[i][2] = arreglo[2];
                mpabellon[i][3] = arreglo[3];
                mpabellon[i][4] = arreglo[4];
                mpabellon[i][5] = arreglo[5];
                mpabellon[i][6] = arreglo[6];
                mpabellon[i][7] = arreglo[7];
                mpabellon[i][8] = arreglo[8];
                mpabellon[i][9] = arreglo[9];
                mpabellon[i][10] = arreglo[10];
                mpabellon[i][11] = arreglo[11];
                mpabellon[i][12] = arreglo[12];
                i++;
            }
            l.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }  
    }
    //Metodos para abrir achivos especificos
    public static void abrirArchivoInfo(String archivoInfo){
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + archivoInfo;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+archivoInfo);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoCompleto(String archivoPrincipal) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + archivoPrincipal;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+archivoPrincipal);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoPabellon1(String pabellon1) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + pabellon1;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+pabellon1);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoPabellon2(String pabellon2) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + pabellon2;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+pabellon2);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoPabellon3(String pabellon3) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + pabellon3;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+pabellon3);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoPabellon4(String pabellon4) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + pabellon4;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+pabellon4);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void abrirArchivoPabellon5(String pabellon5) {
        // Ruta
        String rutaArchivo = System.getProperty("user.dir") + File.separator + pabellon5;
        try {
            abrirArchivos(rutaArchivo);
            System.out.println("Puedes visualizar la informacion en el archivo "+pabellon5);
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo CSV: " + e.getMessage());
        }
    }
    
    public static void buscarPPLcedula(String cedula, String[][] principal){
        int i = 0;
        while(cedula.equals(principal[i][1])){
            if(cedula.equals(principal[i][1])){
                System.out.printf("|#PPL\t|Cedula\t|Nombres\t|Edad(Anios)|\tPena(anios)\t|Fecha(Ingreso)(d/m/a)\t|Fecha(Salida)(d/m/a)\t|Anios Restantes (pena)\t|Pabellon\t|Celda\t|Clasificacion\t|Visitas semanales(Horas)\t|Delito\n");
                System.out.printf("%s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s; %s\n",principal[i][0],principal[i][1],principal[i][2],principal[i][3],principal[i][4],principal[i][5],principal[i][6],principal[i][7],principal[i][8],principal[i][9],principal[i][10],principal[i][11],principal[i][12]);
            }else
                System.out.println("SE FUGO EN EL MOTIN");
            i++;
        }
    }
    //Metodo para abrir cualquier archivo
    private static void abrirArchivos(String rutaArchivo) throws IOException {
        File archivoCSV = new File(rutaArchivo);

        // Verificar si Desktop es compatible
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            // Verificar si el archivo existe antes de intentar abrirlo
            if (archivoCSV.exists()) {
                desktop.open(archivoCSV);//Abrir archivo
            } else {
                System.out.println("El archivo CSV no existe en la ruta especificada.");
            }
        } else {
            System.out.println("La apertura de archivos no es compatible en este sistema.");
        }
    }
    public static String presentarMatriz(String principal[][], int limF, int limC){
    String cadena = "";
    cadena += String.format("|#PPL\t|Cedula\t|Nombres\t|Edad(Anios)|\tPena(anios)\t|Fecha(Ingreso)(d/m/a)\t|Fecha(Salida)(d/m/a)\t|Anios Restantes (pena)\t|Pabellon\t|Celda\t|Clasificacion\t|Visitas semanales(Horas)\t|Delito\n");
    for (int i = 0; i < limF; i++) {
    	for (int j = 0; j < limC; j++){
    		cadena += String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",principal[i][0],principal[i][1],principal[i][2],principal[i][3],principal[i][4],principal[i][5],principal[i][6],principal[i][7],principal[i][8],principal[i][9],principal[i][10],principal[i][11],principal[i][12]);
    	} 
        cadena += "\n";
    }
    return cadena;
}
    public static String estadisticas(){
        
        return null;        
    }
    public static void promedioDePenas(int filas, String principal[][], String archivoPrincipal, int promedioPenas[]){
            try {
            FileReader archivoReader = new FileReader(archivoPrincipal);
            BufferedReader lector = new BufferedReader(archivoReader);
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains("Narcotrafico")) 
                    promedioPenas[0] += 1;
                if (linea.contains("Homicidio")) 
                    promedioPenas[1] += 1;
                if (linea.contains("Robo")) 
                    promedioPenas[2] += 1;
                if (linea.contains("Hurto")) 
                    promedioPenas[3] += 1;
                if (linea.contains("Fraude")) 
                    promedioPenas[4] += 1;
                if (linea.contains("Evasion")) 
                    promedioPenas[5] += 1;
                if (linea.contains("Invasion")) 
                    promedioPenas[6] += 1;
                if (linea.contains("Amenazas")) 
                    promedioPenas[7] += 1;
                if (linea.contains("Estafa")) 
                    promedioPenas[8] += 1;
                if (linea.contains("Otros")) 
                    promedioPenas[9] += 1;
            }
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Cantidad de PPL por Delito");
        System.out.println("(Alto)PPL Narcotrafico: " + promedioPenas[0]);    
        System.out.println("(Alto)PPL Homicidio: " + promedioPenas[1]);    
        System.out.println("(Medio)PPL Robo: " + promedioPenas[2]);    
        System.out.println("(Medio)PPL Hurto: " + promedioPenas[3]);    
        System.out.println("(Medio)PPL Fraude: " + promedioPenas[4]);    
        System.out.println("(Medio)PPL Evasion: " + promedioPenas[5]);    
        System.out.println("(Bajo)PPL Invasion: " + promedioPenas[6]);    
        System.out.println("(Bajo)PPL Amenazas: " + promedioPenas[7]);    
        System.out.println("(Bajo)PPL Estafa: " + promedioPenas[8]);    
        System.out.println("(Bajo)PPL otros: " + promedioPenas[9]);    
        
    }
    public static void delitoMasCometido(int promedioPenas[]){
            //Alto   
            if(promedioPenas[0]>promedioPenas[1]){
                System.out.println("Delito mas cometido(Alto): Narcotrafico");
            }else{
                System.out.println("Delito mas cometido(Alto): Homicidio");
            }
            //Medio
            if ((promedioPenas[2]>promedioPenas[3])&&(promedioPenas[2]>promedioPenas[4])&&(promedioPenas[2]>promedioPenas[5])) 
                System.out.println("Delito mas cometido(Medio): Robo");
            if ((promedioPenas[3]>promedioPenas[2])&&(promedioPenas[3]>promedioPenas[4])&&(promedioPenas[3]>promedioPenas[5])) 
                System.out.println("Delito mas cometido(Medio): Hurto");
            if ((promedioPenas[4]>promedioPenas[3])&&(promedioPenas[4]>promedioPenas[2])&&(promedioPenas[4]>promedioPenas[5])) 
                System.out.println("Delito mas cometido(Medio): Robo");
            if ((promedioPenas[5]>promedioPenas[3])&&(promedioPenas[5]>promedioPenas[4])&&(promedioPenas[5]>promedioPenas[2])) 
                System.out.println("Delito mas cometido(Medio): Robo");
            //Bajo
            if ((promedioPenas[6]>promedioPenas[7])&&(promedioPenas[6]>promedioPenas[8])&&(promedioPenas[6]>promedioPenas[9])) 
                System.out.println("Delito mas cometido(Bajo): Invasion");
            if ((promedioPenas[7]>promedioPenas[6])&&(promedioPenas[7]>promedioPenas[8])&&(promedioPenas[7]>promedioPenas[9])) 
                System.out.println("Delito mas cometido(Bajo): Amenezas");
            if ((promedioPenas[8]>promedioPenas[6])&&(promedioPenas[8]>promedioPenas[7])&&(promedioPenas[8]>promedioPenas[9])) 
                System.out.println("Delito mas cometido(Bajo): Estafa");
            if ((promedioPenas[9]>promedioPenas[6])&&(promedioPenas[9]>promedioPenas[7])&&(promedioPenas[9]>promedioPenas[8])) 
                System.out.println("Delito mas cometido(Bajo): Otros");
    }
    public static void promedioEdadesCompleto(String principal[][]){
        double promedioEdades = 0;
        int cont = 0;
        for (int i = 1; i < principal.length; i++) {
            promedioEdades += Double.valueOf(principal[i][3]); 
            cont++;
        }
        promedioEdades = promedioEdades/cont;
        System.out.printf("%s %.2f %s\n","Promedio (carcel):", promedioEdades, "anios");
    }
    public static void promedioEdadesPabe1(String mpabellon[][], int p){
    double promedioEdades = 0;
    int cont = 0;
    for (int i = 1; i < mpabellon.length; i++) {
        if (mpabellon[i][3] != null) {
                promedioEdades += Double.valueOf(mpabellon[i][3]);
            }
        cont++;
        }
        promedioEdades = promedioEdades / cont;
        System.out.printf("%s %d%s %.2f %s\n", "Promedio de edad del Pabellon", p ,":", promedioEdades, "anios");
    }
    public static void modaCarcelEdadesPrincipal(String matriz[][]){
    int maximoNumRepeticiones = 0;
    double moda = 0;
    for (int i = 1; i < matriz.length; i++) {
        int numRepeticiones = 0;
        for (int j = 1; j < matriz.length; j++) {
            if (Double.valueOf(matriz[i][3]).equals(Double.valueOf(matriz[j][3]))) {
                numRepeticiones++;
            }
        }
        if (numRepeticiones > maximoNumRepeticiones) {
            moda = Double.valueOf(matriz[i][3]);
            maximoNumRepeticiones = numRepeticiones;
        }
    }
     System.out.printf("%s %.0f %s","Edad mas repetida (carcel):", moda, "\n");
}
    public static void modaCarcelEdades(String matriz[][], int p){
    int maximoNumRepeticiones = 0;
    double moda = 0;
    for (int i = 1; i < matriz.length; i++) {
        int numRepeticiones = 0;
        for (int j = 1; j < matriz.length; j++) {
            if (Double.valueOf(matriz[i][3]).equals(Double.valueOf(matriz[j][3]))) {
                numRepeticiones++;
            }
        }
        if (numRepeticiones > maximoNumRepeticiones) {
            moda = Double.valueOf(matriz[i][3]);
            maximoNumRepeticiones = numRepeticiones;
        }
    }
    System.out.printf("%s %d%s %.0f %s","Edad mas repetida Pabellon", p, ":", moda, "\n");
    }
}
    

    