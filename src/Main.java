import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Main {

    public static void main(String[] args) throws IOException {
        String[] path = {
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-amazon-devices.csv",    "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-automotive.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-baby.csv",              "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-books.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-digital-text.csv",      "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-dvd.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-electronics.csv",       "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-grocery.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-handmade.csv",          "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-hpc.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-industrial.csv",        "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-kitchen.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-music.csv",             "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-musical-instruments.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-officeproduct.csv",     "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-pet-supplies.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-shoes.csv",             "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-software.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-sports.csv",            "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-tools.csv",
                "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-toys.csv",              "C:\\Users\\river\\Desktop\\mexico\\csv\\mx-videogames.csv"};

        BufferedReader br;
        BufferedWriter bw;

        ArrayList<tripleta> ArrayTripletas = new ArrayList<>();

        try {
            for(String fn : path){
                ArrayList<ArrayList<String>> dataSet = new ArrayList<>();
                br = new BufferedReader(new FileReader(fn));
                String substring = fn.substring(37, fn.length() - 4);
                String outFilename = fn.substring(0, fn.length() - 4) + new String("_out") + fn.substring(fn.length() - 4);
                bw = new BufferedWriter(new FileWriter(outFilename,false));
                String line = br.readLine();
                while(line != null){
                    ArrayList<String> parsing1 = new ArrayList<>();
                    String[] fila1 = line.split("\\|", -1);
                    for(String x : fila1){
                        parsing1.add(x);
                    }
                    dataSet.add(parsing1);
                    bw.write(line + "\n");
                    line = br.readLine();
                }
                bw.close();
                for(int k = 1; k < dataSet.size(); k++){
                    if(dataSet.get(k).get(3).equals(" ")){
                        continue;
                    }
                    String nombreProducto = dataSet.get(k).get(3);
                    boolean encontrado = false;
                    for (tripleta buscar : ArrayTripletas){
                        if(buscar.getNombreProducto().equals(nombreProducto) && buscar.getCategoria().equals(substring)){
                            encontrado = true;
                            buscar.incrementarConteo();
                        }
                    }
                    if(!encontrado){
                        tripleta nuevaTripleta = new tripleta(substring, nombreProducto);
                        ArrayTripletas.add(nuevaTripleta);
                    }
                }
                br.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Collections.sort(ArrayTripletas);
        cola<String> queue = new cola();
        pila<String> stack = new pila();

        for(tripleta tmp : ArrayTripletas){
            queue.enqueue("Categoria: " + tmp.getCategoria() + " - " + tmp.getNombreProducto() + ", comprado " + tmp.getConteo() + " veces" + "\n");
            stack.push("Categoria: " + tmp.getCategoria() + " - " + tmp.getNombreProducto() + ", comprado " + tmp.getConteo() + " veces" + "\n");
        }

        bw = new BufferedWriter(new FileWriter( "Cola.txt",false));

        for(String tmp : queue){
            bw.write(tmp);
            System.out.println(tmp);
        }

        bw.close();
        bw = new BufferedWriter(new FileWriter("Pila.txt", false));

        for(String tmp : stack){
            bw.write(tmp);
            System.out.println(tmp);
        }
        bw.close();
    }
}

