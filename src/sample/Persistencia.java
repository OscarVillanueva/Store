package sample;

import java.io.*;

public class Persistencia {
    String arch="data.dat";
    public void iniciar(){
        try{
            File file=new File(arch);
            if(!(file.exists())){
                //Estamos creando el archivo en una sola vez
                ObjectOutputStream /*igual que el br*/oos=new ObjectOutputStream(new FileOutputStream(arch));
                oos.close();
            }
        }
        catch(IOException a){

        }
    }
    public void guardarSesion(InfoUser validar){
        try{
            emptyFile(arch);
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(arch,true));
            oos.writeUnshared(validar);
            oos.close();
        }
        catch(Exception a){
            a.printStackTrace();
        }
    }

    public void emptyFile(String filename) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(filename);
        }
        finally {
            if (os != null) {
                os.close();
            }
        }
    }

    public boolean checar() throws FileNotFoundException, IOException, ClassNotFoundException{
        InfoUser validar=null;
        boolean bandera=false;
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(arch));
            validar=(InfoUser) ois.readObject();
            bandera=validar.isLog();
        }
        catch(FileNotFoundException e){
            bandera=false;
        }
        catch(IOException ex){
            bandera=false;
        }
        catch(ClassNotFoundException exx){
            bandera=false;
        }
        return bandera;
    }

    public InfoUser checarUsuario() throws FileNotFoundException, IOException, ClassNotFoundException{
        InfoUser validar=null;
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(arch));
            validar=(InfoUser) ois.readObject();
        }
        catch(FileNotFoundException e){

        }
        catch(IOException ex){

        }
        catch(ClassNotFoundException exx){

        }
        return validar;
    }

}
