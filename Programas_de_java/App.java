import java.util.Scanner;

class Profesor{
    String Cedula = "";
    int equipos = 0;
    int bolivares = 0;

    Profesor(String CI, int x, int y){
        Cedula = CI;
        equipos = x;
        bolivares = y;
    }
}

public class App {





    
    public static void main(String[] args) throws Exception {

        String desc, nf, ci;
        int ct, dd, mm, aaaa;
        float mu;
        System.out.println("Ingrese los siguientes datos en el orden mostrado acontinuacion: ");
        System.out.println("Descripcion del dispositivo: ");
        Scanner sc= new Scanner(System.in);
        desc = sc.nextLine();
        System.out.println("Cantidad de dispositivos: ");
        ct = sc.nextInt();
        System.out.println("Costo unitario del dispositivo: ");
        mu = sc.nextFloat();
        System.out.println("Dia de hoy: ");
        dd = sc.nextInt();
        System.out.println("Mes actual: ");
        mm = sc.nextInt();
        System.out.println("Año actual: ");
        aaaa = sc.nextInt();
        System.out.println("Numero de factura: ");
        nf = sc.nextLine();
        System.out.println("Cedula del profesor: ");
        ci = sc.nextLine();


    }  
}