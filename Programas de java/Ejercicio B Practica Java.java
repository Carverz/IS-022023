import java.util.Scanner;

public class Profesor{
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
    String desc, nf, ci;
    int ct, dd, mm, aaaa;
    float mu;
   public static void read(App app){
        System.out.println("Ingrese los siguientes datos en el orden mostrado acontinuacion: ");
        System.out.println("Descripcion del dispositivo: ");
        Scanner sc= new Scanner(System.in);
        app.desc = sc.nextLine();
        System.out.println("Cantidad de dispositivos: ");
        app.ct = sc.nextInt();
        System.out.println("Costo unitario del dispositivo: ");
        app.mu = sc.nextFloat();
        System.out.println("Dia de hoy: ");
        app.dd = sc.nextInt();
        System.out.println("Mes actual: ");
        app.mm = sc.nextInt();
        System.out.println("Año actual: ");
        app.aaaa = sc.nextInt();
        System.out.println("Numero de factura: ");
        app.nf = sc.nextLine();
        System.out.println("Cedula del profesor: ");
        app.ci = sc.nextLine();
    }

    
    public static void main(String[] args) throws Exception {

        App app;
        read(app);
        System.out.println(app.desc);


    }  
}