package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Partida {

    static ArrayList<String> palabras;

    private int vidas;
    private int intentos;
    private int aciertos;
    private int fallos;
    
    private String palabraOculta;
    private char[] palabraCandidato;

    static final char NO_LETRA = '_';
    static final int TOTAL_VIDAS = 6;
    static final int GANADA =0;
    static final int PERDIDA =1;
    static final int NO_TERMINADA = 2;



    public Partida(){

        vidas = TOTAL_VIDAS;
        intentos = 0;
        aciertos = 0;
        fallos = 0;
        
        if (palabras == null) {
        	System.out.println("Cargando palabras...");
        	palabras = new ArrayList<>();
        	cargaPalabras();
        }

        int pos = (int) (Math.random()*palabras.size());

        palabraOculta = palabras.get(pos);

        palabraCandidato = new char[palabraOculta.length()];

        Arrays.fill(palabraCandidato,NO_LETRA);

    }

    private void cargaPalabras() {
		Scanner sc = new Scanner(getClass().getResourceAsStream("/palabras.txt"));
		while(sc.hasNextLine())
			palabras.add(sc.nextLine());
		
		sc.close();
				
		
	}

	/**
     *
     * @return String con la palabra en contrucción. Los caracteres están separados por un
     *          espacio.
     *          Ejemplo: Si palabraCandidato ={'_','O','_','E','A'}, entonces
     *             devuelve "_ O _ E A"
     */
    public String getPalabraCandidataEspaciada(){

        int n = palabraOculta.length();
        StringBuilder sb = new StringBuilder((n*2) -1);
        int i;
        for( i=0; i< n-1;i++){
            sb.append(palabraCandidato[i]).append(' ');
        }
        sb.append(palabraCandidato[i]);
        return sb.toString();
    }


    /**
     * Comprueba si la letra suministrada está en la palabra a descubir, en cuyo caso
     * la añade a la palabra candidata, en caso contrario decrementa el númroe de vidas
     * de la partida.
     *
     * @param letra letra suministrada para hacer la jugada.
     * @return
     *      Si letra está en la palabra a descubrir
     *          - Palabra candidata dejando un espacio entre sus caracteres.
     *      en caso contrario
     *          - null
     */
    public String hazJugada(char letra) {
        intentos++;
        boolean contieneLetra = false;
        for(int i = 0; i< palabraOculta.length(); i++){
            if(palabraOculta.charAt(i) == letra){
                palabraCandidato[i] = letra;
                contieneLetra = true;
            }
        }

        if(contieneLetra){
        	aciertos++;
            return getPalabraCandidataEspaciada();
            
        } else {
        	fallos++;
            vidas--;
            return null;
        }
    }

    /**
     * Informa sobre si la partida a terminado o no, y si ha terminado con victoria o derrota.
     * @return
     *      Partida.GANADA, si la partida a terminado en victoria.
     *      Partida.PERDIDA, si la partida a terminado en derrota.
     *      Partida.NO_TERMINADA, si la partida aun no ha terminado.
     */
    public int terminada() {
        if( palabraOculta.equals(String.valueOf(palabraCandidato)) )
            return GANADA;
        else if (vidas == 0){
            return PERDIDA;
        } else return NO_TERMINADA;
    }

    public int getVidas() {
        return vidas;
    }
    public int getFallos() {
    	return fallos;
    }

    public int getAciertos() {
    	return aciertos;
    }

    public int getIntentos() {
        return intentos;
    }

    public String getPalabraOculta(){
        return palabraOculta;
    }

    public String getPalabraCandidata(){
        return String.valueOf(palabraCandidato);
    }

}
