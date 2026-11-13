import java.util.Scanner;

public class GeneradorCURP {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- GENERADOR DE CURP ---");
        System.out.print("Nombre(s): ");
        String nombre = scanner.nextLine();

        System.out.print("Apellido paterno: ");
        String apellidoPaterno = scanner.nextLine();

        System.out.print("Apellido materno: ");
        String apellidoMaterno = scanner.nextLine();

        System.out.print("Fecha de nacimiento (DD/MM/AAAA): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Sexo (H/M): ");
        String sexo = scanner.nextLine();

        System.out.print("Entidad federativa: ");
        String entidadFederativa = scanner.nextLine();

        String curp = generarCURP(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, sexo, entidadFederativa);

        System.out.println("\n-----------------------------------------");
        System.out.println("|  CURP GENERADA: " + curp + "  |");
        System.out.println("-----------------------------------------");

        scanner.close();
    }

    public static String obtenerPrimeraLetra(String palabra) {
        String limpia = limpiarCadena(palabra);
        if (limpia.isEmpty()) return "X";
        return String.valueOf(limpia.charAt(0));
    }

    public static String obtenerPrimeraVocal(String palabra) {
        String limpia = limpiarCadena(palabra);
        for (int i = 1; i < limpia.length(); i++) {
            char c = limpia.charAt(i);
            if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return String.valueOf(c);
            }
        }
        return "X";
    }

    public static String obtenerPrimeraConsonante(String palabra) {
        String limpia = limpiarCadena(palabra);
        for (int i = 1; i < limpia.length(); i++) {
            char c = limpia.charAt(i);
            if (c >= 'A' && c <= 'Z' && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U') {
                return String.valueOf(c);
            }
        }
        return "X";
    }

    public static String obtenerFecha(String fecha) {
        String limpia = fecha.replace("/", "").trim();
        if (limpia.length() >= 8) {
            String dia = limpia.substring(0, 2);
            String mes = limpia.substring(2, 4);
            String anio = limpia.substring(6, 8);
            return anio + mes + dia;
        }
        return "000000";
    }

    public static String obtenerClaveEstado(String estado) {
        String est = limpiarCadena(estado);
        switch (est) {
            case "AGUASCALIENTES": return "AS";
            case "BAJA CALIFORNIA": return "BC";
            case "BAJA CALIFORNIA SUR": return "BS";
            case "CAMPECHE": return "CC";
            case "CHIAPAS": return "CS";
            case "CHIHUAHUA": return "CH";
            case "CIUDAD DE MEXICO": case "DISTRITO FEDERAL": return "DF";
            case "COAHUILA": return "CL";
            case "COLIMA": return "CM";
            case "DURANGO": return "DG";
            case "GUANAJUATO": return "GT";
            case "GUERRERO": return "GR";
            case "HIDALGO": return "HG";
            case "JALISCO": return "JC";
            case "MEXICO": return "MC";
            case "MICHOACAN": return "MN";
            case "MORELOS": return "MS";
            case "NAYARIT": return "NT";
            case "NUEVO LEON": return "NL";
            case "OAXACA": return "OC";
            case "PUEBLA": return "PL";
            case "QUERETARO": return "QT";
            case "QUINTANA ROO": return "QR";
            case "SAN LUIS POTOSI": return "SP";
            case "SINALOA": return "SL";
            case "SONORA": return "SR";
            case "TABASCO": return "TC";
            case "TAMAULIPAS": return "TS";
            case "TLAXCALA": return "TL";
            case "VERACRUZ": return "VZ";
            case "YUCATAN": return "YN";
            case "ZACATECAS": return "ZS";
            default: return "NE";
        }
    }

    public static String generarCURP(String nombre, String apellidoPaterno, String apellidoMaterno,
                                     String fechaNacimiento, String sexo, String entidadFederativa) {

        String apClean = limpiarCadena(apellidoPaterno);
        String amClean = limpiarCadena(apellidoMaterno);
        String nomClean = limpiarCadena(nombre);

        String primerNombre = nomClean.contains(" ") ? nomClean.split(" ")[0] : nomClean;

        String p1 = obtenerPrimeraLetra(apClean);
        String p2 = obtenerPrimeraVocal(apClean);
        String p3 = amClean.isEmpty() ? "X" : obtenerPrimeraLetra(amClean);
        String p4 = obtenerPrimeraLetra(primerNombre);
        String p5_10 = obtenerFecha(fechaNacimiento);
        String p11 = (sexo != null && !sexo.isEmpty()) ? String.valueOf(sexo.toUpperCase().charAt(0)) : "X";
        String p12_13 = obtenerClaveEstado(entidadFederativa);
        String p14 = obtenerPrimeraConsonante(apClean);
        String p15 = amClean.isEmpty() ? "X" : obtenerPrimeraConsonante(amClean);
        String p16 = obtenerPrimeraConsonante(primerNombre);
        String p17_18 = "00";

        return p1 + p2 + p3 + p4 + p5_10 + p11 + p12_13 + p14 + p15 + p16 + p17_18;
    }

    public static String limpiarCadena(String str) {
        if (str == null) return "";
        return str.toUpperCase().trim()
                .replace("Á", "A").replace("É", "E").replace("Í", "I").replace("Ó", "O").replace("Ú", "U")
                .replace("Ä", "A").replace("Ë", "E").replace("Ï", "I").replace("Ö", "O").replace("Ü", "U")
                .replace("Ñ", "X");
    }
}